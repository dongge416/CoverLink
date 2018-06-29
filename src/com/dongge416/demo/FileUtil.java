package com.dongge416.demo;

import java.io.File;

public class FileUtil {

	public static boolean creatDir(String path) {
		boolean result = false;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
			result = true;
		}
		return result;
	}

	public static String sortPath(int i) {
		String sortPath = "";
		switch (i) {
		case 1:
			sortPath = "女装\\";
			break;
		case 2:
			sortPath = "母婴\\";
			break;
		case 3:
			sortPath = "美妆\\";
			break;
		case 4:
			sortPath = "居家日用\\";
			break;
		case 5:
			sortPath = "鞋品\\";
			break;
		case 6:
			sortPath = "美食\\";
			break;
		case 7:
			sortPath = "文娱车品\\";
			break;
		case 8:
			sortPath = "数码家电\\";
			break;
		case 9:
			sortPath = "男装\\";
			break;
		case 10:
			sortPath = "内衣\\";
			break;
		case 11:
			sortPath = "箱包\\";
			break;
		case 12:
			sortPath = "配饰\\";
			break;
		case 13:
			sortPath = "户外运动\\";
			break;
		case 14:
			sortPath = "家装家纺\\";
			break;

		default:
			sortPath = "女装\\";
			break;
		}
		return sortPath;
	}
	
	
	public static boolean deleteAllPic() {
		boolean result = false;

		
		for (int i = 1; i < 15; i++) {
			String dirName = sortPath(i);
			String path = Constant.PIC_PATH+dirName;
			File dir = new File(path);
			File[] files = dir.listFiles();
			for (int j = 0; j < files.length; j++) {
				System.out.println("删除文件夹:"+dirName+",文件:"+files[j].getName());
				files[j].delete();
			}
		}
		
		
		return result;
	}

}
