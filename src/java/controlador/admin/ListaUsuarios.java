package controlador.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloUsuarios;
import modelo.entidades.Usuario;

@WebServlet(name = "ListaUsuarios", urlPatterns = {"/ListaUsuarios"})
public class ListaUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = ModeloUsuarios.getUsuarios();
            request.setAttribute("listaUsuarios", usuarios);
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("error", "Error al obtener la lista de usuarios: " + e.getMessage());
        }

        request.getRequestDispatcher("admin/listaUsuarios.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
