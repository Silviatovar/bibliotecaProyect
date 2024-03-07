/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Usuario;
import modelos.ModeloUsuarios;

/**
 *
 * @author Silvia Tovar
 */

@WebServlet(name = "EliminarUsuario", urlPatterns = {"/EliminarUsuario"})
public class EliminarUsuario extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String idUsuarioParam = request.getParameter("idUsuario");

       
         
            Long idUsuario = Long.valueOf(idUsuarioParam);

            // Lógica para eliminar el usuario
            String error = ModeloUsuarios.eliminarUsuario(idUsuario);

            if (error == null) {
                response.sendRedirect("ListaUsuarios");  
               
            } else {
                request.setAttribute("error", error);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
        return "Eliminar Usuario";
    }
}
