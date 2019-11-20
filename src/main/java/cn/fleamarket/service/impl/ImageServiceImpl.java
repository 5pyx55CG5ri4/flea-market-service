package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Image;
import cn.fleamarket.mapper.ImageMapper;
import cn.fleamarket.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zining
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;
    @Override
    public int insert(Image image) {
        return imageMapper.insert(image);
    }
}