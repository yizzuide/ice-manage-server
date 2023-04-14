# ice-manage-server
基于Milkomeda的Ice模块实现的Redis延迟队列服务端，拥有全面的延迟任务监控API。

## 如何布署
导入项目下的 doc/ice.sql 到 mysql 里执行，创建项目依赖的表和基本数据，在启动后使用超级管理员账号：admin，密码：123456

## 延迟任务状态数据存储
用于记录每一个延迟任务的当前状态数据，Ice可提供以下方式：
    
- Redis（默认）
- Mysql
- Mongodb（当前使用的方案）

可通过配置修改存储方案：
```yml
milkomeda:
  ice:
    introspect:
      inspector-type: mongodb
```
