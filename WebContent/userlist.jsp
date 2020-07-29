<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
      		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
      	    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" 
            crossorigin="anonymous">
</head>
<body>
     <header>
			<nav class="navbar navbar-expand-md navbar-dark" 
			  style="background-color:tomato"> 
				<div>
					<a href="https://www.javaguides.net" class="navbar-brand">User Management App</a>
				</div>
					
				 <ul class="navbar-nav">
				    <li>
				       <a href="<%=request.getContextPath() %>/list" class="nav-link">Users</a>
				    </li>
				 </ul>
			</nav>
	</header>
    <br>
    <div class="row">
    	<div class="container">
    	  <h3 class="text-center">List of Users</h3>
    	  <hr>
    	  <div class="container text-left">
    	       <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
    	  </div>
          <br>
  <table class="table table-bordered">
   <thead>
	  <tr>  
		  <th>ID</th>
		  <th>name</th>
		  <th>email</th>
		  <th>country</th>
		  <th>&nbsp;&nbsp;Actions</th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="user" items="${listUser}">
	     <tr>
	       <td><c:out value="${user.id}"/></td>
	       <td><c:out value="${user.name}"/></td>
	       <td><c:out value="${user.email}"/></td>
	       <td><c:out value="${user.country}"/></td>
	       <td>&nbsp;&nbsp;&nbsp;
	       <a href="edit?id=<c:out value='${user.id}'/>">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       	<a href="delete?id=<c:out value='${user.id}'/>">Delete</a>
	       </td>
	     </tr>
	   </c:forEach>
	</tbody>
 </table>
 </div> 
 </div> 
 </body>
</html>
 
