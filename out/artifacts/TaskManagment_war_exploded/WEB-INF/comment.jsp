<%@ page import="manager.UserManager" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ToDo" %>
<%@ page import="model.Comment" %>
<%@ page import="manager.ToDoManager" %><%--
  Created by IntelliJ IDEA.
  User: Hov
  Date: 26.06.2020
  Time: 16:27
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
    ToDoManager toDoManager = new ToDoManager();
    User user = (User) session.getAttribute("user");
    ToDo toDo = (ToDo) request.getAttribute("toDo");

    List<Comment> comments = (List<Comment>) request.getAttribute("toDoComments");

%>

<h1><%=toDo.getTitle()%>
</h1>
<table border="1">
    <tr>
        <td>title</td>
        <td>deadline</td>
        <td>status</td>
        <td>created date</td>
    </tr>
    <tr>
        <td><%=toDo.getTitle()%>
        </td>
        <td><%=toDo.getDeadline()%>
        </td>
        <td><%=toDo.getStatus()%>
        </td>
        <td><%=toDo.getCreatedDate()%>
        </td>
</table>

<form action="/addComment" method="post">
    <input type="hidden" name="toDoId" value="<%=toDo.getId()%>">
    <input type="text" name="comment" placeholder="comment" size="70px">
    <input type="submit" value="comment">

</form>
<% if (comments != null) { %>
<table border="1">

    <% for (Comment comment : comments) { %>
    <tr>
        <td><%=comment.getId()%>
        </td>
        <td><%= toDoManager.getByID(comment.getTaskId()).getTitle()  %>
        </td>
        <td><%= userManager.getByID(comment.getUserId()).getName()  %>
        </td>
        <td><%= comment.getComment()  %>
        </td>
        <td><%= comment.getDate()  %>
        </td>
        <td><a href="/removeComment?id=<%=comment.getId()%>&taskID=<%= comment.getTaskId() %>">Delete</a></td>


    </tr>
    <% } %>
</table>
<% } %>
<br> <a href="/logout">Logout</a>

</body>
</html>
