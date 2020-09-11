package com.example.demo;

import com.ComputeMD5;

public class Test {
    public static void main(String[] args) {
        String s = "asdf";
        String res = ComputeMD5.encryptPassword(s);
        System.out.println(res);
    }
}
