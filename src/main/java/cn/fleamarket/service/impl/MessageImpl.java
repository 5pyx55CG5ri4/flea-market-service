package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Message;
import cn.fleamarket.domain.Product;
import cn.fleamarket.mapper.MessageMapper;
import cn.fleamarket.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class MessageImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public Page<Message> selectListPage(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        String pId = (String) map.get("pId");
        Page<Message> messagePage = new Page<>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("f_id",pId);
        messageMapper.selectPage(messagePage,queryWrapper);
        return messagePage;
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public Page<Message> selectListPageByUser(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        String userId = (String) map.get("userId");
        Page<Message> messagePage = new Page<>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t_id",userId);
        messageMapper.selectPage(messagePage,queryWrapper);
        return messagePage;
    }

    @Override
    public int delete(String id, String pId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",pId);
        updateWrapper.eq("t_id",id);
        return messageMapper.deleteById(pId);
    }

    @Override
    public int deletebyFid(String fid) throws Exception {
        return messageMapper.delete(fid);
    }
}
