package cn.fleamarket.utils;

import cn.fleamarket.domain.Code;
import cn.fleamarket.domain.User;
import cn.fleamarket.mapper.CodeMapper;
import cn.fleamarket.mapper.UserMapper;
import cn.fleamarket.service.CodeService;
import cn.fleamarket.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 定时任务
 */
@Component
@Async
public class ScheduledTool {
    @Autowired
    UserService userService;
    @Autowired
    CodeService codeService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CodeMapper codeMapper;
    @Autowired
    EmailUtil emailUtil;
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() throws Exception {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        List<Code> list = codeMapper.selectList(queryWrapper);
        for (Code code: list) {
            if(!emailUtil.getTimeCJ(StringTool.dataTool(new Date()),StringTool.dataTool(code.getCodeTime()))){
                User user = userService.qureyByEmail(code.getId());
                if(user==null){
                    codeMapper.deleteById(code.getId());
                    System.out.println("删除一个过期的还没注册的邮箱");
                }
            }
        }
    }
}
