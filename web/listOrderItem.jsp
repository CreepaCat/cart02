<%--
  Created by IntelliJ IDEA.
  User: ghost
  Date: 2017/8/30
  Time: 19:59
  显示订单项
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="utf-8" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<h1 align="center">购物车</h1>
<table align="center" cellspacing="0" border="1">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>小计</td>
    </tr>
    <c:forEach items="${ois}" var="oi" varStatus="st">
        <tr>
            <td>${oi.product.name}</td>
            <td>${oi.product.price}</td>
            <td>${oi.num}</td>
            <td>${oi.num*oi.product.price}</td>
        </tr>
    </c:forEach>
</table>
