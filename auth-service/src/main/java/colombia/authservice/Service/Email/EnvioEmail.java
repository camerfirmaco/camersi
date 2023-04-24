package colombia.authservice.Service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EnvioEmail {

    // Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    // Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail(String to, String subject, String content) {

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }

    public void sendEmailPin(String to, String pin) throws AddressException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(to));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("PIN- "+pin+" - Camerfirma Colombia");

        String htmlContent ="<table border='0' cellspacing='0' cellpadding='0'role='presentation' style='width: 100%; max-width: 650px; table-layout: fixed; border: solid thin #d8d8d8; background: #eeeeee;margin:0 auto;'><tbody background=' bgcolor='style='border-spacing:0; border-collapse:collapse; background-color: transparent; width: 100%; max-width: 650px;background-repeat:repeat;'><tr><td style='padding: 0px 0px 0px 0px;'><table cellspacing='0' cellpadding='0' role='presentation'style='width: 100%; table-layout: fixed;'><tbody><tr><td><table cellspacing='0' cellpadding='0' align='left' role='presentation'style='width: 100%; max-width: 650px; box-sizing: content-box; table-layout: fixed;'class='responsiveTable'><tbody><tr><td style='background-image: u;text-align: center; padding: 0px 0px 0px 0px;'><img src='https://drive.google.com/uc?id=1FAe0Zozu9UorlHzCVAVS9vxf2bvEt6B_' alt='BannerCamerfirma'style='width: 100%; height: auto; border: 0; color: #5d5d5d; display: flex;'><p style='font-family: Roboto Condensed, Arial, sans-serif; padding: 0;margin: 0;font-size: 15px;line-height:normal; font-weight: lighter; color: #444444; border-style:none;'><b>El PIN es el siguiente:</b></p></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td><table border='0' cellspacing='0' cellpadding='0' role='presentation' style='width: 100%; max-width: 650px; box-sizing: content-box; table-layout: fixed;'><tbody><tr valign='top'><td style='text-align: center; padding: 0px 0px 0px 0px;'><h1 style='font-size: 60px;font-family:Arial, Helvetica, sans-serif;letter-spacing: 10px;font-weight:700; margin: 10px 0px 20px 0px;'>"+pin+"</h1><p style='display: block; text-align: center; width: 80%; padding: 0px 0px 0px 0px; margin: auto; font-family: Roboto Condensed, Arial, sans-serif; font-size: 21px; line-height:normal; font-weight: lighter; color: #444444; border-style:none;'class='txt'>Por su seguridad, NO compartir por ningun motivo el PIN de restablecimiento o confirmación. <b>De lo contrario corre, el riesgo de perder su usuario</b>.<br><br> Si no solicito el PIN <b>¡OMITA!</b> esté correo. <br><b>Su seguridad es nuestra prioridad</b> somos <b style='color: #243786;'>Camerfirma Colombia S.A.S.</b><br><br></p></td></tr></tbody></table></td></tr><tr><td><table border='0' cellspacing='0' cellpadding='0' role='presentation'style='width: 100%; max-width: 650px; box-sizing: content-box; table-layout: fixed;'><tbody><tr valign='top'><td style='text-align:center; padding: 0px 0px 0px 0px ;margin: auto;'><img src='https://drive.google.com/uc?id=1cvKUx5ehxX4rt4DmlyD9lpXIYexvg-OC' alt='logo' style='width: 50%'></td></tr></tbody></table></td></tr><tr><td style='padding: 0px 0px 0px 0px;'><table border='0' cellspacing='0' cellpadding='0' role='presentation' style='width: 100%; max-width: 650px; background: #eaeaea; box-sizing: content-box; table-layout: fixed;'><tbody><tr valign='top'><td style='text-align: justify; padding: 0px 5% 30px 4%; vertical-align: middle;'><hr style='border: 0px;border-bottom:0.5px solid #dddddd;display: block;max-width:90%;margin-bottom: 30px;'><p class='text-legal' style='padding: 0px; margin: 0px auto; font-family: Roboto, Arial, sans-serif; font-size: 12px; line-height: 18px; font-weight: normal; color: #5d5d5d; border-style:none; position: relative;'><b>Este mensaje</b>, y en su caso, cualquier fichero anexo al mismo, puede contener <b>información CONFIDENCIAL</b>, siendo para uso exclusivo del destinatario, quedando prohibida su divulgación copia o distribución a terceros. Si usted ha recibido este mensaje erróneamente, se ruega lo notifique al remitente y proceda a su borrado. De conformidad con lo establecido en el <b>Reglamento UE 2016/679 de 27 de abril de 2016 General de Protección de Datos (Europa), la Ley 1581 de 2012 y el Decreto 1377 de 2013</b> <b>(Colombia)</b> y, se le informa que la empresa <b>CAMERFIRMA COLOMBIA S.A.S.</b> tratará la información que nos facilita con el exclusivo fin de cumplir con las obligaciones derivadas de la relación comercial o contractual adquirida con usted y que sus datos no podrán ser objeto de otro tratamiento ni de cesión a terceros salvo en los casos en que exista una obligación legal. Usted tiene derecho a obtener confirmación acerca del tratamiento de sus datos personales, y a ejercer sus derechos de acceso, rectificación, supresión, limitación y portabilidad en el tratamiento, dirigiéndose a <b>CAMERFIRMA COLOMBIA S.A.S.</b>, mediante comunicación escrita remitida a la dirección <b>Carrera 13 A No. 28 38 Oficina 202 en la ciudad de Bogotá D.C</b>, o al correo <b style='color:#243786;font-style: italic;'>contacto@colombia.camerfirma.com</b></p></td></tr></tbody></table></td></tr><tr><td style='background:#162E4D;'><table border='0' cellspacing='0' cellpadding='0' role='presentation'style='width: 100%; max-width: 650px; box-sizing: content-box; table-layout: fixed;'><tbody><tr valign='top'><td style='text-align: center; padding: 10px 0px 10px 0px;'><img src='https://camerfirma.com.co/wp-content/uploads/2023/01/Camerfirma-Logo-Negativo-768x308.png' alt='LogoCamerfirmaColombia' style='width: 80px; height: auto; border: 0; margin-left: auto; margin-right: auto;'><img src='https://camerfirma.com.co/wp-content/uploads/2023/01/InfoCert-Logo-Negativo-768x308.png' alt='Logos LogoCamerfirmaColombia' style='width: 80px; height: auto; border: 0; margin-left: auto; margin-right: auto;'><img src='https://camerfirma.com.co/wp-content/uploads/2023/01/Certeurope-Logo-Negativo-768x308.png' alt='Logos LogoCamerfirmaColombia' style='width: 80px; height: auto; border: 0; margin-left: auto; margin-right: auto;'><img src='https://camerfirma.com.co/wp-content/uploads/2023/01/Sixtema-Logo-Negativo-768x308.png'alt='Logos LogoCamerfirmaColombia'style='width: 80px; height: auto; border: 0; margin-left: auto; margin-right: auto;'></td></tr></tbody></table> </td></tr></tbody></table>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}
