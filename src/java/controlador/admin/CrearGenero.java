// CrearGeneroServlet.java
package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloGeneros;

@WebServlet(name = "CrearGenero", urlPatterns = {"/CrearGenero"})
public class CrearGenero extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/CrearGenero.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del género no puede estar vacío.");
            }

            String error = ModeloGeneros.crearGenero(nombre);

            if (error == null) {
                response.sendRedirect("ListaGeneros");
              
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.sendRedirect("ListaGeneros");

            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Crear Género";
    }
}
