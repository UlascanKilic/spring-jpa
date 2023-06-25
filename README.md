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
+ [Recap]()
  - [Normalization in Databases](https://github.com/UlascanKilic/spring-jpa/wiki/What-is-JPA%3F)
    * [1-1 Relation]()
    * [1-n Relation]()
    * [n-n Relation]()
  - [Directional-Bidirectional Relation](https://github.com/UlascanKilic/spring-jpa/wiki/What-is-JPA%3F)
    * [Directional Relation]()
    * [Bidirectional Relation]()
+ [Most Used Annotations]()
  - [@Entity]()
  - [@Table]()
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


