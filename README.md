## IT-Academy coding assignment 
                
Let's imagine, in this repository we keep production code for some very important application. \ 
The statement of work you can find in the issue [#1](/../../issues/1).  

But, oh Boy! Our code contains some bugs and our users made an issue with an example of a wrong behaviour [#2](/../../issues/2) 

### Goals

Your __goals__ are

1. You have to figure out what's going on and __fix__ all bugs you can find.
2. Once you fixed all bugs we will ask you to make 2 REST endpoints:
    * GET `/show` without paramenters - should respond with the current canvas in a String representation (the same way you print it in console)
    * PUT `/command` with a command as a parameter - should send a command to the canvas manager  
For more information see [speficication](rest-api-specification.yaml) file  

Good luck! 

### How to build and run

```
./gradlew clean build
```

Then in the folder `build/libs/` you may find an executable file which you could run with 
```
java -jar build/libs/coding-assignment-1.0.0-SNAPSHOT-all.jar
```
