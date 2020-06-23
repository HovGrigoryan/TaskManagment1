<%@ page import="model.ToDo" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--

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
    if (toDos == null) {
%>



<h3>ToDo list is empty</h3>
<% } else { %>
Welcome <%= user.getName() %> <%= user.getSurname() %>
<table border="1">
    <tr>
        <td>Id</td>
        <td>title</td>
        <td>deadline</td>
        <td>status</td>
        <td>userID</td>
        <td>created date</td>
    </tr>

    <% for (ToDo todo : toDos) { %>
    <tr>
        <td><%=todo.getId()%>
        </td>
        <td><%=todo.getTitle()%>
        </td>
        <td><%=todo.getDeadline()%>
        </td>
        <td><%=todo.getStatus()%>
        </td>
        <td><%=todo.getUserId()%>
        </td>
        <td><%=todo.getCreatedDate()%>
        </td>
        <%--        <td><a href="/userDetail?userId=<%=user.getId()%>">User Detail</a></td>--%>

    </tr>

    <% } %>

</table>
<% } %>





<br> <a href="/logout">Logout</a>


</body>
</html>
