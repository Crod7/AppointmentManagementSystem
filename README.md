## Appointment Management System
# Features:
Ability to add and remove appointments
![giphy](https://github.com/Crod7/Java-Appointment-Management-System/assets/106417568/d0b0227c-7c5a-4efd-8dfc-73660b8337fc)

Ability to manage customers and their appointments are linked to them
![giphy](https://github.com/Crod7/Java-Appointment-Management-System/assets/106417568/6cbab4b2-d44b-4e34-9a29-4e2095c5c367)

Purpose: To help the user organize and maintain customers and appointments within a MySQL database using a UI 
created with Java. The UI makes it simple to add, remove, modify and manage useful data about cusotmers 
and appointments.


## Directions To Set Up Program
The User should have basic knowledge in setting up a locally hosted MySQL servers using "MySQL Workbench".

IDE:
This setup is for intelliJ users.

MySQL Connector:
MYSQL Connector driver Version: mysql-connector-j-8.0.16


1. Set up a mySQL server. 
		Default Settings: run on local host,
					inside server, create a database called "client_schedule"
					make the username to login to the database: "root"
					the password should be "Passw0rd!"
		
2. Run ddl_for_DataBase.txt in the server.
3. Run dml_for_DataBase.txt in the server.
4. The folder included with this project has a file called "mysql-connector-java-8.0.16", make sure before you run the program to select FILE on the top left corner and select PROJECT STRUCTURE. Inside PROJECT STRUCTURE select LIBRARIES and hit the "+" button and select the JAVA option. Navigate inside the directory of this code folder and select the following jar file "Java-Appointment-Management-System/mysql-connector-java-8.0.16/mysql-connector-java-8.0.16.jar" Another menu will pop up asking where you want it to connect to, select MAIN. This ensures the application can connect to MySQL servers. 
5. Run the program and use as you like.

SIDE-NOTE: If you wish to change any of the server information such as the
database name or where it is hosted, make sure you edit these changes
in JDBC.java as well. This includes changing the username and password to access the database.


## Directions To Run Program
1. Login to the program either by entering USERNAME:admin PASSWORD:admin or by 
entering USERNAME:test PASSWORD:test
2. Once logged in the user can create customers and appointments.
3. The user should take note of any error messages that pop up when attempting an invalid action,
the error messages will tell the user how to resolve issues.
4. The user can also look at the reports tab to display a collection of information together to make
use of the data collected.


