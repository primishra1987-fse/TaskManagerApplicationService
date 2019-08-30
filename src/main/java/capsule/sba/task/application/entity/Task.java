package capsule.sba.task.application.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.SerializationFeature;

@Repository
@Entity
@Table(name = "Task")

public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private long taskID;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "parentId", nullable = true)
	@Nullable
    private ParentTask parent;
	 
	
	public ParentTask getParent() {
		return parent;
	}

	public void setParent(ParentTask parent) {
		this.parent = parent;
	}
@Transient

	private Long parentalId;
	

	public Long getParentalId() {
		return parentalId;
	}

	public void setParentalId(Long parentalId) {
		this.parentalId = parentalId;
	}

	@Column(name = "task")
	private String task1;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "status")
	private Boolean isTaskEended;

	
	/*
	 * private long parentid;
	 * 
	 * 
	 * public long getParentid() { return parentid; }
	 * 
	 * public void setParentid(long parentid) { this.parentid = parentid; }
	 */
	public long getTaskID() {
		return taskID;
	}

	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}

	
	public String getTask1() {
		return task1;
	}

	public void setTask1(String task1) {
		this.task1 = task1;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Boolean getIsTaskEended() {
		return isTaskEended;
	}

	public void setIsTaskEended(Boolean isTaskEended) {
		/*
		 * Date today = new Date();
		 * 
		 * if (today.after(this.endDate)) { isTaskEended = true; }
		 */
		this.isTaskEended = isTaskEended;
	}

}
