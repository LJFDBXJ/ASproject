package com.manger.base;

public class FileManger_one {
	private String Filesize;
	private String FileType;

	public String getFilesize() {
		return Filesize;
	}

	public void setFilesize(String filesize) {
		Filesize = filesize;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String fileType) {
		FileType = fileType;
	}

	/**
	 * @param filesize
	 * @param fileType
	 */
	public FileManger_one(String fileType, String filesize) {
		super();
		Filesize = filesize;
		FileType = fileType;
	};
}
