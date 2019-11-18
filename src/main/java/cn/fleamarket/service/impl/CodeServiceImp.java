package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Code;
import cn.fleamarket.mapper.CodeMapper;
import cn.fleamarket.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeServiceImp implements CodeService {
    @Autowired
    CodeMapper codeMapper;
    @Override
    public int insert(Code code1) {
        return codeMapper.insert(code1);
    }

    @Override
    public Code selectById(String id) {
        return codeMapper.selectById(id);
    }

    @Override
    public void delete(String id) {
        codeMapper.deleteById(id);
    }

    @Override
    public int update(Code code) {
        return codeMapper.updateById(code);
    }
}
