package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Rol;
import modelos.ModeloLogin;
import modelo.entidades.Usuario;
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Usuario usuario = ModeloLogin.validarUsuario(email, password);

            if (usuario != null) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                request.getRequestDispatcher("MenuPrincipal").forward(request, response);
            } else {
                request.setAttribute("error", "E-mail o contraseña incorrectos");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        } catch (IOException | ServletException e) {
            // Registra la excepción en un log o imprime un mensaje de error descriptivo.
            e.printStackTrace();
        }
    }
}
