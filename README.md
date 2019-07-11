## POC's for Configurations and Properties 

### Notes:
* Compile and run the [Config server project](https://gitlab.research.att.com/deven/config-server) before running this project.
* This project depends on Config server to be running.
* The Config server needs to be able to clone from github. Make sure the network you are on does not block this.
* This POC runs on port 7777 and the Config Server runs on port 7776

### Compile: 
	mvn clean install
### Run: 
    SPRING_APPLICATION_JSON='{"email":"sam@att.com"}' java -jar poc1-0.0.1-SNAPSHOT.jar