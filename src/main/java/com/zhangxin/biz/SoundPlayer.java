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
    public static String ji = "/Users/zhangxin/z/me/吉.wav";

    public static void playJI() {
        try {
            File file = new File(ji); // 获取文件，传入的参数为String类型，文件的路径
            URL url = file.toURL(); //获取文件的路径
            AudioClip ac = Applet.newAudioClip(url); // 因为AudioClip是接口不能实例化，所有用Applet中的newAduioClip来实例化
            ac.play(); //播放
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
