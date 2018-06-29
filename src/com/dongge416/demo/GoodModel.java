package com.dongge416.demo;

public class GoodModel {
	
	/**
	 * 商品ID
	 */
	private String goodsID ;
	
	/**
	 * 商品标题
	 */
	private String title ;
	/**
	 * 短标题
	 */
	private String d_title ;
	
	/**
	 * 图片地址
	 */
	private String pic ;
	
	/**
	 * 分类ID
	 * 1:女装
	 * 2:母婴
	 * 3:美妆
	 * 4:家居
	 * 5:鞋品
	 * 6:美食
	 * 7:文娱车品
	 * 8:家用电器
	 * 9:男装
	 * 10:内衣
	 * 11:箱包
	 * 12:配饰
	 * 13:户外运动
	 * 14:家装家纺
	 */
	private String cid ;
	
	
	/**
	 * 原价
	 */
	private String org_Price ;
	
	/**
	 * 现价
	 */
	private String price ;
	
	/**
	 * 是否天猫
	 */
	private String isTmall ;
	
	/**
	 * 销量
	 */
	private String sales_num ;
	
	/**
	 * 动态
	 */
	private String dsr ;
	
	/**
	 * 卖家ID
	 */
	private String sellerID ;
	
	/**
	 * 计划(通用)佣金比例
	 */
	private String commission_jihua ;
	/**
	 * 高佣鹊桥佣金比例
	 */
	private String commission_queqiao ;
	/**
	 * 计划链接
	 */
	private String jihua_link ;
	
	/**
	 * 商品文案
	 */
	private String introduce ;
	
	/**
	 * 优惠券ID
	 */
	private String quan_id ;
	/**
	 * 优惠券金额
	 */
	private String quan_price ;
	/**
	 * 优惠券结束时间
	 */
	private String quan_time ;
	/**
	 * 优惠券剩余数量
	 */
	private String quan_surplus ;
	/**
	 * 已领券数量
	 */
	private String quan_receive ;
	/**
	 * 优惠券使用条件
	 */
	private String quan_condition ;
	/**
	 * 手机券链接
	 */
	private String quan_link ;
	/**
	 * 手机券短链
	 */
	private String quan_m_link ;
	
	/**
	 * 佣金类型
	 */
	private String yongjin_type ;
	
	/**
	 * 淘宝客链接（需用大淘客助手转链）
	 */
	private String ali_click ;
	
	/**
	 * 淘口令
	 */
	private String taoWord;

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getD_title() {
		return d_title;
	}

	public void setD_title(String d_title) {
		this.d_title = d_title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getOrg_Price() {
		return org_Price;
	}

	public void setOrg_Price(String org_Price) {
		this.org_Price = org_Price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsTmall() {
		return isTmall;
	}

	public void setIsTmall(String isTmall) {
		this.isTmall = isTmall;
	}

	public String getSales_num() {
		return sales_num;
	}

	public void setSales_num(String sales_num) {
		this.sales_num = sales_num;
	}

	public String getDsr() {
		return dsr;
	}

	public void setDsr(String dsr) {
		this.dsr = dsr;
	}

	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}

	public String getCommission_jihua() {
		return commission_jihua;
	}

	public void setCommission_jihua(String commission_jihua) {
		this.commission_jihua = commission_jihua;
	}

	public String getCommission_queqiao() {
		return commission_queqiao;
	}

	public void setCommission_queqiao(String commission_queqiao) {
		this.commission_queqiao = commission_queqiao;
	}

	public String getJihua_link() {
		return jihua_link;
	}

	public void setJihua_link(String jihua_link) {
		this.jihua_link = jihua_link;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getQuan_id() {
		return quan_id;
	}

	public void setQuan_id(String quan_id) {
		this.quan_id = quan_id;
	}

	public String getQuan_price() {
		return quan_price;
	}

	public void setQuan_price(String quan_price) {
		this.quan_price = quan_price;
	}

	public String getQuan_time() {
		return quan_time;
	}

	public void setQuan_time(String quan_time) {
		this.quan_time = quan_time;
	}

	public String getQuan_surplus() {
		return quan_surplus;
	}

	public void setQuan_surplus(String quan_surplus) {
		this.quan_surplus = quan_surplus;
	}

	public String getQuan_receive() {
		return quan_receive;
	}

	public void setQuan_receive(String quan_receive) {
		this.quan_receive = quan_receive;
	}

	public String getQuan_condition() {
		return quan_condition;
	}

	public void setQuan_condition(String quan_condition) {
		this.quan_condition = quan_condition;
	}

	public String getQuan_link() {
		return quan_link;
	}

	public void setQuan_link(String quan_link) {
		this.quan_link = quan_link;
	}

	public String getQuan_m_link() {
		return quan_m_link;
	}

	public void setQuan_m_link(String quan_m_link) {
		this.quan_m_link = quan_m_link;
	}

	public String getYongjin_type() {
		return yongjin_type;
	}

	public void setYongjin_type(String yongjin_type) {
		this.yongjin_type = yongjin_type;
	}

	public String getAli_click() {
		return ali_click;
	}

	public void setAli_click(String ali_click) {
		this.ali_click = ali_click;
	}
	
	

	public String getTaoWord() {
		return taoWord;
	}

	public void setTaoWord(String taoWord) {
		this.taoWord = taoWord;
	}

	@Override
	public String toString() {
		return "GoodModel [goodsID=" + goodsID + ", title=" + title + ", d_title=" + d_title + ", pic=" + pic + ", cid="
				+ cid + ", org_Price=" + org_Price + ", price=" + price + ", isTmall=" + isTmall + ", sales_num="
				+ sales_num + ", dsr=" + dsr + ", sellerID=" + sellerID + ", commission_jihua=" + commission_jihua
				+ ", commission_queqiao=" + commission_queqiao + ", jihua_link=" + jihua_link + ", introduce="
				+ introduce + ", quan_id=" + quan_id + ", quan_price=" + quan_price + ", quan_time=" + quan_time
				+ ", quan_surplus=" + quan_surplus + ", quan_receive=" + quan_receive + ", quan_condition="
				+ quan_condition + ", quan_link=" + quan_link + ", quan_m_link=" + quan_m_link + ", yongjin_type="
				+ yongjin_type + ", ali_click=" + ali_click + ", taoWord=" + taoWord + "]";
	}
	
	

}
