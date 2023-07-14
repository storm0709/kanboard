# UI and API tests for the Kanboard application
## Run tests with the following commands:
* mvn clean test -Papi-regression - for running all API tests
* mvn clean test -Pregression -Dbrowser=chrome -Dheadless=false - for running all tests in Chrome
* mvn clean test -Pregression -Dbrowser=chrome -Dheadless=true - for running all tests in Chrome headless mode
* mvn clean test -Pregression -Dbrowser=firefox -Dheadless=false - for running all tests in Firefox
## Run the commands to get Allure report
allure generate target/allure-results
allure serve target/allure-results

### Test suits by functionality
* -Plogin - for login checks
* -Pproject - for project check
* -Ptask - for task check
* -Papi-regression - for all API tests

mvn clean test -Pproject -Dbrowser=chrome -Dheadless=false
