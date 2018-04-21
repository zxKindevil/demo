package com.zhangxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/play")
public class PlayController {

    @RequestMapping(value = "/audio")
    public ModelAndView getAudio(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = "~/temp/test/" + filename;
        String range = request.getHeader("Range");
        String[] rs = range.split("\\=");
        range = rs[1].split("\\-")[0];

        File file = new File(path);
        if (!file.exists()) throw new RuntimeException("音频文件不存在 --> 404");
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        long length = file.length();
        // 播放进度
        int count = 0;
        // 播放百分比
        int percent = (int) (length * 1);

        int irange = Integer.parseInt(range);
        length = length - irange;

        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Content-Length", length + "");
        response.addHeader("Content-Range", "bytes " + range + "-" + length + "/" + length);
        response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");

        int len = 0;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
            count += len;
            if (count >= percent) {
                break;
            }
        }
        System.out.println("count: " + count + ", percent: " + percent);
        fis.close();
        os.close();
        return null;
    }

}