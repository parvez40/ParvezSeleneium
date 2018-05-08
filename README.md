How To Run
=======================
1. Chrome browser to use by using the following switches in Maven build configuration:
	- -Dbrowser=chrome
2. If you want to toggle the use of chrome in headless mode by setting the headless flag in pom.xml (by default the headless flag is set to false)
		<headless>false</headless>
3. You don't need to worry about downloading the IEDriverServer, EdgeDriver, ChromeDriver 
4. You can even specify multiple threads in pom.xml to run tests parallely in threads tag (by default thread is set to 1):
		<threads>1</threads>
        
### It's not working!!!

You have probably got outdated driver binaries, by default they are not overwritten if they already exist to speed things up.  You have two options:

- `mvn clean verify -Doverwrite.binaries=true`
- Delete the `selenium_standalone_binaries` folder in your resources directory

##Screenshots
Screenshots will be available for failed tests in target > screenshots folder

##Test Reports
You can see test reports in broser mode by navigating to target > failsafe-reports > index.html

##I have used a selenium maven template from github which is available here
https://github.com/Ardesco/Selenium-Maven-Template
