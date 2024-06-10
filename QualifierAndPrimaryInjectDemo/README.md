## `@Qualifier`和`@Primary`注解的区别
- 首先`@Qualifier`和`@Primary`在发生依赖注入的歧义时，都可以决定要使用哪个bean进行注入。
- `@Qualifier`的优先级要高于`@Primary`
  - `@Primary`只是定义了默认值，也就是说在有多个type的bean时，会决定哪个bean是默认的，后面Spring在启动的时候，会使用`@Primary`注解标注的默认的bean。
  - `@Qualifier`相比于`@Primary`更加的具体，可以指定具体使用哪个bean进行注入。
  - 当二者同时存在的情况下，`@Qualifier`注解优先级高于`@Primary`注解。
- 代码验证（UserDAO，UserService）：
  - 仅使用`@Qualifier`注解的情况，在`AutowiredInjectDemo`中已经展示过。
  - 仅使用`@Primary`注解的情况（
    ~~~java
    @Autowired
    //@Qualifier("userDAOImpl2")
    private UserDAO userDAO;
    
    
    @Repository("userDAOImpl")
    @Primary
    public class UserDAOImpl implements UserDAO{
    
    }
    
    @Repository("userDAOImpl2")
    public class UserDAOImpl2 implements UserDAO{
    
    }
    // 在这两个实现类同时存在的情况下，如果没有@Primary注解，启动就会报错
    // 在正常启动后，发现默认使用的是UserDAOImpl这个实现类
    ~~~
  - 同时使用`@Qualifier`和`@Primary`的情况下
    ~~~java
    @Autowired
    @Qualifier("userDAOImpl2")
    private UserDAO userDAO;
    
    
    @Repository("userDAOImpl")
    @Primary
    public class UserDAOImpl implements UserDAO{
    
    }
    
    @Repository("userDAOImpl2")
    public class UserDAOImpl2 implements UserDAO{
    
    }
    // 在这两个实现类同时存在的情况下，如果没有@Primary注解，启动就会报错
    // 在正常启动后，发现进行注入的是@Qualifier声明的UserDAOImpl2这个实现类
    ~~~