<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company Home</title>
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
	<h2>Welcome: ${sessionScope.loggedCompany.getCompanyName()}</h2>
	<form action="companyAction.php" method="post">
		<button type="submit" name="action" value="view">View Jobs</button><br><br>
		<button type="submit" name="action" value="add">Add Jobs</button><br><br>
		<button type="submit" name="action" value="ViewApplicants">View Applicants</button><br><br>
		<button type="submit" name="action" value="logout">Logout</button><b/><br>
	</form>
	
</body>
</html>