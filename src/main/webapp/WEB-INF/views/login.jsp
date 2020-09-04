<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/loginStyle.css'/>" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/RSA/rsa.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/RSA/jsbn.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/RSA/prng4.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/RSA/rng.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/file/login.js'/>"></script>
<!-- <script type="text/javascript" src="<c:url value='/js/file/login.js'/>"></script> -->
<title>Insert title here</title>
</head>
<body>

	<div class="sidenav">
		<div class="login-main-text">
			<h2>
				Application<br> Login Page
			</h2>
			<p>Login or register from here to access.</p>
		</div>
	</div>
	<div class="main">
		<div class="col-md-6 col-sm-12">
			<div class="login-form">
				<form id="LoginForm" name="LoginForm" method="post">
					<div class="form-group">
						<input type="hidden" id="RSAModulus" value="${RSAModulus}" /> <input
							type="hidden" id="RSAExponent" value="${RSAExponent}" />
					</div>
					<div class="form-group">
						<label>User Name</label> <input type="text" class="form-control"
							id="userId" placeholder="User Name"> <input type="hidden"
							id="USER_ID" name="userId">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password" id="password"
							class="form-control" placeholder="Password"> <input
							type="hidden" id="USER_PW" name="password">
					</div>
					<button type="button" class="btn btn-black" id="submitBtn"
						value="LogIn">Login</button>
					<button type="button" onclick="location.href='/Board/register.do'"
						class="btn btn-secondary">Register</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>