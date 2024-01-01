package eva.myconstruccion.controller;


import eva.myconstruccion.model.User;
import eva.myconstruccion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 0;
    private boolean accountLocked = false;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (accountLocked) {
            model.addAttribute("error", "Account locked. Please contact support.");
            return "login";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
    	User user = userRepository.findByUsername(username);
    	// Lógica para verificar las credenciales del usuario
        if (user != null && user.getPassword().equals(password)) {
            // Restablecer los intentos después de un inicio de sesión exitoso
            loginAttempts = 0;
            return "redirect:/home"; // Redirigir al home después del inicio de sesión
        } else {
            // Incrementar los intentos si las credenciales son incorrectas
            loginAttempts++;
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                accountLocked = true;
                return "redirect:/login?error=locked";
            }
            model.addAttribute("error", "Invalid username or password. Attempts left: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
            return "login";
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Lógica para verificar las credenciales (puede ser una consulta a la base de datos, etc.)
        // Aquí usaremos credenciales fijas para demostración
        return "user".equals(username) && "password".equals(password);
    }
}