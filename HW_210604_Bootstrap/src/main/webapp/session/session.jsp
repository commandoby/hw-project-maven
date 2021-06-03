<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Visits counter</title>
    <meta charset="UTF-8">
</head>
<body>
    <h2>Session ID is <%= session.getId() %></h2>
    <h2>Count of visits is <%= session.getAttribute("count") %></h2>
</body>
</html>