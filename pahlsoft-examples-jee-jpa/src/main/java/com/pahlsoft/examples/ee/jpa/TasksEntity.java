package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "tasks", schema = "", catalog = "megaevent")
public class TasksEntity {
    private int taskId;
    private String name;
    private Integer taskStatus;
    private Integer taskResult;
    private Integer dependencyTaskId;
    private Integer eventId;
    private Integer ownerId;
    private Integer activatorId;
    private Integer validatorId;
    private Integer role;
    private String description;
    private String changeControl;
    private Integer changeStatus;
    private Integer targetedItemId;

    @Id
    @Column(name = "taskID", nullable = false, insertable = true, updatable = true)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "task_status", nullable = true, insertable = true, updatable = true)
    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Basic
    @Column(name = "task_result", nullable = true, insertable = true, updatable = true)
    public Integer getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(Integer taskResult) {
        this.taskResult = taskResult;
    }

    @Basic
    @Column(name = "dependency_taskID", nullable = true, insertable = true, updatable = true)
    public Integer getDependencyTaskId() {
        return dependencyTaskId;
    }

    public void setDependencyTaskId(Integer dependencyTaskId) {
        this.dependencyTaskId = dependencyTaskId;
    }

    @Basic
    @Column(name = "eventID", nullable = true, insertable = true, updatable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "ownerID", nullable = true, insertable = true, updatable = true)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "activatorID", nullable = true, insertable = true, updatable = true)
    public Integer getActivatorId() {
        return activatorId;
    }

    public void setActivatorId(Integer activatorId) {
        this.activatorId = activatorId;
    }

    @Basic
    @Column(name = "validatorID", nullable = true, insertable = true, updatable = true)
    public Integer getValidatorId() {
        return validatorId;
    }

    public void setValidatorId(Integer validatorId) {
        this.validatorId = validatorId;
    }

    @Basic
    @Column(name = "role", nullable = true, insertable = true, updatable = true)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 32672)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "change_control", nullable = true, insertable = true, updatable = true, length = 45)
    public String getChangeControl() {
        return changeControl;
    }

    public void setChangeControl(String changeControl) {
        this.changeControl = changeControl;
    }

    @Basic
    @Column(name = "change_status", nullable = true, insertable = true, updatable = true)
    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    @Basic
    @Column(name = "targeted_itemID", nullable = true, insertable = true, updatable = true)
    public Integer getTargetedItemId() {
        return targetedItemId;
    }

    public void setTargetedItemId(Integer targetedItemId) {
        this.targetedItemId = targetedItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TasksEntity that = (TasksEntity) o;

        if (taskId != that.taskId) return false;
        if (activatorId != null ? !activatorId.equals(that.activatorId) : that.activatorId != null) return false;
        if (changeControl != null ? !changeControl.equals(that.changeControl) : that.changeControl != null)
            return false;
        if (changeStatus != null ? !changeStatus.equals(that.changeStatus) : that.changeStatus != null) return false;
        if (dependencyTaskId != null ? !dependencyTaskId.equals(that.dependencyTaskId) : that.dependencyTaskId != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (targetedItemId != null ? !targetedItemId.equals(that.targetedItemId) : that.targetedItemId != null)
            return false;
        if (taskResult != null ? !taskResult.equals(that.taskResult) : that.taskResult != null) return false;
        if (taskStatus != null ? !taskStatus.equals(that.taskStatus) : that.taskStatus != null) return false;
        if (validatorId != null ? !validatorId.equals(that.validatorId) : that.validatorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        result = 31 * result + (taskResult != null ? taskResult.hashCode() : 0);
        result = 31 * result + (dependencyTaskId != null ? dependencyTaskId.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (activatorId != null ? activatorId.hashCode() : 0);
        result = 31 * result + (validatorId != null ? validatorId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (changeControl != null ? changeControl.hashCode() : 0);
        result = 31 * result + (changeStatus != null ? changeStatus.hashCode() : 0);
        result = 31 * result + (targetedItemId != null ? targetedItemId.hashCode() : 0);
        return result;
    }
}
