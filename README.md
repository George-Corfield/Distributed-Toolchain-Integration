# Distributed Toolchain Integration

## Contents

[Project Overview](#Project-Overview)  

[Tech Stack](#Tech-Stack)

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

A full document for the Project Overview and Requirements is available [here](https://github.com/spe-uob/2023-DistributedToolchainIntegration/blob/main/ProjectOverviewAndRequirements.md), as well as the proposed solution.

## Tech-Stack
The Tech Stack and System architecture are available [here](https://github.com/spe-uob/2023-DistributedToolchainIntegration/blob/main/TechStackAndArchitecture.md)

## Client

[The National Composites Centre (NCC)](https://www.nccuk.com/)

## Stakeholders

National Composite Centre and its members including BAE Systems, Dassault Systèmes, Rolls Royce, Aramco, Airbus and more.

## User-Stories

An engineer at the NCC wants a system to connect the toolchains together so that it is quick and easy to optimise the component they are working on.

An engineer at the NCC wants a system that allows them to easily run simulations on a server that is not physically accessible to allow them to optimise their component.

## Ethics

[Ethics](https://github.com/spe-uob/2023-DistributedToolchainIntegration/blob/main/ETHICS.md)

## Team-Members

Dylan Quinton

Demyan Hibbard

George Corfield

## Gantt-Chart

[Gantt Chart](https://github.com/orgs/spe-uob/projects/119/views/2)

## License

[Distributed under an MIT license.](https://github.com/spe-uob/2023-DistributedToolchainIntegration/blob/main/LICENSE)
