<%-- 
    Document   : InformationCar
    Created on : Mar 4, 2021, 10:47:17 PM
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
            <form action="ConfirmDate" style="width: 500px; margin: auto " method="post">            
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Car ID</b></label>
                            <input type="text" name="carID" class="form-control" value="${inId}" readonly >                          
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Car Name</b></label>
                            <input type="text" name="carName" class="form-control" value="${inName}" readonly >
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Color</b></label>
                            <input type="text" name="carColor" class="form-control" value="${inColor}" readonly >
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Year</b></label>
                            <input type="text" name="carYear" class="form-control" value="${inYear}" readonly>
                        </div>
                    </div>

                </div>

                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Category</b></label>
                            <input type="text" name="carCategory" class="form-control" value="${inCategory}" readonly >
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Price</b></label>
                            <input type="text" name="carPrice" class="form-control" value="${inPrice}" readonly>
                        </div>
                    </div>

                </div>
                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label for="firstname"><b>Rental Date</b></label>
                            <input type="date" name="rentalDate"  value="${rentalDate}"  min="${current}"> 
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <label for="lastname"><b>Return Date</b></label>
                            <input type="date" name="returnDate" value="${returnDate}">
                        </div>
                    </div>

                     
                </div>
                        <input type="hidden" name="hiddenImage" value="${inImage}"/>        
                <input  type="hidden" name="hiddenQuantity" value="${hiddenQuantity}"/>
                <p style="color: red" ><c:out value="${invalid}" /></p>
                <p style="color: red" ><c:out value="${error}" /></p>
                <input type="submit" class="btn btn-primary btn-block" value="Confirm Date" >
            </form>
        </div>
    </body>
</html>
