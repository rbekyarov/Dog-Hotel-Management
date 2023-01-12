package rbekyarov.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rbekyarov.project.models.entity.*;
import rbekyarov.project.models.entity.enums.*;
import rbekyarov.project.repository.*;

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
    private final VendorRepository vendorRepository;
    private final CostRepository costRepository;
    private final ReservationRepository reservationRepository;
    private final InvoiceRepository invoiceRepository;

    public InitData(UserRepository userRepository, BehaviorRepository behaviorRepository, BreedRepository breedRepository, CellRepository cellRepository, CityRepository cityRepository, PriceRepository priceRepository, ClientRepository clientRepository,
                    DogRepository dogRepository, CompanyRepository companyRepository, VendorRepository vendorRepository, CostRepository costRepository, ReservationRepository reservationRepository, InvoiceRepository invoiceRepository) {
        this.userRepository = userRepository;
        this.behaviorRepository = behaviorRepository;
        this.breedRepository = breedRepository;
        this.cellRepository = cellRepository;
        this.cityRepository = cityRepository;
        this.priceRepository = priceRepository;
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
        this.companyRepository = companyRepository;
        this.vendorRepository = vendorRepository;
        this.costRepository = costRepository;
        this.reservationRepository = reservationRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //ADD USER
        if (userRepository.findAll().size() == 0) {
            userRepository.save(new User("owner", "owner", Role.ADMIN));
            userRepository.save(new User("employee", "employee", Role.USER));
        }
        //ADD Dog Behavior
        if (behaviorRepository.findAll().size() == 0) {
            behaviorRepository.save(new Behavior("Good with dogs", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Good with dogs and humans", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Good with humans", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with dogs", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with dogs and humans", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Bad with humans", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            behaviorRepository.save(new Behavior("Dakel", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));

        }
        //ADD Dog Breed
        if (breedRepository.findAll().size() == 0) {
            breedRepository.save(new Breed("Boxer", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("German Shepherd", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Rottweiler", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Bulgarian Shepherd - BOK", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Miniature Schnauzer", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Husky", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Yorkshire Terrier", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Dakel", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            breedRepository.save(new Breed("Pitbull", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));

        }
        //ADD Dog House
        if (cellRepository.findAll().size() == 0) {
            cellRepository.save(new Cell("S1",CellSize.SMALL ,Status.empty, userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("S2", CellSize.SMALL,Status.empty, userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("S3", CellSize.SMALL,Status.empty, userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("M1",CellSize.MEDIUM, Status.empty, userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("M2", CellSize.MEDIUM,Status.empty, userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("M3", CellSize.MEDIUM,Status.empty, userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("L1", CellSize.LARGE,Status.empty, userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("L2",CellSize.LARGE, Status.empty, userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cellRepository.save(new Cell("L3",CellSize.LARGE, Status.empty, userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));

        }
        //ADD Cities
        if (cityRepository.findAll().size() == 0) {
            cityRepository.save(new City("1000", "Sofia", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("2000", "Plovdiv", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("6000", "Stara Zagora", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("9000", "Varna", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            cityRepository.save(new City("7008", "Ruse", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));

        }
        //ADD Company
        if (companyRepository.findAll().size() == 0) {
            companyRepository.save(new Company("DHM Ltd", "logo.png", "Bulgaria", cityRepository.getById(Long.parseLong("3")), "Tzar Simeon Veliki 1", "BG030298796", "office@dhm.bg", "Bulgaria Bank", "BG18RZBB91550123456789", BigDecimal.valueOf(1000.00), "Ivan Petrov"));
        }
        //ADD PRICES
        if (priceRepository.findAll().size() == 0) {
            priceRepository.save(new Price(
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(13.00),
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
                    BigDecimal.valueOf(12.00),
                    BigDecimal.valueOf(15.00),
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
            clientRepository.save(new Client("Ivan", "Ivanov", "ivanov@gmail.com", "0886335241", "Lulin 15", cityRepository.getById(Long.parseLong("1")), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Stanimir", "Pavlov", "spavlov@abv.bg", "08842276361", "Zdravetzh 4", cityRepository.getById(Long.parseLong("2")), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Vladimir", "Georgiev", "vgeorgiev@outlook.com", "0887325579", "Zagorka 16", cityRepository.getById(Long.parseLong("3")), userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Boyan", "Dimitrov", "bdimitrov@hotmail.com", "0881256377", "Ludogorska 12B", cityRepository.getById(Long.parseLong("4")), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Yordan", "Atanasov", "atanasov@abv.bg", "0867258954", "Vasil Levski 22", cityRepository.getById(Long.parseLong("1")), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            clientRepository.save(new Client("Viktor", "Penev", "penev23@abv.bg", "08865578956", "Hristo Botev 24", cityRepository.getById(Long.parseLong("2")), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));


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
                    LocalDate.of(2023, 1, 1),
                    DogSize.LARGE));

            dogRepository.save(new Dog("Tuti",
                    LocalDate.of(2022, 3, 15),
                    "tuti.jpg",
                    3,
                    breedRepository.getById(Long.parseLong("6")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("5")),
                    behaviorRepository.getById(Long.parseLong("2")),
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 1),
                    DogSize.SMALL));

            dogRepository.save(new Dog("Dido",
                    LocalDate.of(2021, 6, 11),
                    "dido.jpg",
                    9,
                    breedRepository.getById(Long.parseLong("7")),
                    Sex.M,
                    Passport.YES,
                    Microchip.YES,
                    clientRepository.getById(Long.parseLong("6")),
                    behaviorRepository.getById(Long.parseLong("4")),
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 1),
                    DogSize.SMALL));

            dogRepository.save(new Dog("Sara",
                    LocalDate.of(2022, 2, 22),
                    "sara.jpg",
                    18,
                    breedRepository.getById(Long.parseLong("7")),
                    Sex.F,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("5")),
                    behaviorRepository.getById(Long.parseLong("6")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1),
                    DogSize.MEDIUM));

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
                    LocalDate.of(2023, 1, 1),
                    DogSize.LARGE));
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
                    LocalDate.of(2023, 1, 1),
                    DogSize.LARGE));
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
                    LocalDate.of(2023, 1, 1),
                    DogSize.LARGE));
            //New Dog "Zara"
            dogRepository.save(new Dog("Zara",
                    LocalDate.of(2022, 9, 16),
                    "Zara.jpg",
                    15,
                    breedRepository.getById(Long.parseLong("1")),
                    Sex.F,
                    Passport.YES,
                    Microchip.NO,
                    clientRepository.getById(Long.parseLong("2")),
                    behaviorRepository.getById(Long.parseLong("7")),
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 1),
                    DogSize.MEDIUM));
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
                    LocalDate.of(2023, 1, 1),
                    DogSize.SMALL));
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
                    LocalDate.of(2023, 1, 1),
                    DogSize.LARGE));

        }
        //ADD VENDORS
        if (vendorRepository.findAll().size() == 0) {
            vendorRepository.save(new Vendor("BTK Ltd", "Bulgaria", cityRepository.getById(Long.parseLong("1")), "Blv Ivan Shishman 3", "BG230234656", "btk@btk.com", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            vendorRepository.save(new Vendor("VIK Ltd", "Bulgaria", cityRepository.getById(Long.parseLong("3")), "Ivan Vazov 8", "BG230364657", "vik-stz@vik.com", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            vendorRepository.save(new Vendor("Kaufland", "Bulgaria", cityRepository.getById(Long.parseLong("3")), "Hristo Botev 1", "BG537268593", "kaufland-bg@kaufland.com", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));
            vendorRepository.save(new Vendor("Bagira OOD", "Bulgaria", cityRepository.getById(Long.parseLong("3")), "Stoletov 31", "BG650765652", "bagira@bagira.bg", userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 1)));
            vendorRepository.save(new Vendor("METRO", "Bulgaria", cityRepository.getById(Long.parseLong("3")), "General Gurko 9", "BG486464798", "metro-stz@metro.com", userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 1)));

        }
        //ADD COSTS
        if (costRepository.findAll().size() == 0) {
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("4")), "lumber 5 pies", BigDecimal.valueOf(44.26), LocalDate.of(2023, 1, 1), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 6)));
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("2")), "Water fee", BigDecimal.valueOf(55.50), LocalDate.of(2023, 1, 2), userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 6)));
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("1")), "Gsm fee", BigDecimal.valueOf(21.00), LocalDate.of(2023, 1, 2), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 6)));
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("3")), "Dog food", BigDecimal.valueOf(191.99), LocalDate.of(2023, 1, 3), userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 6)));
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("5")), "Cosmetics for dogs", BigDecimal.valueOf(94.60), LocalDate.of(2023, 1, 4), userRepository.getById(Long.parseLong("1")), LocalDate.of(2023, 1, 6)));
            costRepository.save(new Cost(vendorRepository.getById(Long.parseLong("4")), "Construction materials", BigDecimal.valueOf(200.01), LocalDate.of(2023, 1, 6), userRepository.getById(Long.parseLong("2")), LocalDate.of(2023, 1, 6)));


        }

        //ADD RESERVATION
        if (reservationRepository.findAll().size() == 0) {

            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("1")),
                    dogRepository.getById(Long.parseLong("3")),
                    LocalDate.of(2023, 1, 1),
                    LocalDate.of(2023, 1, 3),
                    2,
                    cellRepository.getById(Long.parseLong("7")),
                    Food.YES,
                    Training.NO,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(60.00),
                    5.00,
                    BigDecimal.valueOf(57.00),
                    StatusReservation.completed,
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 6),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.YES));

            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("2")),
                    dogRepository.getById(Long.parseLong("6")),
                    LocalDate.of(2023, 1, 15),
                    LocalDate.of(2023, 1, 21),
                    6,
                    cellRepository.getById(Long.parseLong("2")),
                    Food.YES,
                    Training.YES,
                    Bathing.YES,
                    Combing.YES,
                    Ears.YES,
                    Paws.YES,
                    Nails.YES,
                    BigDecimal.valueOf(217.00),
                    10.00,
                    BigDecimal.valueOf(195.30),
                    StatusReservation.upcoming,
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 6),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.NO));


            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("3")),
                    dogRepository.getById(Long.parseLong("4")),
                    LocalDate.of(2023, 1, 17),
                    LocalDate.of(2023, 1, 21),
                    6,
                    cellRepository.getById(Long.parseLong("5")),
                    Food.NO,
                    Training.YES,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(88.00),
                    00.00,
                    BigDecimal.valueOf(88.00),
                    StatusReservation.upcoming,
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 6),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.YES));


            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("4")),
                    dogRepository.getById(Long.parseLong("7")),
                    LocalDate.of(2023, 1, 6),
                    LocalDate.of(2023, 1, 7),
                    1,
                    cellRepository.getById(Long.parseLong("6")),
                    Food.YES,
                    Training.YES,
                    Bathing.YES,
                    Combing.YES,
                    Ears.YES,
                    Paws.YES,
                    Nails.YES,
                    BigDecimal.valueOf(67.00),
                    8.00,
                    BigDecimal.valueOf(61.64),
                    StatusReservation.active,
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 6),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.NO));


            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("2")),
                    dogRepository.getById(Long.parseLong("5")),
                    LocalDate.of(2023, 1, 29),
                    LocalDate.of(2023, 2, 11),
                    13,
                    cellRepository.getById(Long.parseLong("7")),
                    Food.YES,
                    Training.NO,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(398.00),
                    20.00,
                    BigDecimal.valueOf(318.40),
                    StatusReservation.upcoming,
                    userRepository.getById(Long.parseLong("2")),
                    LocalDate.of(2023, 1, 6),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.NO));


            reservationRepository.save(new Reservation(
                    clientRepository.getById(Long.parseLong("4")),
                    dogRepository.getById(Long.parseLong("7")),
                    LocalDate.of(2023, 1, 6),
                    LocalDate.of(2023, 1, 7),
                    1,
                    cellRepository.getById(Long.parseLong("6")),
                    Food.YES,
                    Training.YES,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(908.00),
                    20.00,
                    BigDecimal.valueOf(726.40),
                    StatusReservation.upcoming,
                    userRepository.getById(Long.parseLong("1")),
                    LocalDate.of(2023, 1, 7),
                    companyRepository.getById(Long.parseLong("1")),
                    Invoiced.NO));


        }
        //ADD INVOICES
        if (invoiceRepository.findAll().size() == 0) {

            invoiceRepository.save(new Invoice(
                    "DHM Ltd",
                    "Stara Zagora",
                    "Tzar Simeon Veliki 1",
                    "BG030298796",
                    "office@dhm.bg",
                    "Bulgaria Bank",
                    "BG18RZBB91550123456789",
                    "Ivan Petrov",
                    "Boby",
                    2,
                    "L1",
                    Food.YES,
                    Training.NO,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(60.00),
                    5.00,
                    BigDecimal.valueOf(57.00),
                    "admin",
                    LocalDate.of(2023, 1, 6),
                    Long.parseLong("1"),
                    BigDecimal.valueOf(20.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(8.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    "Ivan Ivanov",
                    "ivanov@gmail.com",
                    "Lulin 15",
                    "Sofia",
                    "0886335241",
                    CancellationInvoice.NO,
                    LocalDate.of(2023, 1, 7)));


            invoiceRepository.save(new Invoice(
                    "DHM Ltd",
                    "Stara Zagora",
                    "Tzar Simeon Veliki 1",
                    "BG030298796",
                    "office@dhm.bg",
                    "Bulgaria Bank",
                    "BG18RZBB91550123456789",
                    "Ivan Petrov",
                    "Cezar",
                    6,
                    "B2",
                    Food.NO,
                    Training.YES,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(88.00),
                    0.00,
                    BigDecimal.valueOf(88.00),
                    "admin",
                    LocalDate.of(2023, 1, 6),
                    Long.parseLong("3"),
                    BigDecimal.valueOf(20.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(8.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    "Vladimir Georgiev",
                    "vgeorgiev@outlook.com",
                    "Zagorka 16",
                    "Stara Zagora",
                    "0887325579",
                    CancellationInvoice.NO,
                    LocalDate.of(2023, 1, 7)));


            invoiceRepository.save(new Invoice(
                    "DHM Ltd",
                    "Stara Zagora",
                    "Tzar Simeon Veliki 1",
                    "BG030298796",
                    "office@dhm.bg",
                    "Bulgaria Bank",
                    "BG18RZBB91550123456789",
                    "Ivan Petrov",
                    "Zara",
                    13,
                    "C1",
                    Food.YES,
                    Training.NO,
                    Bathing.NO,
                    Combing.NO,
                    Ears.NO,
                    Paws.NO,
                    Nails.NO,
                    BigDecimal.valueOf(398.00),
                    20.00,
                    BigDecimal.valueOf(318.40),
                    "admin",
                    LocalDate.of(2023, 1, 9),
                    Long.parseLong("5"),
                    BigDecimal.valueOf(20.00),
                    BigDecimal.valueOf(10.00),
                    BigDecimal.valueOf(8.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(7.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    BigDecimal.valueOf(5.00),
                    "Stanimir Pavlov",
                    "spavlov@abv.bg",
                    "Zdravetzh 4",
                    "Plovdiv",
                    "08842276361",
                    CancellationInvoice.YES,
                    LocalDate.of(2023, 1, 7)));


        }
    }
}