package com.dongge416.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.alibaba.fastjson.JSONObject;

public class ConvertLinkApp {

	public static String haodankuAppKey = "tshtts";
	public static String itemid = "";
	
	//zhaoliuping1967 tshtts,mm_97861461_26020803_101016325
	//天又蓝 tianyoulan,mm_40361636_44852733_507520929
	public static String pid = "mm_97861461_26020803_101016325";
	
	public static List<GoodModel> list;

	public static void main(String[] args) {
		list = new ArrayList<>();
		// 1. 创建一个顶层容器（窗口）
		JFrame jf = new JFrame("转链工具-By dongge416"); // 创建窗口
		jf.setSize(600, 600); // 设置窗口大小
		jf.setLayout(null);
		jf.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
		JTextArea convertLinkTxt = new JTextArea();
		convertLinkTxt.setBounds(5, 5, 590, 200);

		JButton btnConvertLink = new JButton("转换链接");
		btnConvertLink.setBounds(120, 210, 150, 30);
		JButton btnCopyPic = new JButton("清空数据");
		btnCopyPic.setBounds(280, 210, 150, 30);
		JButton btnList = new JButton("生成列表");
		btnList.setBounds(440, 210, 150, 30);

		JTextArea txtLog = new JTextArea();
		txtLog.setBounds(5, 250, 590, 250);

		jf.add(convertLinkTxt);
		jf.add(btnConvertLink);
		jf.add(btnCopyPic);
		jf.add(btnList);
		jf.add(txtLog);
		jf.setVisible(true);
		btnCopyPic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				convertLinkTxt.setText("");
				txtLog.setText("");
			}
		});
		btnList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String str = "";
				for (int i = 0; i < list.size(); i++) {
					str = str + list.get(i).getD_title()+"【"+list.get(i).getPrice()+"】\n";
				}
				txtLog.setText(str);
			}
		});
		btnConvertLink.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String txtContent = convertLinkTxt.getText().trim();

						GoodModel goodModel = covertGood(txtContent);
						System.out.println(goodModel.toString());
						txtLog.append("解析解析，生成商品信息\n"+goodModel.toString()+"\n");
						txtLog.append("向好单库请求\n");
						itemid = goodModel.getGoodsID();
						String result = null;
						String urlString = "http://v2.api.haodanku.com/ratesurl";
						InputStream is = null;
						String param = null;
						StringBuilder sbParams = new StringBuilder();
						sbParams.append("apikey");
						sbParams.append("=");
						sbParams.append(haodankuAppKey);
						sbParams.append("&");
						sbParams.append("itemid");
						sbParams.append("=");
						sbParams.append(itemid);
						sbParams.append("&");
						sbParams.append("pid");
						sbParams.append("=");
						sbParams.append(pid);
						sbParams.append("&");
						try {
							URL url = new URL(urlString);
							HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
							// post请求需要设置DoOutput为true
							urlConnection.setDoOutput(true);
							urlConnection.setRequestMethod("POST");
							// 设置参数
							param = sbParams.toString();
							urlConnection.getOutputStream().write(param.getBytes());
							urlConnection.getOutputStream().flush();
							urlConnection.setConnectTimeout(5 * 1000);
							urlConnection.setReadTimeout(5 * 1000);
							// 连接服务器
							urlConnection.connect();
							StringBuilder stringBuilder = new StringBuilder();
							if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
								is = urlConnection.getInputStream();
								int len = 0;
								byte[] buffer = new byte[1024];
								while ((len = is.read(buffer)) != -1) {
									stringBuilder.append(new String(buffer, 0, len));
								}
								result = stringBuilder.toString();
								txtLog.append("请求结果:"+result+"\n");
								System.out.println(result);
								JSONObject jsonObject = JSONObject.parseObject(result);
								String code = jsonObject.getString("code");
								String msg = jsonObject.getString("msg");
								String resultData = jsonObject.getString("data");
								JSONObject jsonObjectArray = JSONObject.parseObject(resultData);
								String coupon_click_url = jsonObjectArray.getString("coupon_click_url");
								txtLog.append("向淘宝转换淘口令请求:\n");
								String tklStr = TklUtil.getTkl(coupon_click_url);
								txtLog.append("淘口令:"+tklStr+"\n");
								goodModel.setTaoWord(tklStr);
								boolean posterFlag = PosterUtil.creatPoster(goodModel);
								txtLog.append("生成结果:"+posterFlag+"\n");
								list.add(goodModel);
							}
						} catch (MalformedURLException e) {
							e.printStackTrace();
							LogUtil.writeLog(e.getMessage());
						} catch (IOException e) {
							e.printStackTrace();
							LogUtil.writeLog(e.getMessage());
						} finally {
							if (is != null) {
								try {
									is.close();
								} catch (IOException e) {
									e.printStackTrace();
									LogUtil.writeLog(e.getMessage());
								}
							}
						}
						if (result == null) {
							return;
						}
					}
				}).start();

			}

		});
		txtLog.setText("初始化成功\n");
		boolean creatFileFlag = FileUtil.creatDir(Constant.FILE_PATH);
		boolean creatPicFileFlag = FileUtil.creatDir(Constant.PIC_PATH);
		txtLog.append("创建文件夹\n");
	}

	public static GoodModel covertGood(String content) {
		GoodModel goodModel = null;
		String[] strs = content.trim().split("\n");
		if (strs.length < 3) {
			return null;
		}
		goodModel = new GoodModel();
		for (int i = 0; i < strs.length; i++) {

			if (strs[i].contains("下单：")) {
				int idIndex = strs[i].indexOf("id=");
				String id = strs[i].substring(idIndex, strs[i].length()).replace("id=", "").trim();
				goodModel.setGoodsID(id);
				String itemUrl = "http://v2.api.haodanku.com/item_detail/apikey/" + haodankuAppKey + "/itemid/" + id;
				String result = OKHttpUtil.httpGet(itemUrl);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String code = jsonObject.getString("code");
				String msg = jsonObject.getString("msg");
				String resultData = jsonObject.getString("data");
				JSONObject jsonObjectArray = JSONObject.parseObject(resultData);
				String itemprice = jsonObjectArray.getString("itemprice");
				String itemendprice = jsonObjectArray.getString("itemendprice");
				String itemdesc = jsonObjectArray.getString("itemdesc");
				String couponmoney = jsonObjectArray.getString("couponmoney");
				String itempic = jsonObjectArray.getString("itempic");
				String itemshorttitle = jsonObjectArray.getString("itemshorttitle");

				goodModel.setOrg_Price(itemprice);
				goodModel.setPrice(itemendprice);
				goodModel.setIntroduce(itemdesc);
				goodModel.setQuan_price(couponmoney);
				goodModel.setPic(itempic);
				goodModel.setD_title(itemshorttitle);
				// String tklStr = TklUtil.getTkl(coupon_click_url);
				System.out.println(result);
			}

		}

		return goodModel;
	}

}
