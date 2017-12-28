package activiti.chapter8.task.userTask.candidate;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.JunitTest;

import java.util.List;

/**
 * Created by Forever丶诺 on 2017-12-28.
 */
public class CandidateTask extends JunitTest {

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
        String sourceName = "diagram/chapter8/task/userTask/candidate/candidateTask.bpmn";
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
        String dKey = "candidateTask";
        runtimeService.startProcessInstanceByKey(dKey);
    }

    /**
     * @param :
     * @Description: 获取个人任务
     * 此时获取不到任务 该任务还没有设置办理人
     * @Author: Forever丶诺
     * @Date: 22:16 2017-12-28
     */
    @Test
    public void getPersonTasks() {
        String assignee = "张三";
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task task : taskList) {
            System.out.println(task.getId());
        }
    }

    /**
     * @param :
     * @Description: 根据候选人查询对应的任务
     * @sql语句 :
     * select
     * distinct RES.*
     * from
     * ACT_RU_TASK RES
     * inner join
     * ACT_RU_IDENTITYLINK I
     * on I.TASK_ID_ = RES.ID_
     * WHERE
     * RES.ASSIGNEE_ is null
     * and I.TYPE_ = 'candidate'
     * and          (
     * I.USER_ID_ = '王五'
     * )
     * order by
     * RES.ID_ asc      LIMIT 2147483647 OFFSET 0
     * @Author: Forever丶诺
     * @Date: 22:59 2017-12-28
     */
    @Test
    public void getCandidateTask() {
        String candidateUser = "王五";
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(candidateUser).list();
    }

    /**
     * @param :
     * @Description: 拾取候选人 任务
     * 只能拾取 没有 assigned 的任务
     * @sql: update
     * ACT_RU_TASK
     * SET
     * REV_ = 2,
     * NAME_ = '候选人任务',
     * PARENT_TASK_ID_ = NULL,
     * PRIORITY_ = 50,
     * CREATE_TIME_ = '2017-12-28 22:47:21',
     * OWNER_ = NULL,
     * ASSIGNEE_ = '赵六',
     * DELEGATION_ = NULL,
     * EXECUTION_ID_ = '601',
     * PROC_DEF_ID_ = 'candidateTask:1:504',
     * DESCRIPTION_ = NULL,
     * DUE_DATE_ = '2017-12-28 23:17:21',
     * SUSPENSION_STATE_ = 1
     * where
     * ID_= '604'
     * and REV_ = 1
     * @Author: Forever丶诺
     * @Date: 23:07 2017-12-28
     */
    @Test
    public void claimTask() {
        String taskId = "604";
        String userId = "赵六"; //不是候选人中的人员
        taskService.claim(taskId, userId);
    }

    /**
     * @param :
     * @Description: 取消任务的办理人
     * @Author: Forever丶诺
     * @Date: 23:13 2017-12-28
     */
    @Test
    public void unClaimTask() {
        String taskId = "604";
        taskService.unclaim(taskId);
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
