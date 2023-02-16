# Dog-Hotel-Management
My project  -  this app manages a hotel for dogs.

Using software technology: Java, SpringBoot, Spring MVC, JPA, Thymeleaf, MySql, Spring Security, JavaScript



Run:
You need Mysql server and IntelliJ app.

For start application only need you change with your 
mysql user name and password on application.properties file. And start application with Intelij

Local link - localhost:8011

DEMO - http://rrb.stz.ddns.bulsat.com:8011

DEMO VIDEO - https://youtu.be/nv8VCsqkdjo

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




![2023-02-05](https://user-images.githubusercontent.com/29566751/216809212-7d1d8098-b26b-44a3-bbb1-6304d6a2270e.png)



HOME PAGE:

![2023-02-03 (6)](https://user-images.githubusercontent.com/29566751/216555758-cbd3cc0d-55d7-451d-a356-c78c124eca7e.png)



![2023-02-03 (7)](https://user-images.githubusercontent.com/29566751/216555785-cf317081-91cf-423d-a9bc-72bdc05132f8.png)


RESERVATION PAGE:



![2023-02-03 (1)](https://user-images.githubusercontent.com/29566751/216555904-41d31853-dbb9-45d8-852a-06280ea1473a.png)



ADD RESERVATION:


![2023-02-03 (5)](https://user-images.githubusercontent.com/29566751/216556003-a02e72d3-6c58-456a-9a2a-04f895def089.png)

EDIT RESERVATION:

![2023-02-16 (3)](https://user-images.githubusercontent.com/29566751/219395282-3c39e8b6-55d6-4fc1-afb4-04d497991f4d.png)




RESERVATION DETAILS:

![2023-02-03 (3)](https://user-images.githubusercontent.com/29566751/216556132-c64d71d0-e2a4-4b38-aa38-f4d48d1a7f87.png)



INVOICE LIST:



![2023-02-16 (5)](https://user-images.githubusercontent.com/29566751/219395477-9f9b9b90-7194-43fa-b6c7-0eb5da15076f.png)


INVOICE:


![2023-01-19 (6)](https://user-images.githubusercontent.com/29566751/213428876-ca7d91c8-18ea-4862-881d-021cdbf65033.png)



CANCELLED INVOICE LIST:

![2023-01-16 (9)](https://user-images.githubusercontent.com/29566751/212759731-230cef0a-bede-433b-8f8a-1bf2f6cc0467.png)
![2023-01-16 (10)](https://user-images.githubusercontent.com/29566751/212759755-6e71a731-5e49-4d6f-8d60-c9bdd79473e1.png)

COST PAGE:

![2023-01-16 (11)](https://user-images.githubusercontent.com/29566751/212759784-aad30a00-bcad-460c-9018-d838e6088268.png)


VENDOR LIST:


![2023-01-11](https://user-images.githubusercontent.com/29566751/211800794-46d55763-f8d0-4f4f-b89c-3b950bcaee61.png)


LIST CLIENT:

![2023-02-16 (6)](https://user-images.githubusercontent.com/29566751/219395556-d066a836-413d-44d8-8696-4470503ae65a.png)



CITY PAGE:

![2023-01-16 (14)](https://user-images.githubusercontent.com/29566751/212759898-99536124-094c-449b-9b46-2dd66b2801f3.png)

DOG LIST:


![2023-02-16 (4)](https://user-images.githubusercontent.com/29566751/219395593-7f9576ea-1573-4a16-931e-207200a04094.png)


EDIT DOG:

![2023-02-03 (12)](https://user-images.githubusercontent.com/29566751/216556459-8eeed79e-0df6-41bf-9f35-5065e2c1eec3.png)


DOG DETAILS:


![2023-02-03 (19)](https://user-images.githubusercontent.com/29566751/216559278-e07fc383-98e0-4163-9c73-b5de37227d25.png)

DOG HOUSE PAGE:


![2023-02-16 (7)](https://user-images.githubusercontent.com/29566751/219395709-872c9dd6-779c-441f-904f-6eda35c1ae74.png)

PRICE LIST:

![2023-01-16 (23)](https://user-images.githubusercontent.com/29566751/212760066-26b2024d-3544-4790-969f-07d5fb715ce2.png)

BREED LIST:

![2023-01-16 (21)](https://user-images.githubusercontent.com/29566751/212760028-bb01509a-7530-4dea-bfb0-dab8255cf996.png)


BEHAVIOR LIST:

![2023-01-16 (22)](https://user-images.githubusercontent.com/29566751/212760046-47649eb3-5f46-4762-9219-59dfe408394c.png)


MY COMPANY:
![2023-02-03 (18)](https://user-images.githubusercontent.com/29566751/216556736-368f0b6b-8653-433d-a2b7-f1f5a74bc266.png)



USER LIST:

![2023-02-03 (16)](https://user-images.githubusercontent.com/29566751/216556845-9a9fe667-a203-4a8e-9c27-f11f30810670.png)

![2023-02-03 (17)](https://user-images.githubusercontent.com/29566751/216556855-01517646-739c-4715-9fc1-10dfc6f8abf3.png)
