# ing-interview
Ing banks treasury  interview task

Basic REST Web Service using Grizzly, Jersey, and Maven

What Do You Need?
   Maven 3.5.3 
  java 1.8

1 . Get project from github 
    git  clone https://github.com/sezayirdagtekin/ing-interview

2. Open a Terminal or Command Prompt window.

3 .   Compile the project:
       mvn clean compile  /  mvn clean install , execute unit tests
4 .    Execute the project: mvn exec:java

with Postman:
1. Display Customers: GET
   http://localhost:8080/bank/customers
   
2. Display Accounts:GET
   http://localhost:8080/bank/accounts
   
    {
        "accountId": 1001,
        "userName": "user1",
        "balance": 100
    },
    {
        "accountId": 1002,
        "userName": "user2",
        "balance": 2000
    }

   
 3. Transfer money between two users
    PUT
    http://localhost:8080/bank/user1/transfer/user2/300
    
    300 $ is transfered from user1 to user2
    
   4. Check user accounts again
     http://localhost:8080/bank/accounts
     
  
    {
        "accountId": 1001,
        "userName": "user1",
        "balance": 700
    },
    {
        "accountId": 1002,
        "userName": "user2",
        "balance": 2300
    }



