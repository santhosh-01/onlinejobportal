<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Applicant Home</title>
<style type="text/css">

	button {
	background-color: #4CAF50;
	margin-right: 200px
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 10%;
	opacity: 0.9;
}

</style>
</head>
<body>
<h2>Welcome: ${sessionScope.loggedApplicant.getFirstName()}</h2>
<h3>Click on button to proceed!</h3>
	<form action="applicantAction.php" method="post">
		<button type="submit" name="action" value="viewAvailable">View Available Jobs</button><br/>
		<button type="submit" name="action" value="viewApplied">View Applied Jobs</button><br/>
		<button type="submit" name="action" value="update">Update Resume</button><br/><br/>
		<button type="submit" name="action" value="logout">Logout</button><br/>
		
	</form>
</body>
</html>