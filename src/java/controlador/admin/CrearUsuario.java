/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloUsuarios;

/**
 *
 * @author Silvia Tovar
 */
@WebServlet(name = "CrearUsuario", urlPatterns = {"/CrearUsuario"})
public class CrearUsuario extends HttpServlet {
 private static final String vista = "/admin/CrearUsuario.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        String domicilio = request.getParameter("domicilio");
       
        
        
        String error = null;

        if (nombre != null && apellidos != null && email != null && contrasena != null && domicilio != null) {
           
            error = ModeloUsuarios.crearUsuario(nombre, apellidos, email, domicilio, contrasena);
                
            if (error == null){
                 response.sendRedirect("ListaUsuarios");
            }else{
                request.setAttribute("error", error);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.sendRedirect("ListaUsuarios");
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Crear Usuario";
    }
}
