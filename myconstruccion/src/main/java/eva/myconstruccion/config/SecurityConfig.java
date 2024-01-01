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
            .antMatchers("/", "/login").permitAll() // Permitir acceso a la p�gina de inicio y login a todos
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login") // Especificar la p�gina de inicio de sesi�n personalizada
            .defaultSuccessUrl("/home", true) // Redirigir a /home despu�s de un inicio de sesi�n exitoso
            .failureUrl("/login?error=true") // Redirigir a /login?error=true en caso de error de inicio de sesi�n
            .permitAll()
            .and()
            .logout()
            .permitAll();
	}
}