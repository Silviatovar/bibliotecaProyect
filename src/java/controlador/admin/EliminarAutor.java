package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Autor;
import modelos.ModeloAutores;

@WebServlet(name = "EliminarAutor", urlPatterns = {"/EliminarAutor"})
public class EliminarAutor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            long idAutor = Long.parseLong(request.getParameter("idAutor"));

            // Consultar autor existente
            Autor autor = ModeloAutores.consultarAutor(idAutor);

            // Eliminar autor
            String error = ModeloAutores.eliminarAutor(autor);

            if (error == null) {
                response.sendRedirect("ListadoAutor");
             
            } else {
                request.setAttribute("error", error);
                  response.sendRedirect("ListaAutor");
                
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al convertir el ID del autor.");
        } catch (IOException e) {
            request.setAttribute("error", "Error al eliminar el autor: " + e.getMessage());
        } 
     
        
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
        return "Eliminar Autor";
    }
}
