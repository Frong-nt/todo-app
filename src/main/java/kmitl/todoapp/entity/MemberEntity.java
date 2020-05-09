package kmitl.todoapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "member")
public class MemberEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="member_id")
    private Integer id;

    @Column(nullable=false, name = "fname")
    private String fname;
    @Column(nullable=false, name = "lname")
    private String lname;
    @Column(name = "image_URL")
    private String imageURL;
    @Column(nullable=false)
    private String email;
    @Column(name = "password")
    private String password;

    // seft join
    @JoinTable(name = "friend", joinColumns = {
            @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "friend_id", referencedColumnName = "member_id", nullable = false)})
    @ManyToMany
    private Collection<MemberEntity> friendCollection;

    @OneToMany(
            mappedBy = "member"
    )
    private Collection<TaskEntity> taskCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String name) {
        this.fname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public Collection<MemberEntity> getFriendCollection() {
        return friendCollection;
    }

    public void setFriendCollection(Collection<MemberEntity> friendCollection) {
        this.friendCollection = friendCollection;
    }
    public void addFriendCollection(MemberEntity friendCollection) {
        this.friendCollection.add(friendCollection);
    }
    public void delelteFriendCollection(MemberEntity friendCollection) {
        this.friendCollection.remove(friendCollection);
    }
    // task
    public void addTaskCollection(TaskEntity task) {
        this.taskCollection.add(task);
    }
    public void delelteTaskCollection(TaskEntity task) {
        this.taskCollection.remove(task);
    }
}

