package cn.fleamarket.utils;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.CaseFormat;
import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 生成sql
 *
 * @author zhuliyou
 * @date 2021/04/22
 */
public class GenerateSqlToBean {


    /**
     * 不需要生成的字段注解
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface NotGenerateSql {

    }


    /**
     * sql配置
     *
     * @author zhuliyou
     * @date 2021/04/22
     */
    @Data
    public class SqlConfig {
        private String tableName;
        private List<FiledConfig> filedConfigs;
    }

    /**
     * 字段配置
     *
     * @author zhuliyou
     * @date 2021/04/22
     */
    @Data
    public class FiledConfig {
        private String filedName;
        private String filedType;
        private Boolean isPrimaryKey = false;
        private Boolean notNull = false;
        private Boolean isDefault = false;
        private Boolean isAutoIncrement = false;
        private String defaultValue;

        public String build() {
            String field = SqlTemplate.FIELD;
            field = field.replaceAll(TemplateConstant.FIELD_NAME, this.filedName).trim();
            field = field.replaceAll(TemplateConstant.FIELD_TYPE, this.filedType).trim();
            field = field.replaceAll(TemplateConstant.PRIMARY_KEY, this.isPrimaryKey ? TemplateConstant.PRIMARY_KEY_VALUE : "").trim();
            field = field.replaceAll(TemplateConstant.NOT_NULL, this.notNull ? TemplateConstant.NOT_NULL_VALUE : "").trim();
            field = field.replaceAll(TemplateConstant.DEFAULT, this.isDefault ? TemplateConstant.DEFAULT_VALUE : "").trim();
            field = field.replaceAll(TemplateConstant.DEFAULT_CONTENT, this.defaultValue != null ? this.defaultValue : "").trim();
            field = field.replaceAll(TemplateConstant.AUTO_INCREMENT, this.isAutoIncrement ? TemplateConstant.AUTO_INCREMENT_VALUE : "").trim();
            return field.trim();
        }
    }

    /**
     * sql模板
     *
     * @author zhuliyou
     * @date 2021/04/22
     */
    private static class SqlTemplate {

        private static final String START = "CREATE TABLE ${tableName} (";

        private static final String FIELD = "${fieldName} ${fieldType} ${primaryKey} ${notNull} ${default} ${defaultValue} ${autoIncrement},";

        private static final String END = ");";
    }

    /**
     * 模板
     *
     * @author zhuliyou
     * @date 2021/04/22
     */
    private interface TemplateConstant {

        String TABLE_NAME = "\\$\\{tableName}";

        String FIELD_NAME = "\\$\\{fieldName}";

        String FIELD_TYPE = "\\$\\{fieldType}";

        String PRIMARY_KEY = "\\$\\{primaryKey}";

        String PRIMARY_KEY_VALUE = "primary key";

        String NOT_NULL = "\\$\\{notNull}";

        String NOT_NULL_VALUE = "not null";

        String DEFAULT = "\\$\\{default}";

        String DEFAULT_VALUE = "default";

        String DEFAULT_CONTENT = "\\$\\{defaultValue}";

        String AUTO_INCREMENT = "\\$\\{autoIncrement}";

        String AUTO_INCREMENT_VALUE = "auto_increment";

        String STRING_CLASS = "VARCHAR(255)";

        String INT_CLASS = "INT(10)";

        String DATE_CLASS = "DATETIME";

        String BIG_DECIMAL = "decimal(18,4)";

    }


    /**
     * 生成sql
     *
     * @param className 类名称
     * @return {@link String}
     */
    String generateSql(String className) {
        try {
            SqlConfig sqlConfig = new SqlConfig();
            Class<?> aClass = Class.forName(className);
            TableName annotation = aClass.getAnnotation(TableName.class);
            String tableName = Objects.nonNull(annotation) ? annotation.value() : humpTurnedUnderline(aClass.getName());
            Field[] declaredFields = aClass.getDeclaredFields();
            sqlConfig.setTableName(tableName);
            List<FiledConfig> filedConfigs = new ArrayList<>();
            sqlConfig.setFiledConfigs(filedConfigs);
            for (Field declaredField : declaredFields) {
                if ("serialVersionUID".equals(declaredField.getName())
                        || declaredField.getAnnotation(NotGenerateSql.class) != null) {
                    continue;
                }
                declaredField.setAccessible(true);
                String name;
                if (declaredField.getAnnotation(TableField.class) != null) {
                    name = declaredField.getAnnotation(TableField.class).value();
                } else {
                    name = this.humpTurnedUnderline(declaredField.getName());
                }
                String type = this.conversionType(declaredField.getType().getName());
                FiledConfig filedConfig = new FiledConfig();
                filedConfig.setFiledName(name);
                filedConfig.setFiledType(type);
                if (declaredField.getAnnotation(TableId.class) != null) {
                    filedConfig.setIsPrimaryKey(true);
                    filedConfig.setNotNull(true);
                    if ("java.lang.Integer".equals(declaredField.getType().getName())) {
                        filedConfig.setIsAutoIncrement(true);
                    }
                }
                filedConfigs.add(filedConfig);
            }
            return this.toBuildDatabaseStatement(sqlConfig);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 建立sql
     *
     * @param sqlConfig sql配置
     * @return {@link String}
     */
    String toBuildDatabaseStatement(SqlConfig sqlConfig) {
        String start = SqlTemplate.START;
        StringBuilder sql = new StringBuilder(start.replaceAll(TemplateConstant.TABLE_NAME, sqlConfig.getTableName()));
        sqlConfig.getFiledConfigs().forEach(filedConfig -> {
            String build = filedConfig.build();
            sql.append(build);
        });
        String substring = sql.substring(0, sql.lastIndexOf(","));
        return substring + SqlTemplate.END;
    }

    /**
     * 驼峰把下划线
     *
     * @param str str
     * @return {@link String}
     */
    String humpTurnedUnderline(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }


    /**
     * 转换类型
     *
     * @param className 类名称
     * @return {@link String}
     */
    String conversionType(String className) {
        String retString;
        switch (className) {
            case "java.lang.String":
                retString = TemplateConstant.STRING_CLASS;
                break;
            case "java.lang.Integer":
                retString = TemplateConstant.INT_CLASS;
                break;
            case "java.util.Date":
                retString = TemplateConstant.DATE_CLASS;
                break;
            case "java.lang.Double":
            case "java.math.BigDecimal":
                retString = TemplateConstant.BIG_DECIMAL;
                break;
            default:
                retString = "";
                break;
        }
        return retString;
    }

    /**
     * 驼峰把下划线
     *
     * @param str str
     * @return {@link List<String>}
     */
    List<String> humpTurnedUnderline(String... str) {
        List<String> list = new ArrayList<>();
        for (String s : str) {
            String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
            list.add(to);
        }
        return list;
    }

    public static void main(String[] args) {
        String[] classNames = new String[]{"cn.fleamarket.domain.Code", "cn.fleamarket.domain.Favorites", "cn.fleamarket.domain.Image", "cn.fleamarket.domain.Message", "cn.fleamarket.domain.Order", "cn.fleamarket.domain.Product", "cn.fleamarket.domain.User"};
        GenerateSqlToBean generateSqlToBean = new GenerateSqlToBean();
        for (String className : classNames) {
            System.out.println(generateSqlToBean.generateSql(className));
        }
    }
}
