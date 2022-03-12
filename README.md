# Cinema Room Rest Service
A REST Service that delivers common functionality for both users and managers to interface with. This API was built using the Spring Framework and follows the Model-View-Controller design pattern.
## Endpoint Definitions

- "/seats": handles GET requests and returns the information about the movie theatre. 
- "/purchase": handles POST requests and marks a booked ticket as purchased.
- "/return": handles POST requests and allows customers to refund their tickets.
- "/stats": handles POST requests and returns movie theatre statistics.

## Building a Request 

The URI prefix for this project is the local machine: http://localhost:28852. The following are JSON formats necessary to build a successful HTTP Request.

- GET "/seats": No Request Body or URL Parameters necessary
- POST "/purchase": 
  
    ```
    {
      "row": 3,
      "column": 4
    }
    ```
  
- POST "/return": 
  
    ```
    {
      "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
    }
    ```
    
- POST "/stats":
  - > URL Parameters: "/?password=super_secret"

## Receiving a Response
These are response examples from successfully processed requests

- GET "/seats":
    
    ```
    {
     "total_rows":9,
     "total_columns":9,
     "available_seats":[
        {
           "row":1,
           "column":1,
           "price":10
        },
        {
           "row":1,
           "column":2,
           "price":10
        },
        {
           "row":1,
           "column":3,
           "price":10
        },

        ........

        {
           "row":9,
           "column":8,
           "price":8
        },
        {
           "row":9,
           "column":9,
           "price":8
        }
      ]
    }
    ```

- POST "/purchase": 
  
    ```
    {
      "row": 3,
      "column": 4,
      "price": 10
    }
    ```

- POST "/return": 
  
    ```
    {
      "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
      "ticket": {
          "row": 3,
          "column": 4,
          "price": 10
      }
    }
    ```
    
- POST "/stats":

    ```
    {
      "current_income": 30,
      "number_of_available_seats": 78,
      "number_of_purchased_tickets": 3
    }
    ```
