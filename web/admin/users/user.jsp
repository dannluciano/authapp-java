<%@include file="../../WEB-INF/jspf/header.jspf" %>

<h2>Usuario</h2>

<ul>
    <li><strong>Id:</strong> ${requestScope.user.id}</li>
    <li><strong>Username:</strong> ${requestScope.user.username}</li>
</ul>


<a href="<c:url value="/admin/users"/>">Voltar</a>
<%@include file="../../WEB-INF/jspf/footer.jspf" %>