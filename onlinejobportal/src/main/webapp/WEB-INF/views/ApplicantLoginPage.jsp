<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Job Portal</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 32px 0 12px 0;
}

img.avatar {
	width: 10%;
	border-radius: 10%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>
	<h2>Applicant Login Form</h2>

	<form:form commandName="applicant" action="applicant_home_page.php">
		<div class="imgcontainer">
			<img src="${pageContext.request.contextPath}/resources/neu.png" alt="Avatar" class="avatar">
		</div>
		<p style="color:red;">${requestScope.Error}</p>
		<p style="color:green;">${requestScope.msg}</p>
		<div class="container">
			<label for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" required>
				 
			<label for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required>

			<button type="submit">Login</button>
			<label> <a href="Applicantsignup.php" name="signup"> SIGN UP </a>
			</label>
		</div>

		<div class="container">
			<label> <a href="/onlinejobportal" name="homePage"> HOME PAGE </a>
		</div>

	</form:form>
</body>
</html>