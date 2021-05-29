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
	
  
<title>Dashboard</title>
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
						<a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/DashboardServlet">Dashboard</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link" href="<%=request.getContextPath()%>/NotebooksServlet">Notebooks</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link " href="<%=request.getContextPath()%>/NotesServlet" >Notes</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link " href="<%=request.getContextPath()%>/EditUserServlet" >Edit User</a>
					</li>
					<li class="nav-item ml-4">
						<a class="nav-link " href="<%=request.getContextPath()%>/logout" >Logout</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<h3 class="head text-center mt-5 animate__animated animate__fadeInTopLeft">Dashboard</h3>
	<hr class="mb-5 mx-auto" />
  	<h1 style="text-align: center;">Your today's Remainders</h1>
  	<c:if test="${remlist.size() gt 0}">
	<div class="container  ">
		<div class="row  text-center">
			<c:forEach items="${remlist }" var="book" varStatus="counter">
			 <div class="mb-3 col-lg-3 col-lg-offset-0 col-md-3 col-md-offset-0 col-sm-5 col-sm-offset-1 col-6">
			 	<div class="card" >
				  <div class="card-body">
				    <h5 class="card-title">Remainder ${counter.count }</h5>
				    <p class="card-text">Notebook name: ${book.notebookname}</p>
				    <p class="card-text">Note: ${book.note}</p>
				    <p class="card-text">Description: ${book.desc}</p>
				  </div>
				</div>
			 </div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	<c:if test="${remlist.size() eq 0}">
		<p style="text-align:center;">No remainders for today. You can add one by clicking <a href="<%=request.getContextPath()%>/AddNewNote">here</a>.</p>
	</c:if>
</body>
</html>