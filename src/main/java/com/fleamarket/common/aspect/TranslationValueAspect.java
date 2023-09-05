package com.fleamarket.common.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import com.fleamarket.code.domain.R;
import com.fleamarket.code.page.TableDataInfo;
import com.fleamarket.common.annotation.TranslationValue;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;


@Aspect
@Component
@Slf4j
@SuppressWarnings("ALL")
public class TranslationValueAspect {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Pointcut("execution(public * com.fleamarket.modules..*.*Controller.*(..))")
    public void pointOfContact() {
    }

    @Around("pointOfContact()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object result;
        try {
            result = pjp.proceed();
            this.translationValue(result);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public void translationValue(Object object) {
        if (object instanceof TableDataInfo) {
            TableDataInfo tableDataInfo = (TableDataInfo) object;
            List<?> rows = tableDataInfo.getRows();
            if (CollUtil.isNotEmpty(rows)) {
                List<Map<String, Object>> ret = new ArrayList<>();
                for (Object row : rows) {
                    Map<String, Object> rowMap = translate(row);
                    ret.add(rowMap);
                }
                tableDataInfo.setRows(ret);
            }

        } else if (object instanceof R) {
            R r = (R) object;
            Object row = r.get(r.DATA_TAG);
            if (BeanUtil.isBean(row.getClass())) {
                Map<String, Object> translate = translate(row);
                r.put(r.DATA_TAG, translate);
            }

        }
    }


    public Map<String, Object> translate(Object row) {
        if (Objects.isNull(row)) {
            return new HashMap<>();
        }
        Map<String, Object> rowMap = BeanUtil.beanToMap(row);
        Field[] fields = ReflectUtil.getFields(row.getClass());
        Arrays.stream(fields)
                .filter(field -> Objects.nonNull(field.getAnnotation(TranslationValue.class)))
                .forEach(field -> {
                    Object fieldValue = ReflectUtil.getFieldValue(row, field);
                    if (Objects.nonNull(fieldValue)) {
                        TranslationValue annotation = field.getAnnotation(TranslationValue.class);
                        List<Object> list = jdbcTemplate.queryForObject(" SELECT " + annotation.translationField()
                                        + " FROM " + annotation.targetTable()
                                        + " WHERE " + annotation.whereField()
                                        + " = ?", List.class,
                                fieldValue);
                        if (CollUtil.isNotEmpty(list)) {
                            list.stream().findFirst().ifPresent(m -> {
                                rowMap.put(annotation.alias(), m);
                            });
                        }
                    }

                });
        return rowMap;
    }

}
