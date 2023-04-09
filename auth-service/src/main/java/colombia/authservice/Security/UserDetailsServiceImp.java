package colombia.authservice.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import colombia.authservice.Model.Cargo.EntityRole;
import colombia.authservice.Model.Usuario.EntityUsuario;
import colombia.authservice.Model.Usuario.InterfaceUsuario;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private InterfaceUsuario interfaceUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EntityUsuario> user = interfaceUsuario.findByEmail(username);
        EntityUsuario usuario = user.get();
        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getHabilitado(), usuario.getActivo(),
                usuario.getActivo(), usuario.getBloqueado(), getAuthorities(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<EntityRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (EntityRole role : roles) {
            String name = role.getRole().toString().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

}
