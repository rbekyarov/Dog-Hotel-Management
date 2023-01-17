# Dog-Hotel-Management
My project  -  this app manages a hotel for dogs.

Using software technology: Java, SpringBoot, Spring MVC, JPA, Thymeleaf, MySql, Spring Security, JavaScript



Run:
You need Mysql server and IntelliJ app.

For start application only need you change with your 
mysql user name and password on application.properties file. And start application with Intelij

Local link - localhost:8011

DEMO - http://rrb.stz.ddns.bulsat.com:8011

DEMO VIDEO - https://youtu.be/wDdqS93h2EI

Resources:

1.Register Form
Form for registration on user.
Оnly user type registration is possible.
Handler for incorrect data, incomplete data and not allowing existing user to register.

2.Login Form:
Login after correct data.

3.Home menu - data is dynamic after refresh
Includes details:
- of available houses dog
- amount of current invoices
- amount of expenses
- bank account balance
- The last 3 current reservations
- Тhe nearest 3 incoming reservations
- The last two invoices
- The last two expenses

4.Reservations
Includes :
-Customer
-Customer's dog
-Suitable house according to the weight of the dog. Only a vacant house can be selected.
-Check-in date
-Release date
-Total stay - dynamically generated based on the entered dates.

Services:
-Feeding -
-Deworming
-Training
-Bathing
-Air cleaning
-Paws cleaning
-Nails trimming
-Combing

Pricing -
The price of the reservation is formed as follows.
Price per stay is multiplied by the total stay
Meals - the price of the stay is multiplied by the total stay
Deworming - unit price
Training - unit price
Bathing - unit price
Air cleaning - unit price
Paws cleaning - unit price
Nails trimming - unit price
Combing - unit price
Discount - maximum discount is 20%
The final price is formed after calculating and subtracting the discount.

The status of the reservation is dynamic according to the current date. Statuses are updated after reloading.
There are 3 statuses - upcoming, active, completed
Each reservation can be changed, reviewed, removed.
Every reservation can be invoiced - once invoiced, the reservation cannot be changed or deleted. In order to be able to change, the invoice for it must first be cancelled.

From the reservation table - you can also see detailed information about the dog and detailed information about the invoice.


ENTITIES:


![2023-01-15 (1)](https://user-images.githubusercontent.com/29566751/212567330-44576c42-be8c-42c3-8a70-08ee0a75dde8.png)


INDEX PAGE:

![2023-01-16 (1)](https://user-images.githubusercontent.com/29566751/212759426-43d9f9e1-c4d8-49c7-8b22-b58da9bfca78.png)


LOGIN PAGE:


![2023-01-16 (2)](https://user-images.githubusercontent.com/29566751/212759459-ae0bd6d1-c7a0-4e15-af8d-193f46f9f0bb.png)

WELCOM PAGE:


![2023-01-16 (4)](https://user-images.githubusercontent.com/29566751/212759493-80229fce-6d8c-4eb8-ae52-1a1b9404a73f.png)


RESERVATION PAGE:

![2023-01-16 (5)](https://user-images.githubusercontent.com/29566751/212759530-b37ddba9-d309-4ea4-82d8-104c319a71ba.png)

ADD RESERVATION:

![2023-01-16 (6)](https://user-images.githubusercontent.com/29566751/212759562-34c85a67-3515-44ab-bc67-b639f59535c5.png)

EDIT RESERVATION:

![6 EditReservation](https://user-images.githubusercontent.com/29566751/211553934-8b39867d-7df3-4d0c-b298-530089bed402.png)


RESERVATION DETAILS:

![5A ReservationDetails](https://user-images.githubusercontent.com/29566751/211554038-4cb3ca31-afcb-4f1c-b138-6f8d2f762777.png)


INVOICE LIST:

![2023-01-16 (7)](https://user-images.githubusercontent.com/29566751/212759612-fc2c03a4-d381-4083-a114-1794fd2ec5be.png)

INVOICE:

![2023-01-16 (8)](https://user-images.githubusercontent.com/29566751/212759662-ff356783-e749-4e30-bedd-674ecee28617.png)



CANCELLED INVOICE LIST:

![2023-01-16 (9)](https://user-images.githubusercontent.com/29566751/212759731-230cef0a-bede-433b-8f8a-1bf2f6cc0467.png)
![2023-01-16 (10)](https://user-images.githubusercontent.com/29566751/212759755-6e71a731-5e49-4d6f-8d60-c9bdd79473e1.png)

COST PAGE:

![2023-01-16 (11)](https://user-images.githubusercontent.com/29566751/212759784-aad30a00-bcad-460c-9018-d838e6088268.png)


VENDOR PAGE:


![2023-01-11](https://user-images.githubusercontent.com/29566751/211800794-46d55763-f8d0-4f4f-b89c-3b950bcaee61.png)


LIST CLIENT:


![2023-01-16 (13)](https://user-images.githubusercontent.com/29566751/212759855-7b4eaf58-81a5-424a-839d-4ccc42f6a62e.png)

CITY PAGE:

![2023-01-16 (14)](https://user-images.githubusercontent.com/29566751/212759898-99536124-094c-449b-9b46-2dd66b2801f3.png)

DOG PAGE:

![2023-01-16 (15)](https://user-images.githubusercontent.com/29566751/212759917-f7d7d327-9e82-4900-853c-42e8c064fed6.png)

EDIT DOG:

![2023-01-16 (18)](https://user-images.githubusercontent.com/29566751/212759948-cfd52da1-d67f-40d9-a5b2-44096c0f5ef3.png)

DOG DETAILS:

![2023-01-16 (19)](https://user-images.githubusercontent.com/29566751/212759969-457bc212-7c4f-4707-aa23-a1a216b60350.png)

DOG HOUSE PAGE:

![2023-01-16 (20)](https://user-images.githubusercontent.com/29566751/212759993-eced8a61-ba41-4f3f-bbe5-f1ed22662279.png)


PRICE LIST:

![2023-01-16 (23)](https://user-images.githubusercontent.com/29566751/212760066-26b2024d-3544-4790-969f-07d5fb715ce2.png)

BREED LIST:

![2023-01-16 (21)](https://user-images.githubusercontent.com/29566751/212760028-bb01509a-7530-4dea-bfb0-dab8255cf996.png)


BEHAVIOR LIST:

![2023-01-16 (22)](https://user-images.githubusercontent.com/29566751/212760046-47649eb3-5f46-4762-9219-59dfe408394c.png)


MY COMPANY:

![2023-01-16 (26)](https://user-images.githubusercontent.com/29566751/212760101-67a7676f-8b0e-47e3-a760-c7d30b9b95c7.png)

USER LIST:
![2023-01-16 (24)](https://user-images.githubusercontent.com/29566751/212760133-d2f94c1a-0329-48af-9800-bd4bfe48a0e4.png)
![2023-01-16 (25)](https://user-images.githubusercontent.com/29566751/212760146-f631551a-ff47-42b7-a40a-de18d920fe21.png)

