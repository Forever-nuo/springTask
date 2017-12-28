package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Forever丶诺 on 2017-12-27.
 */
public class StartSpring {
    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-all.xml");
}
