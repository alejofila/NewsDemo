
#Sample demo
The main objective of this demo is to show a list of _news_. 
The **data source** is an endpoint thar returns a CSV file, the projects showcase some popular topics in **android development world** : 


* Kotlin
* RxJava
* Retrofit
* Room Database (from Google's Architecture Components)
* Constraint Layout, Constraint Layout Animations
* Model View Presenter
* Clean Architecture
* Unit Testing with JUnit and Mockito
* Modular codebase
* Koin as a dependency injection library, simpler API compared to Dagger 2.
 
The project has 3 modules: 
* Domain : Here is defined the data model that represent the core of the business, in this case : _News_.
* Also defining contracts for **Use Cases**.
* Data : Here is implemented the data layer, consuming an endpoint to get a _CSV_ file, parsing it and saving it to local _SQLITE_ database using **Room** 
* APP : Presentation layer with  **Model View Presenter**  as driving pattern