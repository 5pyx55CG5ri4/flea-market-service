package com.fleamarket.modules.image;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.fleamarket.code.domain.R;
import com.fleamarket.common.annotation.UnAuth;
import com.fleamarket.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


/**
 * 图片表
 *
 * @author zining
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {


    @Value("${file-upload-path}")
    private String fileUploadPath;

    /**
     * 上传img
     *
     * @param file 文件
     * @return {@link JSONObject}
     */
    @PostMapping("upload")
    @UnAuth
    public R uploadImg(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID() + Objects.requireNonNull(fileName).substring(fileName.indexOf("."));
        File uploadFile = new File(fileUploadPath, newFileName);
        if (!uploadFile.exists()) {
            boolean mkdir = uploadFile.mkdir();
            log.info("mkdir {}", mkdir);
        }
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return R.success(Constants.RESOURCE_PREFIX.concat("/").concat(newFileName));
    }
}
