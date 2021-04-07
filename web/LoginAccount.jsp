<%-- 
    Document   : LoginAccount
    Created on : Feb 20, 2021, 9:19:20 PM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN ACCOUNT</title>
        <!--BootStrap-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="https://www.google.com/recaptcha/api.js"></script>
        <!--CSS-->
        <style type="text/css">
            .form-container{
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px #000;
            }
            .bg{
                background: url('loginAccount.jpg') no-repeat;
                width: 100%;
                height: 100vh;
                background-size: 100%;
            }
        </style>
    </head>
    <body>
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="login" method="post">
                        <p style="color: green">${success}</p>
                        <div class="form-group">
                            <label for="exampleInputEmail1">UserID</label>
                            <input type="text" class="form-control"  placeholder="Enter user" name="txtUser" value="${txtUserID}" >                       
                            <small id="emailHelp" class="form-text text-muted">We'll never share your ID with anyone else.</small>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" value="${txtPassword}">
                        </div>

                        <p style="color: red"><c:out value="${error}" /></p>
                         <div class="g-recaptcha" data-type="image"  data-sitekey="6LdI3GgaAAAAAM4kTOrEhY1uGBWtzDBowK_pXMH1"></div>
                         
                            <br/>                 
                        <input type="submit" class="btn btn-primary btn-block" value="Login account"> 
                        <a href="RegisterAccount">Sign Up</a>
                    </form>

                </section>
            </section>
        </section>
    </body>
</html>
