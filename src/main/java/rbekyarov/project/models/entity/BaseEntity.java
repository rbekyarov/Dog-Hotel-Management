package rbekyarov.project.models.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public BaseEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }
}

