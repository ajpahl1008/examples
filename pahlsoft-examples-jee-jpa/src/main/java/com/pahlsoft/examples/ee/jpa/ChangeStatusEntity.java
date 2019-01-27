package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "change_status", schema = "", catalog = "megaevent")
public class ChangeStatusEntity {
    private short changeStatusId;
    private String changeStatus;

    @Id
    @Column(name = "change_statusID", nullable = false, insertable = true, updatable = true)
    public short getChangeStatusId() {
        return changeStatusId;
    }

    public void setChangeStatusId(short changeStatusId) {
        this.changeStatusId = changeStatusId;
    }

    @Basic
    @Column(name = "change_status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeStatusEntity that = (ChangeStatusEntity) o;

        if (changeStatusId != that.changeStatusId) return false;
        if (changeStatus != null ? !changeStatus.equals(that.changeStatus) : that.changeStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) changeStatusId;
        result = 31 * result + (changeStatus != null ? changeStatus.hashCode() : 0);
        return result;
    }
}
