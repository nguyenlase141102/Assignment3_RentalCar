<%-- 
    Document   : RegisterAccount
    Created on : Feb 25, 2021, 9:12:11 AM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="container-fluid bg">
            <form action="registrationn" style="width: 500px; margin: auto " method="post">            
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>User ID</b></label>
                            <input type="text" name="userID" class="form-control" value="${valueId}" >                          
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>User password</b></label>
                            <input type="password" name="userPassword" class="form-control" value="${valuePassword}" >
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Phone</b></label>
                            <input type="text" name="userPhone" class="form-control" value="${valuePhone}" >
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>User name</b></label>
                            <input type="text" name="userName" class="form-control" value="${valueName}">
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>User address</b></label>
                            <input type="text" name="userAddress" class="form-control"value="${valueAddress}" >
                        </div>
                    </div>                       
                </div>

                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <c:choose>
                                <c:when test="${empty verify}">
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="lastname"><b>Please enter your email: (If you don't need to use our service, you can skip it) </b></label>
                                                <input type="text" name="userEmail" class="form-control" >
                                                <input type="submit" value="Verify Email"/>
                                            </div>
                                            
                                        </div>                       
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: green"><c:out value="${verify}" /></p>
                                    <c:set  var="checked" value="success"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>  

                </div>
                <p style="color: red"><b><c:out value="${errorRegistration}" /></b></p>
                <input type="hidden" name="checkVerify" value="${checked}"/>
                <input type="submit" class="btn btn-primary btn-block" value="Create Account" >
            </form>
        </div>
    </body>
</html>
