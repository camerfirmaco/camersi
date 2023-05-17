package colombia.supportservice.Model;

import java.util.Date;


import org.hibernate.annotations.NaturalId;

import colombia.supportservice.Utils.EnumCategoria;
import colombia.supportservice.Utils.EnumCertificado;
import colombia.supportservice.Utils.EnumSoporte;
import colombia.supportservice.Utils.EnumSubSoporte;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "typifiend")
public class EntityTypifiend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID
    private Long id;
    // FECHA DE EMISIÓN DEL CERTIFICADO
    @Column(nullable = true)
    private Date emision;
    // DOCUMENTO DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String documento;
    // NOMBRE DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String nombre;
    // TELEFONO DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String telefono;
    // COMPAÑIA DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String company;
    // EMAIL DEL TITULAR DE LA FIRMA
    @Column(nullable = true)
    private String email;
    // TIPO DE CERTIFICADO EMITIDO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumCertificado cetificado;
    // TIPO DE SOPORTE REALIZADO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumSoporte soporte;
    // SUB TIPO DE SOPORTE
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumSubSoporte subSoporte;
    // FECHA DEL SOPORTE
    @Column(nullable = false)
    private Date fecha;
    // HORA DEL SOPORTE
    @Column(nullable = false)
    private Date hora;
    // OBSERVACIONES DEL SOPORTER
    @Column(nullable = false, columnDefinition = "TEXT")
    private String observacion;
    // ESTADO DEL SOPORTE
    @Column(nullable = true)
    private Boolean estado;
    // TIEMPO DEL SOPORTE
    @Column(nullable = true)
    private Integer tiempo;
    // CATEGORIA DEL SOPORTE
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumCategoria categoria;
    // AGENTE QUIEN REALIZA EL SOPORTE
    @NaturalId
    @Column(nullable = true, columnDefinition = "VARCHAR(36)")
    private String agente;
    // ESTADO DE ELIMINADO
    @Column(nullable = true)
    private Boolean eliminado;

    // PENDIENTE DE LLAMAR
    @Column(nullable = true)
    private Boolean pendiente;

    // GUIA DE SERVIENTREGA
    @Column(nullable = true)
    private Long guia;

    // PENDIENTE DE LLAMAR
    @Column(nullable = true)
    private Boolean evidencia;

    public EntityTypifiend(Long id, Date emision, String documento, String nombre, String telefono, String company,
            String email, EnumCertificado cetificado, EnumSoporte soporte, EnumSubSoporte subSoporte, Date fecha,
            Date hora, String observacion, Boolean estado, Integer tiempo, EnumCategoria categoria, String agente,
            Boolean eliminado, Boolean pendiente, Long guia, Boolean evidencia) {
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
        this.eliminado = eliminado;
        this.pendiente = pendiente;
        this.guia = guia;
        this.evidencia = evidencia;
    }

    public EntityTypifiend(Date emision, String documento, String nombre, String telefono, String company, String email,
            EnumCertificado cetificado, EnumSoporte soporte, EnumSubSoporte subSoporte, Date fecha, Date hora,
            String observacion, Boolean estado, Integer tiempo, EnumCategoria categoria, String agente,
            Boolean eliminado, Boolean pendiente, Long guia, Boolean evidencia) {
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
        this.eliminado = eliminado;
        this.pendiente = pendiente;
        this.guia = guia;
        this.evidencia = evidencia;
    }
    

}
