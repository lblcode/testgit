package com.ischoolbar.programmer.util;
 
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.util.StringUtils;
 
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
 
/**
 * 关于验证码的工具类
 */
public  class CaptchaUtil {
 
    private CaptchaUtil() {
    }
 
    /*
     * 随机字符字典
     */
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
 
 
    //字符串转数组
//    private static final char[] ch = "ABCDEFGHJKLMNPQRSTUVWXY23456798".toCharArray();
 
 
    /*
     * 随机数
     */
    private static Random random = new Random();
 
 
 
    /**
     * 获取长度为codeLength的随机字符
     * @param codeLength
     * @return
     */
    private static String getRandomString(int codeLength) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < codeLength; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)] + " ");
        }
        return buffer.toString();
    }
 
 
 
    /**
     * 获取随机数颜色
     * @return
     */
    private static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255),
                random.nextInt(255));
    }
 
 
 
    /**
     * 返回某颜色的反色
     * @param c
     * @return
     */
    private static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }
 
 
 
 
    /**
     * 输出验证码图片
     * @param length   验证码的长度(默认 4)
     * @param picWidth   图片宽(默认 100)
     * @param picHeigth   图片高(默认 40)
     * @param fontSize    字号(默认 20)
     * @param X_str     字符的起始X坐标(默认 15)
     * @param Y_str     字符的起始Y坐标(默认 25)
     * @param imgFormat   图片格式(默认 JPG)
     * @param request
     * @param response
     * @throws Exception
     */
    public static void outputCaptcha(String length,String picWidth ,String picHeigth ,String fontSize, String X_str ,String Y_str , String imgFormat ,  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
        //设置验证码字符串的默认长度
        int codeLength = 4;
 
        if (!StringUtils.isEmpty(length)){
            codeLength = Integer.parseInt(length);
        }
 
 
 
        //获取随机字符
        String randomString = getRandomString(codeLength);
 
 
//        str.replace(" ",""); //去除所有空格，包括首尾、中间
        request.getSession(true).setAttribute("loginCaptcha", randomString.replace(" ",""));
        //System.out.println( randomString.replace(" ",""));
 
        //设置图片宽高默认值
        int width = 100;
        int height = 40;
 
        if (!StringUtils.isEmpty(picWidth)){
            width = Integer.parseInt(picWidth);
        }
 
        if (!StringUtils.isEmpty(picHeigth)){
            height = Integer.parseInt(picHeigth);
        }
 
        //设置字号 默认值
        int fSize = 20;
        if (!StringUtils.isEmpty(fontSize)){
            fSize = Integer.parseInt(fontSize);
        }
 
        //设置 验证码字符串绘制的 起始坐标
        int strX = 15;
        int strY = 25;
 
        if (!StringUtils.isEmpty(X_str)){
            strX = Integer.parseInt(X_str);
        }
        if (!StringUtils.isEmpty(Y_str)){
            strY = Integer.parseInt(Y_str);
        }
 
 
        //设置默认图片编码格式
        String imageEncode = "JPG";
        if (!StringUtils.isEmpty(imgFormat)){
            imageEncode = imgFormat;
        }
 
 
 
 
 
        //获取背景色 和 字体色  对象
        Color color = getRandomColor();
        Color reverse = getReverseColor(color);
 
        // 创建一个宽width,高height,且不带透明色的image对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
        //获取 2D 画布
        Graphics2D g = bi.createGraphics();
 
        //设置画笔（字体 ， 字重 ，字号）
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fSize));
 
        //设置背景色
        g.setColor(color);
 
        //绘制背景图片(起始横坐标，起始纵坐标，图片宽度，图片高度)
        g.fillRect(0, 0, width, height);
 
        //设置字体色（背景色的反色）
        g.setColor(reverse);
 
//        // 取出四个数字
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < codeLength; i++) {
//
//            int index = random.nextInt(CHARS.length);
//
//            g.drawString(CHARS[index] + "", (i * 18) + 10, 30);
//
//            sb.append(CHARS[index]);
//        }
 
        //设置绘制字符串（字符串，横坐标，纵坐标）
        g.drawString(randomString, strX, strY);
 
 
 
        //绘制干扰像素
        for (int i = 0, n = random.nextInt(100); i < n; i++) {
            //drawRect(横坐标，纵坐标，图片宽度，图片高度)
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
 
        //设置HTTP Content-Type
 
        if(imageEncode.equals("JPG")){
            response.setContentType("image/jpeg");
        }else if (imageEncode.equals("PNG")){
            response.setContentType("image/png");
        }else if (imageEncode.equals("GIF")){
            response.setContentType("image/gif");
        }else {
            imageEncode = "JPG";
            response.setContentType("image/jpeg");
        }
 
 
 
 
        //获取servlet输出流
        ServletOutputStream out = response.getOutputStream();
 
 
 
//        /**
//         * 注意 JPEGImageEncoder和JPEGCodec 位于rt.jar中,只有jdk1.7以上的版本才能使用
//         */
//
//        //设置图片编码格式JPG
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(bi);  //encode加编码 ，decode解码
 
 
        //以JPG格式输出
        ImageIO.write(bi,imageEncode ,out);
 
        //刷新输出流
        out.flush();
    }
 
 
}