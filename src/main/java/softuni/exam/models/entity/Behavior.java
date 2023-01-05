package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "behaviors")
public class Behavior extends BaseEntity{

    private String name;

    private User author;
    public Behavior() {
    }

    public Behavior(String name, User author) {
        this.name = name;
        this.author = author;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
