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
  - [@Id](https://github.com/UlascanKilic/spring-jpa#id)
  - [@GeneratedValue](https://github.com/UlascanKilic/spring-jpa#generatedvalue)
  - [@Column](https://github.com/UlascanKilic/spring-jpa#column)
  - [@Enumerated](https://github.com/UlascanKilic/spring-jpa#enumerated)
  - [@JoinColumn](https://github.com/UlascanKilic/spring-jpa#joincolumn)
  - [@Inheritance](https://github.com/UlascanKilic/spring-jpa#inheritance)
  - [@MappedSuperclass](https://github.com/UlascanKilic/spring-jpa#mappedsuperclass)
  - [@Embeddable](https://github.com/UlascanKilic/spring-jpa#embeddable)
  - [@EmbeddedId](https://github.com/UlascanKilic/spring-jpa#embeddedid)
 + [Relationships]()
    - [OneToOne Relation]()
    - [OneToMany Relation]()
    - [ManyToMany Relation With Join Table]()
    - [ManyToMany Relation With Composite Key]()
    - [Inheritance With Entities]()

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

The `@Entity` annotation in Jakarta Persistence (formerly known as Java Persistence API or JPA) is used to mark a Java class as a persistent entity, which means that instances of this class will be mapped to database records. It is a crucial annotation used in ORM (Object-Relational Mapping) frameworks to map Java objects to database tables.

Here's a simple code example to illustrate the use of `@Entity`:

```java
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private int age;

    // Constructors, getters, setters, and other methods...
}
```

In this example, we have a `Student` class marked with the `@Entity` annotation. Let's go through the properties of this annotation:

1. `@Entity`: This annotation indicates that the `Student` class is a persistent entity, and its instances will be mapped to database records. The entity name defaults to the unqualified class name (`Student` in this case) but can be explicitly specified with `name` attribute.

2. `@Id`: This annotation is used to specify the primary key attribute of the entity. In this example, we have marked the `id` field as the primary key for the `Student` entity. The primary key uniquely identifies each record in the database table.

3. `@GeneratedValue`: This annotation is used to specify the strategy for generating the primary key values. In this example, we have used `GenerationType.IDENTITY`, which means that the database will automatically generate unique primary key values (usually through auto-incrementing).

Other properties of the `@Entity` annotation include:

- `name`: Specifies the name of the entity in the database. By default, it is the simple name of the entity class.
- `schema`: Specifies the schema name of the table associated with the entity.
- `catalog`: Specifies the catalog name of the table associated with the entity.
- `uniqueConstraints`: Specifies unique constraints on the table.

Here's an example that includes more properties of the `@Entity` annotation:

```java
@Entity
@Table(name = "students", schema = "university", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(name = "student_age")
    private int age;

    // Constructors, getters, setters, and other methods...
}
```

In this extended example:

- `@Table`: This annotation allows you to specify the table name and other table-related properties for the entity. Here, we have explicitly set the table name to "students" and the schema to "university." We have also added a unique constraint on the "name" column to ensure that each student's name is unique.

- `@Column`: This annotation is used to specify column-related properties for the entity attributes. In this case, we have used it to set the column name for the `id` attribute to "student_id" and the column name for the `age` attribute to "student_age." We have also set the `name` attribute to be non-nullable and have a maximum length of 100 characters.

These properties allow you to fine-tune the mapping of your Java entity to the underlying database table. Note that the specific attribute names and properties can vary based on the persistence framework you are using (e.g., Jakarta Persistence, Hibernate, etc.), but the basic concept remains the same.
***

### @Table ###

In Jakarta Persistence (JPA), the `@Table` annotation is used in conjunction with the `@Entity` annotation to specify the details of the database table that will be used to store instances of the entity class. The `@Table` annotation allows you to customize various attributes related to the database table.

Here's an explanation of the attributes of the `@Table` annotation:

1. `name`: (Optional) Specifies the name of the table in the database associated with the entity. If not specified, the default table name is derived from the unqualified class name of the entity. Example: `@Table(name = "students")`.

2. `schema`: (Optional) Specifies the name of the database schema where the table should be created. This is useful when working with multi-schema databases. Example: `@Table(name = "students", schema = "university")`.

3. `catalog`: (Optional) Specifies the name of the database catalog where the table should be created. This is used in databases that support catalogs. Example: `@Table(name = "students", catalog = "university_catalog")`.

4. `uniqueConstraints`: (Optional) Defines unique constraints on the table. It is an array of `@UniqueConstraint` annotations, each specifying a set of columns that should have unique values. Example:

   ```java
   @Table(name = "students", uniqueConstraints = {
       @UniqueConstraint(columnNames = {"name", "email"}),
       @UniqueConstraint(columnNames = {"student_id"})
   })
   ```

   In this example, we have specified two unique constraints. The first constraint ensures that the combination of "name" and "email" columns is unique, and the second constraint ensures that the "student_id" column has unique values.

5. `indexes`: (Optional) Defines indexes on the table. It is an array of `@Index` annotations, each specifying the columns to be indexed and other attributes related to indexing. Example:

   ```java
   @Table(name = "students", indexes = {
       @Index(columnList = "name"),
       @Index(name = "idx_age", columnList = "age")
   })
   ```

   In this example, we have specified two indexes. The first index indexes the "name" column, and the second index indexes the "age" column and names the index as "idx_age".

6. `uniqueConstraints`: (Optional) Defines unique constraints on the table. It is an array of `@UniqueConstraint` annotations, each specifying a set of columns that should have unique values. Example:

   ```java
   @Table(name = "students", uniqueConstraints = {
       @UniqueConstraint(columnNames = {"name", "email"}),
       @UniqueConstraint(columnNames = {"student_id"})
   })
   ```

   In this example, we have specified two unique constraints. The first constraint ensures that the combination of "name" and "email" columns is unique, and the second constraint ensures that the "student_id" column has unique values.

7. `indexes`: (Optional) Defines indexes on the table. It is an array of `@Index` annotations, each specifying the columns to be indexed and other attributes related to indexing. Example:

   ```java
   @Table(name = "students", indexes = {
       @Index(columnList = "name"),
       @Index(name = "idx_age", columnList = "age")
   })
   ```

In this example, we have specified two indexes. The first index indexes the "name" column, and the second index indexes the "age" column and names the index as "idx_age". Using the `@Table` annotation with its attributes allows you to have fine-grained control over the name and structure of the database table associated with your entity class, which can be helpful in various database and schema design scenarios.

***

### @Id ###

`@Id` annotation is used to specify that a particular field or property in an entity class represents the primary key of the corresponding database table. The primary key uniquely identifies each row in the table.

Here's how the `@Id` annotation is used:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    // Constructors, getters, setters, etc.
}
```

In this example, we have an `Employee` entity with fields `id`, `firstName`, `lastName`, and `age`. The `id` field is marked with the `@Id` annotation, indicating that it represents the primary key of the `employees` table in the database.

The `@GeneratedValue` annotation is also used alongside `@Id` to specify how the value for the primary key should be generated. In this example, we use `GenerationType.IDENTITY`, which means that the database will automatically generate the primary key value when a new `Employee` entity is persisted (inserted) into the database.

There are several other strategies available for generating primary key values using the `@GeneratedValue` annotation, such as `GenerationType.AUTO`, `GenerationType.SEQUENCE`, and `GenerationType.TABLE`, depending on the underlying database and the requirements of your application.

Using the `@Id` annotation properly is essential in JPA, as it allows the JPA provider (e.g., Hibernate) to identify and manage entities based on their primary keys, enabling efficient and correct database operations and entity associations.

***

### @GeneratedValue ###

The `@GeneratedValue` annotation in JPA is used to specify how the primary key values of an entity should be automatically generated when inserting new records into the database. It is typically used in conjunction with the `@Id` annotation, which designates the field or property representing the primary key.

The `@GeneratedValue` annotation has several attributes to define different strategies for generating primary key values. The common attributes are:

1. `strategy`: Specifies the generation strategy for the primary key value. It accepts one of the values from the `GenerationType` enumeration. Some common strategies are:
   - `GenerationType.AUTO`: The JPA provider selects an appropriate strategy for the underlying database. It may use `IDENTITY`, `SEQUENCE`, or `TABLE` depending on the database capabilities.
   - `GenerationType.IDENTITY`: The primary key value is automatically generated by the database. It relies on an auto-incrementing column (e.g., auto_increment in MySQL) to generate the values.
   - `GenerationType.SEQUENCE`: The primary key value is generated using a database sequence. The `sequenceName` attribute must also be specified to provide the name of the database sequence.
   - `GenerationType.TABLE`: The primary key value is generated using a table that stores the last generated key values. The `table` and `pkColumnName` attributes must be specified to define the name of the table and primary key column in that table.

2. `generator`: Specifies the name of the primary key generator to use. This is optional and is typically used in conjunction with the `strategy` attribute when using custom primary key generation strategies.

Here's an example of using `@GeneratedValue` with different strategies:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated using database identity column

    // ...

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "employee_sequence", allocationSize = 1)
    private Long customId;  // Auto-generated using database sequence

    // ...
}
```

In this example, we have an `Employee` entity with two primary keys: `id` and `customId`. For `id`, we use `GenerationType.IDENTITY`, which will rely on the database's auto-incrementing column to generate the values. For `customId`, we use `GenerationType.SEQUENCE` with a custom sequence generator named `"emp_seq"`. The sequence generator is specified using the `@SequenceGenerator` annotation, which provides the name of the database sequence (`employee_sequence`) to be used and the allocation size (in this case, 1).

Note: The use of multiple `@Id` annotations in a single entity class represents a composite primary key, which is beyond the scope of this example. It is more common to have a single `@Id` annotation representing the primary key.

***

### @Column ###

The `@Column` annotation in JPA is used to specify the mapping of a field or property to a column in the database table. It allows you to customize various attributes of the database column, such as name, length, nullable, uniqueness, etc.

Here are some of the common properties of the `@Column` annotation:

1. `name`: Specifies the name of the database column to which the field or property is mapped. If not provided, the default is the name of the Java field or property.

2. `length`: Specifies the length of the column for string-based fields. For example, `@Column(length = 100)` will create a column with a maximum length of 100 characters.

3. `nullable`: Defines whether the column can hold null values or not. By default, it is set to `true`, meaning the column can be nullable. To make the column not nullable, set it to `false` like `@Column(nullable = false)`.

4. `unique`: Specifies whether the column should enforce uniqueness. Setting `unique = true` means the column values must be unique across all rows.

5. `insertable`: Determines whether the column should be included in SQL INSERT statements when persisting an entity. By default, it is set to `true`.

6. `updatable`: Determines whether the column should be included in SQL UPDATE statements when updating an entity. By default, it is set to `true`.

Here's an example of using the `@Column` annotation with some properties:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    // Getters, setters, constructors, etc.
}
```

In this example, we have an `Employee` entity with four fields: `id`, `firstName`, `lastName`, and `email`. The `@Column` annotation is used to customize the mapping of each field to the corresponding database column:

- For the `id` field, we use `@Column(name = "employee_id")` to map it to the `employee_id` column in the database.
- For `firstName` and `lastName`, we use `@Column(length = 50, nullable = false)` to set the maximum length of the columns to 50 characters and ensure they are not nullable.
- For `email`, we use `@Column(unique = true)` to enforce uniqueness on the `email` column, meaning that each email address must be unique across all rows in the table.

> ⚠️ Keep in mind that the `@Column` annotation is not mandatory in all cases. If you do not use it, JPA will use default mappings for the fields. However, it provides a way to customize the column mappings to suit your specific database requirements.

***
     
### @Enumerated ###

The `@Enumerated` annotation in JPA is used to specify the mapping of an enum type field to the database. It is applied to a field of an enum type to define how the enum's values are stored and retrieved in the database table.

Here are the attributes of the `@Enumerated` annotation:

1. `value` (default): Specifies the strategy to use for persisting the enum value in the database. The `value` attribute can take one of two values:
   - `EnumType.ORDINAL`: This is the default value. It stores the enum as an integer value representing the index of the enum constant in the enum declaration. (0-based index)
   - `EnumType.STRING`: It stores the enum as a string representing the name of the enum constant.

Here's an example of using the `@Enumerated` annotation:

```java
public enum Gender {
    MALE,
    FEMALE
}

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private Long id;

    private String name;

    @Enumerated
    private Gender gender; // Defaults to EnumType.ORDINAL

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus status;

    // Getters, setters, constructors, etc.
}
```

In this example, we have an `Employee` entity with two enum fields: `gender` and `status`. The `gender` field uses the default `@Enumerated` annotation without specifying any attributes, so it will be persisted as an ordinal value (integer). The `status` field, on the other hand, uses the `@Enumerated(EnumType.STRING)` annotation, specifying that it should be persisted as a string (the name of the enum constant).

For instance, if you create an `Employee` object with the following values:

```java
Employee employee = new Employee();
employee.setId(1L);
employee.setName("John Doe");
employee.setGender(Gender.MALE);
employee.setStatus(EmployeeStatus.ACTIVE);
```

In the database, it would be stored like this:

| id | name      | gender | employee_status |
|----|-----------|--------|-----------------|
| 1  | John Doe  | 0      | ACTIVE          |

`Gender.MALE` is stored as an ordinal value `0`, whereas `EmployeeStatus.ACTIVE` is stored as a string `"ACTIVE"` in the `employee_status` column due to the `@Enumerated(EnumType.STRING)` annotation.

***

### @JoinColumn ###

The `@JoinColumn` annotation in JPA is used to specify the mapping of a foreign key column in a database table that is associated with a relationship between two entities. It is applied on the owner side of the relationship to define the column that will hold the foreign key referencing the related entity.

Here are some of the common attributes of the `@JoinColumn` annotation:

1. `name`: Specifies the name of the foreign key column in the owner entity's table. If not provided, the default is the name of the referenced entity's primary key column.

2. `referencedColumnName`: Specifies the name of the referenced entity's primary key column. If not provided, the default is the primary key column name of the referenced entity.

3. `nullable`: Defines whether the foreign key column can hold null values or not. By default, it is set to `true`, meaning the foreign key column can be nullable. To make the column not nullable, set it to `false` like `@JoinColumn(nullable = false)`.

4. `unique`: Specifies whether the foreign key column should enforce uniqueness. Setting `unique = true` means the column values must be unique across all rows.

5. `insertable`: Determines whether the foreign key column should be included in SQL INSERT statements when persisting an entity. By default, it is set to `true`.

6. `updatable`: Determines whether the foreign key column should be included in SQL UPDATE statements when updating an entity. By default, it is set to `true`.

7. `columnDefinition`: Allows you to provide the SQL DDL column definition for the foreign key column. It can be used to specify additional constraints, types, or other properties.

Here's an example of using the `@JoinColumn` annotation:

```java
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    // Other fields, getters, setters, constructors, etc.
}
```

In this example, we have an `Order` entity that is associated with the `Customer` entity through a many-to-one relationship. The `@JoinColumn` annotation is used on the `customer` field to specify the mapping of the foreign key column `customer_id` in the `orders` table, which references the `id` column of the `customers` table (referencedColumnName = "id"). The foreign key column is made not nullable (nullable = false) to enforce that each order must have a valid customer associated with it.

The `@ManyToOne` annotation indicates the direction of the relationship, where many orders can be associated with one customer. In this case, the `Order` entity is the owner side of the relationship, and the `Customer` entity would have the inverse side (possibly using the `@OneToMany` or `@OneToOne` annotations).

***

### @Inheritance ###

In JPA, the `@Inheritance` annotation is used to specify the inheritance strategy for a class hierarchy. It is applied to the root class of the inheritance hierarchy to define how the entities in the hierarchy should be mapped to the database.

Here are the attributes of the `@Inheritance` annotation:

1. `strategy`: Specifies the inheritance strategy to use. The `strategy` attribute can take one of the following values:
   - `InheritanceType.SINGLE_TABLE`: This is the default value. It maps all entities in the hierarchy to a single table. It uses a discriminator column to determine the actual subclass of each row.
   - `InheritanceType.JOINED`: This strategy maps each subclass to a separate table. It creates a join between the root table and each subclass table based on the primary key and foreign key relationship.
   - `InheritanceType.TABLE_PER_CLASS`: This strategy maps each subclass to its own table. It creates a separate table for each subclass and duplicates the common fields in each table.

Here's an example of using the `@Inheritance` annotation:

```java
@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;

    // Other fields, getters, setters, constructors, etc.
}

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    private int numberOfDoors;

    // Car-specific fields, getters, setters, constructors, etc.
}

@Entity
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {

    private boolean hasSideCar;

    // Motorcycle-specific fields, getters, setters, constructors, etc.
}
```

In this example, we have a class hierarchy of `Vehicle`, with two subclasses `Car` and `Motorcycle`. The `@Inheritance` annotation is used on the `Vehicle` class to specify the inheritance strategy as `InheritanceType.SINGLE_TABLE`, meaning all entities in the hierarchy will be mapped to a single table (`vehicles`). The discriminator column `vehicle_type` is used to differentiate between the subclasses.

The `@DiscriminatorColumn` annotation is used to define the discriminator column in the `vehicles` table, which holds the discriminator value (`CAR` for `Car` class and `MOTORCYCLE` for `Motorcycle` class).

The `@DiscriminatorValue` annotation is used on each subclass to specify the discriminator value associated with that subclass (`CAR` for `Car` class and `MOTORCYCLE` for `Motorcycle` class). This value will be stored in the `vehicle_type` column of the `vehicles` table to indicate the actual subclass for each row.

Depending on your application requirements, you can choose a different inheritance strategy based on the complexity and performance considerations of your data model.

***

### @MappedSuperclass ###

The `@MappedSuperclass` annotation in JPA is used to designate a class as a superclass whose properties should be mapped to the database tables of its subclasses. It is a way to define common attributes and behaviors that are shared among multiple entity classes without creating a separate table for the superclass.

Here are the attributes of the `@MappedSuperclass` annotation:

1. None: The `@MappedSuperclass` annotation does not have any attributes of its own.

Let's see an example of how to use the `@MappedSuperclass` annotation:

```java
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors, getters, setters, etc.
}
```

In this example, we have a `BaseEntity` class with common attributes `id`, `createdAt`, and `updatedAt`. The class is annotated with `@MappedSuperclass`, which means that the properties of this class will be inherited by its subclasses, but it will not be mapped to its own database table.

Now, let's create a subclass that extends `BaseEntity`:

```java
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    // Constructors, getters, setters, etc.
}
```

In this example, the `Product` class extends `BaseEntity`, and it inherits the `id`, `createdAt`, and `updatedAt` attributes from the superclass. However, since `BaseEntity` is marked as `@MappedSuperclass`, it will not be mapped to a separate table. Instead, the attributes will be mapped to the `products` table along with the `name` and `price` attributes specific to the `Product` entity.

By using `@MappedSuperclass`, you can avoid code duplication and enforce common fields and behaviors across multiple entity classes in your JPA data model.

***

### @Embeddable ###

The `@Embeddable` annotation in JPA is used to define a class whose instances will be embedded as components into the owning entity's table. It allows you to group multiple fields into a single value type, which is then embedded directly into the entity's table, rather than creating a separate table for the value type.

Here are the attributes of the `@Embeddable` annotation:

1. None: The `@Embeddable` annotation does not have any attributes of its own.

Let's see an example of how to use the `@Embeddable` annotation:

```java
@Embeddable
public class Address {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    // Constructors, getters, setters, etc.
}
```

In this example, we have an `Address` class annotated with `@Embeddable`. This class represents a value type that holds address-related information, such as `street`, `city`, and `postalCode`.

Now, let's use the `Address` class in an entity:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    // Constructors, getters, setters, etc.
}
```

In this example, the `Employee` class has a property of type `Address`, which is annotated with `@Embedded`. This indicates that the `Address` class should be treated as an embedded component within the `employees` table. The `street`, `city`, and `postalCode` attributes of the `Address` class will be stored as columns in the `employees` table.

When an `Employee` entity is persisted or retrieved, the attributes of the embedded `Address` object will be mapped to the corresponding columns in the `employees` table, providing a way to organize related fields without the need for an additional table.

Using `@Embeddable` allows you to create reusable value types that can be embedded in multiple entities, simplifying your data model and avoiding the need for unnecessary joins in queries.

***

### @EmbeddedId ###

The `@EmbeddedId` annotation in JPA is used to specify that a composite key, represented by an embeddable class, is used as the primary key for an entity. It allows you to create an entity with a composite primary key, where the primary key is composed of multiple attributes.

Here are the attributes of the `@EmbeddedId` annotation:

1. None: The `@EmbeddedId` annotation does not have any attributes of its own.

Let's see an example of how to use the `@EmbeddedId` annotation:

```java
@Embeddable
public class EmployeeId {

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "department")
    private String department;

    // Constructors, getters, setters, equals, and hashCode methods (if needed)
}
```

In this example, we have an `EmployeeId` class annotated with `@Embeddable`. This class represents the composite key for the `Employee` entity, consisting of `employeeId` and `department`.

Now, let's use the `EmployeeId` class as the composite primary key for the `Employee` entity:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @EmbeddedId
    private EmployeeId employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    // Constructors, getters, setters, etc.
}
```

In this example, the `Employee` class uses `EmployeeId` as its primary key by annotating the `employeeId` field with `@EmbeddedId`. This indicates that the `EmployeeId` class represents a composite key and its attributes (`employeeId` and `department`) should be used together as the primary key for the `employees` table.

With the `@EmbeddedId` annotation, JPA will automatically handle the mapping of the composite primary key to the corresponding columns in the database table. When an `Employee` entity is persisted or retrieved, JPA will use the `EmployeeId` object to construct and interpret the primary key for the database operations.

Using a composite key with `@EmbeddedId` is useful when your entity's uniqueness depends on multiple attributes, and you want to express that uniqueness as a single primary key. It can be particularly handy for mapping legacy databases or dealing with complex domain models that require composite keys.

***
