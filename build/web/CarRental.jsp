<%-- 
    Document   : CarRental
    Created on : Feb 23, 2021, 11:44:45 AM
    Author     : ANH NGUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                width: 80%;
                border: 1px;
                margin-left: 20px;
                margin-bottom: 15px;
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
            .preferences{
                background-color: rgb(121 165 143 / 50%);
                position: absolute;
                z-index: 99999999;
                width: 180px;
                left: 0%;
                border-radius: 10px;

            }
            .preferences div{
                border: 2px solid #79a58f;
                border-radius: 10px;
                width: 250px;
                margin: 10px auto;
                height: 300px;
                background-color: #d8e6df;
            }

            .preferences h4{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 120%;
                margin: 15px 0px;
            }

            .together{
                background-color: rgb(121 165 143 / 50%);
                position: absolute;
                z-index: 99999999;
                width: 270px;
                top: 127%;
                border-radius: 10px;
            }
            .together div{
                border: solid #79a58f;
                border-radius: 10px;
                width: 250px;
                margin: 10px auto;
                height: 300px;
                background-color: #d8e6df;
            }

            .together h4{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 90%;
                margin: 15px 0px;
            }

            .together h3{
                text-align: center;
                font-family: Georgia, 'Times New Roman', Times, serif;
                font-size: 150%;
                margin: 15px 0px;
            }
            .imgFavor{
                text-align: center;
                border: 5px;
            }
            p{
                margin: 15px 20px;

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
                                        <a class="nav-link" href="UserHistory"><i class="fa fa-history"></i>Rental History</a>
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
                            <form class="form-inline my-2 my-lg-0" action="SearchCar">
                                <input  type="radio" name="radioValue" value="category" id="category"/>
                                &nbsp; 
                                <select name="categoryCar" >
                                    <option>Chervolet</option>
                                    <option>Lamborghini</option>
                                    <option>Ferrari</option>
                                    <option>Mercedes</option>
                                </select>
                                &nbsp; 
                                <input  type="radio" name="radioValue" value="nameCar"/>
                                &nbsp; 
                                <input class="form-control mr-sm-2" type="search" placeholder="Name car" aria-label="Search" name="txtSearchName">
                                &nbsp;                        
                                <div>
                                    Rental Date
                                    <input type="date" name="rentalDate" min="${current}"> 
                                    Return Date
                                    <input type="date" name="returnDate">
                                    Amount cart
                                    <input type="number" name="amountCar" min="1"/>
                                </div>
                                &nbsp;                           
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="search"/>
                            </form>
                    </div>

                    </ul>
            </div> 


            <p style="color: green" ><c:out value="${welcomeUser}" /></p>
            <p style="color: green" ><c:out value="${addCart}" /></p>
            <p style="color: red" ><c:out value="${inValidQuantity}" /></p>
            <p style="color: green" ><c:out value="${emailSuccess}" /></p>
            <p style="color: green" ><c:out value="${confirmMess}" /></p>
            <p style="color: red" ><c:out value="${errorRadio}" /></p>
            <p style="color: red" ><c:out value="${error}" /></p>
            <div class="body">
                <div class="row">
                    <c:choose>
                        <c:when test="${ empty search}">
                            <c:forEach var="c" items="${listCar}">

                                <div class="col-md-3">
                                    <div class="product-top">
                                        <img src="${c.image}" style="width: 300px">
                                        <div class="overlay">
                                            <form action="AddToCartt">
                                                <input type="hidden" name="txtCarID" value="${c.carID}"/>
                                                <input type="hidden" name="txtCarName" value="${c.carName}"/>
                                                <c:if test="${not empty nameLogin and nameLogin ne 'admin'}">
                                                    <button type="submit" class="btn btn-secondary" title="add Cart"  value="Rental car"><i class="fa fa-cart-plus"></i></button>
                                                    </c:if>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="product-bottom text-center">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <h6>${c.carName}</h6>
                                        <h7>${c.price} $</h7>
                                        <h7>${c.category}</h7>
                                    </div>       
                                </div>

                            </c:forEach> 
                        </c:when>               
                    </c:choose>
                    
                    <c:set  var="listSearch2" value="${listSearch}"/>
                       
                            <c:forEach var="c" items="${listSearch2}">
                                <div class="col-md-3">
                                    <div class="product-top">
                                        <img src="${c.image}" style="width: 300px">
                                        <div class="overlay">
                                            <form action="AddToCartt">
                                                <input type="hidden" name="txtCarID" value="${c.carID}"/>
                                                <input type="hidden" name="txtCarName" value="${c.carName}"/>
                                                <c:if test="${not empty nameLogin and nameLogin ne 'admin'}">
                                                    <button type="submit" class="btn btn-secondary" title="add Cart"  value="Rental car"><i class="fa fa-cart-plus"></i></button>
                                                    </c:if>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="product-bottom text-center">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <h6>${c.carName}</h6>
                                        <h7>${c.price} $</h7>
                                        <h7>${c.category}</h7>
                                    </div>       
                                </div>

                            </c:forEach> 
                        
                        
                    
                    
                </div>
                <!-- Paging-->
                <div class="pagination">
                    <form action="LoadHome">
                        <c:forEach var="s" begin="1" end="${endPage}">
                            <input type="submit" name="index"  value="${s}"/>
                        </c:forEach>
                    </form>    
                </div>
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
                                <p><i class="fa fa-github-alt"></i> HN Company.</p>
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
    </body>
</html>
