**校园二手交易平台**

基于springboot的毕业设计
***
**请选择dev分支**  
***
**技术栈:springboot+mybatisplus+jdk8+mysql5.6**
<s>服务器已经过期,需要数据库文件的请自行根据实体类自行生成sql</s>
使用说明:
1. [执行此处的SQL语句](https://gitee.com/zhuliyou/FleaMarket/blob/master/src/main/resources/db/face.sql)
2. 将配置文件中的数据库地址和用户名密码修改为自己的
3. 启动项目

前端地址:[前端项目源码](https://gitee.com/zhuliyou/campu)

-----------------------------------------------
`2021/4/22`

增加根据bean类生成sql文件的工具类(利用反射原理)[工具类地址](https://gitee.com/zhuliyou/FleaMarket/blob/dev/src/main/java/cn/fleamarket/utils/GenerateSqlToBean.java)

要sql的人太多了,新增了一个db文件在resources中 [DB地址](https://gitee.com/zhuliyou/FleaMarket/blob/master/src/main/resources/db/face.sql)

处理了一堆细节


--------------------------------------------
`2021/4/28`

闲来无事,封装了些公共类,本人2019年的代码写的像坨便便,现在完全看不下去,但是也没有精力去优化他(毕竟要吃饭! :joy: )

如果有想拿这个项目做毕业设计或者练手的,欢迎修改和pr

 **已知缺陷:** 

1. 所有Controller都使用了JsonObject(Map)做为参数接收,这是大忌,写时一时爽,维护火葬场!!!
1. 所有业务逻辑都写在Controller里,代码耦合太高!!!
1. 没有定义统一的返回值规则;
1. 没有权限框架;
1. 数据库字段不规范;
1. Service没有事务;
1. 代码健壮性不够;
1. 前后端分离没有做token机制;
1. 没有处理全局异常;
 
 **目前已修改的:** 

1. 封装了统一返回的结果类R(path:"cn.fleamarket.common.R");
1. 封装了接收分页参数类PageParam(path:"cn.fleamarket.common.PageParam"),所有实体类都可以继承此类;
1. 把某个Controller里的便便改成了诗😉(狗头保命!!!,大佬轻喷!!!);

ps有图有真相:

**修改之前:**

没有了,嘻嘻嘻,自己去品吧;

**修改之后:**
![输入图片说明](https://images.gitee.com/uploads/images/2021/0428/180055_5926d6df_5074282.png "1619603948.png")

再PS:*本来想全改完的,实在改不下去了*
***
 <br>**有问题请提issues,不定时回复** 
***

 -  **email** :<s> ---------</s>
 -  **wx** :<s> ---------</s>
 -  **qq** :  <s> ---------</s>

***
情怀:此项目是入行前做的第一个完整项目,成长很大,做完就找到工作了,准备练手的朋友可以clone下来玩一玩,但是别玩坏了,心疼 :broken_heart: 

----------------------------------------------------------------------







 **如果对你有帮助,请用Star砸我,谢谢!** (小声BB:不给点Star不解决问题(不是)手动狗头!)

