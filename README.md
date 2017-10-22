# viajei-microservices

This project simulates a travel agency which provides travel packages that are composed of a hotel room, a hotel, a transport and a transport route.
These packages can be selled to a client and when this occurs, the agency application books a hotel room and creates a new transport ticket by requesting to the hotel and transport microservice.

## Deployment

In order to deploy this project, follow the steps bellow:
- be sure you have docker, docker-compose, maven and git installed in your computer;
- open your terminal and navigate to the folder that you want to download this project
- run the command `git clone https://github.com/pedroviniv/viajei-microservices.git` to clone this repository to your machine
- navigate to the project root folder and run the command `sh start.sh`
	- the command above will execute the following steps:
		- mvn clean install (to compile the source code generating the war file and to install the artifact generated to the maven dependencies folder)
		- sudo docker-compose up -d to run the build the images and instantiate the containers described into docker-compose.yaml file
- finally, run the command `sudo docker container ls` and if the steps above went fine, you will see all the deployed containers