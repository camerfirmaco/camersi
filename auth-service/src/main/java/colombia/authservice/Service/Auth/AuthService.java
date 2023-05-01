package colombia.authservice.Service.Auth;

import colombia.authservice.Mapping.Public.DtoNewPassword;
import colombia.authservice.Mapping.Public.DtoPassword;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public interface AuthService {
    String[] olvidoPasword(DtoPassword dto) throws AddressException, MessagingException; //RECUPERAR CONTRASEÑA

    Boolean newPasword(DtoNewPassword dto); //NUEVA CONTRASEÑA

    String valideMail(String email); //VALIDAR EMAIL

    boolean valideMailKey(String[] valide); //VALIDAR KEY Y PIN
}
