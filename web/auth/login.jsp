<%-- 
    Document   : login
    Created on : Jul 15, 2014, 3:18:46 PM
    Author     : dannluciano
--%>

<%@include file="../WEB-INF/jspf/header.jspf" %>

<form action="login" method="POST">
    <div>
        <label for="username">Username: </label>
        <input type="text" name="username" value="" placeholder="John" required />
    </div>
    <div>
        <label for="password">Password: </label>
        <input type="password" name="password" value="" required />
    </div>
    <div>
        <button type="submit">Login!</button>
    </div>
</form>

<%@include file="../WEB-INF/jspf/footer.jspf" %>
