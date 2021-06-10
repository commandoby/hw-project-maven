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
<h2 align="center">Basket of products</h2>

<form method="post">
    <div class="container" align="right">
        <button type="submit" class="btn btn-success btn-lg" name="command" value="pay">&nbsp&nbsp Pay &nbsp&nbsp
        </button>
        &nbsp&nbsp
        <button type="submit" class="btn btn-primary btn-lg" name="command" value="home_page">Home page</button>
        &nbsp&nbsp
        <button type="submit" class="btn btn-danger btn-lg" name="command" value="sign-in">Escape</button>
    </div>

    <% int id_basket = 0; %>
    <div class="container">
        <input type="hidden" name="command" value="basket"/>
        <br>
        <h3>There are ${basket_size} products in the basket for the amount of:
            <b style="color: orangered">${basket_price}</b></h3>
        <c:if test="${not empty basket_list}">
            <c:forEach items="${basket_list}" var="product">
                <div class="media border">
                    <img class="card-img p-3" style="max-width:220px;max-height: 360px"
                         src="${contextPath}/images/${product.getCategories().getTag()}/${product.getImageName()}"
                         alt="Card image">
                    <div class="media-body">
                        <h4>${product.getName()}&nbsp&nbsp&nbsp<small> Price: </small>
                            <b style="color: orangered">${product.getPrice()}</b></h4>
                        <p class="card-text">${product.getDescription()}</p>
                        <button type="submit" class="btn btn-primary" name="remove_id" value="<%=id_basket%>">
                            Remove from basket
                        </button>
                    </div>
                </div>
                <br>
                <% id_basket++; %>
            </c:forEach>
        </c:if>
    </div>
</form>
</body>
</html>
