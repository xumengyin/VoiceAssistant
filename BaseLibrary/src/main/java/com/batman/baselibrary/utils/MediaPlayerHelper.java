package com.batman.baselibrary.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class MediaPlayerHelper {

    private static MediaPlayer mMediaPlayer;
    private static boolean isPause = false;

    private static boolean mIsCanPlay = true;

    public static void setPlay(boolean isCanPlay) {
        mIsCanPlay = isCanPlay;
    }

    public static void playSound(Context context, int resourceId) {
        playSound(context, resourceId, null);
    }

    public static void playSound(Context context, int resourceId, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (!mIsCanPlay) {
            return;
        }
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(context, resourceId);
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
            mMediaPlayer = MediaPlayer.create(context, resourceId);
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            if (onCompletionListener != null) {
                mMediaPlayer.setOnCompletionListener(onCompletionListener);
            }
            //用prepare方法，会报错误java.lang.IllegalStateException
//            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void playSound(String filePath) {
        playSound(filePath, null);
    }

    public static void playSound(String filePath, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (!mIsCanPlay) {
            return;
        }
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            if (onCompletionListener != null) {
                mMediaPlayer.setOnCompletionListener(onCompletionListener);
            }

            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 暂停播放
     */
    public static void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }


    /**
     * 继续播放
     */
    public static void resume() {
        if (mMediaPlayer != null && isPause) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 释放资源
     */
    public static void realese() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            isPause = true;
        }
    }
}
