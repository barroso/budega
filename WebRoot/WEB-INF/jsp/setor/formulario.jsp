<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../index/header.jsp" />
<div id="topoConteudo">Novo setor</div>
<div id="formulario">
  	<form id="formNewSetor" method="post" action="<c:url value='/setores'/>">
  		<c:if test="${setor.id != null}">
  			<input type="hidden" name="setor.id" value="${setor.id}" />
  			<input type='hidden' name='_method' value='PUT'/>
  		</c:if>
		<input type="text" class="required" label="Setor" id="nome" name="setor.nome" maxlength="40" value="${setor.nome}" />
		<button class="btn primary">Salvar</button>
		<a class='btn small' href="<c:url value='/setores'/>">Voltar</a>
   </form>
</div>
<script type="text/javascript">
	$(function() {
		$("#formNewSetor").wrapLabel();
		budega.validForm("#formNewSetor");
	});
</script>
<jsp:include page="../index/footer.jsp" />
