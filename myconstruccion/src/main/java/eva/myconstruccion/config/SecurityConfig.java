package eva.myconstruccion.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Override
	protected void configure(HttpSecurity http) throws Exception {
    	http
        	.authorizeRequests()
            .antMatchers("/", "/login").permitAll() // Permitir acceso a la página de inicio y login a todos
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login") // Especificar la página de inicio de sesión personalizada
            .defaultSuccessUrl("/home", true) // Redirigir a /home después de un inicio de sesión exitoso
            .failureUrl("/login?error=true") // Redirigir a /login?error=true en caso de error de inicio de sesión
            .permitAll()
            .and()
            .logout()
            .permitAll();
	}
}