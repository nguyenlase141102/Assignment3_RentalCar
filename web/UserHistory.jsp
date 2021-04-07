<%-- 
    Document   : Home
    Created on : Jan 5, 2021, 2:23:11 PM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width" initial-scale="1">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="243151196244-39r53fd3cafmickd0kdi5ksapb2p64sh.apps.googleusercontent.com">
        <title >Login Account</title>
        <!--Use bootStrap 4 -->     
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <style type="text/css"> 
            .container h2{
                background: #0062cc;
                color: #fff;
                width: 200px;
                font-size: 24px;
                padding: 5px;
                height: 40px;
            }
            .container h2::after{
                content: '';

                position: relative;
                left: 48px;
                top: 34px;
            }
            .row{
                margin-top: 30px;
            }
            img{
                width: 100%;
            }
            .product-bottom .fa{
                color: #28a745;
                font-size: 10px;
            }
            .product-bottom h6{
                font-size: 20px;
                font-weight: bold;
            }
            .product-bottom h7{
                font-size: 15px;
                padding-bottom: 10px;
            }
            .overlay{
                display: block;
                opacity: 0;
                position: absolute;
                top: 10%;
                margin-left: 0;
                width: 70px;
            }
            .product-top:hover .overlay{
                opacity: 1;
                margin-left: 5%;
                transition: 0.5s;
            }
            .overlay .fa{
                cursor: pointer;
                background-color: #fff;
                color: #000;
                height: 35px;
                width: 35px;
                font-size: 20px;
                padding: 7px;
                margin: 5%;
                margin-bottom: 5%;
            }
            .overlay .btn-secondary{
                background: transparent !important;
                border:none !important;
                box-shadow: none !important;  
            }
            .pagination{
                display: inline-block;
            }
            .pagination a{
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid;
            }
            .pagination a:hover{
                background-color: #ddd;
            }
            .pagination a:first-child {
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;                
            }
            .pagination a:first-child {
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;                
            }
        </style>

    </head>
    <body>
        <div class="container">
            <!--header -->
            <div class="header">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="LoadHome"><i class="fa fa-home"></i> Home <span class="sr-only">(current)</span></a>
                            </li>

                            <li class="nav-item">
                                <c:choose>
                                    <c:when test="${not empty nameLogin and nameLogin ne 'admin' and status eq 'active'}">
                                        <a class="nav-link" href="#"> <i class="fa fa-user"></i> ${nameLogin} </a>

                                    <li class="nav-item">
                                        <a class="nav-link" href="RentalCart"><i class="fa fa-shopping-cart"></i> Cart</a>
                                    </li>


                                    <li class="nav-item">
                                        <a class="nav-link" href="LogOut"><i class="fa fa-sign-out"></i>Log Out</a>
                                    </li>

                                </c:when>


                                <c:when test="${not empty nameLogin and nameLogin eq 'admin'}">
                                    <a class="nav-link" href="#"> <i class="fa fa-user"></i> ${nameLogin} </a>

                                    <li class="nav-item">
                                        <a class="nav-link" href="LogOut"><i class="fa fa-sign-out"></i>Log Out</a>
                                    </li>
                                </c:when>

                                <c:when test="${not empty nameLogin and nameLogin ne 'admin' and status eq 'new'}">
                                    <a class="nav-link" href="#"> <i class="fa fa-user"></i> ${nameLogin} </a>

                                    <li class="nav-item">
                                        <a class="nav-link" href="VerifyEmail"><i style="font-size:24px" class="fa">&#xf00c;</i>Verify Email</a>
                                    </li>


                                    <li class="nav-item">
                                        <a class="nav-link" href="LogOut"><i class="fa fa-sign-out"></i>Log Out</a>
                                    </li>

                                </c:when>

                                <c:otherwise>
                                    <a class="nav-link" href="LoginAccount"> <i class="fa fa-user"></i> Login </a> 
                                </c:otherwise>
                            </c:choose> 
                            </li>


                            <!--Search Car-->
                            <form class="form-inline my-2 my-lg-0" action="SearchHistory">
                                &nbsp; 
                                <input  type="radio" name="radioValue" value="nameCar"/>
                                &nbsp; 
                                <input class="form-control mr-sm-2" type="search" placeholder="Name car" aria-label="Search" name="txtSearchName">
                                &nbsp;
                                <input  type="radio" name="radioValue" value="date" id="category"/>
                                <div>
                                    Rental Date
                                    <input type="date" name="rentalDate"> 
                                    Return Date
                                    <input type="date" name="returnDate">
                                </div>
                                &nbsp;                           
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="search"/>
                            </form>
                    </div>

                    </ul>

            </div>


        </div> 

        <p style="color: green" ><c:out value="${welUser}" /></p>
        <c:set var="historySearch" value="${listSearchHistory}" />
        <p style="color: red" ><c:out value="${errorRadio}" /></p>
        <!--Body-->
        <div class="body">




            <c:choose>
                <c:when test="${empty historySearch}">
                    <table class="table table-striped table-dark">
                        <thead>
                            <tr>
                                <th scope="col"> No.</th>
                                <th scope="col"> User name </th>
                                <th scope="col"> Create order</th>
                                <th scope="col"> Total price</th>
                                <th scope="col"> Payment</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${listHistory}" varStatus="counter">
                                <c:if test="${s.status eq 'active'}">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${s.userID}</td>
                                        <td>${s.createOrder}</td>
                                        <td>${s.total}</td>
                                        <td>${s.payment}</td>
                                        <td>${s.orderID}</td>
                                        <td>
                                            <form action="HistoryDetails">
                                                <input type="submit" value="History Details"/>
                                                <input type="hidden" name="orderID"  value="${s.orderID}"/>
                                            </form>     
                                        </td>
                                        <td>
                                            <c:forEach var="k" items="${listDetails}">

                                                <c:choose>
                                                    <c:when test="${s.orderID ne k.orderID}">
                                                        <form action="deleteOrder">
                                                            <input type="submit" value="Delete"/>
                                                            <input type="hidden" name="hiddenOrder" value="${s.orderID}"/>
                                                        </form>
                                                    </c:when>
                                                </c:choose>

                                            </c:forEach> 
                                      
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        <a href="LoadHomePage">Continue Shopping</a>
                        </tbody>
                    </table>
                </c:when>


                <c:otherwise>
                    <table class="table table-striped table-dark">
                        <thead>
                            <tr>
                            <tr>
                                <th scope="col"> No.</th>
                                <th>Order ID</th>
                                <th>Car Name</th>
                                <th>Rental Date</th>
                                <th>Return Date</th>
                                <th>Quantity</th>
                            </tr>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${listSearchHistory}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${s.orderID}</td>
                                    <td>${s.carName}</td>
                                    <td>${s.rentalDate}</td>
                                    <td>${s.returnDate}</td>
                                    <td>${s.quantity}</td>
                                </tr>
                            </c:forEach>
                        <a href="LoadHomeServlet">Continue Shopping</a>
                        </tbody>
                    </table>
                </c:otherwise>


            </c:choose> 


        </div>

        <!-- footer -->
        <div class="footer">
            <!-- Footer -->
            <footer class="page-footer font-small mdb-color lighten-3 pt-4">

                <!-- Footer Links -->
                <div class="container text-center text-md-left">

                    <!-- Grid row -->
                    <div class="row">

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 mr-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Content -->
                            <h5 class="font-weight-bold text-uppercase mb-4">More Information</h5>
                            <p><i class="fa fa-github-alt"></i> HN Shop.</p>
                            <p>HN is a brand used by Asus since 2006, encompassing a range of computer hardware, personal computers, peripherals, and accessories oriented primarily toward PC gaming.</p>

                        </div>
                        <!-- Grid column -->

                        <hr class="clearfix w-100 d-md-none">

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 mx-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Links -->
                            <h5 class="font-weight-bold text-uppercase mb-4">About</h5>

                            <ul class="list-unstyled">
                                <li>
                                    <p>
                                        <a href="#!">PROJECTS</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!">ABOUT US</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!">BLOG</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!">AWARDS</a>
                                    </p>
                                </li>
                            </ul>

                        </div>
                        <!-- Grid column -->

                        <hr class="clearfix w-100 d-md-none">

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Contact details -->
                            <h5 class="font-weight-bold text-uppercase mb-4">Address</h5>

                            <ul class="list-unstyled">
                                <li>
                                    <p>
                                        <i class="fa fa-home"></i> Sai gon,Ho chi minh,2020-VN</p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-envelope-o"></i>   info@example.com</p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-phone-square"></i> + 01 234 567 88</p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-phone-square"></i> + 01 234 567 89</p>
                                </li>
                            </ul>

                        </div>
                        <!-- Grid column -->

                        <hr class="clearfix w-100 d-md-none">

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 text-center mx-auto my-4">

                            <!-- Social buttons -->
                            <h5 class="font-weight-bold text-uppercase mb-4">Follow Us</h5>

                            <!-- Facebook -->
                            <a type="button" class="btn-floating btn-fb">
                                <i class="fa fa-facebook-official"></i>
                            </a>
                            <!-- Twitter -->
                            <a type="button" class="btn-floating btn-tw">
                                <i class="fa fa-twitter"></i>
                            </a>
                            <!-- Google +-->
                            <a type="button" class="btn-floating btn-gplus">
                                <i class="fa fa-google"></i>
                            </a>
                            <!-- Dribbble -->
                            <a type="button" class="btn-floating btn-dribbble">
                                <i class="fa fa-dribbble"></i>
                            </a>

                        </div>
                        <!-- Grid column -->

                    </div>
                    <!-- Grid row -->

                </div>
                <!-- Footer Links -->

                <!-- Copyright -->


            </footer>
            <!-- Footer -->
        </div>

    </div> 

</div>

</body>
</html>
