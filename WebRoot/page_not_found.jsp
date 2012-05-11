<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<title>P&#225;gina não encontrada</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/budega.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.css" />
	</head>
	<body>
		<div class="erroAcesso">
			<img src="<c:url value='/imagens/erro404.jpg'/>" />
			<h3>P&#225;gina n&#227;o encontrada!</h3>
			<a class="btn" href="javascript: history.back()">Voltar</a>
		</div>
	</body>
</html>