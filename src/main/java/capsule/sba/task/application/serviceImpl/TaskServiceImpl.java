package capsule.sba.task.application.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capsule.sba.task.application.dao.ParentTaskDao;
import capsule.sba.task.application.dao.TaskManagerDao;
import capsule.sba.task.application.entity.ParentTask;
import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.model.ModelTask;
import capsule.sba.task.application.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskManagerDao taskMgrDao;
	
	@Autowired
	Task task;
	
	@Autowired
	ParentTask parentTask;
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	@Autowired
	ModelTask resultModel;

	private void populateEntityFromModel(ModelTask taskModel) 
	{
		task = new Task();
		task.setTaskID(taskModel.getTaskID());
		task.setEndDate(taskModel.getEndDate());
		task.setPriority(taskModel.getPriority());
		task.setStartDate(taskModel.getStartDate());
		task.setStatus(taskModel.getIsTaskEnded());
		task.setTask1(taskModel.getTask1());
		taskModel.setIsParentValid(true);
		if (taskModel.getParentalId() != 0) {
			if (parentTaskDao.findById(taskModel.getParentalId()).isPresent()) {
				taskModel.setIsParentValid(true);
				task.setParent(parentTaskDao.findById(taskModel.getParentalId()).get());
			} else {
				parentTask = new ParentTask();
				if (taskMgrDao.findById(taskModel.getParentalId()).isPresent()) {
					parentTask.setParentId(taskModel.getParentalId());
					parentTask.setParentTask(taskMgrDao.findById(taskModel.getParentalId()).get().getTask1());
					parentTaskDao.save(parentTask);
					task.setParent(parentTaskDao.findById(taskModel.getParentalId()).get());
					taskModel.setIsParentValid(true);
				} else {
					taskModel.setIsParentValid(false);
				}
			}
		}

	}
	
	private ModelTask populateModelObj(Task taskObject) 
	{
		resultModel = new ModelTask();
		if (taskObject.getParent() != null) {
			resultModel.setParentalId(taskObject.getParent().getParentId());
		}
		resultModel.setEndDate(taskObject.getEndDate());
		resultModel.setPriority(taskObject.getPriority());
		resultModel.setStartDate(taskObject.getStartDate());
		resultModel.setTask1(taskObject.getTask1());
		resultModel.setTaskID(taskObject.getTaskID());
		resultModel.setIsTaskEnded(taskObject.getStatus());
		
		return resultModel;
	}
	
	public List<ModelTask> populateModelFromEntity(List<Task> taskEntityList) {
		List<ModelTask> modelTaskList = new ArrayList<ModelTask>();
		for (Task task : taskEntityList) {
			ModelTask taskModel = new ModelTask();
			if (task.getParent() != null) {
				taskModel.setParentalId(task.getParent().getParentId());
			}
			taskModel.setEndDate(task.getEndDate());
			taskModel.setStartDate(task.getStartDate());
			taskModel.setPriority(task.getPriority());
			taskModel.setTask1(task.getTask1());
			taskModel.setTaskID(task.getTaskID());
			taskModel.setIsTaskEnded(task.getStatus());
			modelTaskList.add(taskModel);
		}
		return modelTaskList;
	}
	
	public Task populateEntityforUpdate(Task entityTask, Long id) {
		Task existingTaskbyId = taskMgrDao.findById(id).get();
		existingTaskbyId.setEndDate(entityTask.getEndDate());
		existingTaskbyId.setStartDate(entityTask.getStartDate());
		existingTaskbyId.setPriority(entityTask.getPriority());
		existingTaskbyId.setTask1(entityTask.getTask1());
		existingTaskbyId.setTaskID(id);
		existingTaskbyId.setParent(entityTask.getParent());
		existingTaskbyId.setStatus(entityTask.getStatus());
		return existingTaskbyId;
	}
	
	/*
	 * public Task updateTask(Task task,Long id) { // TODO Auto-generated method
	 * stub Task taskToUpdate = taskMgrDao.findById(id).get();
	 * taskToUpdate.setEndDate(task.getEndDate());
	 * taskToUpdate.setStartDate(task.getStartDate());
	 * taskToUpdate.setPriority(task.getPriority());
	 * taskToUpdate.setTask1(task.getTask1()); taskToUpdate.setTaskID(id); if
	 * (task.getParent() != null) { ParentTask parentTask = new ParentTask();
	 * parentTask.setParentId(task.getParent().getParentId());
	 * parentTask.setParentTask(task.getTask1()); parentTaskDao.save(parentTask); }
	 * return taskMgrDao.save(taskToUpdate); }
	 */

	

	@Override
	public ModelTask updateTask(ModelTask modelObj,Long id) {
		log.info("Service Call - updateTask for id :" + id);
		populateEntityFromModel(modelObj);
		//Task existingTaskbyId = populateEntityforUpdate(task,id);
		taskMgrDao.save(task);
		return modelObj;
	} 
	
	@Override
	public ModelTask endTask(ModelTask modelObj) {
		log.info("Service Call - endTask ");
		task = taskMgrDao.findById(modelObj.getTaskID()).get();
		task.setStatus("1");
		//populateEntityFromModel(modelObj);
		taskMgrDao.save(task);
		return modelObj;
		
	} 
	
	@Override
	public void deleteTask(Long taskId) {

		log.info("Service Call - deleteTask for taskId " +taskId);
		taskMgrDao.deleteById(taskId);
	}
	
	@Override
	public ModelTask getTask(Long id) {
		
		resultModel = new ModelTask();
		log.info("Service Call - getTask for taskId " +id);
		resultModel = populateModelObj( taskMgrDao.findById(id).get());
		return resultModel;
	}
	
	@Override
	public List<ModelTask> getAllTasks() {
		// TODO Auto-generated method stub
		List<Task> entityTask = new ArrayList<Task>();
		log.info("Service Call - getAllTasks");
		entityTask = taskMgrDao.findAll();
		List<ModelTask> allTaskList = populateModelFromEntity(entityTask);
		return allTaskList;
	}
	
	@Override
	public ModelTask addTask(ModelTask taskModel) {
		populateEntityFromModel(taskModel);
		log.info("Service Call - addTask");
		if (taskModel.getIsParentValid()) {
			taskMgrDao.save(task);
		}
		return taskModel;
	}
}
