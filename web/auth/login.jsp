<%-- 
    Document   : login
    Created on : Jul 15, 2014, 3:18:46 PM
    Author     : dannluciano
--%>

<%@include file="../WEB-INF/jspf/header.jspf" %>


<div class="row">
    <div class=" col-lg-4 col-lg-offset-4">
        <form action="<c:url value="login"/>" method="post" role="form">
            <div class="form-group">
                <label for="username">Username: </label>
                <input class="form-control" type="text" name="username" value="" placeholder="John" required />
            </div>
            <div class="form-group">
                <label for="password">Password: </label>
                <input class="form-control" type="password" name="password" value="" required />
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">Login</button>
            </div>
        </form>
    </div>
</div>
<%@include file="../WEB-INF/jspf/footer.jspf" %>
