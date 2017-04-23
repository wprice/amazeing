Amazeing Example
----
A simple application demonstrating a maze generation and traversal. The maze 
generation algorithm used is the Binary Tree algorithm (South and East), and
the traversal algorithm is a variation of Tremaux.

A maze takes varying forms through program execution

- Created
- Generated
- Traversed

A maze that is created is simply a grid or matrix with a defined number of rows and columns. 
By default this 3x3, but the system allows customization of these values
 
A generated maze is one that has been *carved* out of a created maze. By default a simple Binary Tree 
algorithm is used to create the maze.

A traversed maze is one where a path through the maze has been traversed from a specified *entrance* and *exit*
or start/finish.

 
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

If no options are provided a maze of 3x3 dimensions is created, generated and traversed. The traversal output format is
in grid format showing the path through the maze against the grid itself. Other formats are available. 
Finally, the project is also packaged as a fat Jar (dependencies) and can be executed as such

```bash

mvn package
java -jar ./target/amazeing-<VERSION>-jar-with-dependencies.jar 
```

Program Options
---

A variety of options are provided in running the application. A sample output of

```bash
java -jar ./target/amazeing-<VERSION>-jar-with-dependencies.jar -help
```

provides a reasonable explanation

```
usage: amazeing
 -c,--columns <arg>   the number of columns to create
 -end <arg>           the exit for maze traversal
 -format <arg>        the format for traversal output valid values are
                      [grid, line, stack]
 -help,--help         print options and help description
 -r,--rows <arg>      the number of rows to create
 -start <arg>         the entrance for maze traversal
 -travdir <arg>       direction of traversal format [entrance, exit]

```

Currently, the traversal formats provided are grid, line and stack. 

line

```
Maze traversed: 
(0,0)-->(1,0)-->(2,0)-->(2,1)-->(2,2)

```

In both the stack and line formats, the default is descending order (entrance to exit). 

More examples are recorded in the following locations


- [grid](./doc/grid-traversal.txt)
- [line](./doc/line-traversal.txt)
- [stack](./doc/stack-traversal.txt)

Note, due to the limitations of Markdown, it is recommended you view the above links in your text editor of choice

Docker
---

A simple docker image is maintained on [Docker Hub](https://github.com/) for convenience

```text
docker pull wmprice1240/amazeing

```

Program options are supported and can be used in conjunction with simply running the program:

```text
docker run wmprice1240/amazeing -format stack
```

The Docker image is updated as part of the Travis CI build provided by [GitHub](https://github.com/)

