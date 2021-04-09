# Shopping Cart Api

### Prerequisites
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 8](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html) -  Development Environment 
* [Git](https://git-scm.com/) - Version Control System

## Installing and using 

1. Clone the repository

```
git clone https://github.com/juancamilo399/MasivianCleanCode
```

Note: to run the application, you should create the environment variable (REDIS_HOST) in this case is 172.17.0.1

2. Compile the projet

```
mvn package
```

3. Executing the program

```
mvn exec:java -D "exec.mainClass"="com.tul.shoppingCartApi.ShoppingCartApiApplication"

In your browser: http://localhost:8080/api/v1/products
```

## Endpoints:

The endpoints of the API are the following:
- GET request:
    - /products: Get all products
    - /carts: Get all carts
    - /carts/{id}/products: Get products of a cart
    - /{idCart}/checkout: Get checkout of a cart
- POST request:
    - /products: Creates a new product
    - /create: Creates a new cart
    - /{idCart}/products: Add product to cart
- PUT request:
    - /products: Update a product
    - /{idCart}/products: Change the quantity of a product in a cart
  
 - DELETE request:
    - /{id}: Delete a product
    - /{idCart}/products/{idProduct}: Delete a product of a cart

Make the requests to localhost:8080/api/v1

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Framework](https://spring.io/) - Framework


## Author

* **Juan Camilo Angel Hernandez** - *Systems engineer student* 

## License

This project is under GNU General Public License - see the [LICENSE](LICENSE) file for details.

