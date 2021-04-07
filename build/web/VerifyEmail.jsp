<%-- 
    Document   : VerifyEmail.jsp
    Created on : Mar 3, 2021, 6:34:21 PM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1{
                text-align: center;
                color: blueviolet;
                padding-top: 30px;
            }
            form{
                width: 250px;
                height: 300px;
                padding: 20px;
                position: absolute;
                background-color: blueviolet;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                text-align: center;
            }
            input{
                width: 100%;
                display: inline-block;
                margin: 20px 0;
                font-size: 20px;
            }
            label{
                color: #fff;
                font-weight: 700;
                font-size: 20px;
            }
        </style>
    </head>
    <body>

        <h1>User Email Verification</h1>
        <form action="UserVerify" method="post">
            <label>User ID</label>
            <input type="text" name="userId" value="${nameLogin}" readonly>
            <label>User Email</label>
            <input type="email" name="userEmail">
            <p style="color: red" ><c:out value="${errorEmail}" /></p>
        <input type="submit" value="Send Email">
    </form>
</body>
</html>
