# Project Overview and Requirements
## Contents 
[Overview](Project-Overview)  

[Requirements](Requirements)  

[Limitations](Limitations)  

[Definitions](Definitions)  


## Project-Overview
### Problem
The NCC (National Composites Centre) has a large amount of software used to run tests and simulations on components they are designing. These tests provide information on the performance, cost, durability and many other metrics. These components often need to find a balance between these metrics and manually running simulations and making adjustments is extremely time consuming. This wastes the time of the engineers at the NCC.

We have been tasked with creating a system to sit in the middle of the modules used for testing. This system will take a component and requirements for this component as input. The system will then send the component and the current requirements for the component to each simulation. It will collect the results, adjust the requirements and repeat the process until the component has been optimised.

### Proposed Solution
The system we will create is a web app. This app will be able to store parametric and non-parametric data relating to each component, as well as each component. Users will be able to specify a range for each requirement provided for each component and then the components will be sent to modules used for testing. The modules results will be collected and used to adjust the requirements of the component. This will repeat until the component is optimised. The results will then be passed to the user.

### Technology Stack
Python will be used to represent the simulation software in the project for the following reasons:
* It is commonly used in engineering and scientific applications meaning the Users are likely familiar with it. This will satisfy the requirement of the system being extendable by the users.
* A python API is available for the majority of the modules that are being used. This will make integration with the tools much easier. 

The chosen Database is mongoDB as it is a noSQL databse. This will make it much easier to store parametric and non-parametric data and have that data associated with different users.

The SpringBoot framework has been chosen as it is easy to use and will allow for a connection to the mongoDB database through java and can also be easily connected to the python backend.

A link to the full Technology stack is [here](./documentation/TechStackAndArchitecture).

## Requirements
| Index | Requirement | Rationale |
| ----- | ----------- | --------- |
| 1 | Store parametric and non-parametric data for the components | The server can send correct the data to the required modules |
| 2 | Store parametric and non-parametric data securely | CAD, simulations etc. |
| 3 | Allow for the data to be accessed programmatically | The platform needs to allow data to be accessed through external scrips/programs |
| 4 | Track the source of all data added to the system | Each data set to be traced back to a defined level eg. material data source |
| 5 | Allow testing data to be easily available | Engineers can see the results of their tests |
| 6 | Facilitate future changes and updates | Requirements have the ability to be changed and updated |
| 7 | Allow for the requirements of components to be altered within a specified range | The requirement can be specified between a range e.g. Dia. Between 10mm-50mm |
| 8 | Use a form of version control for the requirements | Protects requirements from both any errors and unintended consequences |
| 9 | Use a form of version control for the data | Protects data from both any errors and unintended consequences |
| 10 | Have a review system that informs the relevant parties of changes | All parties invloved (especially users) must know if changes to the system occur |
| 11 | Enable validation and certification | The system should prompt or facilitate a V&V strategy |
| 12 | Have a glossary | So there is no confusion about defintions |
| 13 | Permissions | Security to stop users from accessing each others data |

### MVP Requirements
This is a large project and it is unlikely that it will be completed by the end of the year. The following is a list of the above requirements needed for a minimum viable product: 1, 2, 5, 6, 7, 12.

### Problems the client faces with current solutions
The main problems the client faces with current solutions are:
* Passing information between different institutions.
* Efficient tracking of parameters between different people developing different modules.
* Online storage for intermediate files.
* Ownership of modules and values.
* Methodology to further develop the system.

Some examples of current solutions that are similar but do not provide all the necessary functionality are:
* [Heeds (Siemens)](https://plm.sw.siemens.com/en-US/simcenter/integration-solutions/heeds/)
* [Isight (Dassault Systèmes)](https://www.3ds.com/products-services/simulia/products/isight-simulia-execution-engine/)
* [Model Centre (Ansys)](https://www.ansys.com/en-gb/products/connect/ansys-modelcenter)
* [Dapta toolchain](https://www.dapta.com/)

All of these products have been tried by the NCC but were missing necessary functionality.

## Limitations
The system that is created will have limitations. 
* Optimisation does not run asynchronously from the rest of the Java backend so nothing can be done while optimising results. This can be fixed in the future.

## Definitions
* CAD stands for Computer Aided Design.
* A component is a CAD model of a physical part of a product.
* Parameters (aka results/output variables/responses) are the resulting values of a simulation. They are calculated by the simulation and passed back to the user.
* System refers to the software that we are building.
* Parametric data is any data that stores parameters or variables.
* Non-Parametric data is any data that is more complex than a series of simple values.
* Requirements are the different aspects of a component e.g. Diameter, length, material etc.
