package server.db.model;

import commonData.UserSend;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@BatchSize(size = 100)
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE, orphanRemoval = true)
    private Set<Message> messages = new HashSet<Message>();

    @ManyToMany(mappedBy = "userList", cascade =  CascadeType.ALL)
    private Set<Group> groupList = new HashSet<Group>();

    public User(UserSend userSend) {
        this.name = userSend.getName();
        this.password = userSend.getPassword();
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Set<Group> groupList) {
        this.groupList = groupList;
    }
}
