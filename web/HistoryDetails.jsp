<%-- 
    Document   : HistoryDetails
    Created on : Mar 7, 2021, 2:43:29 AM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>
            <th>Order ID</th>
            <th>Car Name</th>
            <th>Rental Date</th>
            <th>Return Date</th>
            <th>Quantity</th>

        </thead>
        <tbody>
            <c:forEach var="s" items="${listDetails}" varStatus="counter">
                <tr>
                    <td>${s.orderID}</td>
                    <td>${s.carName}</td>
                    <td>${s.rentalDate}</td>
                    <td>${s.returnDate}</td>
                    <td>${s.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
