# hierarchy-back-end

#Run
#database created with docker
---

-open terminal
-find project file path and copy path

-cd {path}

-docker-compose up -d


-mvn clean install
-run application


#Main Models
---
-Employee
-Role
-Permission
-Privilige

#Relationship between each other
---
-Employee-Role -> @ManyToMany
-Role-Permission -> @ManyToMany
-Permission-Privilege -> @ManyToMany

#For Example Relationship between Employee-Role
---

  @ManyToMany
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(
                    name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

#For Example of Employee
---

-Retrieve all Employees - GET api/v1/employee

-Create a Employee - POST api/v1/employee

-Retrieve one Employee - GET api/v1/employee/{id}

-Update a Employee- PUT api/v1/employee/{id}

-Delete a Employee - DELETE api/v1/employee/{id} 




