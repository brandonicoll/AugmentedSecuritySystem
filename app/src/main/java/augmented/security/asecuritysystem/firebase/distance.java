package augmented.security.asecuritysystem.firebase;

public class distance {
    public long range;
    public Integer timestamp;

    public distance() {

    }


    public distance(long range,Integer timestamp)
    {
        this.range = range;
        this.timestamp = timestamp;


    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
