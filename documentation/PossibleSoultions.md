
# Possible Solutions
## Content
[Summary](#Summary)

[Front-End](#Front-End)
* [Web-App](#Web-App)
* [Desktop-app](#Desktop-App)
* [Command-Line](#Command-Line)

[Back-End](#Back-End)
* [Local](#Local)
* [Server](#Server)
* [Cloud](#Cloud)
  
[Proposed-Solutions](#Proposed-Solutions)
## Summary
The Solution can be made from combinations of the following front and backends.

## Front End

### Web-App
A web app can be used for the front end. This is accessible through any web browser making it compatible with any system with a web browser. 

| Pros| Cons|
| ----- | ----------- |
| Full Graphical user interface. | Maintenance needed, specifically for web development. |
| Accessible through any web browser making it compatible with any system with a web browser. | |
| Compatible with any of the suggested backends. |  |

### Desktop-App

A Desktop app can be used for the front end. This can use any of the suggested backends. 

| Pros| Cons|
| ----- | ----------- |
| Full Graphical User Interface. | Maintenance will be difficult as regular updates are needed. |
| Compatible with any backend type. | Different versions for different operating systems are needed. |

### Command-Line

This is the simplest way to communicate with the backend. A set of simple commands can be made to send the data to the backend. Would likely return a file with the results.

| Pros| Cons|
| ----- | ----------- |
| Very simple, practically no maintenance needed. | No/limited Graphical User Interface. |
| | Different versions for different terminals are needed. |
| | Dependent on users' knowledge and experience with command line. |
| | Would likely require the computer that made the request to stay open to wait for the response instead of being able to come back later. |

This will be entirely ruled out as it would make it much more difficult to use the system for almost no benefit.

## Back End

### Local
The backend can be stored locally on each machine.

| Pros| Cons|
| ----- | ----------- |
| Simple to maintain. | Low/no security measures needed. |
| Files will not be sent across the internet improving security and speed. | Different versions for different operating systems are needed. |
| Cheap. | All the simulation software and modules must be installed on each machine and cannot be shared easily. |
| | Speed dependent on the chosen machine and cannot be altered. |

### Server
The backend can be run on a server owned by the client.

| Pros| Cons|
| ----- | ----------- |
| Complete control over the server. | It may take longer as the file will have to be sent to the server. |
| Easier to develop and test. | Strong security measures needed. |
| | Low/No fault tolerance. |
| | Expensive as the server must be bought and maintained. |
| | Limited ability to scale the system. |

### Cloud
The backend can be stored in the cloud.

| Pros| Cons|
| ----- | ----------- |
| The server is not your responsibility to maintain. | It may take longer as the file will have to be sent to the server. |
| Strong fault tolerance. | Strong security measures needed. |
| Speed dependent on the speed of the server, so it can be increased and decreased to improve performance/ save money. | Expensive as you must pay for the use of the server. |
| Easy to scale the system. | |

### Peer to Peer
A peer to peer network can be used for the backend of the system. 
| Pros| Cons|
| ----- | ----------- |
| Easy to add nodes to the network. | Simulations can only be run on specific machines, complicating how the network will operate. |
| Strong fault tolerance. | Weak security as anyone could pretend to be part of the network, and any node could be used to store sensitive files. |
| Cheap as it uses already available computing power. | Difficult to maintain as errors can come from a lot of different machines, making tracing them very difficult and time consuming. |
| Very easy to share files and data. | Different versions needed for different operating systems. |

Note: The speed for reading, writing and operating on data is dependent on the speed of the peer, so it would be inconsistent. 

## Proposed-Solutions

### Summary
Client has expressed a preference to have a server for the backend.
The web app is a superior option to both the desktop app and command line. The full GUI allows for a better user experience than the command line and it also requires less maintenance and regular updates than the desktop app. Peer to peer networks are a possibility but add too many security issues to be viable.

### Solution 1: Web-App with Server

The most feasible solution is a combination of a web-app and server. This provides us with a full GUI that can be accessed using any web browser, the server will run and owned by the client. This provides the benefit of being OS independent, it will be accessible locally even if the internet is lost, the client has complete control over the system and it is easier to develop and maintain than a cloud solution.

This is the chosen solution. [A link to the Tech Stack and Architecture is here.](./documentation/TechStackAndArchitecture.md)

### Solution 2: Desktop-App with Server

Another feasible solution is to use a desktop-app and server. This will also provide a full GUI however it can only be accessed using an app. This app will need to be built and distributed to any machines that need to use the software. The app will also need different versions for different operating systems and hardware (for example: Intel Mac, Apple silicon Mac, Windows 32-bit, Windows 64-bit, Linux etc). This makes maintenance of the system very costly, time consuming and difficult as each version of the software may need to be updated and maintained separately. This can also introduce other security risks as part of the software is now on other computers.

Below is a basic architecture diagram for this solution:

![Architecture Diagram for the Desktop-App Server solution.](./assets/DesktopArchitecture.png)

### Solution 3: Desktop-App with Local Backend

This Solution is unfeasible. While it comes with the benefits of: having a full GUI, the ability to work offline, optimising the components and storing them in local storage. The main drawbacks are that the app will not be able to quickly and easily share data with other computers, as well as being limited to the speed of the device it is running on. This makes this solution unfeasible. A local backend will always have these problems, making a full local backend unfeasible.

### Solution 4: Web/Desktop-App using Peer to Peer

This solution is unfeasible. A peer to peer network could be used. Each computer on the network would provide compute power for simulations and storage for files. Benefits:  Very fault tolerant, easy to share files and data. The drawbacks are security. Peer to peer networks are much easier to infiltrate, this is especially a problem when the data on the network is sensitive or private. Another major issue is that for the network to work some computers must be on and connected to the network at all times. If everyone lives in the same time zone it is a real possibility that at night all the computers get shut off, preventing anyone from accessing the network. Maintenance will also be difficult as different versions may be needed for each operating system. These issues makes the peer to peer network an unfeasible solution.
