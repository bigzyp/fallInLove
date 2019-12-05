package com.love.fallinlove.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Random;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/23 15:02
 */
@RestController
public class UploadController {

    /**
     * @Description:  上传文件
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request, HttpServletResponse response, Long userId) {
        String url = "";
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."));//文件后缀
            fileName= System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileF;//新的文件名


            //获取文件夹路径
            String filePath = path+"/"+ userId;
            File file1 =new File(filePath);
            //如果文件夹不存在则创建
            if(!file1.exists()  && !file1 .isDirectory()){
                file1.mkdir();
            }
            //将图片存入文件夹
            File targetFile = new File(file1, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                url = filePath + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
