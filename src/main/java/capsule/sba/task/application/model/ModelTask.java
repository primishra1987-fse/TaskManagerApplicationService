package capsule.sba.task.application.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ModelTask {

	private long parentalId;
	private long taskID;
	private String task1;
	private Date startDate;
	private Date endDate;
	private int priority;
	private String isTaskEnded;
	
	
	public String getIsTaskEnded() {
		return isTaskEnded;
	}
	public void setIsTaskEnded(String isTaskEnded) {
		this.isTaskEnded = isTaskEnded;
	}
	private Boolean isParentValid;
	public Boolean getIsParentValid() {
		return isParentValid;
	}
	public void setIsParentValid(Boolean isParentValid) {
		this.isParentValid = isParentValid;
	}
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
	
	public long getParentalId() {
		return parentalId;
	}
	public void setParentalId(long parentalId) {
		this.parentalId = parentalId;
	}
	
	
	
}
