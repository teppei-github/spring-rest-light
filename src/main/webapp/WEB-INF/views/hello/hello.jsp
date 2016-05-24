<%@ page language="java" trimDirectiveWhitespaces="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>Hello World</title>
</head>
<body>
	<h1><c:out value="${message}"/></h1>
	<fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd HH:mm:ss" />
</body>
</html>