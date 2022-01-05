<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Posting Update</title>
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
	
	.register{
		margin-left: 520px;
		padding-top: 130px;
		padding: 40px;
		height: 850px;
		border-bottom: 4px solid #ec1d1d;
		box-sizing: border-box;
		background-color: #FDF6F5;
		width: 487px;
	}
	
	.register > h1 {
		color: #ec1d1d;
		padding-top: 50px;
		padding-bottom: 20px;
	}
	.input> input{
		    background: none;
		    background-color: white;
		    border: none;
		   	border-bottom-color: currentcolor;
		   	border-bottom-style: none;
		    border-bottom-width: medium;
		    border-bottom: 2px solid #ec1d1d;
	}
	.btn{
		color: white;
		background-color: #D41B2C;
		border: none;
		height: 35px;
		width: 88px;	
	}
	.sign-up{
		
		border: none;
		text-decoration: none;
	}
	h3{
		color: red;
	}
</style>

</head>
<body>
	<div class="container-fluid">
        <header>
			<h1><center>Career Foster</center></h1>
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
		
		<div class="register">
			<h1>Update the Position</h1>
			<div class = "container">
			<form class ="jobform" method="post" action = "${contextPath}/employeer/updateJobDetails.htm?name=${name}">
            	
            	<label for="job_title">Job Title*</label><br>
        		<input placeholder="Job Title"  value = ${jobDetails.jobTitle} maxlength="85" required="required" size="50" type="text" name="jobtitle" id="job_title">
        		<br>
        		<br>
            	<label for="job_company-name">Organization Name*</label><br>
            	<input placeholder="Company" value = ${jobDetails.companyName} maxlength="85" required="required" size="50" type="text" name="job_company_name" id="job_company-name">
        		<br>
				<br>
                <label >Job Type*</label>
                <div>
                    <input value = ${jobDetails.jobType} required="required" type="radio" value="Full Time" name="job_type" id="job_type_full">
                    <label for="job_type_full">Full Time</label>
                </div>
                <div>
                    <input value = ${jobDetails.jobType} required="required" type="radio" value="Co-op" name="job_type" id="job_type_coop">
                    <label for="job_type_coop">Co-op</label>
                </div>
                <div>
                    <input value = ${jobDetails.jobType} required="required" type="radio" value="Contract" name="job_type" id="job_type_contract">
                    <label for="job_type_contract">Contract</label>
                </div>
                <div>
                    <input value = ${jobDetails.jobType} required="required" type="radio" value="On Campus" name="job_type" id="job_type_oncampus">
                    <label for="job_type_oncampus">On Campus</label>
                </div>
                <br>
            	<label>Location*</label><br>
					<select name ="state" value = ${jobDetails.state} id ="job_type_state">
               			<option value="---">---</option><option value="Alabama">Alabama</option><option value="Alaska">Alaska</option><option value="Arizona">Arizona</option><option value="Arkansas">Arkansas</option><option value="California">California</option><option value="Colorado">Colorado</option><option value="Connecticut">Connecticut</option><option value="Delaware">Delaware</option><option value="District of Columbia">District of Columbia</option><option value="Florida">Florida</option><option value="Georgia">Georgia</option><option value="Guam">Guam</option><option value="Hawaii">Hawaii</option><option value="Idaho">Idaho</option><option value="Illinois">Illinois</option><option value="Indiana">Indiana</option><option value="Iowa">Iowa</option><option value="Kansas">Kansas</option><option value="Kentucky">Kentucky</option><option value="Louisiana">Louisiana</option><option value="Maine">Maine</option><option value="Maryland">Maryland</option><option value="Massachusetts">Massachusetts</option><option value="Michigan">Michigan</option><option value="Minnesota">Minnesota</option><option value="Mississippi">Mississippi</option><option value="Missouri">Missouri</option><option value="Montana">Montana</option><option value="Nebraska">Nebraska</option><option value="Nevada">Nevada</option><option value="New Hampshire">New Hampshire</option><option value="New Jersey">New Jersey</option><option value="New Mexico">New Mexico</option><option value="New York">New York</option><option value="North Carolina">North Carolina</option><option value="North Dakota">North Dakota</option><option value="Northern Marianas Islands">Northern Marianas Islands</option><option value="Ohio">Ohio</option><option value="Oklahoma">Oklahoma</option><option value="Oregon">Oregon</option><option value="Pennsylvania">Pennsylvania</option><option value="Puerto Rico">Puerto Rico</option><option value="Rhode Island">Rhode Island</option><option value="South Carolina">South Carolina</option><option value="South Dakota">South Dakota</option><option value="Tennessee">Tennessee</option><option value="Texas">Texas</option><option value="Utah">Utah</option><option value="Vermont">Vermont</option><option value="Virginia">Virginia</option><option value="Virgin Islands">Virgin Islands</option><option value="Washington">Washington</option><option value="West Virginia">West Virginia</option><option value="Wisconsin">Wisconsin</option><option value="Wyoming">Wyoming</option>
            		</select><br>
            	<br>
            	<label>Job Description</label><br>
                    <label for="job_desc_url">URL</label>
                    <div class="show-if-active">
                        <input placeholder="http://your-company.com/jobs/job.html" type="url" max="200" size="50" name="job_url" id="job_url">
                    </div>
                    <label for="job_desc_onhere">Job Description</label>
                    <div class="markItUpContainer">
                        <textarea rows="10" cols="40" value = ${jobDetails.description} placeholder="Describe the job briefly" name= "job_description"
                        id="job_description" spellcheck="true" style="z-index: auto; position: relative"; font-size: 19px;></textarea>
                    </div>
                <br>
        		<div style="text-align:center;">
        			<input type = "submit" class="btn btn-success btn-lg" value = "Update" />
       			 </div>
        	</form>
        </div><br>
    </div>
    </div> 
</body></html>