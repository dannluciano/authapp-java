<%@include file="../../WEB-INF/jspf/header.jspf" %>

<a href="<c:url value="/admin/users/new" />">New User</a>

<table>
    <tr>
        <th>ID: </th>
        <th>Username: </th>
        <th>Actions: </th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr id="user-${user.id}">
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td><a href="/admin/users?id=${user.id}">Show</a></td>
            <td><a href="/admin/users/edit?id=${user.id}">Edit</a></td>
            <td><a href="/admin/users/remove?id=${user.id}">Remove</a></td>
        </tr>
    </c:forEach>
</table>

<%@include file="../../WEB-INF/jspf/footer.jspf" %>