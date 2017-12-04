<%@ page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body>
<h4><b><font color="Orange" style="font-family: fantasy;">Collection Created successfully</font> </b></h4>
 <c:forEach var="createCollectionAttributes" items="${createCollectionAttributes}">


<p>${createCollectionAttributes.collectionName}</p>
<p>${createCollectionAttributes.statusCode}</p>
</c:forEach>  
</body>
</html>
