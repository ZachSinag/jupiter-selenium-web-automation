# Jupiter Planit Web - Test Automation
## Pre-requisites For MAC OS
1. Maven (latest):  `brew install maven`
2. JDK (latest):  `brew install java`
## Workflow
1. Clone this repository using HTTPS:  `git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY`
2. Wait until Installation of Dependency is finished
3. Run maven lifecycle clean in your terminal:  `mvn clean`
4. To run All Test Steps you just need to access on the JupiterRun.xml file (default browser - Chrome; default environment - Production) :
```
 mvn clean test -DtestngFile="JupiterRun.xml" -Denvironment="prod" -Dbrowser="chrome" 
```
5. To run on different browser: `edge` (non-local)
```
 mvn clean test -DtestngFile="JupiterRun.xml" -Denvironment="prod" -Dbrowser="edge" 
```
6. Results will be shown on console logs, but test evidence can also be viewed by saving the whole folder  `/Automation Reports/index.html`.


-----------------

### Jupiter Planit Web -  Test Coverage:
- MODULE 1: CONTACT PAGE VALIDATE ERROR MESSAGE
- MODULE 2: CONTACT PAGE SUBMIT APPLICATION
- MODULE 3 : CART PAGE VALIDATE PRODUCTS

-----------------

### Others need to know:
- For -DtestngFile = This is a parameter to run your desired xml file via terminal.
- For -Denvironment  and -Dbrowser = This is a parameter to run your desired environment and browser via terminal. (If you have multiple environment and cross browsers) You can change the environment url and add browser capabilities under `../base/BrowserManager`.
