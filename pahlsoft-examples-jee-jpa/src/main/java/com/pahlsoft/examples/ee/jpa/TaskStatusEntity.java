package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "task_status", schema = "", catalog = "megaevent")
public class TaskStatusEntity {
    private short taskStatusId;
    private String taskStatus;

    @Id
    @Column(name = "task_statusID", nullable = false, insertable = true, updatable = true)
    public short getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(short taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    @Basic
    @Column(name = "task_status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskStatusEntity that = (TaskStatusEntity) o;

        if (taskStatusId != that.taskStatusId) return false;
        if (taskStatus != null ? !taskStatus.equals(that.taskStatus) : that.taskStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) taskStatusId;
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        return result;
    }
}
