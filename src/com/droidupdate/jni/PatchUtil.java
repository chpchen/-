package com.droidupdate.jni;

import java.io.IOException;

import android.content.Context;

public class PatchUtil {

	static {
		System.loadLibrary("DroidUpdate");
	};

	private static native int applyPatchToOldApk(String oldapk_filepath,
			String newapk_savepath, String patchpath);

	/**
	 * @param oldApkPath 旧版apk文件的路径
	 * @param newApkPath 新版apk文件的路径
	 * @param patchPath 增量包的路径
	 * @throws IOException
	 */
	public static int applyPatch(String oldApkPath, String newApkPath,
			String patchPath) throws IOException {
		return applyPatchToOldApk(oldApkPath, newApkPath, patchPath);
	}
	
	public static int applyPatchToOwn(Context context, String newApkPath,
			String patchPath) throws IOException {
		String old = context.getApplicationInfo().sourceDir;
		return applyPatchToOldApk(old, newApkPath, patchPath);
	}

}
