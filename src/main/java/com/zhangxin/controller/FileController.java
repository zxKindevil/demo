package com.zhangxin.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 文件上传处理类
 */
@Controller
public class FileController {

    //单文件上传
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public void queryFileData(@RequestParam("uploadfile") CommonsMultipartFile file, HttpServletRequest request) {
        // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
//            String path = request.getSession().getServletContext()
//                    .getRealPath("/upload/" + filename);// 存放位置

            String path = "~/temp/test/" + filename;
            File destFile = new File(path);
            System.out.println(path);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("okkkkkk");
        } else {
            System.out.println("errr");
        }
    }

    public void ssss(@RequestParam("uploadfile") CommonsMultipartFile file, HttpServletRequest request) {
        try {

            //1.得到解析器工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //2.得到解析器
            ServletFileUpload upload = new ServletFileUpload(factory);

            //3.判断上传表单的类型
            if (!upload.isMultipartContent(request)) {
                //上传表单为普通表单，则按照传统方式获取数据即可
                return;
            }

            //为上传表单，则调用解析器解析上传数据
            List<FileItem> list = upload.parseRequest(request);  //FileItem

            //遍历list，得到用于封装第一个上传输入项数据fileItem对象
            for (FileItem item : list) {

                if (item.isFormField()) {
                    //得到的是普通输入项
                    String name = item.getFieldName();  //得到输入项的名称
                    String value = item.getString();
                    System.out.println(name + "=" + value);
                } else {
                    //得到上传输入项
                    String filename = item.getName();  //得到上传文件名  C:\Documents and Settings\ThinkPad\桌面\1.txt
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    InputStream in = item.getInputStream();   //得到上传数据
                    int len = 0;
                    byte buffer[] = new byte[1024];


                    String savepath = request.getContextPath();
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + filename);  //向upload目录中写入文件
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }

                    in.close();
                    out.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}