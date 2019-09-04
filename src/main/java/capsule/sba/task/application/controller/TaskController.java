package capsule.sba.task.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import capsule.sba.task.application.entity.Task;
import capsule.sba.task.application.model.ModelTask;
import capsule.sba.task.application.service.TaskService;

@RestController
@RequestMapping("/task")
 @CrossOrigin(origins = "http://localhost:4200") 
public class TaskController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskService taskService;

	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value="/getAllTasks", method=RequestMethod.GET, 
	produces = { "application/json", "application/xml" })
	public ResponseEntity<List<ModelTask>> getAllTask() {
		List<ModelTask> taskList;
		log.info("Controller - getAllTasks call");
		taskList = taskService.getAllTasks();
		return ResponseEntity.ok().header("Custom-Header", "foo").body(taskList);
	}

	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value="/getTask/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<ModelTask> getTask(@PathVariable("taskId") Long taskId) {
		ModelTask getTask = new ModelTask();
		log.info("Controller - getTask call for taskId : " +taskId);
		getTask = taskService.getTask(taskId);
		return ResponseEntity.ok().header("Custom-Header", "getTaskById").body(getTask);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = {"application/json"}, consumes = {
			"application/json" })
	public ModelTask addTask(@RequestBody @Valid ModelTask taskModel) {

		try {
			log.info("Controller - addTask call");
			taskModel = taskService.addTask(taskModel);
		} catch (Exception e) {
			log.error("Controller - addTask call returned error : "+e.getMessage());
			e.printStackTrace();
			return taskModel;
		}
		return taskModel;

	}
	
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value="/deleteTask/{taskId}", method=RequestMethod.DELETE)
			public void deleteTask(@PathVariable("taskId") Long taskId){
		try {
			log.info("Controller - deleteTask call for taskId : " +taskId);
			taskService.deleteTask(taskId);
		}
		catch (Exception e) {
			log.error("Controller - deleteTask call returned error : " +e.getMessage());
			e.printStackTrace();
		}
			
	}
	
	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/updateTask/{taskId}", method = RequestMethod.PUT, produces = "application/json", consumes = {
			"application/json" })
	public ModelTask updateTask(@RequestBody ModelTask taskModel,@PathVariable("taskId") Long taskId) {
		try{
			log.info("Controller - updateTask call for taskId : " +taskId);
			taskService.updateTask(taskModel,taskId);
		}
		catch (Exception e) {
			log.error(" Controller - updateTask call returned error : "+e.getMessage());
			e.printStackTrace();
			return taskModel;
		}
		return taskModel;
	}


	@CrossOrigin(origins = "http://localhost:4200") 
	@RequestMapping(value = "/endTask", method = RequestMethod.PUT, produces = "application/json", consumes = {
			"application/json"})
	public ModelTask endTask(@RequestBody ModelTask taskModel) {
		try{
			log.info("Controller - endTask call");
			taskService.endTask(taskModel);
		}
		catch (Exception e) {
			log.error("Controller - endTask call returned error : "+e.getMessage());
			e.printStackTrace();
			return taskModel;
		}
		return taskModel;
	}
	
	
}
