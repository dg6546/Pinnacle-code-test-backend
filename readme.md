# Getting Started

## Develop Environment
Windows 10  
psql (PostgreSQL) 14.2
IntelliJ IDEA

## Dependencies
check pom.xml

## Env virables
/resources/application.properties

```
spring.datasource.url=jdbc:postgresql://*ip:port*/*dbname*  
spring.datasource.username=*username*  
spring.datasource.password=*password*
```

## DB relation
Check /relation.png

## API & schemas
http://localhost:8080/swagger-ui.html  
Dummy data stored in /config

## API Examples

### Get Locations:  
/api/v1/location  
Response:
```json
[
    {
        "id": 1,
        "name": "California",
        "exemption": [
            "food"
        ],
        "tax": 9.75
    },
    {
        "id": 2,
        "name": "New York",
        "exemption": [
            "food",
            "clothing"
        ],
        "tax": 8.875
    }
]
```

### Get products:
/api/v1/good  
Response:

```json
[
    {
        "id": 1,
        "name": "book",
        "price": 17.99,
        "type": "Stationery"
    },
    {
        "id": 2,
        "name": "pencil",
        "price": 2.99,
        "type": "Stationery"
    },
    {
        "id": 3,
        "name": "potato chips",
        "price": 3.99,
        "type": "food"
    },
    {
        "id": 4,
        "name": "shirt",
        "price": 29.99,
        "type": "clothing"
    }
]
```

### Get 1 receipt:  
/api/v1/receipt

Body:
```json
{
  "id":"1"
}
```


Response:  
```json
{
    "location": {
        "id": 1
    },
    "listOfItem": [
        {
            "item": {
                "id": 1
            },
            "quantity": 1
        },
        {
            "item": {
                "id": 3
            },
            "quantity": 1
        }
    ]
}
```

### Create receipt
Request(Post)
```json
{
    "location": {
        "id": 1
    },
    "listOfItem": [
        {
            "item": {
                "id": 1
            },
            "quantity": 1
        },
        {
            "item": {
                "id": 3
            },
            "quantity": 1
        }
    ]
}
```

Response
```json
{
  "success": true,
  "message": "Receipt{id=4, location=Location{id=1, name='California', exemption=[food], tax=9.75}, listOfItem=[ReceiptItem{id=7, item=Goods{id=1, name='book', price=17.99, type='Stationery'}, quantity=1}, ReceiptItem{id=8, item=Goods{id=3, name='potato chips', price=3.99, type='food'}, quantity=1}], subtotal=0.0, tax=0.0, total=0.0}",
  "timestamp": "2022-05-13T19:43:10.170117100"
}
```
