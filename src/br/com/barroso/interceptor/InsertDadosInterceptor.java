package br.com.barroso.interceptor;

import br.com.barroso.dao.UsuarioSession;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class InsertDadosInterceptor implements Interceptor {

	private Result result;
	private UsuarioSession usuarioSession;

	public InsertDadosInterceptor( Result result, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioSession = usuarioSession;
	}

	public boolean accepts(ResourceMethod method) {
		return true;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object object) throws InterceptionException {
		if( usuarioSession.getUsuario() != null ) {
			result.include("usuarioNome", usuarioSession.getUsuario().getNome())
				.include("usuarioId", usuarioSession.getUsuario().getId());
		}
		stack.next(method, object);
	}
}