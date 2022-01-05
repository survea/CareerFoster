<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Jobs</title>
 <script type="text/javascript">
   	function display() {
   		document.getElementById("openForm").style.display = "block";
	}
   	function displayOff(){
   		document.getElementById("openForm").style.display = "none";
   	}
</script>
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
	
	table {
	  font-family: arial, sans-serif;
		border-collapse: collapse;
		width: 100%;
		padding-top: 171px;
		margin-top: 80px
	}
	
	td, th {
	  border: 1px solid #dddddd;
	  text-align: left;
	  padding: 8px;
	}
	
	tr:nth-child(even) {
	  background-color: #dddddd;
	}
	.btn{
		color: white;
		background-color: #D41B2C;
		border: none;
		height: 35px;
		width: 88px;
	}
</style>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
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
		<br><br><br><br><br><br>
		<div>
			<center><h2>My Applications</h2></center>
			<form action = "${contextPath}/student/deleteMyApplication.htm" method="get">
			<table class = "table">
				<tr>
					<th><b>Job ID</b></th>
					<th><b>Title</b></th>
					<th><b>Company Name</b></th>
					<th><b>Location</b></th>
					<th><b>Job-URL</b></th>
					<th><b>Description</b></th>
					<th><b>Posted On</b></th>
					<th><b>Withdraw</b></th>
				</tr>
				<c:forEach var ="i" items= "${jobs}">
				<c:forEach var="j" items="${i}">
				
          		<input type="hidden" name = "jobID" value = "${j.id}" />
					<tr>
						<td>${j.id}</td>
						<td>${j.jobTitle}</td>
						<td>${j.companyName}</td>
						<td>${j.state}, ${j.country}</td>
						<c:if test= "${empty j.jobUrl}"> 
						<td>No link available. Please check the company's website</td></c:if>
						<c:if test= "${not empty j.jobUrl}"> 
						<td>${j.jobUrl}</td></c:if>
						<c:if test= "${empty j.description}"> 
						<td>No description provided</td></c:if>
						<c:if test= "${not empty j.description}"> 
						<td>${j.description}</td></c:if>
						<td>${j.postedOn}</td>
						<td><button type="button" onclick="display();" class ="btn">Withdraw application</button>
					</td>
					<tr>
				</c:forEach>
				</c:forEach>
				
			  	<div id="openForm" style ="display:none; align:center;" role="dialog">
			    <div >
			      <div >
			        <div >
			          <h4  style="color:red"><i class="fas fa-exclamation-triangle"></i> Warning: Withdraw application?</h4>
			        </div>
			        <div >
			          <p>Are you sure you want withdraw the application?</p>
			        </div>
			        <div >
			          
			          <button type="submit" class="btn btn-danger btn-lg">Yes</button>
			        
			          <button type="button" class="btn btn-default btn-lg" onclick="displayOff();" >No</button>
			                  </div>
			    </div></div></div>
           </table></form>
	</div>
  </div> 
</body>
</html>