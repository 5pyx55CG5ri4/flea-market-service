package cn.fleamarket.controller;

import java.io.File;

import cn.fleamarket.domain.Image;
import cn.fleamarket.service.ImageService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 图片表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/image")
@Api("图片接口")
public class ImageController {
    @Autowired
    ImageService imageService;

    /**
     * 图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/uploadImg")
    @ApiOperation("图片上传")
    public JSONObject uploadImg(MultipartFile file, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String fileName = file.getOriginalFilename();
        String newFileName = StringTool.getUUID() + fileName.substring(fileName.indexOf("."));
        Image image = new Image();
        File file1 = null;
        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {
                file1 = new File(Image.WIN_UPLOAD, newFileName);
            }else {
                file1 = new File(Image.LINUX_UPLOAD, newFileName);
            }

            if (!file1.exists()) {
                file1.mkdir();
            }
            file.transferTo(file1);
            image.setId(StringTool.getUUID());
            image.setImgUrl(newFileName);
            imageService.insert(image);
            ret.put("code", 0);
            ret.put("data", image.getImgUrl());
            ret.put("msg", "图片上传成功");
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "图片上传失败");
            e.printStackTrace();
        }
        return ret;
    }
}
