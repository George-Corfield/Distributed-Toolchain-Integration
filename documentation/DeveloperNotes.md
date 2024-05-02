## Notes for future development

### Steps to begin the project
* Ensure that all steps are followed from README.md such as downloading dependencies and other applications for the application to run properly
* Use the application.properties found in `src/main/resources` to set up the correct environment for the mongoDb database. This includes both the local version and the cloud version on Atlas.
* Ensure that the project is running and follow the steps in the video [here](./demonstrationRecording.mp4), ensuring that all steps in the video are reproducible.
* Read the document [In Depth Explanation](./InDepthExplanation.md) to answer any questions about the layout of the project and what each file/folder does within the project.

### Current Project Features
* Login/registration system
* Complete database system
* Database query services and repositories
* Ability for users to create their own project hierarchy of projects and parts
* Ability for users to upload their own modules to be run, as well as use others
* Ability for users to upload .json files of variables into the system for use in optimisations
* Complete working hill climb optimisation algorithm
* Python API that runs outside of the java application
* Complete CI/CD pipeline that runs on pushes to the repository/ pull requests
* Ability for users to select and choose which modules run and in which order, as well as which variables can be used for each module to completely tailor the optimisation.
* Ability for users to see and search for results based on a series of filters, allowing the client to select the best result for them


### Client Feedback 


### Potential Future Developements
* Permissions: the client wanted certain users to have higher/lower permissions than others which would allow them to only view certain pages, or do certain actions such as some users may not be able to actually optimise only upload etc
* Multiple Users: the client would like to see multiple users be able to access the same projects/ parts so that multiple users could view and edit projects.
* Choice of Algorithm: the hill climb algorithm is a great algorithm for the problems we have provided (minimising and maximising) but it would be a great development to access other algorithms for different types of problems
* Interface: there is potential to develop a slightly more advanced interface and more user friendly interface which allows users to drag and drop modules and variables into place and link them together. This is inspired by other optimisation simulation interfaces where users drag, drop and link modules together.
