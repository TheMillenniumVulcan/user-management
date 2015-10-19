To run the service :<br>
1. Make sure you have Java 1.8<br>
2. Make sure you have maven configured properly<br>
3. Run "mvn clean install"<br>
4. Import project into Eclipse as a maven project<br>
5. Open UserManagement.java and run as "Java Application"<br>
   This will start Sparks' internal Jetty server which runs on localhost:4567<br>
6. Open Chrome and use AdvancedRESTClient or any other tool of your preference to exercise themethods on the service<br>
<br>
GET - "/users" - Gets all users<br>
POST - "/createUser" - Creates a new user from the passed in json<br>
PUT - "/updateUser" - Updates existing user with the passed in json<br>
DELETE - "/deleteUser" - Deletes the user matching the passed in id<br>