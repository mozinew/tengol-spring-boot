package com.tengol.boot.hello.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * ImageUtil
 *
 * @author dongrui
 * @date 2020/11/6 10:32
 */
public class ImageUtil {

    public static void main(String[] args) throws IOException {

        Watermark watermark = new Watermark(Positions.BOTTOM_LEFT, getBi("时间：2020-11-6 11:03:07 \n" +
                "\n" +
                " 北京市海淀区丰豪东路"), 1.0f);

        Thumbnails.of("D:\\img_test_1.jpg")
                .scale(1.0f)
                .watermark(watermark)
                .toFile("D:\\abc_111.jpg");

//        createImage("时间：2020-11-6 11:03:07 \n\r 北京市海淀区丰豪东路");
    }

    private static BufferedImage getBi(String content){
        //设置字体样式
        Font font = new Font("宋体", Font.PLAIN, 25);

        int width = content.length();
        BufferedImage waterImage = new BufferedImage(500, 500 ,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = waterImage.createGraphics();
//        graphics.setColor(Color.RED);
        graphics.setFont(font);
//        char[] data = content.toCharArray();
//        graphics.drawChars(data, 0, data.length, 5, 15);
        graphics.drawString(content, 5, 15);
//        waterImage = graphics.getDeviceConfiguration().createCompatibleImage(100,200,Transparency.TRANSLUCENT);

        return waterImage;
    }


    /**
     * 创建图片
     */
    public static void createImage(String content){
        //设置字体样式
        Font font = new Font("宋体", Font.PLAIN, 25);
        //图片大小
        int width = 100;
        int height = 100;

        //创建一个图片缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //获取图片处理对象
        Graphics graphics = image.getGraphics();
        //填充背景色
        graphics.setColor(getRandomColor());
        graphics.fillRect(1, 1, width - 1, height - 1);
        //设定边框颜色
        graphics.setColor(getRandomColor());
        graphics.drawRect(0, 0, width - 1, height - 1);
        //设置干扰线
        graphics.setColor(getRandomColor());
        Random random = new Random();
        for(int i=0;i<20;i++){
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(width - 1);
            int y1 = random.nextInt(height - 1);
            graphics.drawLine(x, y, x1, y1);
        }
        //写入文字
        graphics.setColor(getRandomColor());
        graphics.setFont(font);
        graphics.drawString(content, 100, 100);

        //输出文件
        File file = new File("D:\\123.gif");
        try {
            ImageIO.write(image, "GIF", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //释放资源
        graphics.dispose();
    }

    /**
     * 获取随机颜色
     * @return
     */
    public static Color getRandomColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }
}
