package controlador.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloAutores;
import modelo.entidades.Autor;

@WebServlet(name = "ListadoAutor", urlPatterns = {"/ListadoAutor"})
public class ListadoAutor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener la lista de autores
            List<Autor> Autores = ModeloAutores.getAutores();
            request.setAttribute("listaAutores", Autores);
        } catch (Exception e) {
           
            request.setAttribute("error", "Error al obtener la lista de autores: " + e.getMessage());
        }

        request.getRequestDispatcher("admin/listaAutores.jsp").forward(request, response);
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
        return "Listar Autores";
    }
}
