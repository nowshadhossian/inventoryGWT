package com.anontech.utils;

public class Utils {

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		
		if (str.length() == 0)
			return true;
		
		return false;
	}

	public static String getMimeType(String realFileName) {
		String mimeType = "";
		String fileExtension = realFileName.substring( realFileName.lastIndexOf('.') + 1 , realFileName.length() );

		if( fileExtension.trim().toLowerCase().equals("txt") )
		mimeType = "text/plain";
		else if( fileExtension.trim().toLowerCase().equals("htm") 
				|| fileExtension.trim().toLowerCase().equals("html") )
		mimeType = "text/html";
		else if(fileExtension.trim().toLowerCase().equals("doc") )
		mimeType = "application/msword";
		else if(fileExtension.trim().toLowerCase().equals("xls") )
		mimeType = "application/vnd.ms-excel";
		else if(fileExtension.trim().toLowerCase().equals("ppt") )
		mimeType = "application/vnd.ms-powerpoint";
		else if(fileExtension.trim().toLowerCase().equals("tif") )
		mimeType = "image/tiff";
		else if(fileExtension.trim().toLowerCase().equals("pdf") )
		mimeType = "application/x-pdf";
		else if(fileExtension.trim().toLowerCase().equals("efx") )
		mimeType = "image/efax";
		else {
			mimeType = "application/octet-stream";
		}
		return mimeType;
	}

	public static String getStrValue(String argValue) {
		if(argValue == null) return "";
		else return argValue;
	}
	
}