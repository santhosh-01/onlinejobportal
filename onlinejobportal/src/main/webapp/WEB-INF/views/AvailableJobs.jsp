<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edu.onlinejobportal.Pojo.Jobs"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 60%;
	margin-left: 200px
}
.signupbtn{
	margin-left: 200px
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


td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
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
<h2>Below are the available jobs: </h2>
<p style="color:red;">${requestScope.Error}</p>
	<form action="applyJobs.php" method="get">
		<table>
			<thead>
				<th>select</th>
				<th>ID</th>
				<th>Job Name</th>
				<th>Job Description</th>
				<th>Company Name</th>
			</thead>
			<c:forEach var="list" items="${requestScope.jobs }">
				<tr>
					<td><input type="checkbox" name="jobs" value="${list.getId()}"></td>
					<td><c:out value="${list.getId()}" /></td>
					<td><c:out value="${list.getJobName()}" /></td>
					<td><c:out value="${list.getDescription()}" /></td>
					<td><c:out value="${list.getCompanyName()}" /></td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit" class="signupbtn">Apply</button>
		<input action="action" class="signupbtn" type="button"
			onclick="history.go(-1);" value="Back"></input>
	</form>
</body>
</html>