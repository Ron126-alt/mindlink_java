package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.EmocionDAO;
import modelo.Emocion;
import modelo.Usuario;

@WebServlet("/EmocionServlet")
public class EmocionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        EmocionDAO dao = new EmocionDAO();
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("EmocionServlet");
            return;
        }

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Emocion emo = dao.obtenerPorId(id);

            if (emo != null) {
                request.setAttribute("emocion", emo);
            }
        }

        request.setAttribute("lista", dao.listarPorUsuario(u.getId()));
        request.getRequestDispatcher("emociones.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String tipo = request.getParameter("tipo");
        String descripcion = request.getParameter("descripcion");
        String id = request.getParameter("id");

        if (descripcion == null || descripcion.trim().isEmpty()) {
            response.sendRedirect("EmocionServlet");
            return;
        }

        Emocion e = new Emocion();
        e.setTipo(tipo);
        e.setDescripcion(descripcion.trim());
        e.setIdUsuario(u.getId());

        EmocionDAO dao = new EmocionDAO();

        if (id != null && !id.isEmpty()) {
            e.setId(Integer.parseInt(id));
            dao.actualizar(e);
        } else {
            dao.insertar(e);
        }

        response.sendRedirect("EmocionServlet");
    }
}