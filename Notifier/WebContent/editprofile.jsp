<%@page import="dao.UserDao"%>
<%@page import="model.User"%>
<%
if((request.getSession(false).getAttribute("Student")== null) )
{
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%} %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="style.css" rel="stylesheet" >
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" >

	<link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@500&display=swap" rel="stylesheet">

	<link href="https://fonts.googleapis.com/css2?family=Heebo&family=Roboto+Slab:wght@400;500&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css" />
	
  
<title>Edit Profile</title>
</head>

<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/DashboardServlet">Notifier</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" 
			aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item ml-4">
						<a class="nav-link" href="<%=request.getContextPath()%>/DashboardServlet">Dashboard</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link"  href="<%=request.getContextPath()%>/NotebooksServlet">Notebooks</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link " href="<%=request.getContextPath()%>/NotesServlet" >Notes</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/EditUserServlet" >Edit User</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link " href="<%=request.getContextPath()%>/logout" >Logout</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
  	<h3 class="head text-center mt-5 animate__animated animate__fadeInTopLeft">Your Profile</h3>
	<hr class="mb-5 mx-auto" />
	<div class="container">
		
		<div class="row">
			<div class="col-xxl-8 col-10 col-md-8 mx-auto">
			<div class="text-center mb-3">
				<a href="<%=request.getContextPath()%>/AddNewNote"></a>
			</div>
			</div>
		</div>
		
		<div class="container">

            <div class="row">
                <div class="col-xxl-7 col-10 col-md-6 mx-auto">
                 	<span style="color: red"><%=(request.getAttribute("errMessage")== null)?"":request.getAttribute("errMessage") %></span>      
                
                    <form action="<%=request.getContextPath()%>/EditUserServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" name="name" value="${user.name }"/>
                        <div id="emailHelp" class="form-text">Update name</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" name="pass" value="${user.password }"/>
                        <div id="emailHelp" class="form-text">Update password</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mobile Number</label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" name="mobile" value="${user.mobile }"/>
                        <div id="emailHelp" class="form-text">Update mobile number</div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary mb-3 sbtn">Update Profile</button>
                    </div>
                </form>
                <a href="<%=request.getContextPath() %>/DashboardServlet">Go back</a>
            </div>

        </div>
    </div>
	</div>
	
</body>
</html>