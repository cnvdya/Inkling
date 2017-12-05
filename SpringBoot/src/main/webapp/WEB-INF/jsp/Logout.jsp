<%--
  Created by IntelliJ IDEA.
  User: mudrita
  Date: 12/3/17
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>Inkling Says Good Bye!!!</title>
</head>
<body>

<audio autoplay>
    <source src="<c:out value = "${pollyURL} "/>" type="audio/mpeg">
</audio>

Thank you for using InkLing!!!...
Have a good day


</body>
</html>
