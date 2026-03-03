package io.github.arthur32p.libraryapi.security;

import io.github.arthur32p.libraryapi.model.Usuario;
import io.github.arthur32p.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senhaDigitada = authentication.getCredentials().toString();

        Usuario usuarioEcontrado = usuarioService.obterPorLogin(login);

        if(usuarioEcontrado == null){
            throw getUsernameNotFoundException();
        }

        String senhaCriptografada = usuarioEcontrado.getSenha();

        boolean senhas = encoder.matches(senhaDigitada, senhaCriptografada);

        if(senhas){
            return new CustomAuthentication(usuarioEcontrado);
        }

        throw getUsernameNotFoundException();
    }

    private UsernameNotFoundException getUsernameNotFoundException() {
        return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
