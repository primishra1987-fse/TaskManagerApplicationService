/**
 * 
 */
package capsule.sba.task.application.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;
@Repository
@Entity
@Table(name="Parent_Task")
public class ParentTask {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Parent_Id")
	private Long ParentId;

	@Column (name = "Parent_Task")
	private String parentTask;


	public Long getParentId() {
		return ParentId;
	}

	public void setParentId(Long parentId) {
		ParentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
}
