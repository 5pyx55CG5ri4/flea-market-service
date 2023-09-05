create table sys_favorites
(
    id          bigint auto_increment
        primary key,
    product_id  bigint                              null comment '商品id',
    user_id     bigint                              null comment '用户id',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '收藏表';

create table sys_message
(
    id          bigint auto_increment
        primary key,
    product_id  bigint                              null comment '商品id',
    user_id     bigint                              null comment '用户id',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    message     varchar(1024)                       null comment '消息内容'
)
    comment '消息表';

create table sys_product
(
    id               bigint auto_increment
        primary key,
    title            varchar(255)                        null comment '标题',
    content          varchar(1024)                       null comment '商品内容',
    original_price   decimal(12, 2)                      null comment '原价',
    sale_price       decimal(12, 2)                      null comment '出售价格',
    create_time      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                              null comment '创建人',
    viewed           int       default 0                 null comment '浏览次数',
    collection_count int       default 0                 null comment '收藏次数',
    img_url          varchar(255)                        null comment '图片链接地址',
    state            char      default '1'               null comment '状态 0 下架 1 上架'
)
    comment '商品表';

create table sys_user
(
    id          bigint auto_increment
        primary key,
    nick_name   varchar(45)                         null comment '昵称',
    user_name   varchar(255)                        null comment '用户名/邮箱号/手机号',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    state       char      default '1'               null comment '状态 0 冻结 1 有效'
)
    comment '用户表';

