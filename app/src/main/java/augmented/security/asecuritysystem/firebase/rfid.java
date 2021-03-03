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
}
