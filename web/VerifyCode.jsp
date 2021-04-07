<%-- 
    Document   : VerifyCode
    Created on : Feb 26, 2021, 10:57:14 PM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="VerifyCode" method="post">
            <input  type="text" name="authCode"/>
            <input type="hidden" name="txtId" value="${valueId}"/>
            <input type="hidden" name="txtPassword" value="${valuePassword}"/>
            <input type="hidden" name="txtPhone" value="${valuePhone}"/>
            <input type="hidden" name="txtName" value="${valueName}"/>
            <input type="hidden" name="txtAddress" value="${valueAddress}"/>
            <input type="submit" value="Verify code"/>
        </form>
    </body>
</html>
