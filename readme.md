In this application,

1) I created a inMemory DB H2, where the data is loaded on the application startup.
2) If we want to modify the data we can change it in data.sql file
3) We can view the data in the tables by browsing the following URL
"http://localhost:9090/h2/" in chrome browser
4)you can browse the below URL for customer specific amount spent in the past three
months and total reward points he earned.
"http://localhost:9090/getThreeMonthsTransactions/1" in postman. where 1-> is id of the customer. you can similarly browse for other customers like 2 and 3.

