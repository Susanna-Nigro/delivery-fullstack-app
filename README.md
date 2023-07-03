# Project Documentation
Application for managing a demo delivery service.


### Prerequisites
- Docker must be installed. If not, see [link](https://docs.docker.com/installation/.)
- npm must be installed. If not, see [link](https://nodejs.org/en/download)

## Server
Spring Boot application running inside a Docker container and connected to a MySQL database.

- Check the application configurations, they should correspond to:
~~~
-DMYSQL_USER=deliveryuser -DMYSQL_PASSWORD=test-pwd -DMYSQL_PORT=3307
~~~

### Instructions to run the backend application with Docker

- Go to server app
~~~
cd delivery-server
mvn install
~~~

- Create a network connection:
~~~
docker network create delivery-network
~~~

- Create a container for MySQL 8.0:
~~~
docker run -p 3307:3306 --name delivery-db-container --network delivery-network -e MYSQL_ROOT_PASSWORD=test-pwd -e MYSQL_USER=deliveryuser -e MYSQL_PASSWORD=test-pwd -e MYSQL_DATABASE=delivery -d mysql:8.0.33
~~~

- Populate the database tables:
~~~
docker exec -i delivery-db-container mysql -udeliveryuser -ptest-pwd delivery < db_delivery.sql
~~~

- Create an image:
~~~
docker build -t deliveryproject .
~~~

- Expose the application, through the specified port and created network:
~~~
docker run -p 8090:8080 --name deliveryproject-image --net delivery-network -e MYSQL_HOST=delivery-db-container deliveryproject
~~~

## Client
Application developed with React.

- Go to client app
~~~
cd delivery-client
npm run start
~~~

- Connect to the [localhost](http://localhost:3000) page in the browser, where a button will be displayed to click and show the order history list.

## Technologies used:
- Java 17
- Spring Boot 3.1
- MySQL 8.0
- Hibernate 6.2
- Junit 3.8
- Mapstruct 1.5
- React 18

## Swagger file:
Available at http://localhost:8090/swagger.html