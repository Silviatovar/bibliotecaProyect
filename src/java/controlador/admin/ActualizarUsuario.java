package controlador.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Usuario;
import modelos.ModeloUsuarios;

@WebServlet(name = "ActualizarUsuario", urlPatterns = {"/ActualizarUsuario"})
public class ActualizarUsuario extends HttpServlet {
    private static final String vista = "/admin/ActualizarUsuario.jsp";
    private static final String VISTA_LISTA = "ListaUsuarios";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario de actualización
        String idUsuarioParam = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        String domicilio = request.getParameter("domicilio");

        String error = null;

        if (idUsuarioParam != null && nombre != null && apellidos != null && email != null && contrasena != null && domicilio != null) {
           
                Long idUsuario = Long.parseLong(idUsuarioParam);
                // Obtener el usuario existente
                Usuario usuario = ModeloUsuarios.consultarUsuario(idUsuario);

                if (usuario != null) {
                    // Actualizar los datos del usuario
                    usuario.setNombre(nombre);
                    usuario.setApellidos(apellidos);
                    usuario.setEmail(email);
                    if (contrasena != null && !contrasena.isEmpty()) {
                        usuario.setContraseña(contrasena);
                    }
                    usuario.setDomicilio(domicilio);

                    // Llamar al método para actualizar el usuario en el modelo
                    error = ModeloUsuarios.actualizarUsuario(usuario, nombre, apellidos, email, error, domicilio);

                    if (error == null) {
                        request.getRequestDispatcher(vista).forward(request, response);
                    }
                } else {
                    error = "El usuario con ID " + idUsuario + " no existe.";
                   
                }
           
        } else {
            error = "Se requieren todos los campos para actualizar el usuario.";
        }

    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.getRequestDispatcher(vista).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Actualizar Usuario";
    }
}
