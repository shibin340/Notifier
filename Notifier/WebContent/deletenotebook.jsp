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
	
  
<title>Add Notebook</title>
</head>
<body>
<section class="my-3 pt-5">
        <div class="text-center">
            <h1 class="display-5 animate__animated animate__fadeInRight">Delete a Notebook</h1>
            <hr class="w-25 mx-auto margintop"></hr>
        </div> 
        <div class="container">

            <div class="row">
                <div class="col-xxl-7 col-10 col-md-6 mx-auto">
                 	<span style="color: red"><%=(request.getAttribute("errMessage")== null)?"":request.getAttribute("errMessage") %></span>      
                
                    <form action="<%=request.getContextPath()%>/DeleteNotebook" method="post">
                     <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" name="name" />
                        <div id="emailHelp" class="form-text">Enter the name of notebook you want to delete.</div>
                    </div>
                    <p style="color: red">Note*: Deleting a notebook will delete all notes present in it.</p>
                    <div class="text-center">
                        <button type="submit" class="btn btn-danger mb-3 sbtn" >Delete Notebook</button>
                    </div>
                </form>
                <a href="<%=request.getContextPath() %>/NotebooksServlet">Go back</a>
            </div>

        </div>
    </div>
</section>  
</body>
</html>  
