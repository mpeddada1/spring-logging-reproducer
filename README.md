# spring-logging-reproducer
Sample to reproduce LogFactory build-time error with spring-framework.

Steps to reproduce:

1) Call `mvn clean install`
2) Call `mvn -Pnative native:compile`
3) See the following error:

```
Error: Classes that should be initialized at run time got initialized during image building:
 org.apache.commons.logging.LogFactory was unintentionally initialized at build time. org.springframework.web.servlet.mvc.method.annotation.ReactiveTypeHandler caused initialization of this class with the following trace: 
	at org.apache.commons.logging.LogFactory.<clinit>(LogFactory.java:136)
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Unknown Source)
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77)
	at jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499)
	at java.lang.reflect.ReflectAccess.newInstance(ReflectAccess.java:128)
	at jdk.internal.reflect.ReflectionFactory.newInstance(ReflectionFactory.java:347)
	at java.lang.Class.newInstance(Class.java:645)
	at org.apache.commons.logging.LogFactory.createFactory(LogFactory.java:1047)
	at org.apache.commons.logging.LogFactory$2.run(LogFactory.java:960)
	at java.security.AccessController.executePrivileged(AccessController.java:776)
	at java.security.AccessController.doPrivileged(AccessController.java:318)
	at org.apache.commons.logging.LogFactory.newFactory(LogFactory.java:957)
	at org.apache.commons.logging.LogFactory.getFactory(LogFactory.java:552)
	at org.apache.commons.logging.LogFactory.getLog(LogFactory.java:655)
	at org.springframework.web.servlet.mvc.method.annotation.ReactiveTypeHandler.<clinit>(ReactiveTypeHandler.java:88)
```

4) Add `--initialize-at-build-time=org.apache.commons.logging.LogFactory` to fix the issue. 
