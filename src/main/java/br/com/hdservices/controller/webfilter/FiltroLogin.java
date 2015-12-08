package br.com.hdservices.controller.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.hdservices.model.Pessoa;

@WebFilter(urlPatterns = "/pages/*")
public class FiltroLogin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();

		
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null && (!url.contains("login") || !url.contains("erro")) || usuarioLogado.getStatus() == "Inativo") {
			resp.sendRedirect("/HDServices/Login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
