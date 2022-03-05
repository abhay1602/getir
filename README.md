# Getir Assignment
- Controller layer contains the necessary api for Order management system for book.

- To start the application with Docker
1. Build the application - mvn clean install
2. Build with docker - docker build docker build -t getir-test .
3. Run the docker image - docker run -p8090:8090 -t getir-test

Assumptions:
- Request and Response fields are kept minimal. This can grow depends on 
requirment.
  
   
- One custom excpetion created to handle the error for customer api. We can create for others in case needed and 
handle them with global exception handler.
  

- Unit test cases covers most of the code from controller to repository layer. There is one integration test for the customer and 
new integration test can be added with more data from data.sql . There are separate unit test for each layer.
  
- Once book sold then status will be updated to sold and it will not be shown to 
other users again.

   

   


