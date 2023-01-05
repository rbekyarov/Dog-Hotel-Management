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
    private final CompanyRepository companyRepository;

    public InitData(UserRepository userRepository, BehaviorRepository behaviorRepository, BreedRepository breedRepository, CellRepository cellRepository, CityRepository cityRepository, PriceRepository priceRepository, ClientRepository clientRepository,
                    DogRepository dogRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.behaviorRepository = behaviorRepository;
        this.breedRepository = breedRepository;
        this.cellRepository = cellRepository;
        this.cityRepository = cityRepository;
        this.priceRepository = priceRepository;
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //ADD USER
        if (userRepository.findAll().size() == 0) {
            userRepository.save(new User("admin", "admin", Role.ADMIN));
            userRepository.save(new User("user", "user", Role.USER));
        }
        //ADD Dog Behavior
        if (behaviorRepository.findAll().size() == 0) {
            behaviorRepository.save(new Behavior("Good with dogs", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Good with dogs and humans", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Good with humans", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with dogs", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with dogs and humans", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with humans", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Cowardly dog", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));

        }
        //ADD Dog Breed
        if (breedRepository.findAll().size() == 0) {
            breedRepository.save(new Breed("Boxer", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("German Shepherd", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Rottweiler", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Bulgarian Shepherd - BOK", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Miniature Schnauzer", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Husky", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));

        }
        //ADD Dog House
        if (cellRepository.findAll().size() == 0) {
            cellRepository.save(new Cell("A1", Status.empty, userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("A2", Status.busy, userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("A3", Status.empty, userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("B1", Status.empty, userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("B2", Status.busy, userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("B3", Status.empty, userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("C1", Status.empty, userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("C2", Status.empty, userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));

        }
        //ADD Cities
        if (cityRepository.findAll().size() == 0) {
            cityRepository.save(new City("1000", "Sofia", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("2000", "Plovdiv", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("6000", "Stara Zagora", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("9000", "Varna", userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("7008", "Ruse", userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));

        }
        //ADD Company
        if (companyRepository.findAll().size() == 0) {
            companyRepository.save(new Company("DHM Ltd", "Bulgaria", cityRepository.getById(Long.parseLong("2")), "Tzar Simeon Veliki 1","BG030298796","office@dhm.bg", "BG18RZBB91550123456789", "Ivan Petrov" ));
        }
        //ADD PRICES
        if (priceRepository.findAll().size() == 0) {
            priceRepository.save(new Price(
                    BigDecimal.valueOf(15.00),
                    BigDecimal.valueOf(9.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    LocalDate.of(2023, 1, 1)));
            priceRepository.save(new Price(
                    BigDecimal.valueOf(20.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(8.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    LocalDate.of(2023, 1, 2)));


        }
        //ADD CLIENT
        if (clientRepository.findAll().size() == 0) {
            clientRepository.save(new Client("Ivan", "Ivanov", "ivanov@gmail.com", "0886335241", "Lulin 15", cityRepository.getById(Long.parseLong("1")), userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Stanimir", "Pavlov", "spavlov@abv.bg", "08842276361", "Zdravetzh 4", cityRepository.getById(Long.parseLong("2")), userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Vladimir", "Georgiev", "vgeorgiev@outlook.com", "0887325579", "Zagorka 16", cityRepository.getById(Long.parseLong("3")), userRepository.getById(Long.parseLong("1")),LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Boyan", "Dimitrov", "bdimitrov@hotmail.com", "0881256377", "Ludogorska 12B", cityRepository.getById(Long.parseLong("4")), userRepository.getById(Long.parseLong("2")),LocalDate.of(2023, 1, 1)));


        }
        //ADD DOG
        if (dogRepository.findAll().size() == 0) {
            //New Dog "Jerry"
            dogRepository.save(new Dog("Jerry",
                    LocalDate.of(2018, 7, 15),
                    "jerry.jpg",
                    30,
                    breedRepository.getById(Long.parseLong("1")),
                    Sex.M,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("1")),
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog "Berta"
            dogRepository.save(new Dog("Berta",
                    LocalDate.of(2019, 3, 26),
                    "Berta.jpg",
                    33,
                    breedRepository.getById(Long.parseLong("3")),
                    Sex.F,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("5")),
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog "Boby"
            dogRepository.save(new Dog("Boby",
                    LocalDate.of(2017, 6, 10),
                    "bok.jpg",
                    40,
                    breedRepository.getById(Long.parseLong("4")),
                    Sex.M,
                    Passport.NO,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("1")),
                    behaviorRepository.getById(Long.parseLong("2")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog Cezar
            dogRepository.save(new Dog("Cezar",
                    LocalDate.of(2020, 4, 16),
                    "cezar.jpg",
                    35,
                    breedRepository.getById(Long.parseLong("2")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("3")),
                    behaviorRepository.getById(Long.parseLong("4")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog "Zara"
            dogRepository.save(new Dog("Zara",
                    LocalDate.of(2020, 4, 16),
                    "Zara.jpg",
                    35,
                    breedRepository.getById(Long.parseLong("1")),
                    Sex.F,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("2")),
                    behaviorRepository.getById(Long.parseLong("7")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog "Benji"
            dogRepository.save(new Dog("Benji",
                    LocalDate.of(2020, 5, 28),
                    "benji.jpg",
                    10,
                    breedRepository.getById(Long.parseLong("5")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("2")),
                    behaviorRepository.getById(Long.parseLong("1")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1)));
            //New Dog "Lora"
            dogRepository.save(new Dog("Lora",
                    LocalDate.of(2016, 2, 7),
                    "Lora.jpg",
                    25,
                    breedRepository.getById(Long.parseLong("6")),
                    Sex.F,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("4")),
                    behaviorRepository.getById(Long.parseLong("5")),
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 1)));

        }
    }
}
