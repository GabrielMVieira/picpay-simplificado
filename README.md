# API Rest - Challenge PicPay

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

This project is an API built using **Java, Java Spring, Postgre as the database.**

The API was developed for the practice of my studies, solving the [PicPay Backend Challenge](https://github.com/PicPay/picpay-desafio-backend) using Java Spring.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/GabrielMVieira/picpay-simplificado
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

**GET ALL USERS**
```markdown
GET /users - Retrieve a list of all users.
```
```json
[
  {
    "id": 6,
    "name": "Allyson",
    "email": "Allyson@picpay.com",
    "balance": 172.00,
    "userType": "MERCHANT"
  },
  {
    "id": 7,
    "name": "Graciele",
    "email": "Graciele@picpay.com",
    "balance": 102.00,
    "userType": "COMMUN"
  }
]
```

**GET USERS**
```markdown
GET /users/id - Retrieve a user by id.
```
```json
{
  "id": 5,
  "name": "Nalu",
  "email": "Nalu@picpay.com",
  "balance": 132.00,
  "userType": "MERCHANT"
}
```

**POST USERS**
```markdown
POST /users - Register a new user into the App
```
```json
{
  "name": "Lucas Reis",
  "document":"9009",
  "email": "reis@picpay.com",
  "password": "password",
  "userType": "MERCHANT",
  "balance": "100.00"
}
```

**POST TRANSACTIONS**
```markdown
POST /transactions - Register a new Transaction between users (COMMON to COMMON or COMMON to MERCHANT)
```

```json
{
  "amount": 10,
  "payerId": 7,
  "payeeId": 4
}
```

**GET TRANSACTIONS BY ID**
```markdown
GET /users/6/transactions - Retrieves the user's transaction history provided by the id, limited to the last 10 transactions.
```

```json
{
  "user": {
    "id": 6,
    "name": "Allyson",
    "email": "Allyson@picpay.com",
    "balance": 172.00,
    "userType": "MERCHANT"
  },
   "transactions": [
    {
      "amount": 10.00,
      "counterParty": {
        "id": 4,
        "name": "Jose",
        "email": "Jose@picpay.com",
        "balance": 122.00,
        "userType": "COMMUN"
      },
      "transactionTime": "2024-04-25T17:19:57.856555"
    },
    {
      "amount": 10.00,
      "counterParty": {
        "id": 4,
        "name": "Jose",
        "email": "Jose@picpay.com",
        "balance": 122.00,
        "userType": "COMMUN"
      },
      "transactionTime": "2024-04-29T14:43:02.333663"
    },
    {
      "amount": 10.00,
      "counterParty": {
        "id": 4,
        "name": "Jose",
        "email": "Jose@picpay.com",
        "balance": 122.00,
        "userType": "COMMUN"
      },
      "transactionTime": "2024-04-29T15:35:13.20368"
    }
  ]
}
```

## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please contact me.
