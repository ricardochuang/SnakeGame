package com.msb.game;

import javax.swing.*;
import java.net.URL;

/**
 * Class "images" is set to get images for the game.
 */
public class Images {
    /*
    面向对象的思维要将图片封装
     */

    //将图片路径封装为一个对象
    public static URL bodyUrl = Images.class.getResource("/images/body.png");
    public static URL downUrl = Images.class.getResource("/images/down.png");
    public static URL foodUrl = Images.class.getResource("/images/food.png");
//    public static URL headerUrl = Images.class.getResource("/images/header.png");
    public static URL leftUrl = Images.class.getResource("/images/left.png");
    public static URL rightUrl = Images.class.getResource("/images/right.png");
    public static URL upUrl = Images.class.getResource("/images/up.png");
    //将图片封装为程序中一个对象
    public static ImageIcon bodyIma = new ImageIcon(bodyUrl);
    public static ImageIcon downIma = new ImageIcon(downUrl);
    public static ImageIcon foodIma = new ImageIcon(foodUrl);
//    public static ImageIcon headerIma = new ImageIcon(headerUrl);
    public static ImageIcon leftIma = new ImageIcon(leftUrl);
    public static ImageIcon rightIma = new ImageIcon(rightUrl);
    public static ImageIcon upIma = new ImageIcon(upUrl);
}
