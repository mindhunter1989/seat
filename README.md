# REQUIREMENTS
This project requires Java SDK 11 or greater.

# BUILDING
Gradle and its wrapper are configured for this project.

### CLEANING
```
$ ./gradlew clean
```

### COMPILING AND PACKAGING
```
$ ./gradlew build
```

# TESTING
```
$ ./gradlew test
```

# RUNNING APPLICATION
```
$ ./gradlew bootRun
```

For testing endpoint (e.g. with Postman):
http://localhost:8080/mower/control

with the body:
```json
{
  "plateauUpperRightCoordinates": "5 5",
  "mowerDeployment": {
    "mowerLocation": "1 2 N",
    "mowerInstructions": "LMLMLMLMM"
  }
}
```