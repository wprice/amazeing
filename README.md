Amazeing Example
----
A simple application demonstrating a maze generation and traversal. The maze 
generation algorithm used is the Binary Tree algorithm (South and East), and
the traversal algorithm is a variation of Tremaux.
 

Quick Start
---

```` 
git clone https://github.com/wprice/amazeing.git
cd amazeing
mvn install   
````

The above command will compile, run the unit tests and install the application. 
Similarly, the repository contains a Main class allowing execution through maven once the repository 
above has been cloned and at least compiled
 
````
mvn compile exec:java
    
````

Finally, the project is also packaged as a fat Jar (dependencies) and can be executed as such

```bash

mvn package
java -jar ./target/amazeing-<VERSION>-jar-with-dependencies.jar 
```

Docker
---
TODO