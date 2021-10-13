package br.com.masters3.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.masters3.beans.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String pagina = "";			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			Usuario usuario = this.recuperarUsuario(email,senha);
			if(usuario == null) {
				request.setAttribute("ErroMSG", "Usuário ou senha inválidos !!!");
				pagina = "/erro.jsp";
			}
			else {
				request.getSession().setAttribute("Usuario", usuario);
				pagina = "/dados_usuario.jsp";
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}
		catch (Exception ex) {
			System.out.println("ERRO on LOGINSERVLET "+ ex.getMessage());
		}
	}
	
	public Usuario recuperarUsuario(String email, String senha) {
		if(email.equals("fabio.versolatto@gmail.com") &&
				senha.equals("123456")) {
			Usuario objUsuario = new Usuario();
			objUsuario.setEmail(email);
			objUsuario.setSenha(senha);
			objUsuario.setCidade("São Paulo");
			objUsuario.setNome("Fabio Rossi Versolatto");
			objUsuario.setGenero("M");
			objUsuario.setUltimoAcesso(null);
			objUsuario.setNascimento(null);
			objUsuario.setId(1);
			objUsuario.setFoto("./assets/images/user.png");
			objUsuario.setNivel("Administrador");
			
			return objUsuario;
		}
		else {
			return null;
		}
	}
	
}
