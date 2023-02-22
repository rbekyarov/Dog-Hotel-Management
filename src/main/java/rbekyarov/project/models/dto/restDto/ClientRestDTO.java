package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.enums.ClientType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class ClientRestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private ClientType clientType;
    private CityRestThinDTO city;

    private Set<DogRestThinDTO> dogs;

    public ClientRestDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityRestThinDTO getCity() {
        return city;
    }

    public ClientRestDTO setCity(CityRestThinDTO city) {
        this.city = city;
        return this;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Long getId() {
        return id;
    }

    public ClientRestDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<DogRestThinDTO> getDogs() {
        return dogs;
    }

    public ClientRestDTO setDogs(Set<DogRestThinDTO> dogs) {
        this.dogs = dogs;
        return this;
    }
}
