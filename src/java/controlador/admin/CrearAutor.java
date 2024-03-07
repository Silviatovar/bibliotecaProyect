
package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloAutores;

@WebServlet(name = "CrearAutor", urlPatterns = {"/CrearAutor"})
public class CrearAutor extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.getRequestDispatcher("admin/crearAutor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            // Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");

            // Lógica para crear un nuevo autor
            String error = ModeloAutores.crearAutor(nombre, apellidos);

            if (error == null) {
               
                response.sendRedirect("ListadoAutor");
               
            } else {
              
                request.setAttribute("error", error);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    

    @Override
    public String getServletInfo() {
        return "Crear Autor";
    }
}
