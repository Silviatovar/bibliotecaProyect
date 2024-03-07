package controlador.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloGeneros;
import modelo.entidades.Genero;

@WebServlet(name = "ListaGeneros", urlPatterns = {"/admin/ListaGeneros"})
public class ListaGeneros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve a list of genres from the model
            List<Genero> generos = ModeloGeneros.getGeneros();
            
            // Put the list of genres in the request attribute for the JSP
            request.setAttribute("listaGeneros", generos);
        } catch (Exception e) {
            // Error handling
            request.setAttribute("error", "Error al obtener la lista de géneros: " + e.getMessage());
        }

        // Forward to the JSP view
        
        getServletContext().getRequestDispatcher("/admin/listaGeneros.jsp").forward(request, response);
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
