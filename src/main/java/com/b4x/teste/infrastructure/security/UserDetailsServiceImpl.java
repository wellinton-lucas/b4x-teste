package com.b4x.teste.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.b4x.teste.domain.model.Usuario;
import com.b4x.teste.infrastructure.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuariosRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = usuariosRepository.findByLogin(email);
        //System.out.println("user: " + user.get().getNome());
        return new UserDetailsImpl(user.get());
    }
}
