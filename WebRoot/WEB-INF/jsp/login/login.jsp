<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath">${pageContext.request.contextPath}</c:set>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Budega</title>
		<script src="${contextPath}/js/jquery/jquery-1.7.0.min.js"></script>
		<script src="${contextPath}/js/jquery/jquery.validate.min.js"></script>		
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		<script>
			$(function() {
				$("#formLogin").validate({
					rules : {
						"nome" : { required : true },
						"senha" : { required : true }
					},
					messages : {
						"nome" : { required : "Digite o nome." },
						"senha" : { required : "Digite a senha." }
					}
				});
				$("#logar").click(function() {
					if( $("#formLogin").valid() ) $("#formLogin").submit();
				});
				$("#formLogin").find("input").keydown(function(event) {
					if( event.keyCode === 13 ) $("#logar").click(); 
				});
			});
		</script>
	</head>
	<body>
		<div id="geral">
			<div id="login">
				<div id="budega"></div>
				<div id="logoLogin">
				    <form method="post" id="formLogin" action="/budega/login">
				    	<div id="erro">${erro}</div>
				    	<div class="clearfix">
					    	<label>Login:</label>
					    	<div class="input"><input type="text" maxlength="32" class="span2-5" name="nome" id="usuarioLogin"></div>
				    	</div>
				    	<div class="clearfix">
				    		<label>Senha:</label>
				    		<div class="input"><input type="password" maxlength="16" class="span2-5" name="senha" id="uruarioPassword"></div>
				    	</div>
				    	<button type="button" id="logar" class="btn small">Entrar</button>
				    </form>
                </div>
			</div>
		</div>
	</body>
</html>