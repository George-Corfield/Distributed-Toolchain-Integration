# Distributed Toolchain Integration

## Contents
[Project Overview](#Project-Overview)

[Tech-Stack](#Tech-stack)

[Setup-Instructions](#Setup-instructions)

[Client](#Client)

[Stakeholders](#Stakeholders)

[User-Stories](#User-Stories)

[Ethics](#Ethics)

[Team Members](#Team-Members)

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

## Setup-Instructions
To setup the project please do the following:

1. Download the repository.
2. [Install Java 17](https://www.oracle.com/uk/java/technologies/downloads/#java17).
3. [Install Python 3](https://www.python.org/downloads/).
4. Use the following commands to install the python dependencies. Start in the root directory of the repository.
```powershell
pip install -r pythonBackend/requirements.txt
```
5. [Install MongoDB](./documentation/MongoDBinstructions.md).

## Containerization
To use docker containers with this project use this [guide](./documentation/Containerization.md).

## Client
[The National Composites Centre (NCC)](https://www.nccuk.com/) 

## Stakeholders
National Composite Centre and its members including BAE Systems, Dassault Systèmes, Rolls Royce, Aramco, Airbus and more.

## User-Stories
An engineer at the NCC wants a system to connect the toolchains together so that it is quick and easy to optimise the component they are working on.

An engineer at the NCC wants a system that allows them to easily run simulations on a server that is not physically accessible to allow them to optimise their component.

As a client to the NCC I want a secure way for the components to be optimised so that we don't risk leaking information to competitors.  

## Ethics

[Ethics](./documentation/ETHICS.md)

## Team-Members 
Dylan Quinton  

Demyan Hibbard  

George Corfield

## Gantt-Chart
[Gantt Chart](https://github.com/orgs/spe-uob/projects/119/views/2)

## License
[Distributed under an MIT license.](./documentation/LICENSE)

