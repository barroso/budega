<jsp:include page="../index/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="setors">
	<div id="topoConteudo">Setores</div>
	<div class="defaultContentWidth">
		<form id="formDelete" method="post" action="<c:url value='/setores/?'/>">
			<input type="hidden" name="_method" value="DELETE" />
		</form>
		<div id="page_container">
			<table class="defaultArgsTable">
				<thead>
					<tr>
						<th width=25%>Ações</th>
						<th>Setor</th>
					</tr>
				</thead>
				<tbody class="content">
					<c:forEach items="${setores}" var="setor">
						<tr setorid="${setor.id}">
							<td id="acoes">
								<a href="<c:url value='/setores/${setor.id}/edit'/>">
									<img src="<c:url value='/imagens/icones/editar.png'/>" title="Editar" />
								</a>
								<button type="button" onclick="budega.formDelete(${setor.id})">
									<img src="<c:url value='/imagens/icones/excluir.png'/>" title="Excluir" />
								</button>
							</td>
							<td>${setor.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page_navigation pagination"></div>
		</div>
		<a class='btn primary' href="<c:url value='/setores/new'/>" id="cadastrarSetor">Inserir</a>
	</div>
</div>
<jsp:include page="../index/footer.jsp" />
