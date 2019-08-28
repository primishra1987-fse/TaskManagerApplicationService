package capsule.sba.task.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.service.TaskService;

@RestController
@RequestMapping("/task")
 @CrossOrigin(origins = "http://localhost:4200") 
public class TaskController {

	@Autowired
	private TaskService taskService;

	
	@RequestMapping(value="/getAllTasks", method=RequestMethod.GET, 
	produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Task>> getAllTask() {
		List<Task> taskList;
		 	
		taskList = taskService.getAllTasks();

		return ResponseEntity.ok().header("Custom-Header", "foo").body(taskList);

	}

	
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST, produces = "application/xml", consumes = {
			"application/json", "application/xml" })
	public String updateTask(@RequestBody Task task) {
		
		taskService.updateTask(task);
		return "TaskUpdated";
	}

	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/xml", consumes = {
			"application/json", "application/xml" })
	public String addTask(@RequestBody Task task) {
		System.out.println("Task1:" + task.getTask1());
		System.out.println("Priority:" + task.getPriority());
		System.out.println("TaskId:" + task.getTaskID());
		System.out.println("EndDate:" + task.getEndDate());
		System.out.println("StartDate:" + task.getStartDate());
		System.out.println("ParentId:" + task.getParentId());
		System.out.println("IsTaskEended:" + task.getIsTaskEended());
		taskService.addTask(task);
		return "";
	}
}
