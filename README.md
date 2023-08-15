# Cartify - Cart Management API

Cartify is a Java-based e-commerce cart management API system that allows users to manage their shopping carts.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Features

- Create carts
- Add products to the cart
- Get carts by id
- Remove carts
- Create products
- Automatically delete inactive carts

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Intellij Idea or your favorite IDE
- Postman
  
### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/alvdiaasc97/cartify.git
   
2. Build the project:
   ```sh
    mvn clean install
   
### Usage
Start the application: run the application with maven in your IDE.

See API documentation for available endpoints and usage details.

### Documentation
#### Create Cart
```sh
POST http://localhost:8080/carts/create
```
    {
      "products": [
        {
          "description": "Camisa",
          "amount": 25.99
        },
        {
          "description": "Pantalón",
          "amount": 40.75
        }
      ],
      "activityTime": "2023-08-15T10:30:00"
    }


#### Add Product to Cart
  ```sh
  POST http://localhost:8080/carts/{cartId}/add
  ```
    {
        "id": 3,
        "amount": 5.6,
        "description": "Product 3"
    }
#### Get Cart By ID
  ```sh
  GET http://localhost:8080/carts/{cartId}
  ```
#### Get Carts
  ```sh
  GET http://localhost:8080/carts
  ```

#### Delete cart
  ```sh
  DEL http://localhost:8080/carts/{cartId}/delete
  ```

    
#### Get Products
  ```sh
 GET http://localhost:8080/products
  ```

#### Create Product
  ```sh
 POST http://localhost:8080/products/create
  ```

    {
          "description": "Chándal",
          "amount": 24.55
    }
 
### Contributing
Contributions are welcome! If you'd like to contribute, please follow these steps:

Fork the repository.
Create a new branch: git checkout -b feature/your-feature-name.
Make your changes and commit them: git commit -m 'Add some feature'.
Push to the branch: git push origin feature/your-feature-name.
Open a pull request.


### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.

