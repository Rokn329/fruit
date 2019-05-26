package com.fruit.sorb.controller;

import com.fruit.sorb.vo.PicUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: FileUploadController
 * @Description: 文件上传
 * @Author: lokn
 * @Date: 2019/2/21 21:10
 */
@Controller
public class FileUploadController {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * 文件上传
     * 1、采用文件正确的接收方式
     * 2、判断是否为一个图片。0表示无异常，1代表异常（jpg|gif|png）
     * 3、判断是否是一个“正经”的图片，判断是否有宽度（width）和高度（height）
     * 4、编辑磁盘目录 D:\fruit\images\yyyy\MM\dd\HH\mm
     * 5、编辑相对路径url:image.fruit.com/images/yyyy/MM/dd/HH/mm/文件名称
     * 6、将文件保存
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult fileUpload(MultipartFile uploadFile) {
        PicUploadResult picUploadResult = new PicUploadResult();

        // 1.获取文件名称
        String fileName = uploadFile.getOriginalFilename();
        // 2.获取后缀名称
        String endName = fileName.substring(fileName.lastIndexOf("."));

        // 3.判断是否为图片格式
        if (!endName.matches("^.(jpg|png|gif)$")) {
            picUploadResult.setError(1);
            LOG.error("~~~~~~~文件后缀名不符合图片格式");
            return picUploadResult;
        }
        // 4.判断图片文件是否为一个正确的图片
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            // 获取宽度和高度，如果获取时有问题，则会抛异常
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            picUploadResult.setWidth(Integer.toString(width));
            picUploadResult.setHeight(Integer.toString(height));

            String localPath = "D:/fruit/images/";
            String datePath = new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
            String urlPath = "http://image.fruit.com/images/";
            localPath += datePath+ "/" + fileName;
            urlPath  += datePath + "/" + fileName;

            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            uploadFile.transferTo(file);
            picUploadResult.setUrl(urlPath);
            return picUploadResult;
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("~~~~~~~该文件是一个非法文件");
            picUploadResult.setError(1);
            return picUploadResult;
        }
    }

}
