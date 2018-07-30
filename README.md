"xpathx -version 0.0.1"
2018-07-24 09:52:00  仿Mybatis配置实现数据库连接
 1.解析Mybatis配置,配置保存入Configuration
2018-07-27 09:36:00 关于Mybatis的MetaObject来为属性赋值的解析
 1.MetaObject主要用来为setter方法或者属性赋值.支持xxx.xxx.name这样的赋值方式.最后会为name属性或者为setName方法赋值.
 MetaObject对应三个Factory,分别是:
 (1)ObjectFactory
 ObjectFactory用于当没有实例的时候创建实例.从0到1
 (2)ObjectWrapperFactory
    objectWrapperFactory用于在有实例的时候为实例赋值.使的属性值被填充.
    objectWrapperFactory,可以用来扩展,来实现自己的实例生产工厂.系统默认为DefaultObjectWrapperFactory.暂时不提供什么功能.如果要实现自己的实例生成工厂类,继承ObjectWrapperFactory
    ObjectWrapperFactory可以用来生成自定义的ObjectWrapper.
 (3)ReflectorFactory
    用来生成Reflector,生成
 接下来会一一来解析这三个Factory的用途,ReflectorFactory会返回一个Reflector,Reflector保存了类的getter和setter以及属性.
 
 MetaObject
           _| ObjectFactory //对象实例化工厂
           _| ObjectWrapperFactory //实例Wrapper工厂
           _| originObject //原始对象
           _| ReflectFactory //发生工厂
           _| ObjectWrapper 是对实例的扩展,形成实例以及实例对应的反射信息
                 _| Object
                 _| MetaClass
                        _| ReflectFactory
                        _| Reflector
 对外来说任何操作都是由MetaObject来完成.