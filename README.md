# tech-test
Microservice for tech test
Service that call Chuck Norris Api to get random jokes and save them in Redis Cache.
The jokes saved in cache can be search by id.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Software you will need to install.

```
1. Install Java JDK11

2. Clone repository: https://github.com/LescanoMicaela/tech-test.git

3. Apache Maven package and variable environment 

	- MAVEN_HOME=C:\{your_system_route}\apache-maven-x.x.x
	- %MAVEN_HOME%\bin

4. Check your Maven config: mvn --version

5. Run mcn clean install in root directory


```

## Deployment

1. Follow the deployment guide on the orchestrator readme.

2. Check the Swagger on your browser:

http://localhost:8006/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config


