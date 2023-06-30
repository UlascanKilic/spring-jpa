# Welcome to the JPA wiki!
This is a personal documentation and library for me. Since I often use JPA and Hibernate ORM in my projects, I wanted to make my own documentation. There is a database with 1-1, 1-n, n-1, n-n relationships in the project. You can see the tables of the database in the image below.

![](https://raw.githubusercontent.com/UlascanKilic/spring-jpa/main/images/db_schema.jpg)

## Table of Context
     
+ [What is JPA?](https://github.com/UlascanKilic/spring-jpa#what-is-jpa)
+ [What is Hibernate?](https://github.com/UlascanKilic/spring-jpa#what-is-hibernate)
+ [Entity Life Cycle](https://github.com/UlascanKilic/spring-jpa#entity-life-cycle)
  - [Persistence Context](https://github.com/UlascanKilic/spring-jpa#persistence-context)
  - [Transient State](https://github.com/UlascanKilic/spring-jpa#transient-state)
  - [Managed State](https://github.com/UlascanKilic/spring-jpa#managed-state)
    * [Persisting](https://github.com/UlascanKilic/spring-jpa#persisting)
    * [Attached to Persistence Context](https://github.com/UlascanKilic/spring-jpa#attached-to-persistence-context)
  - [Detached State](https://github.com/UlascanKilic/spring-jpa#detached-state)
    * [Detaching](https://github.com/UlascanKilic/spring-jpa#detaching)
    * [No Persistence Context Association](https://github.com/UlascanKilic/spring-jpa#no-persistence-context-association)
  - [Merging](https://github.com/UlascanKilic/spring-jpa#merging)
  - [Removed State](https://github.com/UlascanKilic/spring-jpa#removed-state)
    * [Removing](https://github.com/UlascanKilic/spring-jpa#removing)
    * [Deletion](https://github.com/UlascanKilic/spring-jpa#deletion)
  - [Transient State (Again)](https://github.com/UlascanKilic/spring-jpa#transient-state-again)
+ [Recap](https://github.com/UlascanKilic/spring-jpa#recap)
  - [Types of Relationship](https://github.com/UlascanKilic/spring-jpa#types-of-relationship)
    * [One-to-One (1-1) Relationship](https://github.com/UlascanKilic/spring-jpa#one-to-one-1-1-relationship)
    * [One-to-Many (1-N) Relationship](https://github.com/UlascanKilic/spring-jpa#one-to-many-1-n-relationship)
    * [Many-to-Many (N-N) Relationship](https://github.com/UlascanKilic/spring-jpa#many-to-many-n-n-relationship)
  - [Directional-Bidirectional Relationship](https://github.com/UlascanKilic/spring-jpa#directional-bidirectional-relationship)
    * [Directional Relationship](https://github.com/UlascanKilic/spring-jpa#directional-relationship)
    * [Bidirectional Relationship](https://github.com/UlascanKilic/spring-jpa#bidirectional-relationship)
+ [Most Used Annotations](https://github.com/UlascanKilic/spring-jpa#most-used-annotations)
  - [@Entity](https://github.com/UlascanKilic/spring-jpa#entity)
  - [@Table](https://github.com/UlascanKilic/spring-jpa#table)
  - [@Id]()
  - [@GeneratedValue]()
  - [@Column]()
  - [@Enumerated]()
  - [@JoinColumn]()
  - [@OneToOne]()
  - [@OneToMany]()
  - [@ManyToOne]()
  - [@ManyToMany]()
  - [@Inheritance]()
  - [@SuperBuilder]()
  - [@MappedSuperclass]()
  - [@Embeddable]()
  - [@EmbeddedId]()
 + [OneToOne Relation]()
 + [OneToMany Relation]()
 + [ManyToMany Relation With Join Table]()
 + [ManyToMany Relation With Composite Key]()
 + [Inheritance With Entities]()

***

## What is JPA? ##

**Java Persistence API** (JPA) materializes as a defining Java specification, which proves valuable in the persistence of data between Java objects and relational databases. JPA serves as a connective tissue between object-oriented domain models and relational database systems, streamlining data storage and retrieval.

***

## What is Hibernate? ##

**Hibernate** is an object-relational mapping (ORM) framework for Java applications that provides an efficient way to interact with relational databases. It simplifies the task of storing, retrieving, and manipulating data in the database by mapping Java objects to database tables and vice versa. Hibernate is an open-source framework that automates much of the repetitive database-related code, allowing developers to focus on business logic instead.
<details>
<summary> Jpa and Hibernate Architecture</summary>

![](https://raw.githubusercontent.com/UlascanKilic/spring-jpa/main/images/jpa-hibernate-arch.png)

</details>

***

## Entity Life Cycle ##

Each Hibernate entity undergoes a transformative journey within the framework, cascading through states of transience, management, detachment, or deletion. Comprehending the intricacies of these states, both in terms of conceptual and technical nuances, is imperative to wield Hibernate with dexterity

![](https://raw.githubusercontent.com/UlascanKilic/spring-jpa/main/images/lifecycle.png)

* #### Persistence Context ####
The Persistence Context is a crucial concept in JPA and Hibernate. It represents the environment or context in which entity objects are managed and their state is tracked during interactions with the database. The Persistence Context is typically associated with an EntityManager.

* #### Transient State ####
The entity starts in the transient state when it is first created using the "new" keyword. In this state, the entity is not yet associated with the Persistence Context or any database table. It is just a regular Java object.

* #### Managed State ####
  - ##### Persisting
    To make an entity managed, you need to persist it. Calling the EntityManager's persist() method on the transient entity transitions it to the managed state. The entity becomes associated with the Persistence Context, is assigned an identifier (primary key), and will be synchronized with the database during the next flush or commit operation.

  - ##### Attached to Persistence Context
    In the managed state, any changes made to the entity's properties are automatically tracked by the Persistence Context. The Persistence Context keeps a copy of the entity's original state to perform dirty checking and determine which modifications should be synchronized with the database.

* #### Detached State ####
  - ##### Detaching
     An entity transitions from the managed state to the detached state when it is detached from the Persistence Context. This can happen explicitly by calling the EntityManager's **detach()** or **clear()** methods, or implicitly when the Persistence Context is closed or the transaction ends.

  - ##### No Persistence Context Association
    In the detached state, the entity is no longer associated with the Persistence Context. However, it still retains its identifier and any modifications made to the entity will not be tracked by the Persistence Context.

* #### Merging ####
If a detached entity needs to be reattached and have its changes synchronized with the database, the EntityManager's **merge()** method is used. This merges the detached entity with the Persistence Context, creates a new managed entity with the same state, and returns the managed entity. The original detached entity remains detached.

* #### Removed State ####
  - ##### Removing
     The EntityManager's **remove()** method is used to mark a managed entity for removal from the database. This transitions the entity to the removed state.

  - ##### Deletion
    During the next flush or commit operation, the entity is deleted from the database, and its association with the Persistence Context is severed.

* #### Transient State (Again) ####
After being removed, the entity returns to the transient state. It can be considered as a new entity, and if needed, it can be persisted again to start a new lifecycle.


***

## Recap ##

### Types of Relationship ###

#### One-to-One (1-1) Relationship ####

In a one-to-one relationship, one record in the first table (let's call it Table A) is related to only one record in the second table (Table B), and vice versa. This means that for each row in Table A, there is exactly one corresponding row in Table B, and vice versa. This relationship is relatively rare, as it is typically more efficient to combine the two tables into a single table.
Example: Consider two tables, "Employee" and "EmployeeAddress." Each employee can have only one address, and each address belongs to only one employee. Here, the relationship between "Employee" and "EmployeeAddress" is a one-to-one relationship.

***

```
Employee Table:
EmployeeID | Name    | Department
-------------------------------
1          | John    | HR
2          | Jane    | Finance
3          | Alex    | IT

EmployeeAddress Table:
EmployeeID | Address
---------------------
1          | 123 Main St
2          | 456 Oak Ave
3          | 789 Elm Rd

```

#### One-to-Many (1-N) Relationship ####

In a one-to-many relationship, one record in the first table (Table A) is associated with one or more records in the second table (Table B). However, each record in Table B can be related to only one record in Table A. This is the most common type of relationship found in databases.
Example: Consider two tables, "Department" and "Employee." Each department can have many employees, but each employee can belong to only one department. Here, the relationship between "Department" and "Employee" is a one-to-many relationship.

***

```
Department Table:
DepartmentID | Name
-------------------
1            | HR
2            | Finance
3            | IT

Employee Table:
EmployeeID | Name    | DepartmentID
-----------------------------------
1          | John    | 1
2          | Jane    | 2
3          | Alex    | 3
4          | Sarah   | 2
5          | Mike    | 3

```

#### Many-to-Many (N-N) Relationship ####
In a many-to-many relationship, one or more records in the first table (Table A) can be associated with one or more records in the second table (Table B), and vice versa. To represent this relationship in a relational database, a junction table (also known as an associative table or linking table) is used. The junction table connects the primary keys of both tables through foreign keys.
Example: Consider two tables, "Student" and "Course." Each student can enroll in multiple courses, and each course can have multiple students. To represent this relationship, a junction table "Enrollment" is used.

***

```
Student Table:
StudentID | Name
----------------
1         | John
2         | Jane
3         | Alex

Course Table:
CourseID | Name
---------------
101      | Math
102      | Science
103      | History

Enrollment Table:
StudentID | CourseID
--------------------
1         | 101
1         | 102
2         | 102
3         | 103

```

### Directional-Bidirectional Relationship ###
In the context of relationships in database management systems, "directional" and "bidirectional" refer to the way data can be accessed and traversed between related tables. Let's take a closer look at each:

#### Directional Relationship ####
In a directional relationship, data can be accessed and traversed in only one direction between the related tables. This means that one table acts as the parent or master table, and the other table acts as the child or detail table. **The relationship is established using foreign keys in the child table**, which reference the primary key in the parent table.
In this type of relationship, you can easily access data from the child table by referencing the foreign key, but you cannot directly access data from the parent table using the child table's foreign key.

Example: Consider two tables, "Author" and "Book." Each book can have only one author, but each author can have multiple books. The relationship between "Author" and "Book" is a directional relationship because you can easily find the books written by a specific author by using the author's primary key as a foreign key in the "Book" table. However, you cannot directly find the author of a specific book using the "Author" table.

***
```
Author Table:
AuthorID | Name        | Country
-------------------------------
1        | John Smith  | USA
2        | Jane Doe    | UK
3        | Alex Johnson| Canada

Book Table:
BookID | Title               | AuthorID (Foreign Key to Author Table)
------------------------------------------------------------
101    | The Book of Life   | 1
102    | Beyond the Stars   | 2
103    | Coding Adventures  | 1
104    | Mystery of the Forest | 3
105    | Journey to Mars    | 1

```
#### Bidirectional Relationship ####
In a bidirectional relationship, data can be accessed and traversed in both directions between the related tables. This means that each table can act as both the parent and the child table simultaneously. The relationship is established using foreign keys in both tables, allowing you to navigate data between the tables in either direction.
In this type of relationship, you can easily access data from both tables using their respective foreign keys.

Example: Consider two tables, "Student" and "Course." Each student can enroll in multiple courses, and each course can have multiple students. The relationship between "Student" and "Course" is a bidirectional relationship because you can find the courses a specific student is enrolled in using the "Student" table or find the students enrolled in a specific course using the "Course" table.

***

```
Student Table:
StudentID | Name
----------------
1         | John
2         | Jane
3         | Alex

Course Table:
CourseID | Name
---------------
101      | Math
102      | Science
103      | History

Enrollment Table:
StudentID | CourseID
--------------------
1         | 101
1         | 102
2         | 102
3         | 103

```

In this bidirectional relationship:

1. The "Student" table contains information about the students.
2. The "Course" table contains information about the courses.
3. The "Enrollment" table acts as a junction table, representing the many-to-many relationship between students and courses. Each row in the "Enrollment" table connects a student to a course through their respective IDs.

Now, using this bidirectional relationship:

* You can easily find the courses a specific student is enrolled in by referencing the "StudentID" in the "Enrollment" table.
* You can also find the students enrolled in a specific course by referencing the "CourseID" in the "Enrollment" table.

For example, if you want to find the courses John is enrolled in, you can look for rows in the "Enrollment" table where the "StudentID" is 1. Similarly, if you want to find all students enrolled in the Math course, you can look for rows in the "Enrollment" table where the "CourseID" is 101.

***

## Most Used Annotations ##

### @Entity ###

The @Entity annotation is used to mark a Java class as a persistent entity, which means that instances of this class will be mapped to database records. It is a crucial annotation used in ORM (Object-Relational Mapping) frameworks to map Java objects to database tables.

Here's a simple code example to illustrate the use of @Entity:

```
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private int age;
}
```

@Entity annotation indicates that the Student class is a persistent entity, and its instances will be mapped to database records. The entity name defaults to the unqualified class name (Student in this case) but can be explicitly specified with name attribute.

***

### @Table ###

@Table annotation is used in conjunction with the @Entity annotation to specify the details of the database table that will be used to store instances of the entity class. The @Table annotation allows you to customize various attributes related to the database table.

#### Attributes ####

##### name (Optional) #####

**name :** specifies the name of the table in the database associated with the entity. If not specified, the default table name is derived from the unqualified class name of the entity. Example: @Table(name = "students").

***

##### schema (Optional) #####

**schema :** specifies the name of the database schema where the table should be created. This is useful when working with multi-schema databases. Example: @Table(name = "students", schema = "university").

***

##### catalog (Optional) #####

**catalog :** specifies the name of the database catalog where the table should be created. This is used in databases that support catalogs. Example: @Table(name = "students", catalog = "university_catalog").

***

##### uniqueConstraints (Optional) #####

**uniqueConstraints :** defines unique constraints on the table. It is an array of @UniqueConstraint annotations, each specifying a set of columns that should have unique values. Example:

***

```
@Table(name = "students", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "email"}),
    @UniqueConstraint(columnNames = {"student_id"})
})

```

In this example, we have specified two unique constraints. The first constraint ensures that the combination of "name" and "email" columns is unique, and the second constraint ensures that the "student_id" column has unique values.

***

##### indexes (Optional) #####

**indexes:** defines indexes on the table. It is an array of @Index annotations, each specifying the columns to be indexed and other attributes related to indexing. Example:

```
@Table(name = "students", indexes = {
    @Index(columnList = "name"),
    @Index(name = "idx_age", columnList = "age")
})
```
In this example, we have specified two indexes. The first index indexes the "name" column, and the second index indexes the "age" column and names the index as "idx_age".

***

Using the @Table annotation with its attributes allows you to have fine-grained control over the name and structure of the database table associated with your entity class, which can be helpful in various database and schema design scenarios.

```
@Entity
@Table(
    name = "tbl_students", // Custom table name
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "email"}), // Composite unique constraint
        @UniqueConstraint(columnNames = {"student_code"}) // Unique constraint for the student_code column
    },
    indexes = {
        @Index(columnList = "name"), // Index on the name column
        @Index(name = "idx_age", columnList = "age") // Named index on the age column
    }
)
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50, unique = true, name = "student_code")
    private String studentCode;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    private int age;
}

```

In this example, we have used the @Table annotation with its name, uniqueConstraints, and indexes attributes:

1. **name = "tbl_students":** Specifies the custom table name as "tbl_students".
2. **uniqueConstraints:** Defines two unique constraints on the table. One constraint is a composite unique constraint on the combination of "student_id" and "email" columns, and the other is a unique constraint on the "student_code" column.
3. **indexes:** Defines two indexes on the table. One index is on the "name" column, and the other is a named index "idx_age" on the "age" column.

<details>
<summary>Registering a User</summary>

```
curl --location --request POST 'localhost:9004/api/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "amangarg1995sep@gmail.com",
    "password": "amangarg",
    "registerAsAdmin": true
}'
```

> ⚠️ If you re-register an email twice, you'll get the "email in use" error

</details>


