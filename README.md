# Clustered Data Warehouse

## Description

This project is a Spring Boot-based application designed to manage foreign exchange transactions for a clustered data warehouse. It provides RESTful services to create and retrieve FX deal information, aiming to streamline the data handling and analysis process within financial institutions. The application is intended for backend systems of banks, financial analysts, and data scientists interested in FX market trends and data storage solutions.

## Installation

### Prerequisites

- Java JDK 17
- Maven
- Docker & Docker Compose
- PostgreSQL (if running locally outside Docker)

### Setup Instructions

1. Clone the repository:
git clone <repository-url>

2. Navigate to the project directory:
cd clustered_data_warehouse

3. Build & Start the application using Docker Compose:
docker-compose up --build

This will start the application and the associated PostgreSQL database in containers. The application will be available on port 8080.

## Usage

After starting the application, you can interact with it using the following endpoints:

### Create FX Deal
- **Endpoint:** `POST /fxdeals`
- **Body:**
    ```json
    {
      "fromCurrencyISOCode": "USD",
      "toCurrencyISOCode": "EUR",
      "dealTimestamp": "2024-02-18T10:00:00",
      "dealAmount": 1000.00
    }
    ```
- **Response:** Returns the created FX deal with a 201 status code.

### Get All FX Deals
- **Endpoint:** `GET /fxdeals`
- **Response:** Returns a list of all FX deals stored in the system.

### Get FX Deal by ID
- **Endpoint:** `GET /fxdeals/{id}`
- **Response:** Returns the FX deal with the specified ID. If the FX deal is not found, returns a 404 status code.

### Update FX Deal
- **Endpoint:** `PUT /fxdeals/{id}`
- **Body:**
    ```json
    {
      "fromCurrencyISOCode": "GBP",
      "toCurrencyISOCode": "USD",
      "dealTimestamp": "2024-02-19T12:00:00",
      "dealAmount": 1500.00
    }
    ```
- **Response:** Updates the specified FX deal with the new details and returns the updated deal. If the FX deal is not found, returns a 404 status code.

### Delete FX Deal
- **Endpoint:** `DELETE /fxdeals/{id}`
- **Response:** Deletes the FX deal with the specified ID. If the FX deal is successfully deleted, returns a 200 status code. If the FX deal is not found, returns a 404 status code.

## Testing

To run the automated tests for this project, use the following command:
mvn test

This will execute the unit and integration tests defined in the `src/test/java` directory.