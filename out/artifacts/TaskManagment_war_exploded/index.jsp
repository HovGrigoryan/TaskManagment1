<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<% if (session.getAttribute("message") != null) { %>

<p style="color: red"><%= request.getAttribute("message")%>
</p>

<% } %>

login:
<form action="/login" method="post" >
    <input type="text" name="email" placeholder="please input username" required/><br>
    <input type="password" name="password" placeholder="please input password" required/> <br>
    <input type="submit" value="Login">
</form>
<br>
<br>
<br>
<%
    String msg = "";
    if (session.getAttribute("msg") != null) {

        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }
%>
<p style="color: red"><%= msg %>
</p>
Register:
<form action="/register" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="please input name" required/><br>
    <input type="text" name="surname" placeholder="please input surname" required/><br>
    <input type="text" name="email" placeholder="please input email" required/><br>
    <input type="password" name="password" placeholder="please input password" required/><br>
    <input type="file" name="image"/><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
