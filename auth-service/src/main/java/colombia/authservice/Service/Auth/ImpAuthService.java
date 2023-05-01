package colombia.authservice.Service.Auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import colombia.authservice.Mapping.Public.DtoJwt;
import colombia.authservice.Mapping.Public.DtoLogin;
import colombia.authservice.Mapping.Public.DtoNewPassword;
import colombia.authservice.Mapping.Public.DtoPassword;
import colombia.authservice.Mapping.Public.RequestDto;
import colombia.authservice.Mapping.Public.TokenDto;
import colombia.authservice.Model.Usuario.EntityUsuario;
import colombia.authservice.Model.Usuario.InterfaceUsuario;
import colombia.authservice.Security.jwt.JwtProvider;
import colombia.authservice.Service.Email.EnvioEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@Service
public class ImpAuthService implements AuthService {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private InterfaceUsuario interfaceUsuario;

    @Autowired
    private EnvioEmail EnvioEmail;

    @Autowired
    private JwtProvider jwtProvider;

    private PasswordEncoder password = new BCryptPasswordEncoder();

    //////////////////////////////////////////////////////////////////////////////////////////////
    // <---CONTRASEÑA---->//
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String[] olvidoPasword(DtoPassword dto) throws AddressException, MessagingException {
        Integer PIN = getNumberPin();
        String correo = dto.getEmail();
        String identificacion = dto.getIdentificacion();
        if (!(correo == null)) {
            EntityUsuario user = interfaceUsuario.findByEmail(dto.getEmail()).get();
            String email = user.getEmail();
            EnvioEmail.sendEmailPin(email, PIN.toString());
            String respuesta = email.substring(0, email.indexOf("@") - 3).replaceAll("[a-z]", "*")
                    + email.substring(email.indexOf("@") - 3, email.length());
            String data[] = { password.encode(PIN.toString()), respuesta, new Date().toString(), user.getId() };
            return data;
        } else if (!(identificacion == null)) {
            EntityUsuario user = interfaceUsuario.findByIdentificacion(dto.getIdentificacion()).get();
            String email = user.getEmail();
            EnvioEmail.sendEmailPin(email, PIN.toString());
            String respuesta = email.substring(0, email.indexOf("@") - 3).replaceAll("[a-z]", "*")
                    + email.substring(email.indexOf("@") - 3, email.length());
            String data[] = { password.encode(PIN.toString()), respuesta, new Date().toString(), user.getId() };
            return data;
        }
        return null;
    }

    private Integer getNumberPin() {
        double fiveDigits = 100000 + Math.random() * 900000;
        Integer PIN = (int) fiveDigits;
        return PIN;
    }

    @Override
    public Boolean newPasword(DtoNewPassword dto) {
        if (password.matches(dto.getPin(), dto.getKey())) {
            EntityUsuario user = interfaceUsuario.findById(dto.getId()).get();
            user.setPassword(password.encode(dto.getPassword()));
            interfaceUsuario.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String valideMail(String email) {
        Integer PIN = getNumberPin();
        try {
            EnvioEmail.sendEmailPin(email, PIN.toString());
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return password.encode(PIN.toString());
    }

    @Override
    public boolean valideMailKey(String[] valide) {
        if (!(valide[0] == null) && !(valide[1] == null)) {
            if (password.matches(valide[0], valide[1])) {
                return true;
            }
            return false;
        }
        return false;
    }

    public TokenDto validateToken(String token, RequestDto dto) {
        if (!jwtProvider.validateToken(token, dto)) // FALTA RUTA!
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if (!interfaceUsuario.findByEmail(username).isPresent())
            return null;
        return new TokenDto(token);
    }

    public DtoJwt login(DtoLogin login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(), login.getPassword());
        org.springframework.security.core.Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        Date vence = jwtProvider.getExpirationFromToken(jwt);
        String id = jwtProvider.getUserIdFromToken(jwt);
        Object role = jwtProvider.getRolesFromToken(jwt);

        DtoJwt jwtDto = new DtoJwt(jwt, id, vence, role);

        return jwtDto;
    }
}