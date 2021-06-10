<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Categories</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2 align="center">Shop products of Sony.</h2>

<form method="post">
    <div class="container" align="right">
        <button type="submit" class="btn btn-primary btn-lg" name="command" value="basket">Basket (${basket_size})
        </button>
        &nbsp&nbsp
        <button type="submit" class="btn btn-danger btn-lg" name="command" value="sign-in">Escape</button>
    </div>

    <div class="container" method="post" align="center">
        <br>
        <c:if test="${not empty categories}">
            <input type="hidden" name="command" value="product_list"/>
            <div class="row">
                <c:forEach items="${categories}" var="category">
                    <button type="submit" class="btn btn-light" style="width:360px;height:260px"
                            name="category_tag" value="${category.getTag()}">
                        <div class="card-body">
                            <h4 class="card-title">${category.getName()}</h4>
                            <img class="card-img" style="height:160px"
                                 src="${contextPath}/images/${category.getImageName()}" alt="Card image">
                        </div>
                    </button>
                    &nbsp
                </c:forEach>
            </div>
        </c:if>
    </div>
</form>
</body>
</html>
