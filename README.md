# Getting Started
This exercise is built by using Java8,SpringBoot and Gradle.

### Executing Application
Follow below steps to execute application
* Import the gradle project onto your Java IDE
* Run clean build to execute tests.
* Open Class VideostreamApplication which is under pkg -> com.reply.videostream
and run as standalone Java Application.
* Use post man to call RESTful services.
or
* Take the jar file from the root folder and execute the command
java -jar videostream-0.0.1-SNAPSHOT.jar

### RESTful api calls for User endpoint
* Register User -> url : http://{hostname:port}/videostream/api/users
 method: Post
 eg: Payload
 {
 	"userName" : "User1",
 	"password" : "passwoR2",
 	"email" : "test2@gmail.com",
 	"dob" : "1989-07-16T19:23:51",
 	"ccNumber" : "1234567810121314"
 } 
 or
 {
 	"userName" : "User1",
 	"password" : "passwoR2",
 	"email" : "test2@gmail.com",
 	"dob" : "1989-07-16T19:23:51"
 }
 
* Retrieve All Users ->  url : http://{hostname:port}/videostream/api/users
method : Get

* Retrieve All Users parameter ->  url : http://{hostname:port}/videostream/api/users?creditCard=yes
method : Get

* Retrieve All Users parameter ->  url : http://{hostname:port}/videostream/api/users?creditCard=no
method : Get

### RESTful api calls for Payment endpoint
* Add Payment Details -> url : http://{hostname:port}/videostream/api/payments
 method: Post
 Payload example:
 {
     "ccNumber" : "1234567810121314",
     "amount" : "999"
 }
 
 ### Postman scripts also attached to the project.
