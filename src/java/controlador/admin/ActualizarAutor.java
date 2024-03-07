package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Autor;
import modelos.ModeloAutores;

@WebServlet(name = "ActualizarAutor", urlPatterns = {"/ActualizarAutor"})
public class ActualizarAutor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            long idAutor = Long.parseLong(request.getParameter("idAutor"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");

            // Consultar autor existente
            Autor autor = ModeloAutores.consultarAutor(idAutor);

            // Actualizar autor
            String error = ModeloAutores.actualizarAutor(autor, nombre, apellidos);

            if (error == null) {
                response.sendRedirect("ListaAutores");
                return;
            } else {
                request.setAttribute("error", error);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al convertir el ID del autor.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar el autor: " + e.getMessage());
        } finally {
            // Cierre de recursos
            request.getRequestDispatcher("admin/ActualizarAutor.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aquí puedes realizar lógica adicional antes de mostrar el formulario
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Actualizar Autor";
    }
}
