
# Possible Solutions
## Content 
### [Summary](#Summary)
### [Front End](#Front-End) 
* [Web App](#Web-App)
* [Desktop app](#Desktop-App)
* [Command Line](#Command-Line)


### [Back End](#Back-End)
* [Local](#Local)
* [Server](#Server)
* [Cloud](#Cloud)

### [Proposed Solutions](#Proposed-Solutions)

## Summary
The Solution can be made from combinations of the following front and backends.

## Front End

### Web-App

A web app can be used for the front end. This will provide the users with a full Graphical User Interface that is accessible through any web browser. It is likely that this solution will use a server or cloud backend. 

| Pros| Cons| 
| ----- | ----------- | 
| Full Graphical user interface. | Maintenance will be needed. |
| Accessible through any web browser. |  |


### Desktop-App

A Desktop app can be used for the front end. This can use any of the suggested backends.

| Pros| Cons| 
| ----- | ----------- | 
| Full Graphical User Interface. | Maintenance will be difficult as regular updates are needed. |
| Compatible with any backend type. | Different versions for different operating systems are needed. |

### Command-Line
This is the simplest way to communicate with the backend. A set of simple commands can be made to send the data to the backend. Would work best with a local backend.

| Pros| Cons| 
| ----- | ----------- | 
| Very simple, practically no maintenance needed. | No/limited Graphical User Interface. |
|  | Different versions for different operating systems are needed. |
|  | Dependant on users' knowledge and experience with command line |

## Back End

### Local
The backend can be stored locally on each machine. 

| Pros| Cons| 
| ----- | ----------- | 
| Simple to maintain. | Low/no security measures needed. |
| Files will not be sent across the internet improving security and speed. | Different versions for different operating systems are needed. |
| Cheap. | All the simulation software and modules must be installed on each machine and cannot be shared easily. |
|  | Speed dependent on the chosen machine and cannot be altered. |

### Server
The backend can be run on a server owned by the client.

| Pros| Cons| 
| ----- | ----------- | 
| Complete control over the server. | It may take longer as the file will have to be sent to the server. |
| Easier to develop and test. | Strong security measures needed. | 
|  | Low/No fault tolerance. | 
|  | Expensive as the server must be bought and maintained. | 
|  | Limited ability to scale the system. | 

### Cloud
The backend can be stored in the cloud. 

| Pros| Cons| 
| ----- | ----------- | 
| The server is not your responsibility to maintain. | It may take longer as the file will have to be sent to the server. |
| Strong fault tolerant. | Strong security measures needed. | 
| Speed dependent on the speed of the server, so it can be increased and decreased. | Expensive as you must pay for the use of the server. | 
| Easy to scale the system. |  | 

## Proposed-Solutions

### Summary
Client has expressed a preference to have a server for the backend.
The web app is a superior option to both the desktop app and command line. The full GUI allows for a better user experience than the command line and it also requires less maintenance and regular updates than the desktop app.

### Solution 1: Web-App with Server
The most feasible solution is a combination of a web-app and server. This provides us with a full GUI that can be accessed using any web browser, the server will run and owned by the client. This provides the benefit of being OS independent, it will be accessible locally even if the internet is lost, the client has complete control over the system and it is easier to develop and maintain than a cloud solution.
This is the chosen solution. [A link to the Tech Stack and Architecture is here.](./TechStackAndArchitecture.md)

### Solution 2: Desktop-App with Server
Another feasible solution is to use a desktop-app and server. This will also provide a full GUI however it can only be accessed using an app. This app will need to be built and distributed to any machines that need to use the software. The app will also need different versions for different operating systems and hardware (for example: Intel Mac, Apple silicon Mac, Windows 32-bit, Windows 64-bit, Linux etc). This makes maintenance of the system very costly, time consuming and difficult as each version of the software may need to be updated and maintained separately. This can also introduce other security risks as part of the software is now on other computers.
