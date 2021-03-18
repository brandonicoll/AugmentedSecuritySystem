package augmented.security.asecuritysystem.firebase;

public class data{

    public String fire;
    public Integer timestamp;

    public data() {

    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public data(String fire, Integer timestamp)
    {
        this.fire = fire;
        this.timestamp = timestamp;


    }


}
