package jeronimafloriano.com.github.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//define que a autenticação será em memória
                .passwordEncoder(encoderPassword())//define qual será o encoder
                .withUser("jeje")  //usuário que estamos criando
                .password(encoderPassword().encode("12345")) //codificação de senha aleatória que estamos criando
                .roles("USER"); //define a autorização, o perfil do usuário
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    public PasswordEncoder encoderPassword(){
        return new BCryptPasswordEncoder();
    }
}
