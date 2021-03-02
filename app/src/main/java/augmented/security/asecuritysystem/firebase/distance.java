package augmented.security.asecuritysystem.firebase;

public class distance {
    public String range;
    public Integer timestamp;

    public distance(){

    }

    public distance(String range, Integer timestamp){
        this.range = range;
        this.timestamp = timestamp;
    }

    public String getRange() {
        return range;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "distance{" +
                "range='" + range + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
