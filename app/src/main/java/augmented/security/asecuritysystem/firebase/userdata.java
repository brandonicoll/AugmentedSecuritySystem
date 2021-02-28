package augmented.security.asecuritysystem.firebase;

public class userdata {

    public String range;
    public Integer timestamp;

    public userdata(String range,Integer timestamp)
    {
        this.range = range;
        this.timestamp = timestamp;


    }

    public String getRange() {
        return range;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
