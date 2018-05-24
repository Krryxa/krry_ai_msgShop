package com.krry.action;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.krry.util.Base64Util;
import com.krry.util.FileUtil;
import com.krry.util.HttpUtil;

/**
 * 文字识别工具类
 * @author krry
 * @version v1.0 
 * 
 */
public class ShopQuery {
	
	//识别出来的文字集合
	private static List<ShopMsg> shopmsg = new ArrayList<>();
	// 文字识别url
	private static final String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/receipt";		
	/**
     * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
     */
	private static String accessToken = AccessToken.getAuth();
	
	/**
	 * 循环文件夹里面的文件，并识别文字信息，写入到Excel中
	 * @param filePath 路径名
	 * @return
	 */
	public static String getIDInfo(String filePath,HttpServletRequest request){
    	
		String result = null;
		
		//扫描文件夹中的文件
		File dirFile = new File(filePath);

		if(!dirFile.exists()){
			System.out.println("文件夹不存在");
		}else{
			File[] files = dirFile.listFiles();

			for(File f:files){
				//转换图片  上线的时候打开
				changeJpg(f.getName(),filePath);
				//解析图片文字
				getWordMsg(filePath+"//"+f.getName());
			}
			
			//写入Excel文件
			ChangeExcel.writeExcel(shopmsg,request);
		}
	

        return result;
	
	}
	
	/**
	 * 识别图片文字信息，写入到Excel中
	 * @param filePath
	 */
	public static void getWordMsg(String filePath){
		
		String result = null;
		
		try {
        	
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            
            String imgStr = Base64Util.encode(imgData);
            
            //检查图片方向true
            String params = "detect_direction=true&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            
            result = HttpUtil.post(idcardIdentificate, accessToken, params);

            int count = 0;
            
            ShopMsg msg = new ShopMsg();
            //转化成json对象
            JSONObject json = JSONObject.fromObject(result);
            //从json对象得到words_result 变成json数组
            JSONArray arr = (JSONArray) json.get("words_result");
            int len = arr.size();
            for(int i=0;i<len;i++){
            	//每个数组元素得到words文字信息
            	JSONObject words = JSONObject.fromObject(arr.get(i));
            	String msgWord = (String) words.get("words");
//            	System.out.println(msgWord);
            	if(msgWord.contains("注册号")){
            		//文字长度
            		int lenmo = msgWord.length();
            		//多种格式图片的问题解决
            		int index = msgWord.indexOf(":");
            		if(index == -1) index = msgWord.indexOf("：");
            		if(index == -1) index = msgWord.indexOf("号");
            		if(msgWord.indexOf("用") != -1) lenmo--;
            		if(msgWord.indexOf("日") != -1) lenmo--;
            		if(msgWord.indexOf(")") != -1) lenmo--;
            		
            		msgWord = msgWord.substring(index+1, lenmo);
//            		System.out.println(msgWord);
            		msg.setNumber(msgWord);
            		count++;
            	}
            	if(msgWord.equals("企业名称") || msgWord.equals("名称")) continue;
            	if(msgWord.contains("名称") || msgWord.contains("公")){
            		//文字长度
            		int lengmo = msgWord.length();
            		//多种格式图片的问题解决
            		int index = msgWord.indexOf(":");
            		if(index == -1) index = msgWord.indexOf("：");
            		if(index == -1) index = msgWord.indexOf("∶");
            		if(index == -1) index = msgWord.indexOf("称");
            		int companyIndex = msgWord.indexOf("公司");
            		
            		if(companyIndex<index && companyIndex != -1){
            			index = msgWord.indexOf("称");
            			companyIndex = msgWord.lastIndexOf("司");
            			lengmo = companyIndex+1;
            		}
            		
            		msgWord = msgWord.substring(index+1, lengmo);
            		if(msgWord.charAt(msgWord.length()-1) == '公'){
            			msgWord += "司";
            		}
//            		System.out.println(msgWord);
            		msg.setUsername(msgWord);
            		count++;
            		
            	}
            	if(count >= 2) break;
            }
            //添加到list集合
            shopmsg.add(msg);
            
       
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	/**
	 * 将背景透明背景的png图片改成白色背景的png图片
	 * @param pathname 文件名
	 * @param basePath 路径
	 */
	public static void changeJpg(String pathname,String basePath){
		
		int laseindex = pathname.lastIndexOf(".");
		//获取文件后缀名
		String exe = pathname.substring(laseindex, pathname.length());
		//获取文件名，无后缀
		String filename = pathname.substring(0, laseindex);
		
		BufferedImage bufferedImage;
	    try {
	      // read image file
	      bufferedImage = ImageIO.read(new File(basePath+"/"+pathname));
	      // create a blank, RGB, same width and height, and a white
	      // background
	      BufferedImage newBufferedImage = new BufferedImage(
	          bufferedImage.getWidth(), bufferedImage.getHeight(),
	          BufferedImage.TYPE_INT_RGB);
	      // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
	      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
	          Color.WHITE, null);
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, "png", new File(basePath+"/"+filename+exe));
	      
	      System.out.println("Done");
	    
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}
	
	
	public static void main(String[] args) {
//		getIDInfo("C://Users//asusaad//Workspaces//MyEclipse Professional 2014//krry_ai_msgShop//WebRoot//天猫工商信息执照");
		
//		getIDInfo("C://Users//asusaad//Desktop//新建文件夹");
		
	}
	    
	
}
