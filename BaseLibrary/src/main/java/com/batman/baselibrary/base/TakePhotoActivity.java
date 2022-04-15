//package com.batman.baselibrary.base;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.net.Uri;
//
//import com.batman.baselibrary.Constant;
//import com.batman.baselibrary.R;
//import com.batman.baselibrary.utils.FileUtils;
//import com.batman.utils.ToastUtils;
//import com.media.image.compress.ImageCompress;
//import com.media.image.compress.OnCompressListener;
//import com.network.utils.LogUtils;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.yalantis.ucrop.UCrop;
//import com.zhihu.matisse.Matisse;
//import com.zhihu.matisse.MimeType;
//import com.zhihu.matisse.internal.entity.CaptureStrategy;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import androidx.annotation.Nullable;
//import io.reactivex.functions.Consumer;
//
///**
// * 拍照压缩
// */
//public abstract class TakePhotoActivity extends BaseActivity {
//
//    protected static final int REQUEST_CODE_TAKE_PHOTO = 101;
//    protected static final int REQUEST_CODE_TAKE_PHOTO_AND_CLIP = 102;
//    List<Uri> mSelectedUri;
//    List<String> mSelectedPath;
//
//
//    /**
//     * 拍照
//     */
//    protected void takePhoto() {
//
//        takePhoto(true);
//    }
//
//    protected void takePhoto(boolean isClip) {
//
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            //当所有权限都允许，才会到这里
//                            takePicture(isClip);
//                        }
//                    }
//                });
//    }
//
//    private void takePicture(boolean isClip) {
//        Set<MimeType> mimeTypes = new HashSet<>();
//        mimeTypes.add(MimeType.JPEG);
//        mimeTypes.add(MimeType.PNG);
//        if (isClip) {
//            doMatisse(mimeTypes, R.dimen.ui_offset_120, REQUEST_CODE_TAKE_PHOTO_AND_CLIP);
//        } else {
//            doMatisse(mimeTypes, R.dimen.ui_offset_120, REQUEST_CODE_TAKE_PHOTO);
//        }
//    }
//
//    private void doMatisse(Set<MimeType> mimeTypes, int p, int requestCodeTakePhotoAndClip) {
//        Matisse.from(this)
//                .choose(mimeTypes)
//                .countable(true)
//                .capture(true)
//                .captureStrategy(
//                        new CaptureStrategy(true, Constant.FILE_PATH_PROVIDER))
//                .maxSelectable(1)
////                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(
//                        getResources().getDimensionPixelSize(p))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//                .thumbnailScale(0.85f)
////                .imageEngine(new MyGlideEngine())
//                .forResult(requestCodeTakePhotoAndClip);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_TAKE_PHOTO_AND_CLIP && resultCode == RESULT_OK) {
//            /**
//             * 裁剪
//             */
//            mSelectedUri = Matisse.obtainResult(data);
//            LogUtils.d("Matisse", "mSelected: " + mSelectedUri);
//            UCrop.Options options = new UCrop.Options();
//            options.setHideBottomControls(true);
//            options.setToolbarColor(getResources().getColor(R.color.ui_config_color_main));
//            options.setStatusBarColor(getResources().getColor(R.color.ui_config_color_main));
//            UCrop.of(mSelectedUri.get(0), Uri.fromFile(FileUtils.getDiskCachePicture(mContext)))
//                    .withAspectRatio(1, 1)
//                    .withMaxResultSize(1080, 1920)
//                    .withOptions(options)
//                    .start(this);
//        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
//            /**
//             * 不裁剪 压缩
//             */
//            mSelectedPath = Matisse.obtainPathResult(data);
//
//            compress(mSelectedPath.get(0));
//
//        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
//
//            /**
//             * 裁剪后 压缩
//             */
//            final Uri resultUri = UCrop.getOutput(data);
//            String path = resultUri.getPath();
//            compress(path);
//
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            final Throwable cropError = UCrop.getError(data);
//            ToastUtils.showLong(cropError.getMessage());
//        }
//
//    }
//
//    /**
//     * 压缩图片
//     *
//     * @param path
//     */
//    private void compress(String path) {
//        File file = new File(path);
//        ImageCompress.with(this)
//                .load(file)                                   // 传人要压缩的图片列表
////                    .setTargetDir(getPath())                        // 设置压缩后文件存储位置
//                .setCompressListener(new OnCompressListener() { //设置回调
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onSuccess(File file) {
//
//                        compressSuccess(file);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//
//                    }
//                }).launch();
//    }
//
//    protected void compressSuccess(File file) {
//
//    }
//}
