package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {

    //继承了Jpanel以后，才具备面板的功能，才成为一个面板
    //定义蛇的长度
    int length;
    // 一个数组，专门蛇的x轴坐标
    int[] snakeX = new int[200];
    // 一个数组，专门蛇的y轴坐标
    int[] snakeY = new int[200];
    //定义蛇的行走方向
    String direction;
    //定义食物的坐标
    int foodX;
    int foodY;
    //定义积分
    int score;
    //游戏只有两个状态：开始，暂停
    boolean isStart = false; //默认暂停
    //加入定时器
    Timer tm;
    //死亡判定
    boolean isDie = false;
    //完成判定
    boolean finish = false;


    public void init(){
        //初始化蛇的长度
        length = (new Random().nextInt(25)) + 1;
        //初始化蛇的方向
        direction = "R"; // U D R L
        //初始化蛇头坐标：
        snakeX[0] = 600;
        snakeY[0] = 600;
//        //初始化第一节身子坐标：
//        snakeX[1] = 150;
//        snakeY[1] = 275;
//
//        //初始化第二节身子坐标：
//        snakeX[2] = 125;
//        snakeY[2] = 275;
        for(int i = 1; i < length; ++i){
            snakeX[i] = 600 - 25 * i;
            snakeY[i] = 600;
        }
        foodX = 300;
        foodY = 200;
        score = 0;
    }

    public GamePanel(){
        init();

        //将焦点定位在当前操作的面板上
        this.setFocusable(true);

        //加入监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //监听键盘的按下操作
                super.keyPressed(e);
                int KetCode = e.getKeyCode();
                if(KetCode == KeyEvent.VK_SPACE){ //监听空格
                    if(isDie || finish){

                        init();
                        isDie = false;
                        finish = false;
                    }
                    else {
                        isStart = !isStart;
                        repaint(); //重绘
                    }
                }
                if(KetCode == KeyEvent.VK_UP){ //监听上箭头
                    direction = "U";
                }
                if(KetCode == KeyEvent.VK_DOWN){ //监听下箭头
                    direction = "D";
                }
                if(KetCode == KeyEvent.VK_LEFT){ //监听左箭头
                    direction = "L";
                }
                if(KetCode == KeyEvent.VK_RIGHT){ //监听右箭头
                    direction = "R";

                }
            }
        });

        //对定时器进行初始化
        tm = new Timer(70, new ActionListener() {
            //事件监听，相当于每100ms监听一下是否有动作，具体动作放入actionPerformed
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart && !isDie && !finish){ //游戏开始，蛇才动
                    //后一节身子走到前一节身子的位置上
                    for(int i = length - 1; i > 0; --i){
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    if("R".equals(direction)){
                        snakeX[0] += 25;
                    }
                    if("L".equals(direction)){
                        snakeX[0] -= 25;
                    }
                    if("U".equals(direction)){
                        snakeY[0] -= 25;
                    }
                    if("D".equals(direction)){
                        snakeY[0] += 25;
                    }
                    //防止越界
                    if(snakeX[0] > 750){
//                        snakeX[0] = 25;
                        isDie = true;
                    }
                    if(snakeX[0] < 25){
//                        snakeX[0] = 750;
                        isDie = true;
                    }
                    if(snakeY[0] > 725){
//                        snakeY[0] = 100;
                        isDie = true;
                    }
                    if(snakeY[0] < 100){
//                        snakeY[0] = 725;
                        isDie = true;
                    }

                    //检测碰撞
                    //食物左边和蛇头左边重合
                    if(snakeX[0] == foodX && snakeY[0] == foodY){
                        //长度变化
                        if(length < 15 ){
                            length++;
                        }
                        if(length > 15){
                            length--;
                        }
                        if(length == 15){
                            length = (new Random().nextInt(60)) + 1;
                        }

                        //积分增加
                        score += 10;
                        if(score > 300){
                            finish = true;
                        }
                        //食物坐标变化，坐标随机生成，且为25的倍数
                        foodX = ((int)(Math.random() * 30 + 1)) * 25; //[25,750]
                        foodY = (new Random().nextInt(26) + 4) * 25; //[100, 725]

                    }

                    for(int i = 1; i < length ; ++i){
                        if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            isDie = true;
                        }
                    }
                    repaint(); //重绘

                }
            }
        });

        //定时器start
        tm.start();
    }
    /*
    这个方法属于图形版的main方法，自动调用
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //填充背景颜色
        this.setBackground(new Color(110, 99, 234, 54));
        //画头部图片，四个参数 this：当前面板 g:使用画笔 xy： 坐标
//        Images.headerIma.paintIcon(this,g,10,10);
        //调整画笔颜色
        g.setColor(new Color(123, 123, 227));
        //画一个矩形
        g.fillRect(10, 70, 770, 685);
        //画小蛇
        //画蛇头：
        if("R".equals(direction)){
            Images.rightIma.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("L".equals(direction)){
            Images.leftIma.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("U".equals(direction)){
            Images.upIma.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("D".equals(direction)){
            Images.downIma.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

//        //画第一节身子
//        Images.bodyIma.paintIcon(this, g, snakeX[1], snakeY[1]);
//
//        //画第二节身子
//        Images.bodyIma.paintIcon(this, g, snakeX[2], snakeY[2]);

        //优化为循环画身子
        for(int i = 1; i < length; i ++){
            Images.bodyIma.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //如果游戏是暂停的，界面中间有一句话
        if(!isStart){
            g.setColor(new Color(1,1,1));
            //三个参数：字体，加粗，字号
            g.setFont(new Font("宋体", Font.BOLD, 40));
            //画文字
            g.drawString("点击空格开始游戏", 250, 330);
        }

        //画食物
        Images.foodIma.paintIcon(this, g, foodX, foodY);
        //画积分
        g.setColor(new Color(248, 247, 247));
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("积分：" + score, 640, 40);
        //画长度
        g.setColor(new Color(248, 247, 247));
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("长度：" + length, 480, 40);
        //嘲讽
        if(isDie){
            g.setColor(new Color(11, 11, 11));
            g.setFont(new Font("宋体", Font.BOLD, 80));
            g.drawString("菜鸡，就这？", 200, 410);

            g.setColor(new Color(11, 11, 11));
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString("按空格重新投胎", 310, 460);
        }
        //嘲讽2.0
        if(finish){
            g.setColor(new Color(11, 11, 11));
            g.setFont(new Font("宋体", Font.BOLD, 60));
            g.drawString("这破游戏你也能玩这么久？", 30, 410);

            g.setColor(new Color(11, 11, 11));
            g.setFont(new Font("宋体", Font.BOLD, 60));
            g.drawString("你是真的闲", 260, 480);
        }

    }
}
