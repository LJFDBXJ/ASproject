package com.manger.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class ShoftMa {
	private String AppName;
	private String PackageName;
	private String VersionName;
	private Drawable AppIcon;
	private boolean check;
	private Intent intent;

	public Intent getIntent() {
		return intent;
	}

	public String getAppName() {
		return AppName;
	}

	public String getPackageName() {
		return PackageName;
	}

	public String getVersionName() {
		return VersionName;
	}

	public Drawable getAppIcon() {
		return AppIcon;
	}

	public boolean isCheck() {
		return check;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public void setVersionName(String versionName) {
		VersionName = versionName;
	}

	public void setAppIcon(Drawable drawable) {
		AppIcon = drawable;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;

	}

	/**
	 * 
	 */
	public ShoftMa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param appName
	 * @param packageName
	 * @param versionName
	 * @param appIcon
	 * @param check
	 */
	// public ShoftMa(String appName, String packageName, String
	// versionName,Drawable appIcon, boolean check) {
	// super();
	// AppName = appName;
	// PackageName = packageName;
	// VersionName = versionName;
	// AppIcon = appIcon;
	// this.check = check;
	// }

}
