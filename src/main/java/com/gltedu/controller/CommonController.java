package com.gltedu.controller;

import com.gltedu.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 巩乐天
 * @version 1.0
 */
/*
* 文件上传和下载
* */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value(("${reggie.path}"))
    private String basePath;
    /*
    * 文件上传
    * */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        //file是一个临时文件 需要转存到指定位置 否则本次请求完成后临时文件会删除
        log.info(file.toString() );

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //构造唯一的文件名（不能重复） --uuid（通用唯一识别码）
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String name = UUID.randomUUID().toString() + extname;
        log.info("新文件名：{}",name);

        //将文件存储在服务器的磁盘目录中：E:\images
        file.transferTo(new File(basePath+ name));

        return R.success(name);
    }

    /*
    * 文件下载
    */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));

            //输出流  通过输出流将文件谢辉浏览器 在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0,len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
