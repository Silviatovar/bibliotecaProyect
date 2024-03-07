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
import modelos.ModeloLibros;

@WebServlet(name = "EliminarLibro", urlPatterns = {"/EliminarLibro"})
public class EliminarLibro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/ListaLibros.jsp";

        // Obtener parámetro del formulario
        String isbnParam = request.getParameter("isbn");

        try {
            // Lógica para eliminar el libro
            String error = ModeloLibros.eliminarLibro(isbnParam);

            if (error == null) {
                response.sendRedirect(request.getContextPath() + "/ListaLibros");
                return;
            } else {
                request.setAttribute("error", error);
            }
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, redirigir a una página de error.
            e.printStackTrace();  // Imprimir la traza de la excepción para depuración.
            request.setAttribute("error", "Error al eliminar el libro.");
        }

        // Si hay errores o falta algún parámetro, reenvía a la vista con los errores.
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
        return "Eliminar Libro";
    }
}
