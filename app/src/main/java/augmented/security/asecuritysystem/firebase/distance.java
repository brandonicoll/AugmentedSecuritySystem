package augmented.security.asecuritysystem.firebase;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

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
    public String getDate(Integer timestamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000L);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return date;
    }

}
