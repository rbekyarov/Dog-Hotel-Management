package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    private Long id;
    private String username;
    private String password;

    public UserLoginDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
