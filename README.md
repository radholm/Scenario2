# Scenario 2

Scenario 2 is a employee management REST API service

## Installation

Build project with Maven. Create a Microsoft SQL Server database 'scenario2db'.

```bash
call mvn clean -f %cd%\scenario2\pom.xml
call mvn package -f %cd%\scenario2\pom.xml
```

## Usage

```
	These are all the available operations:

	GET 	localhost:8080/api/v1/<roleType>
		
		Fetches a list of specified role.
		Remember that the subordinates are always referenced inside the CEO and managers.
		Example:
			"GET localhost:8080/api/v1/EMPLOYEE"

	GET 	localhost:8080/api/v1/employee/<id>
	
		Fetches an employee object based on the id.
		Example:
			"GET localhost:8080/api/v1/employee/1"

	POST 	localhost:8080/api/v1/<roleType> 
		OR
		localhost:8080/api/v1/<roleType>/<superiorId>
		WITH
		Body = {"firstName"="example", "lastName"="exampleson", "rank"="1"}
		Content-type = application/json

		Creates an employee.
		<superiorId> makes the employee an subordinate ('Regular' employees MUST specify their superior/manager).
		CEO cannot manage a 'regular' employee.
		Example:
			"POST localhost:8080/api/v1/EMPLOYEE/2"
			Body = {"firstName"="example", "lastName"="exampleson", "rank"="1"}
			Content-type = application/json

	DELETE	localhost:8080/api/v1/<employeeId>

		Deletes an employee.
		Employees with subordinates cannot be deleted.
		Example:
			"DELETE localhost:8080/api/v1/1"

	PUT	localhost:8080/api/v1/<employeeId>/<roleType>
		OR
		localhost:8080/api/v1/<employeeId>/<roleType>/<superiorId>
		WITH
		Body = {"firstName"="example", "lastName"="exampleson", "rank"="1"}
		Content-type = application/json

		Specify and edit the employee of <employeeId> and current/wanted <roleType>.
		Managers with subordinates cannot be downgraded to employees (but can become managed by other managers).
		
		Example:
			"PUT localhost:8080/api/v1/4/MANAGER/2"
			(employee with id 4 upgrades to manager and becomes a subordinate of manager (with id 2))
			Body = {"firstName"="example", "lastName"="exampleson", "rank"="1"}
			Content-type = application/json
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)