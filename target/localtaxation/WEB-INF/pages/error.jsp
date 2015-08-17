<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	String url=request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%= url %>/resources/css/temp.css" rel="stylesheet" type="text/css">
</head>
<body>
	<p>${ exception.message }</p>

    <%--bean validation--%>
    <spring:hasBindErrors name="tempBean">
        <c:if test="${errors.fieldErrorCount > 0}">
            字段错误：<br/>
            <c:forEach items="${errors.fieldErrors}" var="error">
                <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>
                ${error.field}------${message}<br/>
            </c:forEach>
        </c:if>

        <c:if test="${errors.globalErrorCount > 0}">
            全局错误：<br/>
            <c:forEach items="${errors.globalErrors}" var="error">
                <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>
                <c:if test="${not empty message}">
                    ${message}<br/>
                </c:if>
            </c:forEach>d
        </c:if>
    </spring:hasBindErrors>

</body>
</html>