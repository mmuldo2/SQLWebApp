<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>City List</title>
</head>
<body>

	<div style="background: #E0E0E0; height: 55px; padding: 5px;">
	  <div style="float: left">
	     <h1>Cities in ${countryCode}</h1>
	  </div>
	</div>
  <div>
  
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Country Code</th>
          <th>District</th>
          <th>Population</th>
       </tr>
       <c:forEach items="${cityList}" var="City" >
          <tr>
             <td>${City.id}</td>
             <td>${City.name}</td>
             <td>${City.countryCode}</td>
             <td>${City.district}</td>
             <td>${City.population}</td>
          </tr>
       </c:forEach>
    </table>
  </div>
    
</body>
</html>