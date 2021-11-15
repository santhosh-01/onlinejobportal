<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edu.onlinejobportal.Pojo.Application"%>
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
input[type=action]{
	margin-left: 200px
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
	margin-left: 200px
	background-color: #4CAF50;
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
	<h2>Below are the applications: </h2>
	<form action="cancelJobs.php" method="get">
		<table>
			<thead>
				<th>Job ID</th>
				<th>Job Name</th>
				<th>Application ID</th>
				<th>Applicant ID</th>
				<th>Applicant Name</th>
				<th>Date Applied</th>
				<th>Resume</th>
			</thead>
			<c:forEach var="list" items="${requestScope.jobs }">
				<c:forEach var="list_application" items="${list.getApplication()}">
					<tr>
						<td><c:out value="${list.getId()}" /></td>
						<td><c:out value="${list.getJobName()}" /></td>
						<td><c:out value="${list_application.getId()}" /></td>
						<td><c:out value="${list_application.getApplicant().getId()}" /></td>
						<td><c:out value="${list_application.getApplicant().getFirstName()}" /></td>
						<td><c:out value="${list_application.getApplydate()}" /></td>
						<td><a href="resume.pdf?name=<c:out value='${list_application.getApplicant().getResumefile()}' />" target="_blank" name="pdfName" data-value="<c:out value='${list_application.getApplicant().getResumefile()}' />" >Resume</a></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		<input action="action" class="signupbtn" type="button"
			onclick="history.go(-1);" value="Back"></input>
	</form>
</body>
</html>