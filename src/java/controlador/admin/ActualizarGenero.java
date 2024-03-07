package controlador.admin;

import java.io.IOException;
import static java.lang.Long.parseLong;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Genero;
import modelos.ModeloGeneros;

@WebServlet(name = "ActualizarGenero", urlPatterns = {"/ActualizarGenero"})
public class ActualizarGenero extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vista = "/admin/ActualizarGenero.jsp";
        String id = request.getParameter("idGenero");
        Genero g = ModeloGeneros.consultarGenero(Long.parseLong(id));
        request.setAttribute("genero", g);
        
        if(request.getParameter("idGenero")!= null && request.getParameter("nombre")!= null){
            String nombre = request.getParameter("nombre");
            ModeloGeneros.actualizarGenero(id , nombre);
            vista="/ListaGeneros";
        }
        
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
        return "Actualizar Género";
    }
}
