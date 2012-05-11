package br.com.barroso.interceptor;

import java.util.Arrays;

import br.com.barroso.annotation.Administrador;
import br.com.barroso.controller.IndexController;
import br.com.barroso.controller.LoginController;
import br.com.barroso.dao.UsuarioSession;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class LoginInterceptor implements Interceptor {

	private Result result;
	private UsuarioSession usuarioSession;
	
	public LoginInterceptor( Result result, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioSession = usuarioSession;
	}
	
	@SuppressWarnings("unchecked")
	public boolean accepts( ResourceMethod method ) {
		return !Arrays.asList(LoginController.class).contains(method.getMethod().getDeclaredAnnotations());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object object) throws InterceptionException {
		boolean isUsuarioLogado = ( usuarioSession.getUsuario() != null );
		boolean isQuerAcessarLogin = method.getResource().getType().equals(LoginController.class);
		
		if( !isUsuarioLogado && !isQuerAcessarLogin )
			result.redirectTo(LoginController.class).login();
		else 
			if( hasAcessoClasse(method) && hasAcessoMetodo(method) )
				stack.next(method, object);
			else result.redirectTo(IndexController.class).negado();
	}
	
	private boolean hasAcessoClasse( ResourceMethod method ) {
		Administrador permissaoDeAcesso = method.getResource().getType().getAnnotation(Administrador.class);
		return hasPermissao(permissaoDeAcesso);
	}

	private boolean hasAcessoMetodo( ResourceMethod method ) {
		Administrador permissaoDeAcesso = method.getMethod().getAnnotation(Administrador.class);
		return hasPermissao(permissaoDeAcesso);
	}

	private boolean hasPermissao( Administrador permissaoDeAcesso ) {
		boolean hasAnnotation = ( permissaoDeAcesso != null ); 
		if( !hasAnnotation ) return true;
		return false;//tmp
	}
}