package com.manger.base;

import android.graphics.drawable.Drawable;

public class Speed {
	private String packagename;

	private Drawable icon;
	private String phonename;
	private String mearry;
	private boolean check;

	/**
	 * @param packagename
	 */

	public boolean isCheck() {
		return check;
	}

	public void setSCheck(boolean check) {
		this.check = check;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public void setphonename(String phone) {
		this.phonename = phone;
	}

	public String getphonename() {
		return phonename;
	}

	public String getmearry() {
		return mearry;
	}

	public void setmearry(String version) {
		this.mearry = version;
	}

	/**
 * 
 */
	public Speed() {
		super();
	}

	/**
	 * @param packagename
	 * @param icon
	 * @param phonename
	 * @param check
	 */
	public Speed(String packagename, Drawable icon, String phonename,
			boolean check) {
		super();
		this.packagename = packagename;
		this.icon = icon;
		this.phonename = phonename;
		this.check = check;
	}

	/**
	 * @param check
	 * @param icon
	 * @param phonename
	 * @param mearry
	 * @param packagename
	 */

}
