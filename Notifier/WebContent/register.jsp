<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="style.css" rel="stylesheet" >
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" 
  >

	<link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@500&display=swap" rel="stylesheet">

	<link href="https://fonts.googleapis.com/css2?family=Heebo&family=Roboto+Slab:wght@400;500&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css" />
	
  
<title>Login</title>
</head>
<body>
<h3 class="head text-center mt-5 animate__animated animate__fadeInTopLeft">LOGIN</h3>
    <section class="my-3 pt-5">
        <div class="text-center">
            <h1 class="display-5 animate__animated animate__fadeInRight">SignUp Form</h1>
            <hr class="w-25 mx-auto margintop"></hr>
        </div> 
        <div class="container">
            <div class="row">
                <div class="col-xxl-6 col-10 col-md-6 mx-auto">
                    <span style="color: red"><%=(request.getAttribute("errMessage")== null)?"":request.getAttribute("errMessage") %></span>   
				  <form action="<%= request.getContextPath() %>/register"  method="post">
				    <h4><c:if test="${msg=='failure duplicate'}">Email already registered</c:if></h4>
				    <h4><c:if test="${msg=='success register'}"> successfully registered</c:if></h4>
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" class="form-control" name="name" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email address</label>
                            <input type="email" class="form-control" aria-describedby="emailHelp" name="email"/>
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mobile no.</label>
                            <input type="number" class="form-control" aria-describedby="mobileHelp" name="mobile"/>
                            <div id="mobileHelp" class="form-text">We'll never share your mobile with anyone else.</div>
                        </div>
                        <div class="mb-4">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password"/>
                        </div>
                        <div class="mb-4">
                            <label class="form-label">Confirm-Password</label>
                            <input type="password" class="form-control" name="cpassword"/>
                        </div>
                      
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary mb-3 sbtn">Register</button>
                        </div>
                    </form>

                    <p class="mb-3">Already Have An Account ? <a href="<%=request.getContextPath()%>/login">Login Here</a></p>
                </div>

            </div>
        </div>
    </section>
</body>
</html>  
