<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ToDo" %>
<%@ page import="model.UserStatus" %>
<%@ page import="manager.UserManager" %><%--
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
    UserManager userManager = new UserManager();
    User user = (User) session.getAttribute("user");
    List<ToDo> toDos = (List<ToDo>) request.getAttribute("tasks");
    List<User> users = (List<User>) request.getAttribute("users");
    if (user != null) {

%>

Welcome <%= user.getName() %> <%= user.getSurname() %> <br> <a href="/logout">Logout</a>
<a href="/">Index</a><br><br>
<% } %>
<div style="width: 800px;">
    <div style="width: 50%;float: left">
        Add User: <br>
        <form action="/UserRegisterFromAdmin" method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="please input name" required/><br>
            <input type="text" name="surname" placeholder="please input surname" required/><br>
            <input type="text" name="email" placeholder="please input email" required/><br>
            <input type="password" name="password" placeholder="please input password" required/><br>
            <input type="file" name="image"/><br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select><br>
            <input type="submit" value="Register">

        </form>
    </div>
</div>
<br>
<br>
<br>
<br>
<br>
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
<br>
<br>
<br>

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
<br>
<br>
<br>
<div>
    All ToDos: <br>
    <table border="1">
        <tr>
            <th>title</th>
            <th>deadline</th>
            <th>status</th>
            <th>user</th>
            <th>created_date</th>
        </tr>
        <%
            for (ToDo toDo : toDos) { %>
        <tr>
            <td><a href="/comment?id=<%=toDo.getId()%>"><%=toDo.getTitle()%>
            </td>
            <td><%=toDo.getDeadline()%>
            </td>
            <td><%=toDo.getStatus().name()%>
            </td>
            <%User user1 = userManager.getByID(toDo.getUserId()); %>
            <td><%= user1.getName()%>
            </td>
            <td><%=toDo.getCreatedDate()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
