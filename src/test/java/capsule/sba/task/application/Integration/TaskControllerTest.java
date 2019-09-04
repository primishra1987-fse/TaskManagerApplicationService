package capsule.sba.task.application.Integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import capsule.sba.task.application.dao.TaskManagerDao;
import capsule.sba.task.application.entity.Task;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {

	 @Autowired
	    private MockMvc mockMvc;
	 
	 //private static final ObjectMapper om = new ObjectMapper();

	  @MockBean
	  	private TaskManagerDao mockRepository;
	  
	  private static final ObjectMapper om = new ObjectMapper();
	/*
	 * @Autowired private Task task;
	 */
	  
	  @Before
	  public void init() {
		  
		 
		  
		  Task task1 = new Task();
		  task1.setTaskID(1L);
		  task1.setTask1("TestTask1");
		  
		  Task task2 = new Task();
		  task2.setTaskID(2L);
		  task2.setTask1("TestTask2");
		  
		  List<Task> taskList = Arrays.asList(task1,task2);
		  
		  when(mockRepository.findById(1L)).thenReturn(Optional.of(task1));
		  when(mockRepository.findAll()).thenReturn(taskList);
		  when(mockRepository.save(any(Task.class))).thenReturn(task1);
		  doNothing().when(mockRepository).deleteById(1L);
		  
	  }
	  
	  @Test
	  public void find_getAllTasks_ok() throws Exception {
		  
	
		  mockMvc.perform(get("/task/getAllTasks"))
               .andExpect(status().isOk())
          .andExpect(jsonPath("$.[0].taskID", is(1)))
          .andExpect(jsonPath("$.[0].task1", is("TestTask1")))
          .andExpect(jsonPath("$.[1].taskID", is(2)))
          .andExpect(jsonPath("$.[1].task1", is("TestTask2")));
	  }
	  
	  @Test
	  public void find_getTask_ok() throws Exception {
		  mockMvc.perform(get("/task/getTask/1"))
          .andExpect(status().isOk())
     .andExpect(jsonPath("$.taskID", is(1)))
     .andExpect(jsonPath("$.task1", is("TestTask1")));
    
	  }
	  
	@Test
	public void save_addTask_ok() throws Exception {
		Task task = new Task();
		task.setTaskID(1L);
		task.setTask1("TestTask");
		
		mockMvc.perform(post("/task/addTask").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskID", is(1)))
				.andExpect(jsonPath("$.task1", is("TestTask")));
	}
	
	@Test
	public void save_deleteTask_ok() throws Exception {
		/*
		 * Task task = new Task(); task.setTaskID(1L); task.setTask1("TestTask");
		 */
		mockMvc.perform(delete("/task/deleteTask/1")).andExpect(status().isOk());
	}
	
	@Test
	public void save_updateTask_ok() throws Exception {
		Task task = new Task();
		task.setTaskID(1L);
		task.setTask1("TestTask");
		
		mockMvc.perform(put("/task/updateTask/1").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskID", is(1)))
				.andExpect(jsonPath("$.task1", is("TestTask")));
		
	}
	
	@Test
	public void save_endTask_ok() throws Exception{
		Task task = new Task();
		task.setTaskID(1L);
		task.setTask1("TestTask");
		
		mockMvc.perform(put("/task/endTask").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskID", is(1)))
				.andExpect(jsonPath("$.task1", is("TestTask")));
	}
	  }
	  
