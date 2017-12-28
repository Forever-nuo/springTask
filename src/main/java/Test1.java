import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Forever丶诺 on 2017-12-26.
 */
public class Test1 {

    public static void main(String[] args) {

        //获得spring容器
        ApplicationContext appContext =  new ClassPathXmlApplicationContext("spring/spring-all.xml");
        //获得流程引擎对象
        RepositoryService  repositoryService = (RepositoryService) appContext.getBean("repositoryService");

    }

}
