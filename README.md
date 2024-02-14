# Distributed Toolchain Integration

[![Pylint](https://github.com/spe-uob/2023-DistributedToolchainIntegration/actions/workflows/pylint.yml/badge.svg?branch=dev)](https://github.com/spe-uob/2023-DistributedToolchainIntegration/actions/workflows/pylint.yml)

## Contents
[Project Overview](#Project-Overview)

[Tech-Stack](#Tech-stack)

[Setup-Instructions](#Setup-instructions)

[Client-&-Stakeholders](#Client--Stakeholders)

[Team Members](#Team-Members)

[Ethics](#Ethics)

[Gantt-Chart](#Gantt-Chart)

[License](#License)


## Project-Overview

The NCC (National Composites Centre) has a large number of software used to run tests and simulations on components they are designing. These tests provide information on the performance, cost, durability and many other metrics. These components often need to find a balance between these metrics and manually running simulations and making adjustments is extremely time consuming. This wastes the time of the engineers at the NCC.

We have been tasked with creating a system to sit in the middle of the modules used for testing. This system will take a component and requirements for this component as input. The system will then send the component and the current requirements for the component to each simulation. It will collect the results, adjust the requirements and repeat the process until the component has been optimised.

This system will be need to be able to: 
* Store CAD (Computer Aided Design) files their corresponding parameters in a database/filesystem, and retrieve them as requested to perform the optimisation process.
* Communicate across the internet or a local network as the simulations will be run on seperate computers/servers.
* Include security features as data stored in each part may be sensitive and/or patented or protected in some other way. Many of the NCC's Members are defence companies which will introduce more security requirements.

A full document for the Project Overview and Requirements is available [here](./documentation/ProjectOverviewAndRequirements.md), as well as the proposed solution.

## Tech Stack
* Python
* Java
* SpringBoot
* mongoDB

MongoDB was chosen as we will need to store parametric and non-parametric data. Examples of data types we will be storing are floats, integers, strings and files for components and simulations.

Python will be used to create an app that pretends to be the simulation software that the NCC will use, since we don't have access to their simulation as it is private/secret

Java and Spring Boot will be used to create the web app. This will provide the users with a user interface, and a way to send and retrieve data from the Python software.

The Java backend will connect to the database and it will forward the data onto the pretend simulation software. This has less points of failure and will prevent any issues with both areas of the backend trying to access the database at the same time.


## Architecture

![Architecture Diagram](/assets/archietectureDiagram.PNG  "Architecture Diagram")

* The Frontend and Java backend will be used to issue commands and retrieve data from the Python simulation software and Database.
* mongoDB Database will be used to store parametric and non-parametric data.
* The python application will be a stand-in for the simulation software the NCC uses. It must be separate from the rest of the application as the simulations may be run locally, across the network or even across the internet.

## Setup-Instructions

### Prerequisites
You will need the following installed to build the project. 
1. [Install Java 17](https://www.oracle.com/uk/java/technologies/downloads/#java17).
2. [Install Python 3](https://www.python.org/downloads/).
3. [Install MongoDB](./documentation/MongoDBinstructions.md).
4. [Install Apache Maven](https://maven.apache.org/download.cgi)
5. [Install Docker](https://docs.docker.com/engine/install/) (Optional but highly recommended)

Next you need to clone the repo:
```powershell
git clone git@github.com:spe-uob/2023-DistributedToolchainIntegration.git
```
Use the following commands to install all the python dependencies. Start in the root directory of the repository.
```powershell
pip install -r pythonBackend/requirements.txt
```
### Running the Application
To run the application:

Start the database using the instructions [here](./documentation/MongoDBinstructions.md).

Run the following command in the terminal to start the python application **without using docker**. 
```powershell
py pythonbackend/python_api.py
```
To use docker for the python application, there is a setup guide [here](./documentation/Containerization.md).

To run the container use the command:
```powershell
docker run -it -p 5000:5000 pythonbackend		#change pythonbackend to the container name you chose
```
Finally you can start the Springboot application using the command:
```powershell
mvn spring-boot:run
```


## Containerization
To use docker containers with this project use this [guide](./documentation/Containerization.md).

## Client-&-Stakeholders
[The National Composites Centre (NCC)](https://www.nccuk.com/) 

National Composite Centre and its members including BAE Systems, Dassault Systèmes, Rolls Royce, Aramco, Airbus and more.

### User-Stories
An engineer at the NCC wants a system to connect the toolchains together so that it is quick and easy to optimise the component they are working on.

An engineer at the NCC wants a system that allows them to easily run simulations on a server that is not physically accessible to allow them to optimise their component.

As a client to the NCC I want a secure way for the components to be optimised so that we don't risk leaking information to competitors.  

## Team-Members 
Dylan Quinton  

Demyan Hibbard  

George Corfield

## Gantt-Chart
[Gantt Chart](https://github.com/orgs/spe-uob/projects/119/views/2)

## Ethics
[Ethics](./documentation/ETHICS.md)

## License
[Distributed under an MIT license.](./documentation/LICENSE)

