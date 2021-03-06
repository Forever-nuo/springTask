package activiti.chapter8.task.userTask.personTask;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.JunitTest;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用方法表达式 进行表示赋值
 * Created by Forever丶诺 on 2017-12-28.
 */
public class PersonTaskByExpFun extends JunitTest {

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
        String sourceName = "diagram/chapter8/task/userTask/personTask/PersonTaskByExpFun.bpmn";
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

        Map varMap = new HashMap();
        varMap.put("name","猪八戒");
        //启动流程实例
        String dKey = "PersonTaskByExpFun";
        runtimeService.startProcessInstanceByKey(dKey,varMap);
    }

    /**

     *
     * @param :
     * @Description: 获取个人任务
     * @Author: Forever丶诺
     * @Date: 22:16 2017-12-28
     */
    @Test
    public void getPersonTasks() {
        String assignee = "张三";
        List<Task> taskList  = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task task : taskList) {
            System.out.println(task.getId());
        }
    }

    /**
     * @param :
     * @Description: 完成任务
     * @Author: Forever丶诺
     * @Date: 23:33 2017-12-27
     */
    @Test
    public void testFinishTask() {
        String taskId = "304";
        taskService.complete(taskId);
    }

}
