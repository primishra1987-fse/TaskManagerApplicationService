package capsule.sba.task.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import capsule.sba.task.application.controller.TaskController;



@SpringBootApplication


public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TaskManagerApplication.class);
    }

}
