package com.dongge416.demo;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;

public class TklUtil {
	/**
	 * 淘宝serverUrl
	 */
	public static final String URL_serverUrl = "http://gw.api.taobao.com/router/rest";
	
	/**
	 * 淘宝app key
	 */
	public static final String KEY_TAOBAO_APP_KEY = "24333767";
	
	/**
	 * 淘宝appSecret
	 */
	public static final String SECRET_TAOBAO = "8b5750f690730e4a2938b409329b0a3e";

	/**
	 * 获取淘口令
	 * @param good
	 * @return
	 */
	public static String getTkl(String  url) {
		String result = "";
		try {
			
		
		TaobaoClient client = new DefaultTaobaoClient(URL_serverUrl, KEY_TAOBAO_APP_KEY, SECRET_TAOBAO);
		TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
		req.setUserId("123");
		req.setText("省省吃货君");
		req.setUrl(url);
		req.setLogo("http://tshtts.cn/pic/share_02.jpg");
		req.setExt("{}");
		TbkTpwdCreateResponse rsp = client.execute(req);

		String taoWord = rsp.getData().getModel();
		result = taoWord;

		}catch(Exception e){
			e.printStackTrace();
			result = "";
		}
		return result ;
	}
	
}
