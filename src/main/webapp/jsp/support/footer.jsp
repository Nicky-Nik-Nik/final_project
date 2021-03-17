<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 3/15/2021
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="footer.title"/></title>
    <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<footer class="footer">
    <div class="footer__addr">
        <h1 class="footer__logo"><fmt:message key="footer.thanks"/></h1>

        <h2><fmt:message key="footer.contact"/></h2>

        <address>
            <fmt:message key="footer.address"/><br>

            <a class="footer__btn" href="mailto:example@gmail.com"><fmt:message key="footer.emailus"/></a>
        </address>
    </div>
    <div class="legal">
        <p>&copy; 2021 <fmt:message key="footer.rights"/></p>
    </div>
</footer>
</body>
</html>
