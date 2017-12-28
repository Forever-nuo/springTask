package activiti.chapter8.task.userTask.personTask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 使用监听器办理任务
 * Created by Forever丶诺 on 2017-12-29.
 */
public class PersonTaskListener implements TaskListener {
    public void notify(DelegateTask delegateTask) {
        String assignee = "孙行者";
        delegateTask.setAssignee(assignee);

    }
}
