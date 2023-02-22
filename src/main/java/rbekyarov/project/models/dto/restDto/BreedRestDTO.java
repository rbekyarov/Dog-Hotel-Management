package rbekyarov.project.models.dto.restDto;



public class BreedRestDTO {
    private Long id;
    private String breedName;

    public BreedRestDTO() {
    }

    public String getBreedName() {
        return breedName;
    }

    public BreedRestDTO setBreedName(String breedName) {
        this.breedName = breedName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BreedRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
