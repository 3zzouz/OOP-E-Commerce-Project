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

# Connection: Administrators can log in with their username and password.

# Features:

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

# Connection: Clients can log in with their username and password.

# Features:

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

# Connection:Product Manager can log in with their username and password.

# Features:

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
-`PaymentMethod`
- `Produit`: Represents a product with a name, a category, a price, and a customer satisfaction rate.
- `Panier`: Manages the products added to the cart by the client.
- `InfoLivraison`: Contains information about the delivery, such as the order number, address, price, duration, and method.
- `Commande`: Represents an order with a number, an associated client, an address, a date, products, a status, and a total price.
- `Utilisateur`: Base class for users with common properties such as username, password, email, etc.
- `Administrateur`: Subclass of `Utilisateur` specific to administrators.
- `Client`: Subclass of `Utilisateur` specific to clients with additional features like the cart, orders, etc.
- `App`: Contains the business logic of the application, including the management of clients, stock, and main features.

## 5. Design Decisions:

- Object-Oriented Approach: The application is designed using an object-oriented approach to encapsulate data and behaviors in classes. This makes the code modular, scalable, and easier to maintain.
- Data Structures: HashMaps are used to store the products in stock, manage the products in the cart, and map the clients in the application. ArrayList maintains the history of the clients' orders.
- User Authentication: User authentication is implemented using a simple HashMap where the usernames are the keys and the passwords are the values. For simplicity, passwords are stored in plain text.

## 6. OOP Implementation:

- Inheritance: The `Administrateur` and `Client` classes inherit from the `Utilisateur` class, representing a hierarchical relationship.
- Encapsulation: Data encapsulation is achieved by making the attributes private and providing public methods to access and modify them.

## 7. Optional Features:

- Comment and Rating System: Implemented a comment and rating system for products using the `feedbackRate` attribute in the `Produit` class. Clients can rate products on a scale of 0 to 5, and the average feedback rate is calculated.
- Multiple Delivery Options: Introduced the concept of shipping options in the `InfoLivraison` class. Clients can choose between standard, express, and urgent shipping methods, each method affecting the cost and delivery time.

For more information, you will find well-commented code that will guide you when reading the code and during its execution.

Work done by Oussema Guerami GL2/2
