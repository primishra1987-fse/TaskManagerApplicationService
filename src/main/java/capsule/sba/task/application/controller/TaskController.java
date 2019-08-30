package capsule.sba.task.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value="/getAllTasks", method=RequestMethod.GET, 
	produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Task>> getAllTask() {
		List<Task> taskList;
		 	
		taskList = taskService.getAllTasks();

		return ResponseEntity.ok().header("Custom-Header", "foo").body(taskList);

	}

	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value="/deleteTask/{taskId}", method=RequestMethod.DELETE)
			public void deleteTask(@PathVariable("taskId") Long taskId){
			taskService.deleteTask(taskId);
			
	}
	
	@RequestMapping(value = "/updateTask/{taskId}", method = RequestMethod.PUT, produces = "application/xml", consumes = {
			"application/json", "application/xml" })
	public String updateTask(@RequestBody Task task,@PathVariable("taskId") Long taskId) {
		
		taskService.updateTask(task,taskId);
		return "TaskUpdated";
	}

	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/xml", consumes = {
			"application/json", "application/xml" })
	public String addTask(@RequestBody Task task) {
		System.out.println("parental id -- " +task.getParentalId());
		taskService.addTask(task);
		return "";
	}
	
}
