package com.dongge416.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class LogUtil {

	/**
	 * @将错误信息输入到txt中
	 * @param path
	 * @throws IOException
	 */
	public static void writeLog(String content) {
		FileWriter fw = null;
		try {
			File F = new File(Constant.LOG_PATH);
			// 如果文件不存在,就动态创建文件
			if (!F.exists()) {
				F.createNewFile();
			}
			
			String writeDate = "时间:" + get_nowDate() + "--->" + "" + content;

			// 设置为:True,表示写入的时候追加数据
			fw = new FileWriter(F, true);
			// 回车并换行
			fw.write(writeDate + "\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			if (fw != null) {
				try {
					fw.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}

	}

	/**
	 * @获取系统当前时间
	 * @return
	 */
	public static String get_nowDate() {

		Calendar D = Calendar.getInstance();
		int year = 0;
		int moth = 0;
		int day = 0;

		year = D.get(Calendar.YEAR);
		moth = D.get(Calendar.MONTH) + 1;
		day = D.get(Calendar.DAY_OF_MONTH);
		int hour = D.get(Calendar.HOUR);
		 int minute = D.get(Calendar.MINUTE);
		 int second = D.get(Calendar.SECOND);
//		String now_date = String.valueOf(year) + "-" + String.valueOf(moth) + "-" + String.valueOf(day);
		 String now_date = year + "-" + moth + "-" + day + " " + hour + ":" +minute+":"+second;
		return now_date;
	}

}
