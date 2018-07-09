package commonData;

import javax.persistence.*;
import java.io.Serializable;

public class UserSend  implements Serializable {
    private int id;

    private String name;

    private String password;

    public UserSend(String name, String password, int id) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserSend(String name, String password) {
        this(name, password, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
