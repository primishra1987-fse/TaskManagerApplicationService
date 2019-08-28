package capsule.sba.task.application.service;

import java.util.List;



import capsule.sba.task.application.entity.Task;




public interface TaskService {
	public Task addTask(Task task);

	public List<Task> getAllTasks();

	public Task updateTask(Task task);
}
