# Calculate the distance and invite the customers who are under a distance of 100 KM from a given coordinates.

### Requirements for running the code:
* JDK 8 or higher
* Maven (check by running mvn -v)

### How to run the application
* Navigate to the directory containing the project.
* Run the following command: 
`./mvnw spring-boot:run`
  
* This will start an instance of apache Tomcat server which would be running on port 8080
* Go to: [The home page](http://localhost:8080/)
* Click on the button which says __View Customers__
* You will be presented with the data in the input list in a tabular format.
* The entries are paginated and can be sorted and there is a search feature as well.
* To generate the invitation list which would only contain the user_id and names of the people who live under 100 KM from the Intercom Office 
click on __Generate invitation list__.
  
* You will be presented with a page which contains the list in a sorted manner. 
* There is a button on the top of the table which says __PDF__. It can be used to generate and download the PDF copy of the result.
  
### How to run the tests
`./mvnw test`

### Frameworks used
* Spring boot
* Datatables
* Junit 5