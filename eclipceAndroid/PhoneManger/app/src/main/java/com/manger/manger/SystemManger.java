package com.manger.manger;

import java.io.File;
import java.util.Map;

import android.os.Environment;
import android.os.StatFs;

public class SystemManger {
	public static long getSize() {
		/**
		 * 设备储存自身的大小
		 */
		File path = Environment.getDataDirectory();

		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSizeLong();
		long blockCount = stat.getBlockCountLong();
		long dataFileSize = blockCount * blockSize;

		path = Environment.getDownloadCacheDirectory();
		stat = new StatFs(path.getPath());
		blockSize = stat.getBlockSizeLong();
		blockCount = stat.getBlockCountLong();
		long cacheFileSize = blockCount * blockSize;

		path = Environment.getRootDirectory();
		stat = new StatFs(path.getPath());
		blockSize = stat.getBlockSizeLong();
		blockCount = stat.getBlockCountLong();
		long rootFileSize = blockCount * blockSize;

		return dataFileSize + cacheFileSize + rootFileSize;

	}

	/**
	 * 设备自身有效储存空间大小 单位B
	 */
	public static long getSelfAvailableSize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSizeLong();
		long blockCount = stat.getAvailableBlocksLong();
		long dataFileSize = blockCount + blockSize;

		path = Environment.getDownloadCacheDirectory();
		stat = new StatFs(path.getPath());
		blockSize = stat.getBlockSizeLong();
		blockCount = stat.getBlockCountLong();
		long cacheFileSise = blockCount * blockSize;

		path = Environment.getRootDirectory();
		stat = new StatFs(path.getPath());
		blockSize = stat.getBlockSizeLong();
		blockCount = stat.getBlockCountLong();
		long rooFileSize = blockCount + blockSize;

		return dataFileSize + cacheFileSise + rooFileSize;

	}

	/**
	 * 获取手机外在Sdcard路径，为null表示无外置TF卡。
	 */
	public static String getPhoneOutTFPath() {
		Map<String, String> map = System.getenv();
		// LogUtil.i("map"+map)
		if (map.containsKey("EXTERNAL_STORAGE")) {
			String paths = map.get("EXTERNAL_STORAGE");
			String path[] = paths.split(":");
			if (path == null || path.length <= 0) {
				return null;

			}
			return path[0];

		}
		return null;
	}

	/**
	 * 获取手机内置sdcard路径，为null表示无内置TF卡
	 */
	public static String getPhoneinTFPath() {
		String sdcardState = Environment.getExternalStorageState();
		if (!sdcardState.equals(Environment.MEDIA_MOUNTED)) {
			return null;

		}
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * 获取手机储存卡大小（有外置获取的是外置TF卡大小，无外置获取的是内置的大小）
	 *
	 * @return
	 */

	public static long getSDCardSize() {

		String tfPath = "";
		// 获取外置TF卡路径-返回外置TF卡大小
		tfPath = getPhoneOutTFPath();
		if (tfPath != null) {
			File path = new File(tfPath);
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSizeLong();
			long blockCount = stat.getBlockCountLong();
			return blockCount * blockSize;

		}
		// 没有获取到了外置TF卡路径-返回内置TF卡大小
		tfPath = getPhoneinTFPath();
		if (tfPath != null) {
			File path = new File(tfPath);
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSizeLong();
			long blockCount = stat.getBlockCountLong();
			return blockCount * blockCount;

		}
		return 0;
	}

	public static long getSDCardAcailableSize() {
		String tfPath = "";
		// 获取外置TF卡路径-返回外置TF卡大小
		tfPath = getPhoneOutTFPath();
		if (tfPath == null) {
			File path = new File(tfPath);
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSizeLong();
			long blockCount = stat.getBlockCountLong();
			return blockCount * blockSize;

		}
		return 0;

	}
}
