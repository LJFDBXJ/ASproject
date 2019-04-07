package com.manger.tools;

import java.text.DecimalFormat;

public class CommonUtil {

	private static DecimalFormat df = new DecimalFormat("#.00");

	public static String getFileSize(long fileSize) {
		StringBuffer sb = new StringBuffer();
		if (fileSize < 1024) {
			sb.append(fileSize);
			sb.append("B");
		} else if (fileSize < 1048576) {
			sb.append(df.format((double) fileSize / 1024));
			sb.append("k");
		} else if (fileSize < 1073741824) {
			sb.append(df.format((double) fileSize / 1048576));
			sb.append("M");
		} else {
			sb.append(df.format((double) fileSize / 1073741824));
			sb.append("G");
		}
		return sb.toString();
	}

}
