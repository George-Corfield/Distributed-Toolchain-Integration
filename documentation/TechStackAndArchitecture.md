## Tech Stack

* Python
* Java
* SpringBoot
* mongoDB

MongoDB was chosen as we will need to store parametric and non-parametric data. Examples of data types we will be storing are floats, integers, strings and files for components and simulations.

Python will be used to create an app that will act as a stand in for the simulation software that the NCC uses. It will accept POST requests which will contain a module and parameters. The parameters will be used as input for the corresponding module.

Java and Spring Boot will be used to create the web app. This will provide the users with a user interface, and a way to store the relevant files as well as send them to the python app to be optimised.

## Architecture

![Architecture Diagram](/assets/architectureDiagram.png  "Architecture Diagram")

* The Frontend and Java backend will be used to issue commands and retrieve data from the Python Control Scripts and Database.
* The Python Control Scripts will be used to control the python modules for the simulation work, collect the results and iterate on them.
* mongoDB Database will be used to store parametric and non-parametric data.
* The Python Modules and API's will be used as a stand in for the simulation software.
