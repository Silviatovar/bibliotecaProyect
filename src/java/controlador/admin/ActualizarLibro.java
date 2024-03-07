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
import modelo.entidades.Libro;
import modelos.ModeloLibros;

@WebServlet(name = "ActualizarLibro", urlPatterns = {"/ActualizarLibro"})
public class ActualizarLibro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lógica para obtener información del libro a actualizar
            String isbn = request.getParameter("isbn");

            // Llamada al modelo para obtener información del libro
            Libro libro = ModeloLibros.consultarLibro(isbn);

            if (libro != null) {
                // Configurar el libro en el request para que pueda ser utilizado en el formulario JSP
                request.setAttribute("libro", libro);
                // Después de la configuración, redirigir al formulario de actualización
                request.getRequestDispatcher("/admin/ActualizarLibro.jsp").forward(request, response);
            } else {
                // Si no se encuentra el libro, redirigir a una página de error o mostrar un mensaje
                request.setAttribute("error", "No se encontró el libro con ISBN " + isbn);
                request.getRequestDispatcher("admin/Error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("error", "Error al obtener información del libro: " + e.getMessage());
            request.getRequestDispatcher("admin/Error.jsp").forward(request, response);
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
        return "Servlet para actualizar información de un libro";
    }
}
