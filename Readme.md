## **ExpensesTracker**

This Expense Tracker is a straightforward financial app that uses MySQL for reliable data storage and JUnit for testing accuracy. 
It helps users easily record and categorize expenses, providing a simple solution for effective expense tracking.
It also send reminder when the enddate of set budget had due

### **Features**

* Register
* Login
* addIncome
* addExpense
* get balance
* setBudget
* check budget
* findARecentBudget
* End A budget
* findAllBudget

### **HOW TO INSTALL PROGRAM**

* Install git
* Click on this link https://github.com/AdewuyiDelighted/Expense-Tracker
* Clone the project by following the cloning process
* Install mySql for proper database functions
* Ensure all dependencies in the the above project are well injected in your pom.xml
* Ensure your project is on the right server.port
* Open your Post man Application,paste the accurate url on the given url space

### **ENDPOINT**

### **POST REGISTER REQUEST**
This endpoint is the first step a for time user to enable them to add their information,
in order to make it store the the database
Required field are:
* Username
* Email
Method:POST
Content-Type:application/json

### **Response 1**
`status code 202 created
body
{
"data": {
"message": "Registration successful"
},
"successful": true
}`

### **Response 2**
`status code 400 BadRequest
body
{
"data": {
"message": "Password too weak"
},
"successful": false
}`

### **POST LOGIN REQUEST**
This is an endpoint that enable the user to have access to the application features,
in order to use the core funtionaility of the application
This endpoint required the user to be enter correct information that was enter when they register

Required field are:
* Username
  * Email
  Method:POST
  Content-Type:application/json

### **Response 1**
`status code 202 created
body
{
{
"data": {
"message": "Login successful"
},
"successful": true
}`
### **Response 2**
`status code 400 BadRequest
body
{
"data": {
"message": "Invalid details"
},
"successful": false
}`

## **POST addIncome**
This endpoint is used to add income to the users account in the application,
In order for them to able to engage in some transactions

Required field are:
* IncomeCategoryName
* Amount
* Email

    Method:POST
    Content-Type:application/json
### **Response 1**
`status code 200 ok
body {
"data": {
"message": "Income added successfully"
},
"successful": true
}`
### **Response 2**
`status code 400 Bad Request
body {
"data": {
"message": "Invalid amount"
},
"successful": false
}`
### **Response 3**
`status code 400 Bad Request
body {
"data": {
"message": "Invalid details"
},
"successful": false
}`

## **POST addExpense**
This endpoint is used to add expenses to the users account in the application ,
In order for them to able to engage in some transactions

Required field are:

* ExpenseCategoryName
* Amount
* Email

Method:POST
Content-Type:application/json

### **Response 1**
`status code 200 ok
body {
"data": {
"message": "Expenses added successfully"
},
"successful": true
}`
### **Response 2**
`status code 400 Bad Request
body {
"data": {
"message": "Invalid amount"
},
"successful": false
}`
### **Response 3**
`status code 400 Bad Request
body {
"data": {
"message": "Invalid details"
},
"successful": false
}`

## **GET getBalance**
This end point enable the user to get their balance,either after adding income or adding expense
it takes the user email
it return the user accurate balance
Required field are:
* userEmail

Method:GET
Header: Content-Type:application/json

### **Response 1**
`status code 200 ok
body {
"data": {
"message": "Your balance is #4500.0"
}
"successful": true
}
"successful": true
}`
### **Response 2**
`status code 400 Bad Request
body {
"data": {
"message": "Invalid details"
},
"successful": false
}`



















