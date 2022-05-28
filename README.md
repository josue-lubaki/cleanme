# CleanMe [![CircleCI](https://circleci.com/gh/josue-lubaki/cleanme/tree/main.svg?style=svg)](https://circleci.com/gh/josue-lubaki/cleanme/tree/main)
Backend Application CleanMe

# Models
<ol>
<li>Address</li>

```java
public class Address {
    private String apartment;
    private String street;
    private String city;
    private String state;
    private String zip;
}
```

Exemple JSON

```json
{
    "apartment": "2B",
    "street": "Bowne Street",
    "city": "Trois-Rivières",
    "state": "Québec",
    "zip": "G8V 0C9"
}
```

<br><br>
<li>User</li>

```java
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
}
```
Exemple JSON

```json
{
    "firstName": "Josue",
    "lastName": "Lubaki",
    "email": "josuelubaki@gmail.com",
    "phone": "(819) 567-8984",
    "Address": {
        "apartment": "2B",
        "street": "Bowne Street",
        "city": "Trois-Rivières",
        "state": "Québec",
        "zip": "G8V 0C9"
    } 
}
```

<br><br>
<li>Reservation</li>

```java
public class Reservation {
    private User user;
    private Address address;
    private LocalDate date;
    private LocalTime time;
}
```

<br>
Request : <br>
<b>POST Method :</b>

```http request
    https://clean-me.herokuapp.com/api/v1/reservations
```

Exemple JSON

```json
{
    "firstName": "Josue",
    "lastName": "Lubaki",
    "email": "josuelubaki@gmail.com",
    "phone": "(819) 567-8984",
    "Address": {
        "apartment": "2B",
        "street": "Bowne Street",
        "city": "Trois-Rivières",
        "state": "Québec",
        "zip": "G8V 0C9"
    },
    "date": "2022-05-27",
    "time": "12:00:00"
}
```
</ol>


