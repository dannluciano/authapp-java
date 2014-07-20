<%@include file="../../WEB-INF/jspf/header.jspf" %>

<div class="row">
    <h2>Usuarios</h2>
    <button id="new-user" class="btn btn-info">Novo Usuario</button>
    <br/>
    <br/>
    <div id="user-form" class="col-md-6 col-md-offset-3">
        <form action="<c:url value="/admin/users"/>" method="post" role="form">
            <div class="form-group">
                <label for="username">Login:</label>
                <input class="form-control" type="text" name="username" placeholder="John" required >
            </div>
            <div class="form-group">
                <label for="password">Senha:</label>
                <input class="form-control" type="password" name="password" required >
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">Enviar</button>
                <button type="reset" class="btn">Limpar</button>
            </div>
        </form>
    </div>
</div>     

<br/>

<p>Numero de usuarios cadastrados: ${requestScope.number_of_users}</p>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>ID: </th>
            <th class="col-lg-8">Username: </th>
            <th>Actions: </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr id="user-${user.id}">
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>
                    <a href="<c:url value="/admin/users?id=${user.id}"/>">Show</a>
                    &nbsp;
                    <a href="<c:url value="/admin/users/edit?id=${user.id}"/>" class="btn btn-warning">Edit</a>
                    &nbsp;
                    <form action="<c:url value="/admin/users/delete"/>" method="post" class="form-inline">
                        <input type="hidden" name="id" value="${user.id}">
                        <button class="btn btn-danger">Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@include file="../../WEB-INF/jspf/footer.jspf" %>