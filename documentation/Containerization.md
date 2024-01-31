# Containerization
We have used docker to containerize the application. Containers are packages of software which contain all necessary elements to run in any environment. This project currently uses three containers: The SpringBoot application, the python back-end and the database. Containers function completely independent from each other and interact through localhost. A common issue is the incorrect ports being used in communication between the containers.

We used docker to containerize the application. To install docker follow the instructions [here](https://docs.docker.com/engine/install/).

## Python Back-end Container:

**If there is no requirements.txt file then run the following:**
```powershell
pip freeze > requirements.txt			#Creates the file requirements.txt
```
NOTE: pip freeze adds all downloaded libraries to the requirements.txt. Only use this if the container is missing dependencies. If possible go through and remove unnecessary dependencies from the file.

To build the container image:
```powershell
docker build -t pythonbackend .			#Builds the image for the container
```

To run the container after the image has been built.
```powershell
docker run -it -p 5000:5000 pythonbackend	#Runs the container, mapping port 5000 container port 5000
```
