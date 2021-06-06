<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2 align="center">List products category: ${category_name}</h2>

<form method="post">
    <div class="container" align="right">
        <button type="submit" class="btn btn-primary btn-lg" name="command" value="home_page">Home page</button>
        &nbsp
        <button type="submit" class="btn btn-primary btn-lg" name="command" value="basket">Basket</button>
        &nbsp
        <button type="submit" class="btn btn-danger btn-lg" name="command" value="sign-in">Escape</button>
    </div>

    <div class="container" method="post">
        <input type="hidden" name="category_name" value="${category_name}"/>
        <br>
        <div class="media border">
            <img class="card-img p-3" style="width:240px;height:160px" src="${contextPath}/images/tv.jpeg"
                 alt="Card image">
            <div class="media-body">
                <h4><small>Njdfh awe ae gaer </small>&nbsp98984651</h4>
                <p class="card-text">diuagweuvg;aisueva;weivua;eivha'eviha;sievu
                    iseuvglisuegvisuevg;IUSevg;isugviuvg aiweu aiue aiu ef
                    e; iauwefiuawe;iuf i;auef iuaefiu;iuehf;iuewfiu eiu ;aiu f;iu :EIUs ;iu ;ieufaiwuflerug re gal</p>
                <button type="submit" class="btn btn-primary" name="command" value="product_list">
                    Add to basket
                </button>
            </div>
        </div>
        <br>
    </div>
</form>
</body>
</html>