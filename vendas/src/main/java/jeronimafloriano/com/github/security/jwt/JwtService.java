package jeronimafloriano.com.github.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jeronimafloriano.com.github.VendasApplication;
import jeronimafloriano.com.github.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){

        long minutos = Long.valueOf(expiracao);
        LocalDateTime expiracao = LocalDateTime.now().plusMinutes(minutos);
        Instant instant = expiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();

    }

    private Claims obterClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isBefore(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token){
        return obterClaims(token).getSubject();
    }

    public static void main(String [] args){
        var contexto = SpringApplication.run(VendasApplication.class);
        var bean = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("mario").build();
        String token = bean.gerarToken(usuario);
        System.out.println(token);

        System.out.println("Token está valido?: " + bean.tokenValido(token));
        System.out.println("Usuario: " + bean.obterLoginUsuario(token));
    }
}
