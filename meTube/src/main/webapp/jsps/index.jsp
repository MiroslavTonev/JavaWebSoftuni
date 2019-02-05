<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<c:import url="templates/head.jsp"></c:import>	
</head>
<body>
	<div class="container">
        <main>
            <div class="jumbotron">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Welcome to MeTube application</h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col md-12 d-flex justify-content-center">
                        <p>Cool app at Betta version</p>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col md-12 d-flex justify-content-center">
                        <div class="col md-6 d-flex justify-content-center">
                            <a href="/tubes/create" class="btn btn-primary">Create Tube</a>
                        </div>
                        <div class="col md-6 d-flex justify-content-center">
                            <a href="/tubes/all" class="btn btn-primary">All Tube</a>
                        </div>
                    </div>
                </div>                              
            </div>
            <footer>
                <c:import url="templates/footer.jsp"></c:import>
            </footer>
        </main>
    </div>
</body>
</html>