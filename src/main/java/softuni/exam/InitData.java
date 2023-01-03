package softuni.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.*;
import softuni.exam.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
            breedRepository.save(new Breed("German Shepherd"));
            breedRepository.save(new Breed("Rotwailer"));
            breedRepository.save(new Breed("Bulgarian Shepherd - BOK"));
            breedRepository.save(new Breed("Miniature Schnauzer"));

        }
        if (cellRepository.findAll().size() == 0) {
            cellRepository.save(new Cell("A1", Status.empty));
            cellRepository.save(new Cell("A2", Status.busy));
            cellRepository.save(new Cell("A3", Status.empty));
            cellRepository.save(new Cell("B1", Status.empty));
            cellRepository.save(new Cell("B2", Status.busy));
            cellRepository.save(new Cell("B3", Status.empty));

        }
        if (cityRepository.findAll().size() == 0) {
            cityRepository.save(new City("1000", "Sofia"));
            cityRepository.save(new City("2000", "Plovdiv"));
            cityRepository.save(new City("6000", "Stara Zagora"));
            cityRepository.save(new City("9000", "Varna"));
            cityRepository.save(new City("7008", "Ruse"));

        }
        if (priceRepository.findAll().size() == 0) {
            priceRepository.save(new Price(BigDecimal.valueOf(20.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(8.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00)));


        }
        if (clientRepository.findAll().size() == 0) {
            clientRepository.save(new Client("Ivan", "Ivanov", "ivanov@gmail.com", "0886335241", "Lulin 15", cityRepository.getById(Long.parseLong("1"))));
            clientRepository.save(new Client("Stanimir", "Pavlov", "spavlov@abv.bg", "08842276361", "Zdravetz 4", cityRepository.getById(Long.parseLong("2"))));
            clientRepository.save(new Client("Vladimir", "Georgiev", "vgeorgiev@outlook.com", "0887325579", "Zagorka 16", cityRepository.getById(Long.parseLong("3"))));


        }

        if (dogRepository.findAll().size() == 0) {
            dogRepository.save(new Dog("Jerry",
                    LocalDate.of(2018, 7, 15),
                    "jerry.jpg",
                    30,
                    breedRepository.getById(Long.parseLong("1")),
                    Sex.M,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("1"))));

            dogRepository.save(new Dog("Berta",
                    LocalDate.of(2019, 3, 26),
                    "Berta.jpg",
                    33,
                    breedRepository.getById(Long.parseLong("3")),
                    Sex.F,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("5"))));

            dogRepository.save(new Dog("Boby",
                    LocalDate.of(2017, 6, 10),
                    "bok.jpg",
                    40,
                    breedRepository.getById(Long.parseLong("4")),
                    Sex.M,
                    Passport.NO,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("2"))));

            dogRepository.save(new Dog("Cezar",
                    LocalDate.of(2020, 4, 16),
                    "cezar.jpg",
                    35,
                    breedRepository.getById(Long.parseLong("2")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("3")),
                    behaviorRepository.getById(Long.parseLong("4"))));

            dogRepository.save(new Dog("Zara",
                    LocalDate.of(2020, 4, 16),
                    "Zara.jpg",
                    35,
                    breedRepository.getById(Long.parseLong("1")),
                    Sex.F,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("2")),
                    behaviorRepository.getById(Long.parseLong("7"))));

            dogRepository.save(new Dog("Benji",
                    LocalDate.of(2020, 5, 28),
                    "benji.jpg",
                    10,
                    breedRepository.getById(Long.parseLong("5")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("2")),
                    behaviorRepository.getById(Long.parseLong("1"))));

        }
    }
}
