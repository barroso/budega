<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../index/header.jsp" />
<div id="topoConteudo">Usuário</div>
<div id="formulario">
	<form id="formNewUsuario" method="post" action="<c:url value='/usuarios'/>">
		<c:if test="${usuario.id != null}">
			<input type='hidden' name='_method' value='PUT'/>
 			<input type="hidden" name="usuario.id" value="${usuario.id}" />
		</c:if>
			<select id="setorId" label="Setor" name="">
				<option value="">Escolha um setor...</option>
				<c:forEach items="${setores}" var="setor">
					<option value="${setor.id}">${setor.nome}</option>
				</c:forEach>
			</select>
		
	  		<input type="text" class="required" label="Nome" id="nome" name="usuario.nome" maxlength="255" value="${usuario.nome}" />
			<c:if test="${usuario.id == null}">
	  		<div class="clearfix">
	  			<label>Login: </label>
	  			<div class="input">
					<input type="text" id="login" class="required" maxlength="32" name="usuario.login" value="${usuario.login}" />
	  			</div>
	  		</div>
			<input type="password" class="required" id="senha" label="Senha" maxlength="16" name="usuario.senha" value="${usuario.senha}" />
		</c:if>
	<button class="btn primary">Salvar</button>
   </form>
</div>
<script>
	$(function() {
		$("#formNewUsuario").wrapLabel();
		$("#formNewUsuario").submit(function() { 
			return true;
		});
		budega.validForm("#formNewUsuario");
		if( !("${usuario.id}") ) {
			$("#formNewUsuario")[0].reset();
		}
	});
</script>
<jsp:include page="../index/footer.jsp" />