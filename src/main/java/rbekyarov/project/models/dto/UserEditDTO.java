package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.enums.Role;

public class UserEditDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public UserEditDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
