package com.msb.game;

import java.net.URL;

public class TestURL {
    public static void main(String[] args) {
        URL bodyUrl = Images.class.getResource("/"); // ‘/’是一个相对路径
        System.out.println(bodyUrl);
    }
}
