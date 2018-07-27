"xpathx -version 0.0.1"
2018-07-24 09:52:00  仿Mybatis配置实现数据库连接
 1.解析Mybatis配置,配置保存入Configuration
2018-07-27 09:36:00 关于Mybatis的MetaObject来为属性赋值的解析
 1.MetaObject主要用来为setter方法或者属性赋值.支持xxx.xxx.name这样的赋值方式.最后会为name属性或者为setName方法赋值.
 MetaObject对应三个Factory,分别是:
 (1)ObjectFactory
 (2)ObjectWrapperFactory
 (3)ReflectorFactory
 接下来会一一来解析这三个Factory的用途,ReflectorFactory会返回一个Reflector,Reflector保存了类的getter和setter以及属性.