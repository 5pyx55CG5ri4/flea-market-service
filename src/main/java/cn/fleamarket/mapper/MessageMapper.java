package cn.fleamarket.mapper;

import cn.fleamarket.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    @Delete("DELETE FROM f_message where f_id = #{id}")
    int delete(String id);
}
