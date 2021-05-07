package cn.fleamarket.controller;

import cn.fleamarket.common.R;
import cn.fleamarket.config.PathConfig;
import cn.fleamarket.domain.Image;
import cn.fleamarket.service.ImageService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;


/**
 * 图片表
 *
 * @author zining
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/image")
@Api("图片接口")
@CrossOrigin
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    private PathConfig pathConfig;


    /**
     * 上传img
     *
     * @param file 文件
     * @return {@link JSONObject}
     */
    @SneakyThrows
    @PostMapping("/uploadImg")
    @ApiOperation("图片上传")
    public R<String> uploadImg(MultipartFile file) {
        JSONObject ret = new JSONObject();
        String fileName = file.getOriginalFilename();
        String newFileName = StringTool.getUUID() + Objects.requireNonNull(fileName).substring(fileName.indexOf("."));
        Image image = new Image();
        File file1;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            file1 = new File(pathConfig.getWinPath(), newFileName);
        } else {
            file1 = new File(pathConfig.getWinPath(), newFileName);
        }

        if (!file1.exists()) {
            System.out.println(file1.mkdir());
        }
        file.transferTo(file1);
        image.setId(StringTool.getUUID());
        image.setImgUrl(newFileName);
        imageService.insert(image);
        return R.ok(0,"图片上传成功",File.separator + "static" + File.separator + "img" + File.separator + image.getImgUrl());
    }
}
