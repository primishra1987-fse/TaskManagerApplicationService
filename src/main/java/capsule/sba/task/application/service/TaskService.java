package capsule.sba.task.application.service;

import java.util.List;



import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.model.ModelTask;




public interface TaskService {
	//public ModelTask addTask(Task task);

	public List<ModelTask> getAllTasks();

	public ModelTask updateTask(ModelTask task, Long id);
	
	public ModelTask endTask(ModelTask task);
	
	public void deleteTask(Long id);
	
	public ModelTask getTask(Long id);

	public ModelTask addTask(ModelTask task);
}
