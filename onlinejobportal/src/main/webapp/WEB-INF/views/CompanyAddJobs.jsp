<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new jobs</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

select {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

option{
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=button]{
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: 1px solid #f1f1f1;
	cursor: pointer;
	width: 20%;
	opacity: 0.9;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: 1px solid #f1f1f1;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
	padding: 14px 20px;
	background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
	float: left;
	width: 20%;
}

/* Add padding to container elements */
.container {
	padding: 16px;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .signupbtn {
		width: 100%;
	}
}
</style>
</head>
<body>
	<form:form commandName="addJobs" action = "addJobs.php" style="border: 1px solid #ccc">
		<div class="container">
			<h1>ADD NEW JOBS</h1>
			<p>Please fill in this form to create new job.</p>
			<hr>

			<label for="jobName"><b>Job Title</b></label> <input type="text"
				placeholder="Enter Job Title" name="jobName" required>
				
			<label for="description"><b>Description</b></label> <input type="text"
				placeholder="Enter Description" name="description" required>
				
			<label for="city"><b>City</b></label> <input type="text"
				placeholder="Enter City" name="city" required> 
				
			<label for="state"><b>State</b></label> <input type="text"
				placeholder="Enter State" name="state" required>
				
			<div class="clearfix">
				<button type="submit" class="signupbtn">Save</button>
				<input action="action" class="signupbtn" type="button" onclick="history.go(-1);" value="back"></input>
			</div>
		</div>
	</form:form>
</body>
</html>