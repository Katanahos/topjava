<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal List</title>
    <style>
        .normal {color: green}
        .excess {color: red}
    </style>
</head>
<body>
    <section>
        <h2><a href = "index.html">Home </a></h2>
        <h3>Meal List</h3>
        <hr>
        <p><a href="meals?action=create">Add Meal</a> </p>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Calories</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            <c:forEach items = "${mealList}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr class="${meal.excess ? 'excess' : 'normal'}">
                    <td>
                        <%=TimeUtil.toString(meal.getDateTime())%>
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </c:forEach>
        </table>
    </section>
</body>
</html>
