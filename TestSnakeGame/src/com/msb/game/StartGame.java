package com.msb.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    //main方法，主程序入口
    public static void main(String[] args) {
        //创建一个窗体
        JFrame jf = new JFrame();

        //给窗体设置标题
        jf.setTitle("是兄弟就来吃我 by Ricardo");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        //设置弹出对应坐标，窗体的宽和高
        jf.setBounds((width - 800) / 2,(height - 800) / 2,800,800);

        //设置窗体大小不可调节
        jf.setResizable(false);

        //关闭窗口同时，程序要随之关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //创建面板
        GamePanel gp = new GamePanel();

        //将面板放入窗体
        jf.add(gp);

        //默认情况下窗体是隐藏的,注意这句话最好放在最后，最后再显现
        jf.setVisible(true);
    }
}
