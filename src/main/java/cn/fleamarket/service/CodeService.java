package cn.fleamarket.service;

import cn.fleamarket.domain.Code;

public interface CodeService {

    int insert(Code code1);

    Code selectById(String id);

    void delete(String id);

    int update(Code code);
}
