
## Tech Stack
* Python
* Java
* SpringBoot
* mongoDB

MongoDB was chosen as we will need to store parametric and non-parametric data. Examples of data types we will be storing are floats, integers, strings and files for components and simulations.

Python will be used to create control scripts. These scripts will be responsible for taking components and their requirements from the database, connecting to the modules and then using the modules to iterate over the requirements.

Java and Spring Boot will be used to create the web app. This will provide the users with a user interface, and a way to send and retrieve data from the python control scripts.

The Java backend will not connect to the database and it will instead have to use the python control scripts to retrieve data from the database. This has less points of failure and will prevent any issues with both areas of the backend trying to access the database at the same time.


## Architecture

![Architecture Diagram](/assets/architectureDiagram.png  "Architecture Diagram")

* The Frontend and Java backend will be used to issue commands and retrieve data from the Python Control Scripts and Database.
* The Python Control Scripts will be used to control the python modules for the simulation work, collect the results and iterate on them.
* mongoDB Database will be used to store parametric and non-parametric data.
* The Python Modules and API's will be used as a stand in for the simulation software.
