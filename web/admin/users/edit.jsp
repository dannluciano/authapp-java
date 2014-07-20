<%@include file="../../WEB-INF/jspf/header.jspf" %>

<div class="row">
    <div id="user-form-edit" class="col-md-6 col-md-offset-2">
        <h2>Editar Usuario</h2>
        <form action="<c:url value="/admin/users/edit"/>" method="post" role="form">
            <input type="hidden" name="id" value="${requestScope.user.id}">
            <div class="form-group">
                <label for="UserModelname">Login:</label>
                <input class="form-control" type="text" name="username" placeholder="John" value="${requestScope.user.username}" required >
            </div>
            <div class="form-group">
                <label for="password">Senha:</label>
                <input class="form-control" type="password" name="password" required >
            </div>
            <div class="form-group">
                <button type="submit" class="btn">Enviar</button>
            </div>
        </form>
    </div>
</div>


<a href="<c:url value="/admin/users"/>">Voltar</a>
<%@include file="../../WEB-INF/jspf/footer.jspf" %>