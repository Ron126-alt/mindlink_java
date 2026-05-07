package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UsuarioDAO;
import modelo.Usuario;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(correo, contrasena);

        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", u);

            response.sendRedirect("EmocionServlet");

        } else {
            request.setAttribute("error", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}