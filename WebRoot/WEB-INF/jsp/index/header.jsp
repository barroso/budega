<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<title>Budega</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/budega.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/jquery/jquery.ui.all.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/menu.css" />
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery-ui-1.8.13.custom.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.ui.datepicker-pt-BR.js"></script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.pajinate.js"></script>
		<script src="${contextPath}/js/jquery/sliderAccess.js"></script>
		<script src="${contextPath}/js/jquery/jquery.ui.swappable.js"></script>
		<script src="${contextPath}/js/jquery/jquery.maskedinput-1.1.4.pack.js"/></script>
		<script src="${contextPath}/js/budega.js"></script>
		<script src="${contextPath}/js/jquery.wrap_label.js"></script>
		
	</head>
	<body>
		<div id="geral">
			<div id="cabecalho">
				<div id="saudacao"><span>Bem vindo, ${usuarioNome}</span>
					<a href="<c:url value='/logout'/>">[sair]</a>
				</div>
			</div>
			<div id="menu">
				<div class="accordion">
					<h6>Administração</h6>
					<div>
						<div>
							<p><a href="<c:url value='/setores'/>">Setores</a></p>
							<p><a href="<c:url value='/usuarios'/>">Usuários</a></p>
						</div>
					</div>
				</div>
			</div>
			<div id="conteudo">