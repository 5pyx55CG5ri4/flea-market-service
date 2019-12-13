package cn.fleamarket.mapper;

import cn.fleamarket.domain.Code;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

@Mapper
public interface CodeMapper extends BaseMapper<Code> {
}
