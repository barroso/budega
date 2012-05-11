<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<title>Acesso Negado</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/budega.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.css" />
	</head>
	<body>
		<div class="erroAcesso">
			<img src="<c:url value='/imagens/acesso_negado.png'/>" />
			<h3>Acesso Negado!</h3>
			<span>Você não tem acesso a essa p&#225;gina.</span>
			<a class="btn" href="javascript: history.back()">Voltar</a>
		</div>
	</body>
</html>