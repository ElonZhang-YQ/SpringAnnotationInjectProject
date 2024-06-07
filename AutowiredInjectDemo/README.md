## 通过代码验证`@Autowired`注解实现注入
- `@Autowired`由Spring提供，只按照byType注入，默认情况下必须要求依赖对象存在，如果要允许null值，可以设置它的required属性为false。如果想使用名称装配可以结合@Qualifier注解进行使用。
  1. 首先，验证`@Autowired`注解是按照Type相同进行注入
     1. UserService中对UserDAO进行`@Autowired`注入
     2. 在UserService只有一个实现类的情况下，并且使用`@Repository`注解声明的情况下，可以注入成功
  2. 其次，验证如果有多个相同type，此时进行注入`@Autowired`会有怎样的输出，怎样进行多type的注解注入（`@Qualifier`注解）
     1. 当UserService有两个实现类的情况下（UserServiceImpl和UserServiceImpl2，且都添加`@Repository`注解)，此时IDEA已经报错，同时运行时出现报错：`Field userDAO in com.ez.autowiredinject.service.UserServiceImpl required a single bean, but 2 were found`
     2. 此时有多个相同type的实现类时，会出现注入失败的情况。
     3. `@Qualifier`注解的坑？
     4. 在正常开发过程中，dao,service在添加注解`@Repository`和`@Service`之后，一般不会添加别名了，直接让Spring去生成bean name去完成注入（早期Spring开发除外，13到17年大学期间学习Spring的时候，需要定义bean的name和class。）
     5. 在使用`@Qualifier`注解的情况下，具体格式为
     6. ~~~java
        @Autowired
        @Qualifier("bean name")
        private Bean bean;
        ~~~
     7. 此时需要注意，上面的bean name需要显式定义出来
        ~~~java
        @Repository("beanName") 
        @Service("beanName")  
        @Controller("beanName") 
        @Resource("beanName")
        public class Bean{
        
        }
        ~~~
     8. 如果不定义beanName的情况下，此时在`@Qualifier`中设置的无效，例如
        ~~~java
        public class UserServiceImpl extends UserService {
        
            @Autowired
            @Qualifier("userDAOImpl2")
            private UserDAO userDAO;
        
        }
        
        /**
         *  虽然不显式指定bean的name，Spring会自动创建一个beanName为 userDAOImpl2
         *  但是，@Qualifier指定的 userDAOImpl2是无效的。
         *  需要显式的指定bean的name
         */
        @Repository("userDAOImpl2")
        public class UserDAOImpl2 implements UserDAO {
        
        }
        
        @Repository("userDAOImpl")
        public class UserDAOimpl implements UserDAO {
        
        }
        ~~~
  3. 然后，验证如果Type为空的情况下，注入的结果是怎样的
     1. 此时有例子ProductDAO和ProductService
     2. 此时ProductDAO接口类没有实现类，在进行`@Autowired`操作的时候，IDEA已经报错。此时运行时报错为：
        ~~~markdown
        Field productDAO in com.ez.autowiredinject.service.ProductServiceImpl required a bean of type 'com.ez.autowiredinject.dao.ProductDAO' that could not be found.
        The injection point has the following annotations:
        @org.springframework.beans.factory.annotation.Autowired(required=true)
        ~~~
  4. 报错的情况下，怎么样才能使得注入成功呢？（即使为null，`required = false`）
     1. 在上面的例子中发现，ProductDAO的实现类为空，导致Spring启动失败
     2. 使用`@Autowired(required = false)`查看启动结果，发现可以正常启动
     3. 此时，尝试调用这个注入的bean，查看调用结果
     ~~~markdown
     java.lang.NullPointerException: Cannot invoke "com.ez.autowiredinject.dao.ProductDAO.findAllProduct()" because "this.productDAO" is null
         at com.ez.autowiredinject.service.ProductServiceImpl.findAllProduct(ProductServiceImpl.java:22) ~[classes/:na]
         at com.ez.autowiredinject.controller.ProductController.getAllProducts(ProductController.java:24) ~[classes/:na]
         at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[tomcat-embed-core-10.1.24.jar:6.0]
         at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.24.jar:6.0]
     ~~~
- `@Autowired`的`@Qualifier`和`@Primary`注解用例分析