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



![2023-02-16 (3)](https://user-images.githubusercontent.com/29566751/219396393-d2d7916b-b2bd-46f9-9bd5-db97a405fc43.png)




ADD RESERVATION:

![2023-02-16 (10)](https://user-images.githubusercontent.com/29566751/219396726-e5ba1566-eb8c-4257-8252-c547de5b1685.png)



EDIT RESERVATION:


![2023-02-20 (9)](https://user-images.githubusercontent.com/29566751/220046626-1436dfcc-ee27-4a81-8730-e2938deb4abb.png)


RESERVATION DETAILS:


![2023-02-20 (8)](https://user-images.githubusercontent.com/29566751/220046433-b76708e9-e58b-416c-95be-68547a92f9aa.png)

GENERATE INVOICE:
![2023-02-20 (10)](https://user-images.githubusercontent.com/29566751/220048153-6fd8d4d2-6e91-4547-b213-10cef48fc585.png)

INVOICE LIST:



![2023-02-16 (5)](https://user-images.githubusercontent.com/29566751/219395477-9f9b9b90-7194-43fa-b6c7-0eb5da15076f.png)


INVOICE:


![2023-01-19 (6)](https://user-images.githubusercontent.com/29566751/213428876-ca7d91c8-18ea-4862-881d-021cdbf65033.png)



CANCELLED INVOICE LIST:

![2023-02-20 (11)](https://user-images.githubusercontent.com/29566751/220048384-5f316b6f-14eb-499c-bcff-d88cb50a234b.png)

![2023-01-16 (9)](https://user-images.githubusercontent.com/29566751/212759731-230cef0a-bede-433b-8f8a-1bf2f6cc0467.png)
![2023-01-16 (10)](https://user-images.githubusercontent.com/29566751/212759755-6e71a731-5e49-4d6f-8d60-c9bdd79473e1.png)

COST PAGE:


![2023-02-20](https://user-images.githubusercontent.com/29566751/220043964-a6d7e67c-d0aa-47a6-9e9e-0f7f092f82dd.png)


VENDOR LIST:


![2023-02-20 (1)](https://user-images.githubusercontent.com/29566751/220043989-fffbfed4-fdad-4c89-acf3-3d286a88120e.png)



LIST CLIENT:

![2023-02-16 (6)](https://user-images.githubusercontent.com/29566751/219395556-d066a836-413d-44d8-8696-4470503ae65a.png)



CITY PAGE:

![2023-01-16 (14)](https://user-images.githubusercontent.com/29566751/212759898-99536124-094c-449b-9b46-2dd66b2801f3.png)

DOG LIST:


![2023-02-16 (4)](https://user-images.githubusercontent.com/29566751/219395593-7f9576ea-1573-4a16-931e-207200a04094.png)


EDIT DOG:


![2023-02-20 (5)](https://user-images.githubusercontent.com/29566751/220045762-cfa2c673-333a-463c-84e5-4d068df8eb21.png)


DOG DETAILS:

![2023-02-20 (6)](https://user-images.githubusercontent.com/29566751/220045795-519c3bcb-5fd2-4992-829d-25269808ea95.png)


DOG HOUSE PAGE:


![2023-02-16 (7)](https://user-images.githubusercontent.com/29566751/219395709-872c9dd6-779c-441f-904f-6eda35c1ae74.png)

PRICE LIST:

![2023-02-20 (2)](https://user-images.githubusercontent.com/29566751/220044626-0c09b29b-d29e-4dbe-8e73-2f626bcb1780.png)


BREED LIST:

![2023-01-16 (21)](https://user-images.githubusercontent.com/29566751/212760028-bb01509a-7530-4dea-bfb0-dab8255cf996.png)


BEHAVIOR LIST:

![2023-01-16 (22)](https://user-images.githubusercontent.com/29566751/212760046-47649eb3-5f46-4762-9219-59dfe408394c.png)


MY COMPANY:

![2023-02-20 (3)](https://user-images.githubusercontent.com/29566751/220044657-04f4c7fa-fbb2-4cd8-a9eb-65404067e474.png)


EDIT MY COMPANY:
![2023-02-20 (4)](https://user-images.githubusercontent.com/29566751/220044705-0d974567-99dd-4b9c-a011-b81fb5574e9c.png)


USER LIST:

![2023-02-03 (16)](https://user-images.githubusercontent.com/29566751/216556845-9a9fe667-a203-4a8e-9c27-f11f30810670.png)

![2023-02-03 (17)](https://user-images.githubusercontent.com/29566751/216556855-01517646-739c-4715-9fc1-10dfc6f8abf3.png)
