
package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class System {

    @SerializedName("hostname")
    @Expose
    private String hostname;
    @SerializedName("architecture")
    @Expose
    private String architecture;
    @SerializedName("platform")
    @Expose
    private String platform;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public System withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public System withArchitecture(String architecture) {
        this.architecture = architecture;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public System withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("hostname", hostname).append("architecture", architecture).append("platform", platform).toString();
    }

}
