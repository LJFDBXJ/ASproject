package com.manger.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class FielCopy {
	/**
	 * 这个类主要设置了一个复制的方法，来读取数据库中的内容。
	 *
	 * @param context
	 * @param path
	 * @param topath
	 */
	public static void copypath(Context context, String path, String topath) {

		InputStream is = null;
		BufferedInputStream bis = null;

		OutputStream os = null;
		BufferedOutputStream bos = null;

		try {

			is = context.getResources().getAssets().open(path);// 读取本地资源下放在assets文件下的数据库。
			bis = new BufferedInputStream(is);

			os = new FileOutputStream(topath);
			bos = new BufferedOutputStream(os);

			byte[] data = new byte[1024];
			int len = 0;

			while ((len = bis.read(data, 0, data.length)) != -1) {
				bos.write(data, 0, data.length);
				bos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();// 读取写入流需要关闭
				bis.close();
				os.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
