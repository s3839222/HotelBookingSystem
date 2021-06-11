# Readme
Name: Abu Kuhafah	Student Number: S3839222 	Course : Further Programing

This project is a system to manage "Hotdesking" methodolgogy to allocate tables to Arub Employees.
It prevents the employees from sitting in the same table every time. Furthermore due to Covid having
huge impacts in everyones lives there is sitting condition placed for that, one being social distancing
where the employees are unable to sit next to each other and are a table away, another is complete 
lockdown where the meployees arnt able to make any booking during that phase. Once an employee books
a table, it goes into pending where after 48 hours it is cancelled. During that time an admin can accpet
the booking from the employee and thus changing the status of their booking.
Along with the booking system there is also an account managemenet system, where the employees are are 
able to manage their own accounts and change details such as their password. On the other hand Admins are
able to edit the employees account aswell as delete them. Admins are also able to generate reports for both
bookings and employees which are generated as CSV files.

It is easy to run and compile as the main file is called main and once run, then the user is given the option
to login, register or reset password. Here the user is able to login if they have an account or create a 
new account. After login they are able to make booking or manage their account through the navigation bar 
at the top of the application. After making a booking/manging details they can signout and exit the applicaiton.

Another design pattern besides MVC that was used was singleton. This was done as singleton encapsulates a resource
and makes it readily available thorughout the whole application. Thus a user singleton was great for this 
applicaiton as it allowed the user to be available to other classes for it's usage. 

The design has changed greatly through the process, this was the result of new addition information that had to 
be added/removed to better fit the application and the usage. An example of this is the registation where at first
did not have any secret question and answer for the user to include in their registration process. This change was
made to better fit the application to real life applicaction where that is very common as it allows an increase in 
security. Another change is the navigation system, at first it was more like a mobile app where there was buttons 
in the middle of the page for navigation, however this application is not mobile based and to make it more 
proffessional it was deccided that it was better to include the navigation bar at the top.


For Admin login:
Username: test
Password: test

For Employee login:
Username: strange
Password: strange
