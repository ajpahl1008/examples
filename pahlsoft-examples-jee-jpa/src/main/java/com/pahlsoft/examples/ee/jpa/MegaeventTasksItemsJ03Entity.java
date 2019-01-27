package com.pahlsoft.examples.ee.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "megaevent_tasks_items_j03", schema = "", catalog = "megaevent")
public class MegaeventTasksItemsJ03Entity {
    private int taskId;
    private String assetName;
    private String assetType;
    private Integer eventId;

    @Basic
    @Column(name = "taskID", nullable = false, insertable = true, updatable = true)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "asset_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Basic
    @Column(name = "asset_type", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Basic
    @Column(name = "eventID", nullable = true, insertable = true, updatable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MegaeventTasksItemsJ03Entity that = (MegaeventTasksItemsJ03Entity) o;

        if (taskId != that.taskId) return false;
        if (assetName != null ? !assetName.equals(that.assetName) : that.assetName != null) return false;
        if (assetType != null ? !assetType.equals(that.assetType) : that.assetType != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (assetName != null ? assetName.hashCode() : 0);
        result = 31 * result + (assetType != null ? assetType.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
