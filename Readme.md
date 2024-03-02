Click this link for published documentation
https://documenter.getpostman.com/view/31783055/2sA2xb6vb2
ExpensesTracker
This Expense Tracker is a straightforward financial app that uses MySQL for reliable data storage and JUnit for testing accuracy. It helps users easily record and categorize expenses, providing a simple solution for effective expense tracking.It also send reminder when the endate of setted budget had due

Features

Register
Login
addIncome
addExpense
get balance
setBudget
check buget
findARecentBuget
End A budget
findAllBudget
HOW TO INSTALL PROGRAM

Install git
Click on this link https://github.com/AdewuyiDelighted/Expense-Tracker
Clone the project by following the cloning process
Install mySql for proper database functions
Ensure all dependencies in the the above project are well injected in your pom.xml
Ensure your project is on the right server.port
Open your Post man Application,paste the accurate url on the given url space
POST
endABudget
localhost:8087/budget/endABudget?email=Lawerence1234@gmail.com
This endpoint enable the user to end a recent and ruuning bugdet., it tkes the user email and it returns "Budget Ended Successfully"

Method:POST

Header: Content-Type:application/json

PARAMS
email
Lawerence1234@gmail.com

Example Request
endABudgetResponse1
View More
curl
curl --location --request POST 'localhost:8087/budget/endABudget?email=Lawerence1234%40gmail.com'
302 FOUND
Example Response
Body
Headers (5)
json
{
"data": {
"data": "Budget Ended Successfully"
},
"successful": true
}
GET
findAllBudgets
This endpoint enable the user to able to view all bugdet that have been created

it takes user email

it return list of user budget

Example Request
findAllBudgetResponse1
View More
curl
curl --location 'localhost:8087/budget/findAllBudget?email=Lawerence1234%40gmail.com'
302 FOUND
Example Response
Body
Headers (5)
View More
json
{
"data": {
"data": [
{
"id": 1,
"budgetBalance": 3000,
"budgetAmount": 3000,
"startDate": "2024-03-01",
"endDate": "2024-03-03",
"expenseAppTrackerId": 1,
"active": false
}
]
},
"successful": true
}
GET
findHistory