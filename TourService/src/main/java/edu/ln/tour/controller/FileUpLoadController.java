package edu.ln.tour.controller;


import edu.ln.tour.utils.QiniuUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin //跨域支持
@RestController
public class FileUpLoadController {

    // 上传文件到nginx图片服务器
    // form中的文件name 要和方法中的文件名一致，必须为uploadFile
    @PostMapping("/uploadNginx")
    public String uploadNginx(MultipartFile uploadFile) {

        // 保存的文件名
        String fileName = "img/product/small/"+UUID.randomUUID() + uploadFile.getOriginalFilename()
                .substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        //
//        String targetFileNameWithPath = "F:/Tool/nginx-1.23.0/html/" + fileName;
        String targetFileNameWithPath = "F:/workspace_idea1/TourProject/travel/pages/" + fileName;

        try {
            uploadFile.transferTo(new File(targetFileNameWithPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回文件名
        return fileName;
    }

    @PostMapping("/uploadNginxQingniu")
    public  String uploadQingniu(MultipartFile uploadFile){
        String fileName = UUID.randomUUID()+uploadFile.getOriginalFilename().
                substring(uploadFile.getOriginalFilename().lastIndexOf("."));

        try {
            QiniuUtils.upload2Qiniu(uploadFile.getBytes(),fileName);
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }


        return  fileName;
    }
}
