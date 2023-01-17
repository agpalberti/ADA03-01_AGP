import jakarta.persistence.*

@Entity
@Table(name = "talleres")
class Taller(

    @Id
    @Column(name = "cif")
    var CIF: String,
    @Column(name = "nombre")
    var nombre: String,

    // Con la etiqueta OneToOne relacionamos una clave ajena a su tabla, además de proporcionar una regla para el borrado
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion: Direccion,

    @ManyToMany(mappedBy = "talleres")
    var clientes: Set<Cliente>? = null,
)