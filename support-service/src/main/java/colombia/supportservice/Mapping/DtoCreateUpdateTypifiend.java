package colombia.supportservice.Mapping;

import java.util.Date;
import java.sql.Time;

import colombia.supportservice.Utils.EnumCategoria;
import colombia.supportservice.Utils.EnumCertificado;
import colombia.supportservice.Utils.EnumSoporte;
import colombia.supportservice.Utils.EnumSubSoporte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoCreateUpdateTypifiend {
    // ID
    private Long id;
    // FECHA DE EMISIÓN DEL CERTIFICADO
    private Date emision;
    // DOCUMENTO DEL TITULAR DE LA FIRMA
    private String documento;
    // NOMBRE DEL TITULAR DE LA FIRMA
    private String nombre;
    // TELEFONO DEL TITULAR DE LA FIRMA
    private String telefono;
    // COMPAÑIA DEL TITULAR DE LA FIRMA
    private String company;
    // EMAIL DEL TITULAR DE LA FIRMA
    private String email;
    // TIPO DE CERTIFICADO EMITIDO
    private EnumCertificado cetificado;
    // TIPO DE SOPORTE REALIZADO
    private EnumSoporte soporte;
    // SUB TIPO DE SOPORTE
    private EnumSubSoporte subSoporte;
    // FECHA DEL SOPORTE
    private Date fecha;
    // HORA DEL SOPORTE
    private Time hora;
    // OBSERVACIONES DEL SOPORTER
    private String observacion;
    // ESTADO DEL SOPORTE
    private Boolean estado;
    // TIEMPO DEL SOPORTE
    private Integer tiempo;
    // CATEGORIA DEL SOPORTE
    private EnumCategoria categoria;
    // AGENTE QUIEN REALIZA EL SOPORTE
    private String agente;
    public DtoCreateUpdateTypifiend(Long id, Date emision, String documento, String nombre, String telefono,
            String company, String email, EnumCertificado cetificado, EnumSoporte soporte, EnumSubSoporte subSoporte,
            Date fecha, Time hora, String observacion, Boolean estado, Integer tiempo, EnumCategoria categoria,
            String agente) {
        this.id = id;
        this.emision = emision;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.company = company;
        this.email = email;
        this.cetificado = cetificado;
        this.soporte = soporte;
        this.subSoporte = subSoporte;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
        this.estado = estado;
        this.tiempo = tiempo;
        this.categoria = categoria;
        this.agente = agente;
    }
    public DtoCreateUpdateTypifiend(Date emision, String documento, String nombre, String telefono, String company,
            String email, EnumCertificado cetificado, EnumSoporte soporte, EnumSubSoporte subSoporte, Date fecha,
            Time hora, String observacion, Boolean estado, Integer tiempo, EnumCategoria categoria, String agente) {
        this.emision = emision;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.company = company;
        this.email = email;
        this.cetificado = cetificado;
        this.soporte = soporte;
        this.subSoporte = subSoporte;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
        this.estado = estado;
        this.tiempo = tiempo;
        this.categoria = categoria;
        this.agente = agente;
    }

    
}
