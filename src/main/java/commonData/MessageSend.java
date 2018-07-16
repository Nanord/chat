package commonData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MessageSend implements Serializable {
    private UserSend user;

    private String commandText;

    private String data;

    private String nameGroup;

    private String time;

    public MessageSend(UserSend user, String commandText, String data, String nameGroup) {
        this.user = user;
        this.commandText = commandText;
        this.data = data;
        this.nameGroup = nameGroup;

        Calendar calendar = new GregorianCalendar();
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if(day.length() == 1) {
            day = "0"+day;
        }
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if(month.length() == 1) {
            month = "0"+month;
        }
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if(hour.length() == 1) {
            hour = "0"+hour;
        }
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if(minute.length() == 1) {
            minute = "0"+minute;
        }
        time = day + "." + month + " " + hour + ":" + minute;
    }

    public MessageSend(UserSend user, String commandText, String data, String nameGroup, String time) {
        this.user = user;
        this.commandText = commandText;
        this.data = data;
        this.nameGroup = nameGroup;
        this.time = time;
    }


    public UserSend getUser() {
        return user;
    }

    public void setUser(UserSend user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }


    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
