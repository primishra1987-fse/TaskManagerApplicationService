# TaskManagerApplicationService

Step 1. Look for Port number in application.properties to run code locally , ensure code is not in use by any other app.
Step 2. Check the database connection in application.properties
Step 3. Local Testing Urls are below :
http://localhost:8095/task/getAllTasks
http://localhost:8095/task/getTask/{taskId}
http://localhost:8095/task/addTask
http://localhost:8095/task/deleteTask/{taskId}
http://localhost:8095/task/updateTask/{taskId}
http://localhost:8095/task/endTask

In case the port number is to be changed , Angular service Url would also need to change.
