package controlador.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloLibros;
import modelo.entidades.Libro;

@WebServlet(name = "ListaLibros", urlPatterns = {"/ListaLibros"})
public class ListaLibros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener la lista de libros desde el modelo
            List<Libro> listaLibros = ModeloLibros.getLibros();

            // Enviar la lista de libros al JSP
            request.setAttribute("listaLibros", listaLibros);
        } catch (Exception e) {
            // Manejar la excepción (puedes enviar un mensaje de error o redirigir a una página de error)
            request.setAttribute("error", "Error al obtener la lista de libros: " + e.getMessage());
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
        return "Servlet para manejar la lista de libros";
    }
}
