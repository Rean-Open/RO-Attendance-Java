# RO-Attendance-Java
Rean-Open - QR Attendance API with Java/Spring

## Prerequisites

Make sure you have installed the following software.

* Java 17
* Apache Maven 3.6.x
* Docker

## Build 
Open a terminal, and switch to the root folder of the project, and run the following command to build the whole project.

```bash
docker-compose up postgres // start up a postgres
mvn clean install // build the project
```

Run the application.

```bash
mvn spring-boot:run
// or from command line after building
java -jar target/xxx.jar
```

## Available API

### Version 0.1 - 2023
- Login: "http://localhost:8080/auth/signin"
- User Me: "http://localhost:8080/auth/me"
- Employee List: "http://localhost:8080/api/employees"
- Employee by Id: "http://localhost:8080/api/employees/1"
- Working Schedule: "http://localhost:8080/api/working-schedules"
- Working Schedule by Id: "http://localhost:8080/api/working-schedules/1"
- Attendance Policies: "http://localhost:8080/api/attendance-policies"
- Attendance Policy by Id: "http://localhost:8080/api/attendance-policies/1"
- Report Summary: "http://localhost:8080/api/report/staff-list?code=EMP-00001&gender=MALE"
- Report Summary by Gender: "http://localhost:8080/api/report/total-staff-by-gender"
- Report by Staff: "http://localhost:8080/api/report/staff-attendance-detail?code=EMP-00001&from_date=2023-01-01&end_date=2023-01-31"

## Team
### Initial Project Team - 2023
- Lecturer: mt@osify.com
- Ms. Thida TEK
- Mr. Pengleng HUOT
- Mr. Keomorakort MAN
- Ms. Malen SUN
- Mr. Rathana SOK
- Mr. Sovonndara VONG
