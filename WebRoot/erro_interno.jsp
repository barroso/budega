<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<title>Erro interno</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/budega.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.css" />
	</head>
	<body>
		<div class="erroAcesso">
			<img src="<c:url value='/imagens/erro500.png'/>" />
			<h3>Erro interno!</h3>
			<div style="margin-bottom:10px"><span>Ocorreu um problema não esperado, por favor contate a administração.</span></div>
			<a class="btn" href="javascript: history.back()">Voltar</a>
		</div>
	</body>
</html>