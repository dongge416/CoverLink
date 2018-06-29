package com.dongge416.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class PosterUtil {

	public static boolean creatPoster(GoodModel goodModel) {
		boolean result = false;
		String rawPath = ConvertLinkApp.class.getResource("/").getPath();
		rawPath = rawPath.replace("bin/", "");
		InputStream imageinTarget = null;
		InputStream imageinBg = null;
		OutputStream outImage = null;
		//sdfda
		Image imageTaget = null;
//		InputStream imageInQrcode = null;
//		String targetPicUrl = goodModel.getPic()+"_500x500.jpg";
		String targetPicUrl = goodModel.getPic();
		String taoWrod = goodModel.getTaoWord().replace("￥", "");
		String taoWordUrl = "http://tm.tshtts.cn/custom/getletters.html?letters="+taoWrod+"&address=&image=";
		try {
			 imageinTarget = OKHttpUtil.httpGetPicInputStream(targetPicUrl); 
	         imageinBg = new FileInputStream(Constant.FILE_PATH+"/bg.jpg");
//	         imageInQrcode = new FileInputStream(rawPath+"/Raw/qrcode.jpg");
	         
	         
	         
	         BufferedImage imageTargetPic = ImageIO.read(imageinTarget);  
	         imageTaget = imageTargetPic.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
	         BufferedImage imageBg = ImageIO.read(imageinBg);  
	         BufferedImage imageQrcode = QrcodeUtil.creatQrBufferImage(taoWordUrl, "jpeg", 150);
	         Graphics2D g = (Graphics2D)imageBg.getGraphics();  
	         //合并主图
	         g.drawImage(imageTaget, 0, 0,  
	        		 imageTaget.getWidth(null) , imageTaget.getHeight(null) , null);  
	         //合并二维码
	         g.drawImage(imageQrcode, imageBg.getWidth()-175, imageTaget.getHeight(null)+20, 150, 150, null); 
	         //字体平滑
	         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
	         //设置标题
	         Font fontTitle = new Font("黑体",Font.PLAIN,20);
	         g.setFont(fontTitle); 
	         g.setColor(Color.BLACK);
	         String dTitle = goodModel.getD_title();
	         if(dTitle.length() > 15) {
	        	 dTitle = dTitle.substring(0, 14);
	         }
	         g.drawString(dTitle, 10, imageTaget.getHeight(null)+40);
	         
	         //设置原价
	         Font fontOrgPrice = new Font("黑体",Font.PLAIN,16);
	         g.setFont(fontOrgPrice);
	         g.setColor(Color.GRAY);
	         g.drawString("原价", 10, imageTaget.getHeight(null)+70);
	         g.drawString(goodModel.getOrg_Price(), 55, imageTaget.getHeight(null)+70);
	         g.drawLine(50, imageTaget.getHeight(null)+65, 100, imageTaget.getHeight(null)+65);
	         
	         //设置券后价
	         g.setFont(new Font("黑体",Font.PLAIN,16));
	         g.drawString("券后", 10, imageTaget.getHeight(null)+95);
	         g.setFont(new Font("黑体",Font.PLAIN,20));
	         g.setColor(Color.RED);
	         g.drawString(goodModel.getPrice(), 55, imageTaget.getHeight(null)+95);
	         Color tagColor = new Color(254, 102, 0);
	         g.setColor(tagColor);
	         RoundRectangle2D rect=new RoundRectangle2D.Double(130,imageTaget.getHeight(null)+72,80,25,0,0);//创建矩//形对象
	         //我的
	         g.draw(rect);
	         g.fillRoundRect((int)rect.getX(), (int)rect.getY(), 80, 25,0,0);
	         g.setColor(Color.WHITE);
	         String quan = goodModel.getQuan_price().replace(".00", "");
	         g.drawString("券 "+quan+"元", 135, imageTaget.getHeight(null)+92);
	         
	         g.setColor(Color.RED);
	         g.setFont(new Font("黑体", Font.PLAIN, 16));
	         String introduceTxt = goodModel.getIntroduce();
//	         introduceTxt.ss
	         List<String> listIntroduce = StringUtil.getStrList(introduceTxt, 17);
	         int height = 130;
	         for (int i = 0; i < listIntroduce.size(); i++) {
	        	 	if(i>=3) {
	        	 		break;
	        	 	}
	         		g.drawString(listIntroduce.get(i), 10, imageTaget.getHeight(null)+height);
	         		height = height+20;
	 		}
	         g.setColor(Color.BLACK);
	         g.setFont(new Font("黑体", Font.PLAIN, 16));
	         g.drawString("长按识别二维码", imageBg.getWidth()-160, imageBg.getHeight()-20);
	         
	         
	         g.drawRect(0, 0, imageBg.getWidth()-1, imageBg.getHeight()-1);
	         
	         g.dispose();
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	         String datetime = sdf.format(new Date());
	         String fileName = datetime+"_"+goodModel.getGoodsID()+".jpg";
//	         String sortPath = FileUtil.sortPath(Integer.valueOf(goodModel.getCid()))+"//";
	          outImage = new FileOutputStream(Constant.PIC_PATH+fileName);  
	         ImageIO.write(imageBg, "jpeg", outImage);
	         setClipboardImage(imageBg);
	         result = true;
		}catch(Exception e) {
			e.printStackTrace();
			LogUtil.writeLog(e.getMessage());
		}finally {
			if(imageinTarget!=null) {
				try {
					imageinTarget.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.writeLog(e.getMessage());
				}
			}
			if(imageinBg!=null) {
				try {
					imageinBg.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.writeLog(e.getMessage());
				}
			}
			if(outImage!=null) {
				try {
					outImage.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.writeLog(e.getMessage());
				}
			}
//			if(imageTaget!=null) {
//				try {
//					imageTaget=null;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					LogUtil.writeLog(e.getMessage());
//				}
//			}
		}
		
		return result;
		
	}
	
	 public static void setClipboardImage( Image image)
	    {
	        Transferable trans = new Transferable(){
	            @Override
	            public Object getTransferData(DataFlavor flavor)
	                    throws UnsupportedFlavorException, IOException {
	                // TODO Auto-generated method stub
	                if (isDataFlavorSupported(flavor))
	                {
	                    return image;
	                }                      
	                throw new UnsupportedFlavorException(flavor);
	            }
	 
	            @Override
	            public DataFlavor[] getTransferDataFlavors() {
	                // TODO Auto-generated method stub
	                return new DataFlavor[] { DataFlavor.imageFlavor };
	            }
	 
	            @Override
	            public boolean isDataFlavorSupported(DataFlavor flavor) {
	                // TODO Auto-generated method stub
	                return DataFlavor.imageFlavor.equals(flavor);
	            }             
	        };
	         
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	    }
	
}
