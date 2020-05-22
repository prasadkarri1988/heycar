# heycar

Java Case Study – Hey Car Backend Challenge
https://github.com/prasadkarri1988/heycar.git

I made it very easy you can test all service through my swagger UI.
http://localhost:8080/heycar/swagger-ui.html

------------------------------

Tools and technology  Used
----------------------------

Spring Boot 2.
Spring core,Transaction module.
Java 8.
H2 in-memory database
Git 
Junit integration test cases
Maven
Docker
JPA 
Swagger UI
CI/CD Process 

Documentation
The documentation can be found in swagger API : 

http://localhost:8080/heycar/swagger-ui.html

--------------------------------

APIs Exposed

1.  Get Search Request :

/heycar/search/v1


2. Post Request sendDealerInfoViaCSV:-

/heycar/upload/v1/upload_csv/{dealerId}/ 

3. Post sendDealerInfoViaJson

/heycar/upload/v1/vehicle_listings/{dealerId}/


--------------------------------
Sample URL


1. Get Search Request :

http://localhost:8080/heycar/search/v1?color=balck&page=0&pageSize=10

input data : 

/search/v1?make=mercedes&amp; model=222&amp; year=2019&amp; color=green


2. Post Request sendDealerInfoViaCSV:-

http://localhost:8080/heycar/upload/v1/upload_csv/{dealerId}/    

Example:-

http://localhost:8080/heycar/upload/v1/upload_csv/111/

Content-Disposition: form-data; name="file"; filename="C:\Users\prasad\Desktop\test.csv

3. Post sendDealerInfoViaJson


http://localhost:8080/heycar/upload/v1/vehicle_listings/{dealerId}/

example:-

http://localhost:8080/heycar/upload/v1/vehicle_listings/123/

input :-  


[
{
"code": "a",
"make": "renault",
"model": "megane",
"kW": 132,
"year": 2015,
"color": "red",
"price": 13990
}


--------------------------------

Import into your IDE as existing Maven project and run.

or

Download the source code and navigate to the root folder where the pom.xml is located and run

https://github.com/prasadkarri1988/heycar.git

mvn clean Install

java -jar heycar.jar

or 
 
use below docker commands for run in container. 

Step for Docker deployment

Docker build and run commands.

mvn clan install
docker build -t prasadkarri/heycar .
docker image ls
docker run -d -p 8080:8080 --name heycar prasadkarri/heycar
docker container ps
docker container stop  <<container id>>

Steps for open DB in WEB.


Spring boot application will run and load data in db.
I am using in memory db h2. So connect the DB to the following steps.
Open in the browser. http://localhost:8080/heycar/h2
Make sure use the jdbc url.
Jdbc url :: jdbc:h2:mem:testdb
username  : sa
password :  no password  empty only 

---------------------------------------
Questions Asked


• Problems you discovered ? 

1. The main problem I found here is the contract for request parameters was different when it      comes to saving the data.
Because of which, I need to create a mapper in order to specify the format for saving the data.
   
2. Data in CSV file some fields are missed  for record 4.If Dealer sends data in wrong or missing format you did not mention how to handle it.

3. The search result data is very huge and how to handle it was not mentioned.

4. Not mentioned any validation on input data.

---------------------------------------


Executed tests and results

1. I have written 16 test cases in TDD process.All test will run if you use below command.

     mvn clean install
	 
after successfully run the above command it will give you test results.
	 
----------------------------------------

Ideas you would like to implement if you had time - explain how you would implement them

1.If I had time, then I would have preferred to use Spring 5 in order to make all the calls async by making the full use of CompletableFuture and CompletionStage APIs.
2.I would like to implement micro service architecture to effectively handle auto scaling and load balancing.
3.Implement own CI/CD process using cloud,junkins and docker.
4.Define more validation on input data using custom and spring validations.
	 
 -------------------------------------
Decisions you had to take and why ?
1. In CSV file data upload if any fields are missed i am not processed the records because it is wrong format data.So I am sending response to user 
   how many records are processed and failed in a given CSV file.This will give you a user to view the failed records and update the records with missing fields.
   
2. I have implemented pagination to show the search results.Which will give better performance in terms of the search results if it has very huge results.

3. Based on your sample data I have designed my data model.

4. I have taken Spring boot for implement the service It is very easy to develop Spring Based applications with Java 

5. I have created documentation by using Swagger UI.To understand the application model,services,request and response.

6. version controlled by using GIT which is open source ,coordinating work on those files among multiple people.

7. The last one is a database, I could have used the general purpose database like Oracle, MongoDB.  But I moved to H2, since it's a small application and not meant for production.
   If you want to move to any production DB we need to change datasource.No need any code changes in class files.
   
8. The other one is to make full use of Lombok in order to remove the getters and setters in POJOs.

-------------------------------------

 Tested architectures and reasons why you chose them instead of other options
 
 1.I mostly focused on the layered architecture of Spring.
 2.From a design perspective, each dealer has their own set of cars that need to be placed in a repository.
 3.The dealer may send this information either by csv or by json.
 4.The customer will come in and search for his favourite car based upon his specifications. It will list down all the information of the available cars posed by all the dealers.
--------------------------------------


if you faces any issues in deployment process or doubts please reach out me.


Name: karri prasad
mail id: Prasadkarri1988@gmail.com
phone no:+49 15129001838

