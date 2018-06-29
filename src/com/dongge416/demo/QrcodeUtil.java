package com.dongge416.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrcodeUtil {

	public static BufferedImage creatQrBufferImage(String _content, String _format, int _size) {
		BufferedImage bufImage = null;
		
		// 定义二维码的样式
		int width = _size;
		int height = _size;
		String format = _format;
		String contents = _content;// 扫描二维码时产生的连接

		// 定义二维码的参数
		HashMap hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);// 设置二维码的容错等级
		hints.put(EncodeHintType.MARGIN, 0);// 边距

		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
//			Path file = new File(_filePath).toPath();// 保存的路径
			MatrixToImageConfig config = new MatrixToImageConfig();
			
			bufImage = MatrixToImageWriter.toBufferedImage(bitMatrix,config);
//			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
//			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImage;
	}

}
