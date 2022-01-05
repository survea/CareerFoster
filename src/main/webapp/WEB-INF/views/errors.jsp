<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
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
	
	<div>
        <div>
        <header>
			<h1>Career Foster</h1>
			<div>
				<nav>
				<a href="${contextPath}/student/viewalljobs.htm?name=${name}" style = "color:white; margin-left:20px;text-decoration:none">View all jobs</a>
                <a href="${contextPath}/student/viewMyJobs.htm?name=${name}" style="color: white; margin-left:20px; text-decoration:none">View my applied jobs</a>
				</nav>
			</div>
			<div class="nav">
				 <a href="${contextPath}/logout.htm" style = "color:white; margin-left:50px">Logout</a>
				 <a style = "color:white; margin-left:50px" >Hello , ${name.fname}</a>
			</div>
		</header>
        </div>
        <br><br><br><br><br><br>
        <center><h1 style="color: #35404f">An Error Occured</h1></center><br><br><br>
		  <centerstyle="color: red"><strong>${errorMessage}</strong></center>
</body>
</html>