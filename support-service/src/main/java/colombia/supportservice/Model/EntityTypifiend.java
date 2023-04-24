package colombia.supportservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String emision;
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
    @Column(nullable = true)
    private String cetificado;
    // TIPO DE SOPORTE REALIZADO
    @Column(nullable = true)
    private String soporte;
    // FECHA DEL SOPORTE
    @Column(nullable = true)
    private String fecha;
    // HORA DEL SOPORTE
    @Column(nullable = true)
    private String hora;
    // OBSERVACIONES DEL SOPORTER
    @Column(nullable = true)
    private String observacion;
    // ESTADO DEL SOPORTE
    @Column(nullable = true)
    private String estado;
    // TIEMPO DEL SOPORTE
    @Column(nullable = true)
    private String tiempo;
    // CATEGORIA DEL SOPORTE
    @Column(nullable = true)
    private String categoria;
    // AGENTE QUIEN REALIZA EL SOPORTE
    @Column(nullable = true)
    private String agente;
    // ESTADO DE ELIMINADO
    @Column(nullable = true)
    private Boolean emilinado;

    public EntityTypifiend(Long id, String emision, String nombre, String telefono, String company, String email,
            String cetificado, String soporte, String fecha, String hora, String observacion, String estado,
            String tiempo, String categoria, String agente, Boolean emilinado) {
        this.id = id;
        this.emision = emision;
        this.nombre = nombre;
        this.telefono = telefono;
        this.company = company;
        this.email = email;
        this.cetificado = cetificado;
        this.soporte = soporte;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
        this.estado = estado;
        this.tiempo = tiempo;
        this.categoria = categoria;
        this.agente = agente;
        this.emilinado = emilinado;
    }

}
