/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuario;

import dao.prueba.LibroJpaController;
import dao.prueba.PrestamoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

/**
 *
 * @author Silvia Tovar
 */
public class PanelUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            sesion.setAttribute("usuario", usuario);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
            PrestamoJpaController prestamoJpaController = new PrestamoJpaController(emf);
            List<Prestamo> listaPrestamos = prestamoJpaController.findPrestamosByUsuario(usuario);
            request.setAttribute("listaPrestamos", listaPrestamos);
            System.out.println(listaPrestamos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("usuario/panelUsuario.jsp").forward(request, response);
    }
}
