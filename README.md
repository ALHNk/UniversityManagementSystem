This is the pure backend project done with Spring Boot 3 using Spring Security, JWT and Data JPA.
The project is only backend, so for testing endpoints I use POSTMAN.
The project is done in Intellij Idea, so I recommend you to use it. The application runs on the tomcat server included in Spring Boot.


  Firstly, configure database connection on src/main/resources/application.properties file   ------ change database, port , username and password for your owns(In My Code the app connected to UniversityManagement database)
  So, Now you can Start the Web-App
    Usually it runs on port:8080, but if it run in other port it would be seen in the run logs 

    This project implements 4 entities. ADMIN, TEACHER and STUDENT are Entities inherited by USER entity, and LESSONS entity.
    Each implements the CRUD, and saved into postgreSQL database.

The application has many endpoints with different types of the access.
  firstly 

        http://localhost:8080/auth/registration
        and
        http://localhost:8080/auth/login 
        endpoints are accessed by any client.

        /auth/registration is used for registration and requires next JSON(for example:          POST METHOD
                                                                          {
                                                                              "email": "ali@gmail.com",
                                                                              "password": "qwerty",
                                                                              "role": "ADMIN",
                                                                              "firsName": "Alikhan",
                                                                              "lastName": "Khamidulla",
                                                                              "phone": "+77472120506",
                                                                              "groupName": "IT-2303"
                                                                          }) the email, password, role fields are required.
        /auth/login used for login and requires only email and password : {"email": "ali@gmail.com","password": "qwerty",}, the login will return JWT token.               POST METHOD

        NEXT is the /admin  endpoints. For this access have only users with role admin
        http://localhost:8080/admin/add/student  is for creating new student (REQUIRES HEADERS Authorization bearer [JWT AUTHORIZATION TOKEN TAKEN FROM LOGIN], and JSON as like for registration          POST METHOD {
                                                                                                                                                                                            "email": "baqli@gmail.com",
                                                                                                                                                                                            "password": "qwerty",
                                                                                                                                                                                            "firsName": "Alikhffan",
                                                                                                                                                                                            "lastName": "Khamisecwdulla",
                                                                                                                                                                                            "phone": "+7747212wcdxsa0506",
                                                                                                                                                                                            "groupName": "IT-2303"}
        http://localhost:8080/admin/add/teacher   is also admin's endpoint just to create new teachers  as like students:      POST METHOD         {"email": "teacher@mail.kz",
                                                                                                                                                         "password": "teacher",
                                                                                                                                                         "firstName":"Mug",
                                                                                                                                                         "lastName": "Alim",
                                                                                                                                                         "department":"computer Science"
                                                                                                                                                        } 
                                                                                                                                                        
                                                                                                                                                        
       http://localhost:8080/admin/add/lesson  is endpoint to add leasson
                                 takes teacher's ID, title, and speciality.
                                                                   {"teacherID":"10",
                                                                   "title":"OOP",
                                                                   "speciality":"Computer Science"
                                                                   }
                                                                   Also POST METHOD and needs ADMIN authorization JWT token
      THEN there are some PUT METHODS 
                                      
                                        http://localhost:8080/admin/update/student/{id}
                                        http://localhost:8080/admin/update/teacher/{id}
                                        http://localhost:8080/admin/update/admin/{id}
                                        Each requieres ADMIN authorization, and json with any fields {not desired for updating fields can be empty}

                                                                                                            for example === url  http://localhost:8080/admin/update/student/4
                                                                                                            Authorization Bearer [JWT]  
                                                                                                            json : {"firstName": "MADI"}  

      AND some DELETE METHODS
                                    http://localhost:8080/admin/delete/student/{id}
                                    http://localhost:8080/admin/delete/teacher/{id}       
                                    http://localhost:8080/admin/delete/admin/{id}




    Then there are /auth endpoints, that can be accessed for any authorized user(ADMIN, TEACHER, STUDENT)

                   http://localhost:8080/auth/get/students                      ------------       GET METHOD return all the students
                   http://localhost:8080/auth/get/teachers                      ------------       GET METHOD return all the teachers
                   http://localhost:8080/auth/get/student/{id}                      ------------       GET METHOD return specific student
                   http://localhost:8080/auth/get/teacher/{id}                      ------------       GET METHOD return  specific teacher

  
    http://localhost:8080/lessons    is a get method endpoint  to get every lessons. CAN BE ACCESSED BY ANY CLIENT
    http://localhost:8080/lesson/{id}    is a get method endpoint  to get specific lesson. CAN BE ACCESSED BY ANY CLIENT

    http://localhost:8080/enrolment/add/lesson/{lessonID}   is an endpoint that can be accessed only by the STUDENT; it adds new lesson to the logged in STUDENT
    http://localhost:8080/enrolment/delete/lesson/{lessonID}  is an endpoint that can be accessed only by the STUDENT; it deletes lesson from the logged in STUDENT.
        
