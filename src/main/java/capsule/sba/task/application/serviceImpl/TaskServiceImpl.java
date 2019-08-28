package capsule.sba.task.application.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capsule.sba.task.application.dao.TaskManagerDao;
import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	public TaskManagerDao taskMgrDao;
	@Override
	public Task addTask(Task task) {
		// TODO Auto-generated method stub
		System.out.println("parent id " +task.getParentId());
		if (task.getEndDate().before(new Date())) {
			task.setIsTaskEended(true);
		}
		else {
			task.setIsTaskEended(false);
		}
		return taskMgrDao.save(task);
		
	}

	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskMgrDao.findAll();
		
	}

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		return taskMgrDao.save(task);
	}

}
