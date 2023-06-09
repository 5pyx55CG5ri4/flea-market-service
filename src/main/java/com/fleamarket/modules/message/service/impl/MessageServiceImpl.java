package com.fleamarket.modules.message.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleamarket.modules.message.domain.Message;
import com.fleamarket.modules.message.mapper.MessageMapper;
import com.fleamarket.modules.message.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
