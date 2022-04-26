# Course-Planner
A Spring Boot server which helps students plan their courses. Specifically, it will show students when a course has been offered in the past which may help predict when it will be offered in the future. The server uses a REST interface to support a web-based UI (web UI provided to you by the instructor). The server will read a locally stored CSV input file containing data about course offerings at SFU. It will then process the data to organize it into a well structured model. The server’s controllers will provide access to this data via a REST API.

#files
The public folder contains two JSON files that can be used to test the application.
- One file contains large and intricate course data, whereas the other has simple data.
- The file paths could be altered in the controller class.
