# 关于 注解以及注解 处理器的使用
## 注解的类型
- 1.标准注解「Override」
- 2.元注解「Retention 等」
> Retention 注解有三种类型.
>SOURCE 源码级注解.编译后,注解信息丢弃,不保留在字节码中.
>CLASS 编译时注解.运行时jvm会丢弃该注解信息.
>RUNTIME 运行时注解,jvm也会保留该部分注解信息,可以通过反射方式来获取该注解信息.
## 注解的使用方式
- 略
##注解处理器的使用
- 1.创建库存放注解处理器.
- 2.创建类继承AbstractProcessor类,重写三个方法.init(),process()以及getSupportedAnnotationTypes()
- 3.注册注解处理器.在main目录下创建目录resources->在resources下创建META-INF目录->在META-INF目录下创建services目录,在该目录下创建文件.文件名和代码中一致,文件内容:包名+「注解处理器」名
## 相关 依赖 导入.
- 主项目导入注解处理器依赖 AnnotationProcessor project(';「process名」')
- api project('项目名')
## 注意一定要注册,不然用不了了.
