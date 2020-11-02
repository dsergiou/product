<%@page import="java.util.*" %>

<html>
<body>
<h1 align="center">Product</h1>
<p>
<%
String pr = (String)request.getAttribute("product");
out.print(pr);
%>
</body>
</html>
