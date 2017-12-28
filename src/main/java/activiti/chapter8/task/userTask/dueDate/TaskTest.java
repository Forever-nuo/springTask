package activiti.chapter8.task.userTask.dueDate;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.JunitTest;

/**
 * Created by Forever丶诺 on 2017-12-27.
 */
public class TaskTest extends JunitTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * @param :
     * @Description: 部署流程
     * @Author: Forever丶诺
     * @Date: 22:40 2017-12-27
     */
    @Test
    public void testDeployProcess() {
        String sourceName = "diagram/chapter8/task/userTask/dueDate/userTask.bpmn20.xml";
        repositoryService.createDeployment().addClasspathResource(sourceName).deploy();
    }

    /**
     * @param :
     * @Description:
     * @Author: Forever丶诺
     * @Date: 23:16 2017-12-27
     */
    @Test
    public void testStartProcess() {
        //启动流程实例
        String dKey = "myProcess_1";
        runtimeService.startProcessInstanceByKey(dKey);
    }

    /**
     * @param :
     * @Description: 完成任务
     * @Author: Forever丶诺
     * @Date: 23:33 2017-12-27
     */
    @Test
    public void testFinishTask() {
        String taskId = "1204";
        taskService.complete(taskId);
    }

}
