package softuni.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.Role;
import softuni.exam.models.entity.enums.Status;
import softuni.exam.repository.*;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class InitData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BehaviorRepository behaviorRepository;
    private final BreedRepository breedRepository;
    private final CellRepository cellRepository;
    private final CityRepository cityRepository;
    private final PriceRepository priceRepository;
    private final ClientRepository clientRepository;
    private final DogRepository dogRepository;

    public InitData(UserRepository userRepository, BehaviorRepository behaviorRepository, BreedRepository breedRepository, CellRepository cellRepository, CityRepository cityRepository, PriceRepository priceRepository, ClientRepository clientRepository,
                    DogRepository dogRepository) {
        this.userRepository = userRepository;
        this.behaviorRepository = behaviorRepository;
        this.breedRepository = breedRepository;
        this.cellRepository = cellRepository;
        this.cityRepository = cityRepository;
        this.priceRepository = priceRepository;
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size() == 0) {
            userRepository.save(new User("admin", "admin", Role.ADMIN));
            userRepository.save(new User("user", "user", Role.USER));
        }
        if (behaviorRepository.findAll().size() == 0) {
            behaviorRepository.save(new Behavior("Good with dogs"));
            behaviorRepository.save(new Behavior("Good with dogs and humans"));
            behaviorRepository.save(new Behavior("Good with humans"));
            behaviorRepository.save(new Behavior("Bad with dogs"));
            behaviorRepository.save(new Behavior("Bad with dogs and humans"));
            behaviorRepository.save(new Behavior("Bad with humans"));
            behaviorRepository.save(new Behavior("Cowardly dog"));

        }
        if (breedRepository.findAll().size() == 0) {
            breedRepository.save(new Breed("Boxer"));
            breedRepository.save(new Breed("German Shepherd "));

        }
        if (cellRepository.findAll().size() == 0) {
            cellRepository.save(new Cell("A1", Status.empty));
            cellRepository.save(new Cell("A2", Status.busy));
            cellRepository.save(new Cell("A3", Status.empty));

        }
        if (cityRepository.findAll().size() == 0) {
            cityRepository.save(new City("1000","Sofia"));
            cityRepository.save(new City("2000","Plovdiv"));
            cityRepository.save(new City("6000","Stara Zagora"));

        }
        if (priceRepository.findAll().size() == 0) {
            priceRepository.save(new Price(BigDecimal.valueOf(20.00),
                                           BigDecimal.valueOf(10.00),
                                           BigDecimal.valueOf(5.00),
                                           BigDecimal.valueOf(5.00),
                                           BigDecimal.valueOf(5.00),
                                           BigDecimal.valueOf(5.00),
                                           BigDecimal.valueOf(5.00),
                                           BigDecimal.valueOf(5.00)));


        }
        if (clientRepository.findAll().size() == 0) {
           clientRepository.save(new Client("Ivan", "Ivanov", "ivanov@gmail.com", "0886335241", "Lulin 15", cityRepository.getById(Long.parseLong("1"))));
           clientRepository.save(new Client("Stanimir", "Pavlov", "spavlov@abv.bg", "08842276361", "Zdravetz 4", cityRepository.getById(Long.parseLong("2"))));
           clientRepository.save(new Client("Vladimir", "Georgiev", "vgeorgiev@outlook.com", "0887325579", "Zagorka 16", cityRepository.getById(Long.parseLong("3"))));



        }

    }
}
