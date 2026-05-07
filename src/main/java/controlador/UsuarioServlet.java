package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import modelo.Usuario;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO dao = new UsuarioDAO();
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("lista.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = new Usuario();

        u.setNombre(request.getParameter("nombre"));
        u.setCorreo(request.getParameter("correo"));
        u.setContrasena(request.getParameter("contrasena"));
        u.setTipoUsuario(request.getParameter("tipoUsuario"));

        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            u.setId(Integer.parseInt(id));
            dao.actualizar(u);
        } else {
            dao.insertar(u);
        }

        response.sendRedirect("login.jsp");
    }
}