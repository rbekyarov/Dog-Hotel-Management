package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {
    private String code;
    private String name;
    private User author;
    public City() {
    }

    public City(String code, String name, User author) {
        this.code = code;
        this.name = name;
        this.author = author;
    }

    @Column(nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
   @Column(nullable = false)
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
