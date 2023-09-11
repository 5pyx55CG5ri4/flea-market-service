**校园二手交易平台**

基于springboot的毕业设计

**技术栈**

springboot+mybatisplus+jdk8+mysql5.6

[前端项目源码](https://github.com/5pyx55CG5ri4/flea-market-ui)

**使用说明:**

1. [执行此处的SQL语句](https://github.com/5pyx55CG5ri4/flea-market-service/blob/main/src/main/resources/db/face.sql)
2. 将[application-dev.yml](src%2Fmain%2Fresources%2Fapplication-dev.yml)配置文件中的数据库地址和用户名密码修改为自己的
3. 将[application-dev.yml](src%2Fmain%2Fresources%2Fapplication-dev.yml)
   配置文件中的file-upload-path改为自己想要上传文件的地址(请确保目录存在)
4. 启动项目[FleaMarketApplication.java](src%2Fmain%2Fjava%2Fcom%2Ffleamarket%2FFleaMarketApplication.java)

一些问题:

1. 缓存问题:为了避免上手难度,这次重构剔除了Redis,缓存使用本地内存,重启项目验证码和token将失效
2. 文件上传问题,请确保使用说明中的第3步做了 并且目录存在 并且有权限
3. end

**近期工作不忙 重新捡起以前的毕业设计 重构了一波前后端 适合学习和做毕设**

**如果对你有帮助,请用Star砸我,谢谢!** (小声BB:不给点Star不解决问题(不是)手动狗头!)

