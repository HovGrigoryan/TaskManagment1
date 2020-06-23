<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ToDo" %>
<%@ page import="model.UserStatus" %><%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 20.06.2020
  Time: 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    List<ToDo> toDos = (List<ToDo>) request.getAttribute("tasks");
     List<User> users = (List<User>) request.getAttribute("users");
    if (user != null) {

%>

Welcome <%= user.getName() %> <%= user.getSurname() %> <br> <a href="/logout">Logout</a>
<a href="/">Index</a><br><br>
<% } %>

All Users
<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>status</td>
        <td>Delete</td>
    </tr>

    <% for (User user1 : users) { %>
    <tr>
        <td><%=user1.getId()%>
        </td>
        <td><%=user1.getName()%>
        </td>
        <td><%=user1.getSurname()%>
        </td>
        <td><%=user1.getEmail()%>
        </td>
        <td><%=user1.getStatus()%>
        </td>
        <td><a href="/removeUser?id=<%=user1.getId()%>">delete</a></td>

    </tr>
    <% } %>

</table>

Add ToDo:
<form action="/addTodo" method="post">
    <input name="title" type="text"/><br>
    <input name="deadline" type="date"/><br>
    status:TODO <input type="radio" value="TODO" name="status"> INPROGRESS
    <input type="radio" value="INPROGRESS" name="status">FINISHED
    <input type="radio" value="FINISHED" name="status"> <br>
    <select name="userID">
        <% for (User user1 : users) { %>
        <% if (user1.getStatus() == UserStatus.USER) { %>
        <option value="<%=user1.getId()%>"><%=user1.getName()%>
        </option>
        <%
                }
            }
        %>
    </select><br>
    <input type="submit" value="addToDo">

</form>
</body>
</html>
