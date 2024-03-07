package controlador.admin;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Autor;
import modelo.entidades.Genero;
import modelos.ModeloAutores;
import modelos.ModeloGeneros;
import modelos.ModeloLibros;

@WebServlet(name = "CrearLibro", urlPatterns = {"/CrearLibro"})
public class CrearLibro extends HttpServlet {
private static final String vista = "/admin/listaLibros.jsp"; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
         
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String fechaEdicion = request.getParameter("fechaEdicion");
        String imagen = request.getParameter("imagen");
        Long cantidad = Long.parseLong(request.getParameter("cantidad"));

        List<Autor> autores = ModeloAutores.getAutores();
        List<Genero> generos = ModeloGeneros.getGeneros();

      if (isbn != null && titulo != null && fechaEdicion != null && autores != null && generos != null && imagen != null && cantidad != null) {
            String error = ModeloLibros.crearLibro(isbn, titulo, fechaEdicion, autores, generos, imagen, cantidad);

            if (error == null) {
                response.sendRedirect("ListaLibros");
                
            } else {
                request.setAttribute("error", error);
                 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                 request.getRequestDispatcher("admin/listaLibros.jsp").forward(request, response);
            }   
     }
    }
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     getServletContext().getRequestDispatcher(vista).forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    public String getServletInfo() {
        return "Crear Libro";
    }

}