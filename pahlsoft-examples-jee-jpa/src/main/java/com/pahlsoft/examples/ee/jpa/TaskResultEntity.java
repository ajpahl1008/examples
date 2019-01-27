package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "task_result", schema = "", catalog = "megaevent")
public class TaskResultEntity {
    private int taskResultId;
    private String taskResult;

    @Id
    @Column(name = "task_resultID", nullable = false, insertable = true, updatable = true)
    public int getTaskResultId() {
        return taskResultId;
    }

    public void setTaskResultId(int taskResultId) {
        this.taskResultId = taskResultId;
    }

    @Basic
    @Column(name = "task_result", nullable = true, insertable = true, updatable = true, length = 45)
    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskResultEntity that = (TaskResultEntity) o;

        if (taskResultId != that.taskResultId) return false;
        if (taskResult != null ? !taskResult.equals(that.taskResult) : that.taskResult != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskResultId;
        result = 31 * result + (taskResult != null ? taskResult.hashCode() : 0);
        return result;
    }
}
