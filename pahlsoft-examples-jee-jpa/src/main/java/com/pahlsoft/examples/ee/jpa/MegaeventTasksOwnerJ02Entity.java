package com.pahlsoft.examples.ee.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "megaevent_tasks_owner_j02", schema = "", catalog = "megaevent")
public class MegaeventTasksOwnerJ02Entity {
    private int taskId;
    private String taskName;
    private String taskStatus;
    private String taskResult;
    private String eventTitle;
    private String role;
    private String description;
    private String changeControl;
    private String changeStatus;
    private String firstName;
    private String lastName;

    @Basic
    @Column(name = "taskID", nullable = false, insertable = true, updatable = true)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "task_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "task_status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Basic
    @Column(name = "task_result", nullable = true, insertable = true, updatable = true, length = 45)
    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    @Basic
    @Column(name = "event_title", nullable = false, insertable = true, updatable = true, length = 32672)
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Basic
    @Column(name = "role", nullable = false, insertable = true, updatable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
    @Column(name = "change_status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MegaeventTasksOwnerJ02Entity that = (MegaeventTasksOwnerJ02Entity) o;

        if (taskId != that.taskId) return false;
        if (changeControl != null ? !changeControl.equals(that.changeControl) : that.changeControl != null)
            return false;
        if (changeStatus != null ? !changeStatus.equals(that.changeStatus) : that.changeStatus != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (eventTitle != null ? !eventTitle.equals(that.eventTitle) : that.eventTitle != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (taskResult != null ? !taskResult.equals(that.taskResult) : that.taskResult != null) return false;
        if (taskStatus != null ? !taskStatus.equals(that.taskStatus) : that.taskStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        result = 31 * result + (taskResult != null ? taskResult.hashCode() : 0);
        result = 31 * result + (eventTitle != null ? eventTitle.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (changeControl != null ? changeControl.hashCode() : 0);
        result = 31 * result + (changeStatus != null ? changeStatus.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
