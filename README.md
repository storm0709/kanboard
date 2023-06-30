# UI and API tests for the Kanboard application
## Run tests with the following commands:
* mvn clean test -Pregression -Dbrowser=chrome -Dheadless=false - for running all tests in Chrome
* mvn clean test -Pregression -Dbrowser=chrome -Dheadless=true - for running all tests in Chrome headless mode
* mvn clean test -Pregression -Dbrowser=firefox - for running all tests in Firefox

### Test suits by functionality
* -Plogin - for login checks
* -Pproject - for project check
* -Ptask - for login check

mvn clean test -Ptask -Dbrowser=chrome -Dheadless=false