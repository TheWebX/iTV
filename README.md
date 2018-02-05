# AngularJS Spring MongoDB - iTV Test

This is the web frontend for select the products, calculate the discount and for checkout with PayPal.

* The Store page will display the products and the last promotions available
* Inside the Cart will be displayed the price before the promotions, the total discount and final price
* Inside the PayPal official transaction page will be displayed the products, discount and total to pay

#### #### How to configure
* Edit the file application file into the context for select the spring profile "mongodb" or "mock"
* Switch the comment inside the application boot class for exclude the mongo autoconfig for don't start the db client (mock profile)

#### #### How to run the app
* Use the command "mvn spring-boot:run" inside the project folder
* Go on the page http://localhost:8080