package com.zhangxin.biz;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhangxin on 2018/1/15.
 */
public class SoundPlayer {
    private static String ji = "/Users/zhangxin/z/me/吉.wav";
    private static String wolf = SoundPlayer.class.getResource("/").getPath() + "wolf.wav";
    private static String mie = SoundPlayer.class.getResource("/").getPath() + "mie.wav";

    public static void playJI() {
        play(ji);
    }

    public static void playWolf() {
        play(wolf);
    }

    public static void playMie() {
        play(mie);
    }

    public static void playMie(String key) {
        System.out.println("notify mie " + key);
        play(mie);
    }

    private static void play(String path) {
        try {
            File file = new File(path); // 获取文件，传入的参数为String类型，文件的路径
            URL url = file.toURL(); //获取文件的路径
            AudioClip ac = Applet.newAudioClip(url); // 因为AudioClip是接口不能实例化，所有用Applet中的newAduioClip来实例化
            ac.play(); //播放
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        playMie();
    }
}
