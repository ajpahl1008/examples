package com.pahlsoft.examples.ee.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by aj on 3/19/2014.
 */
@Entity
@Table(name = "megaevent_events_j01", schema = "", catalog = "megaevent")
public class MegaeventEventsJ01Entity {
    private short eventId;
    private String title;
    private String description;
    private String status;

    @Basic
    @Column(name = "eventID", nullable = false, insertable = true, updatable = true)
    public short getEventId() {
        return eventId;
    }

    public void setEventId(short eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 32672)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, insertable = true, updatable = true, length = 32672)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MegaeventEventsJ01Entity that = (MegaeventEventsJ01Entity) o;

        if (eventId != that.eventId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) eventId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
