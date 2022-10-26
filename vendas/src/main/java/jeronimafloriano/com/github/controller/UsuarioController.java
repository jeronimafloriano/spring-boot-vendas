package jeronimafloriano.com.github.controller;

import jeronimafloriano.com.github.domain.entity.Usuario;
import jeronimafloriano.com.github.dto.CredenciaisDto;
import jeronimafloriano.com.github.dto.TokenDto;
import jeronimafloriano.com.github.exception.SenhaInvalidaException;
import jeronimafloriano.com.github.security.jwt.JwtService;
import jeronimafloriano.com.github.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserServiceImp usuarioServiceImp;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioServiceImp.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDto autenticar(@RequestBody CredenciaisDto credenciais){
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            var usuarioAutenticado = usuarioServiceImp.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
