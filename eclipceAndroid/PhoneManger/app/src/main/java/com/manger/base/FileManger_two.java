package com.manger.base;

import java.io.File;

public class FileManger_two {
	private boolean Filecheck;

	private String icunname;// 图片名字
	private long FileType;// 文件类型
	private String OptenType;// 最后修改时间
	private File targetFile;// 文件

	public boolean isFilecheck() {
		return Filecheck;
	}

	public void setFilecheck(boolean filecheck) {
		Filecheck = filecheck;
	}

	public String getIcunname() {
		return icunname;
	}

	public void setIcunname(String icunname) {
		this.icunname = icunname;
	}

	public long getFileType() {
		return FileType;
	}

	public void setFileType(long fileType) {
		FileType = fileType;
	}

	public String getOptenType() {
		return OptenType;
	}

	public void setOptenType(String optenType) {
		this.OptenType = optenType;
	}

	public File getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	/**
	 * @param filecheck
	 * @param icunname
	 * @param fileType
	 * @param lastTime
	 * @param filesize
	 * @param targetFile
	 */

	public FileManger_two(File targetFile, String icunname, String optenType,long fileType) {
		super();
		this.targetFile = targetFile;
		this.icunname = icunname;
		OptenType = optenType;
		FileType = fileType;

	}

	/**
	 *
	 */
	public FileManger_two() {
		super();
	}

}