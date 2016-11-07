package com.droidupdate;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。
		String pName = intent.getDataString();
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			System.out.println("安装程序:" + pName);
			if (pName.equals(context.getPackageName())) {
				File newFile = new File(
						Environment.getExternalStorageDirectory(), "apkTmp.apk");
				if (newFile.exists())
					newFile.delete();
				File file = new File(Environment.getExternalStorageDirectory(),
						"apkTmp.patch");
				if (file.exists())
					file.delete();
				System.out.println("安装完成删除文件");
			}
		} else if (intent.getAction().equals(
				"android.intent.action.PACKAGE_REMOVED")) {
			System.out.println("卸载程序:" + pName);
		}
	}
}