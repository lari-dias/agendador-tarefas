package com.javanauta.agendadortarefas.infrastructure.security;

import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioClient usuarioClient;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        throw new UnsupportedOperationException(
                "Use o método carregaDadosUsuario(email, token)."
        );
    }

    public UserDetails carregaDadosUsuario(
            String email,
            String token
    ) {

        UsuarioDTO usuarioDTO =
                usuarioClient.buscaUsuarioPorEmail(
                        email,
                        "Bearer " + token
                );

        return User.builder()
                .username(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .roles("USER")
                .build();
    }
}