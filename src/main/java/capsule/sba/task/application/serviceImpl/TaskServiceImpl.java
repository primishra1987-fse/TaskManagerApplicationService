package capsule.sba.task.application.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capsule.sba.task.application.dao.ParentTaskDao;
import capsule.sba.task.application.dao.TaskManagerDao;
import capsule.sba.task.application.entity.ParentTask;
import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	public TaskManagerDao taskMgrDao;
	
	@Autowired
	public ParentTaskDao parentTaskDao;

	@Override
	public Task addTask(Task task) {
		Task task1 = null;

		if (task.getEndDate().before(new Date())) {
			task.setIsTaskEended(true);
		} else {
			task.setIsTaskEended(false);
		}
		if (task.getParentalId() !=null)
		{
			System.out.println("parent id " + task.getParentalId());
			
			Task task2 = taskMgrDao.findById(task.getParentalId()).get();
			
			ParentTask parentTask = new ParentTask();
			parentTask.setParentId(task.getParentalId());
			parentTask.setParentTask(task2.getTask1());
			parentTaskDao.save(parentTask);
			
			task.setParent(parentTask);
		}
			 task1 = taskMgrDao.save(task);
		
		return task1;

	}

	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskMgrDao.findAll();

	}

	@Override
	public Task updateTask(Task task,Long id) {
		// TODO Auto-generated method stub
		
		return taskMgrDao.save(task);
	}

	@Override
	public void deleteTask(Long taskId) {

		taskMgrDao.deleteById(taskId);
	}

}
