<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Home Page</title>
<style>
	header{
		display: flex;
		align-items: center;
		background-color: #D41B2C;
		height: 94px;
		color: white;
		margin-left: 2;
		position: fixed;
		width: 100%;
		margin-top: 0px;
	}
	
	header > h1{
		color: white;
		margin-left:25px;
	}
	nav{
		display: flex;
		justify-items: center;
		align-content: center;
		margin-left: 300px;
	}
	nav > a{
		color: white;
	}
	.nav{
		display: flex;
		justify-items: center;
		align-content: center;
		margin-left: 500px;
	}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<header>
			<h1>Career Foster</h1>
			<div >
				<nav>
				<a href="${contextPath}/employeer/postjob.htm?name=${name}" style = "color:white; margin-left:20px">Post a Job</a>
                <a href="${contextPath}/employeer/myjobposts.htm" style = "color:white; margin-left:20px">View my Posts</a>
				</nav>
			</div>
			<div class="nav">
				 <a href="${contextPath}/logout.htm" style = "color:white; margin-left:50px">Logout</a>
				 <a style = "color:white; margin-left:50px" >Hello , ${name.fname}</a>
			</div>
		</header>
		<br><br><br><br><br><br>
		<h1><strong>${successMessage}</strong></h1>
		<h1><strong>${errorMessage}</strong></h1>
</body>
</html>