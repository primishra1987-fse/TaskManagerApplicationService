package capsule.sba.task.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import capsule.sba.task.application.entity.ParentTask;

public interface ParentTaskDao extends JpaRepository<ParentTask, Long> {

}
