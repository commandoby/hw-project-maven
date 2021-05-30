<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="col-md-8 offset-md-4">
        <h2>Login form with validation</h2>
        <p>Please, enter your credentials</p>
        <form action="${pageContext.request.contextPath}/index.jsp" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="uname">Username:</label>
                <input type="text" class="form-control w-25" id="uname" placeholder="Enter username" name="uname"
                       required>
                <div class="invalid-feedback">Please fill out username field</div>
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control w-25" id="pwd" placeholder="Enter password" name="pswd"
                       required>
                <div class="invalid-feedback">Please fill out password field</div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
