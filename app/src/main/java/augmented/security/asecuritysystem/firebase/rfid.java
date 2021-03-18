package augmented.security.asecuritysystem.firebase;

public class rfid {
    public long id;
    public String info;
    public Integer timestamp;

    public rfid(){

    }

    public rfid(long id, String info, Integer timestamp)
    {
        this.id = id;
        this.info = info;
        this.timestamp = timestamp;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
