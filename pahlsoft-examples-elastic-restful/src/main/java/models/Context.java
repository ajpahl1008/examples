
package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Context {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("custom")
    @Expose
    private Custom custom;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Context withUser(User user) {
        this.user = user;
        return this;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Context withTags(Tags tags) {
        this.tags = tags;
        return this;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public Context withCustom(Custom custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).append("tags", tags).append("custom", custom).toString();
    }

}
