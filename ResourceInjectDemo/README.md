## 通过代码验证`@Resource`注解实现注入
- `@Resource`是由J2EE提供，而并非Spring提供。
- 在默认情况下是按照name进行注入，Spring将`@Resource`注解的name属性解析为bean的名字。如果设置了byType的情况下，和`@Autowired`一样，根据type进行自动注入。
- 如果只设置了name的情况下，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
- 如果只设置了type的情况下，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
- 如果同时设置了name和type，此时会从Spring的上下文找到唯一的匹配，如果找不到就会抛出异常。
- 如果都没有设置的情况下，既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。
- 用例验证
  - 什么都不设置的情况下，查看输出结果
    - 对应的bean没有实现类(ProductDAO, ProductService)
      ~~~markdown
      Description:
      A component required a bean of type 'com.ez.resourceinject.dao.ProductDAO' that could not be found.
      ~~~
    - 对应的bean有多个实现类(UserDAO, UserService)
      ~~~markdown
      Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.ez.resourceinject.dao.UserDAO' available: expected single matching bean but found 2: userDAOImpl,userDAOImpl2
      
      此时的情况是，先按照byName的方式进行装配，但是没有匹配成功
      （此时`@Resource`根据Spring生成的beanName为userDAO，但是同类型的IOC池中有两个bean
      但是beanName分别是userDAOImpl和userDAOImpl2，并没有和userDAO匹配。
      所以后面进入到byType的方式进行装配，此时发现type有两个bean，所以装配失败
      ~~~
    - 又问，如果对应的bean和Spring生成的名字相同情况下呢？
      ~~~markdown
      猜想：
      1. 如果没有实现类，还是会有bean not found的错误
      2. 如果有多个实现类（实现类就已经决定了beanName不同，会进入byType装配）
      ~~~
      ~~~java
      @Bean
      public class Person {
      
      }
      
      @Bean
      public class Person1 {
      
      }
      
      @Resource
      private Person person;
      ~~~
  - 只设置name的情况下(UserDAO, UserService)
    - 如果有多个type相同的情况，会发生什么样的结果？
      ~~~java
      @Resource(name = "userDAOImpl2")
      private UserDAO userDAO;
      ~~~
      只要指定对应的beanName，就可以完成装配，即使有多个相同type实现
  - 只设置type的情况下
    - 如果有多个name相同的情况下，会发生什么样的结果？
      ~~~markdown
      有多个name相同的情况下，已经注定了启动失败。多个bean不能有相同的beanName。
      ~~~
    - 如果多个相同type的bean，有不同的name，会发生什么样的结果？
      ~~~java
      // 需要分两种情况：
      // 情况1，@Resource(type = 接口类)
      @Resource(type = UserDAO.class)
      private UserDAO userDAO;
      // 此时不能注入成功，因为接口有两个实现类UserDAOImpl和UserDAOImpl2
      
      
      // 情况2，@Resource(type = 实现子类)
      @Resource(type = UserDAOImpl.class)
      private UserDAO userDAO;
      // 此时能够注入成功，因为指定的是UserDAO接口的实现类UserDAOImpl，所以会将这个UserDAOImpl进行注入，而UserDAOImpl2不会注入。
      ~~~
      - 在type = 接口类.class的情况下，type只有一种实现，可以完成注入吗？
        ~~~markdown
        可以完成注入
        ~~~
        ~~~java
        @Resource(type = UserDAO.class)
        private UserDAO userDAO;
        ~~~
  - 同时设置name和type的情况下