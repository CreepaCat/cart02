<%--
  Created by IntelliJ IDEA.
  User: ghost
  Date: 2017/8/26
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="utf-8" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
    <table align="center" border="1" cellpadding="0">
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>数量</td>
        </tr>
        <c:forEach items="${products}" var = "product" varStatus ="st">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <form action = "addOrderItem" method = "post">
                        数量
                        <input type="text" value="1" name="num">
                        <input type="hidden" name="pid" value="${product.id}">
                        <input type="submit" value="购买">
                    </form>
                </td>

            </tr>
        </c:forEach>
        <tr>

        </tr>
    </table>

</body>
</html>
