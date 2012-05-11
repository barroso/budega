<jsp:include page="../index/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="topoConteudo">Usuários</div>
<div id="usuarios">
	<div class="defaultContentWidth">
		<form id="formDelete" method="post" action="<c:url value='/usuarios/?'/>">
			<input type="hidden" name="_method" value="DELETE" />
		</form>
		<div id="page_container">
			<table class="defaultArgsTable">
				<thead>
					<tr>
						<th width=25%>Ações</th>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody class="content">
					<c:forEach items="${usuarios}" var="usuario">
						<tr usuarioid="${usuario.id}">
							<td id="acoes">
								<a href="<c:url value='/usuarios/${usuario.id}/edit'/>">
									<img src="<c:url value='/imagens/icones/editar.png'/>" title="Editar" />
								</a>
								<button type="button" onclick="budega.formDelete(${usuario.id})">
									<img src="<c:url value='/imagens/icones/excluir.png'/>" title="Excluir" />
								</button>
							</td>
							<td>${usuario.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page_navigation pagination" ></div>
		</div>
		<a class='btn primary' href='<c:url value="/usuarios/new"/>' id="cadastrarUsuario">Inserir</a>
	</div>
</div>
<jsp:include page="../index/footer.jsp" />