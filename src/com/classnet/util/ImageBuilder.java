package com.classnet.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageBuilder implements Image {

	private String input;
	private int WIDTH;
	private int HEIGHT;
	private BufferedImage image;
	private Graphics g;
	
	public ImageBuilder(String input) {
		WIDTH = 60;  //250
		HEIGHT = 20;  //60
		this.input = input;
		init();
		draw();
	}
	
	void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		g = image.getGraphics();
	}
	Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
	void draw() {
	    // 设定背景色
	    g.setColor(getRandColor(200,250));
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    //设定字体
	    g.setFont(new Font("Times New Roman",Font.PLAIN,18));
	    //画边框
	    //g.setColor(new Color());
	    //g.drawRect(0,0,width-1,height-1);
	    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	    g.setColor(getRandColor(160,200));
	    //生成随机类
	    Random random = new Random();
	    for (int i=0;i<155;i++){
		     int x = random.nextInt(WIDTH);
		     int y = random.nextInt(HEIGHT);
		     int xl = random.nextInt(12);
		     int yl = random.nextInt(12);
		     g.drawLine(x,y,x+xl,y+yl);
	    }
	    char array[] = input.toCharArray();
		for (int i = 0; i < array.length; i++){
			 String rand=String.valueOf(array[i]);
		     // 将认证码显示到图象中
		     g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		     g.drawString(rand,13*i+6,16);
		}
	}

	public static void main(String args[]) throws Exception {
		ImageBuilder builder = new ImageBuilder("12AB");
		builder.init();
		builder.draw();
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream("d:/a.jpg"));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder
				.getDefaultJPEGEncodeParam(builder.image);
		param.setQuality(15F, true);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(builder.image);
		out.flush();
		out.close();
		System.out.println("OK");
	}

	public BufferedImage getImage() {
		return image;
	}

	public void out(OutputStream out) throws Exception {
		if (image == null) {
			throw new IOException("\u56FE\u7247\u751F\u6210\u5931\u8D25");
		} else {
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
			param.setQuality(15F, true);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(image);
			out.flush();
			return;
		}
	}
}