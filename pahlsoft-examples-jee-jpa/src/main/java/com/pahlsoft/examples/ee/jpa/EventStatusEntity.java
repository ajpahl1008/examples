package com.pahlsoft.examples.ee.jpa;

import javax.persistence.*;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "event_status", schema = "", catalog = "megaevent")
public class EventStatusEntity {
    private short eventStatusId;
    private String eventStatus;

    @Id
    @Column(name = "event_statusID", nullable = false, insertable = true, updatable = true)
    public short getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(short eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    @Basic
    @Column(name = "event_status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventStatusEntity that = (EventStatusEntity) o;

        if (eventStatusId != that.eventStatusId) return false;
        if (eventStatus != null ? !eventStatus.equals(that.eventStatus) : that.eventStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) eventStatusId;
        result = 31 * result + (eventStatus != null ? eventStatus.hashCode() : 0);
        return result;
    }
}
