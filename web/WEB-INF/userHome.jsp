<%@ page import="model.ToDo" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/img1.jpg" style="width:100%">
        <div class="text">Caption Text</div>
    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/img2.jpg" style="width:100%">
        <div class="text">Caption Two</div>
    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/img3.jpg" style="width:100%">
        <div class="text">Caption Three</div>
    </div>

    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<!-- The dots/circles -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>

<%
    User user = (User) session.getAttribute("user");
    List<ToDo> toDos = (List<ToDo>) request.getAttribute("tasks");
    if (toDos == null) {
%>
<h3>ToDo list is empty</h3>
<% } else { %>
Welcome  <%= user.getName()%> <% if (user.getPictureUrl() != null) {%>
<img src="/image?path=<%=user.getPictureUrl()%>" width="50"/> <%}%>

<table border="1">
    <tr>
        <td>Id</td>
        <td>title</td>
        <td>deadline</td>
        <td>status</td>
        <td>userID</td>
        <td>created date</td>
        <th>Update Status</th>
    </tr>

    <% for (ToDo todo : toDos) { %>
    <tr>
        <td><%=todo.getId()%>
        </td>
        <td><a href="/comment?id=<%=todo.getId()%>"><%=todo.getTitle()%>
        </a></td>
        <td><%=todo.getDeadline()%>
        </td>
        <td><%=todo.getStatus()%>
        </td>
        <td><%=todo.getUserId()%>
        </td>
        <td><%=todo.getCreatedDate()%>
        </td>
        <td>
            <form action="/changeToDoStatus" method="post">
                <input type="hidden" name="toDoId" value="<%=todo.getId()%>">
                <select name="status">
                    <option value="TODO">TODO</option>
                    <option value="INPROGRESS">INPROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select><input type="submit" value="OK">
            </form>
        </td>
        <%--        <td><a href="/userDetail?userId=<%=user.getId()%>">User Detail</a></td>--%>

    </tr>

    <% } %>

</table>
<% } %>


<br> <a href="/logout">Logout</a>


</body>
<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>

<script src="/js/slider.js" type="text/javascript"></script>
</html>
