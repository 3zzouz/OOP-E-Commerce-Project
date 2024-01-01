# End of Semester Project Documentation

## 1. Application Access:

You should access the console application from the Main.java
The App.java is used to group the functions used in the Main.java
I have already created 3 users one is a customer which is the one with the lowest permission level and the other is a product manager with more permissions (mainly managing product inventory) and the admin level with higher accesslevel (managing both inventory and users list)
And i have created 4 products one of each type : Books and Clothing and Electronics and SportAndOutDoor
To access the application as an administrator, use the username "admin1" and the password "admin1password".
To access the application as a customer, use the username "customer1" and the password "customer1password".
When you run the application, it will give 4 options : to login with one of the existant accounts or to create a new account after choosing which type of new user you want or to reset password or simply to exit and therefore close the application.
And if you login based on the permissionLevel you will be given Menu for either Administrator or Customer or ProductManager

## 2. Administrator Menu:

Connection: Administrators can log in with their username and password.

Features:

- View the list of clients: display the list of registered clients
- View a user: view a user in the list of of users
- Ban a user: ban a user by removing them from the list of registered user
- Unblock a user : to unblock a user from users list
- View all products in the stock
- Search products using filters
- Add a product to the stock
- Modify a product: update one or many aspect(s) of a product
- Remove a product from the stock
- Disconnect
- Close this Menu and go back to the first Menu

## 3. Customers Menu:

Connection: Clients can log in with their username and password.

Features:

- View all Products in the stock
- Search for Products using filters
- Add a product to the cart
- Display the cart
- Remove a product from the cart
- Update quantity of a product in the cart
- Place an order
- View the purchase history
- To clear the chart
- To add payment method : (Every customer has the right to maximum two payment methods one is credit card and the other is paypal account)
- Disconnect
- Close this Menu and go back to the first Menu

## 3.Product Manager Menu:

Connection:Product Manager can log in with their username and password.

Features:

- View all Products in the stock
- Search for Products using filters
- Add a product to the stock
- Change product information on the stock
- Remove a product from the stock
- To add Discount to the list of discounts (make sure to write a valid date because the system will verify that)
- To Remove a discount from the list of discounts
- To update a discount using its code (unique among all discounts)
- To print the list of all products
- Disconnect
- Close this Menu and go back to the first Menu

## 5. Code Structure:

The code is organized into several classes for better readability.

- `CreditCard`: Represents a bank card with a number, a balance ,a CVV, an expiry date and card holders name.
- `Paypal`: Represents a paypal account (email + password) and also a balance.
- `PaymentMethod`:Represents the abstract super class of `Paypal` and `CreditCard`
- `Product`: Represents an abstract class with a name,an id, a description, a price, and a stockQuantity and an ImageURL .
- `Clothing` : Represents a subclass of `Product` having array of sizes and colors
- `Books` : Represents a subclass of `Product` having array of genres and an author and a publisher
- `Electronics`:Represents a subclass of `Product` having array of specs
- `SportsAndOutDoor`:Represents a subclass of `Product` having a type, brand, material, weight and a sportType
- `Cart`: Manages the products added to the cart by the client and having totalPrice representing the total amount to pay and every cart is specefic to one and only customer.
- `Discount`: Manages the list of discounts that reduce the cost of orders

- Exceptions:

  - `NotEnoughMoneyException`:Representing the error that occurs when you try to pay with a payment method that doesn't have enough balance
  - `WrongPasswordException`:Representing the error that occurs when you enter the wrong password in the login section

- `Order`:Represents an order made by a customer. This class handles the order process, including calculating the total price,applying discounts, setting the order ID, canceling the order, shipping the order, and simulating the checkout process. The order status can be one of the following: "Pending", "Processing","Shipped", "Delivered", or "Canceled". The order ID is generated based on the customer's username and a unique counter. The total price of the order is calculated by summing the prices of the products in the customer's cart and adding the shipping cost.The order can be canceled within 72 hours of placing the order. The order can be shipped, which changes the order status to "Shipped". The order can be paid, which reduces the customer's balance and updates the preferred payment method. Notifications can be pushed to the product manager when a product is running out of stock.

- `Order`: Represents an order with a number, an associated client, an address, a date, products, a status, and a total price.

- `HashPassword`:The HashPasswords class is responsible for hashing and verifying passwords using the SHA-512 algorithm so when an admin views the users it doesn't view the password of the other accounts.

- Utility: -`DateFormat`: The DateFormat class provides methods to convert between Date objects and formatted date strings.
- Shipping :

  - `ShippingMethod` : The abstract class representing a shipping method. -`StandardShipping` :The StandardShipping class represents a standard shipping method for an order. It extends the ShippingMethod class and provides a default constructor and a method to calculate the shipping cost. -`ExpressShippingMethod` : Represents an express shipping method for an order.

- `User`: The abstract user class represents a user in the e-commerce system. It provides methods for user registration, login, logout, and password management.Users can search for products using filters.
- `ProductManager`:The ProductManager class represents a user with the role of a product manager in an e-commerce system. It extends the User class and provides functionality to manage products,including adding, removing, updating,and searching for products. It also allows the product manager to manage discounts for products.

- `Customer`: Represents a customer in the e-commerce system inherits from the User class.

- `Admin`:The Admin class represents an administrator in the e-commerce system. It extends the ProductManager class and provides additional functionalities for managing users and their permissions.

- `App`: Contains the business logic of the application, including the management of clients, stock, and all features that will be called from the main function.

- `Main` : Entry point to the console application

## 5. Design Decisions:

- Object-Oriented Approach: The application is designed using an object-oriented approach to encapsulate data and behaviors in classes. This makes the code modular, scalable, and easier to maintain.
- Data Structures: HashMaps are used to store the products in stock, manage the products in the cart, and map the clients in the application. ArrayList maintains the history of the clients' orders.
- User Authentication: User authentication is implemented using a simple HashMap where the usernames are the keys and the passwords are the values. For simplicity, passwords after being hashed are stored in plain text.

## 6. OOP Implementation:

- Inheritance: The `Admin` and `Customer` and `ProductManager` classes inherit from the `User` class, representing a hierarchical relationship.
- Encapsulation: Data encapsulation is achieved by making the attributes private and providing public methods to access and modify them which is done in many classes.
- Polymorphism : Is achieved in this project for example in the `Admin` and `Customer` and other subclasses i have overridden methods such as toString()
- Abstraction : Is also achieved in this project by using abstract classes such as `User` and `Product` and `PaymentMethod`

## 7. Optional Features:

- Discounts and Promotions: Implemented a system for applying discount codes and promotions to purchases. Customers can enter a discount code at checkout to receive a percentage off their total purchase price.

- Multiple Shipping Options: Introduced the concept of shipping options. Customers can choose between standard and express shipping methods, each method affecting the cost of the orders.

For more information, you will find well-commented code that will guide you when reading the code and during its execution.
For many classes and methods when you hover on them you will find all the information needed

Work done by Dhouibi Mohamed Aziz GL2/1
