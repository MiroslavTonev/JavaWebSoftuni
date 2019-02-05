<%@page import="meTube.domain.models.view.AllTubesViewModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<c:import url="templates/head.jsp"></c:import>
</head>
<body>
<% List<AllTubesViewModel> tubes = (List<AllTubesViewModel>) request.getAttribute("allTubes"); %>
    <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>All Tubes</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col md-12 d-flex justify-content-center">
                    <h3>Check our tubes below</h3>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col md-12 d-flex justify-content-center">
                    <ul>
                    	<% for(AllTubesViewModel tube : tubes){%>
                    		<li><a href = "/tubes/details?name=<%= tube.getName() %>"><%= tube.getName() %></a></li>
                   		<% } %>
                    </ul>
                    
                </div>
            </div>  
            <div class="row">
                <div class="col md-12 d-flex justify-content-center">
                    <a href="/">Back to home.</a>
                </div>
            </div>                             
        </div>
        <footer>
            <c:import url="templates/footer.jsp"></c:import>
        </footer>       
</body>
</html>