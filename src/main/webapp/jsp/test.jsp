<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 19.07.2020
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=TEST" enctype="multipart/form-data" method="POST">
    <input type="hidden" name="command" value="test"/>
    <input type="file" name="file" id="file"/>
    <input type="text" name="name" placeholder="Name"/>
    <input type="checkbox" name="yes" value="yes"/>
    <input type="checkbox" name="no" value="yes"/>

    <input type="submit" name="submit"/>
</form>
</body>
</html>
