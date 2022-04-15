/**
 * @Title: LogUtil.java
 * @Package com.cpsdna.libs.util
 * @Description: TODO
 */
package com.jerry.voiceassistant.utils;

import android.util.Log;

/**
 * @Description: 控制调测日志是否打印
 * @author wangwenbin
 * @date 2013-3-8 上午11:35:30
 */
public class Logs {

	/**
	 * 是否打印日志
	 */

	public static boolean mLogout = false;
	//超过4000 logcat 要换行打印
	private static final int MAX_LOG_LENGTH = 4000;
	public static void setDebug(boolean isLog) {
		Logs.mLogout = isLog;
	}

	public static void v(String tag, String msg) {
		if (mLogout) {
			log(Log.VERBOSE,tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (mLogout) {
			log(Log.DEBUG,tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (mLogout) {
			log(Log.INFO,tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (mLogout) {
			log(Log.WARN,tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (mLogout) {
			log(Log.ERROR,tag, msg);
		}
	}

	public static void log(int logLevel,String tag,String message)
	{
		for (int i = 0, length = message.length(); i < length; i++) {
			int newline = message.indexOf('\n', i);
			newline = newline != -1 ? newline : length;
			do {
				int end = Math.min(newline, i + MAX_LOG_LENGTH);
				Log.println(logLevel, tag, message.substring(i, end));
				i = end;
			} while (i < newline);
		}
	}
}
