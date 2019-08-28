package capsule.sba.task.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capsule.sba.task.application.entity.Task;

public interface TaskManagerDao extends JpaRepository<Task, Long> {

}
