# Employee Management System
#### RESTful Web Service using Spring Boot

##### TODO
- [x] Model: [Employee](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/java/com/example/employeemgmt/model/Employee.java)
- [x] Repository: [EmployeeRepository](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/java/com/example/employeemgmt/repository/EmployeeRepository.java)
- [x] Service: [EmployeeService](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/java/com/example/employeemgmt/service/EmployeeService.java)
- [x] Controller: [EmployeeController](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/java/com/example/employeemgmt/controller/EmployeeController.java)
- [x] MySQL Database Configuration & Credentials: [application.properties](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/resources/application.properties)
- [x] Initial DB data: [import.sql](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/main/resources/import.sql)
- [x] Test Coverage:
- Repository
  - [x] Integration Test: [EmployeeRepositoryIntegrationTest](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/test/java/com/example/employeemgmt/repository/EmployeeRepositoryIntegrationTest.java)
- Service
  - [x] Unit Test: [EmployeeServiceTest](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/test/java/com/example/employeemgmt/service/EmployeeServiceTest.java)
  - [x] Integration Test: [EmployeeServiceIntegrationTest](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/test/java/com/example/employeemgmt/service/EmployeeServiceIntegrationTest.java)
- Controller
  - [x] Unit Test: [EmployeeControllerTest](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/test/java/com/example/employeemgmt/controller/EmployeeControllerTest.java)
  - [x] Integration Test: [EmployeeControllerIntegrationTest](https://github.com/sebastian-tf97/employeemgmt-spring-boot/blob/main/src/test/java/com/example/employeemgmt/controller/EmployeeControllerIntegrationTest.java)
- [ ] DAO Implementation
- [ ] Javadoc Documentation

<br />

### API
**1. Get All Employees**

`GET http://localhost:8080/employees/`

Returns json data about all employees.

* **URL:** <br />
  `/employees`


* **Method:** <br />
  `GET`


* **Success Response:**<br />
  * **Code:** 200 <br />
  * **Sample Response:**
      ```json
      [
        {
            "empid": 1000,
            "name": "Employee A",
            "age": 23,
            "gender": "M",
            "designation": "Systems Engineer",
            "department": "IT"
        },
        {
            "empid": 1001,
            "name": "Employee B",
            "age": 23,
            "gender": "F",
            "designation": "Assistant Systems Engineer",
            "department": "IT"
        },
        {
            "empid": 1002,
            "name": "Employee C",
            "age": 23,
            "gender": "M",
            "designation": "Recruiter",
            "department": "HR"
        },
        {
            "empid": 1003,
            "name": "Employee D",
            "age": 23,
            "gender": "F",
            "designation": "Systems Engineer",
            "department": "IT"
        },
        {
            "empid": 1004,
            "name": "Employee E",
            "age": 23,
            "gender": "M",
            "designation": "Database Administrator",
            "department": "IT"
        },
        {
            "empid": 1005,
            "name": "Employee B",
            "age": 23,
            "gender": "M",
            "designation": "Database Administrator",
            "department": "IT"
        }
      ]
    ```
    <br />
  
**2. Get Single Employee By Employee Id**

`GET http://localhost:8080/employees/1000`

Returns json data about a single employee having the input id.

* **URL** <br />
  `/employees/:empid`


* **Method:** <br />
  `GET`


* **Success Response:**

  * **Code:** 200 <br />
  * **Sample Response:**
      ```json
      {
        "empid": 1000,
        "name": "Employee A",
        "age": 23,
        "gender": "M",
        "designation": "Systems Engineer",
        "department": "IT"
      }
      ```
    <br />
  
**3. Get Single Employee By Employee Name**

`GET http://localhost:8080/employees?name=Employee A`

Returns json data about a single employee having the input name.

* **URL** <br />
  `/employees`


* **Method:** <br />
  `GET`


*  **URL Params** <br />
   `name=[string]`


* **Success Response:**

  * **Code:** 200 <br />
  * **Sample Response:**
      ```json
      [
        {
            "empid": 1001,
            "name": "Employee B",
            "age": 23,
            "gender": "F",
            "designation": "Assistant Systems Engineer",
            "department": "IT"
        },
        {
            "empid": 1005,
            "name": "Employee B",
            "age": 23,
            "gender": "M",
            "designation": "Database Administrator",
            "department": "IT"
        }
      ]
      ```
    <br />  


**4. Create A New Employee**

`POST http://localhost:8080/employees/`
```json
{
    "name": "Employee Y",
    "age": 23,
    "gender": "M",
    "designation": "New Designation",
    "department": "New Department"
}
```

Creates a new Employee with the given data and returns json data the newly created employe.

* **URL** <br />
  `/employees`


* **Method:** <br />
  `POST`


*  **Request Body Params** <br />
   `name=[string]`   
   `age=[integer]`   
   `gender=[string]`   
   `designation=[string]`   
   `department=[string]`   


* **Success Response:**

  * **Code:** 200 <br />
  * **Sample Response:**
      ```json
      {
        "empid": 1006,
        "name": "Employee Y",
        "age": 23,
        "gender": "M",
        "designation": "New Designation",
        "department": "New Department"
      }
      ```
    <br />  