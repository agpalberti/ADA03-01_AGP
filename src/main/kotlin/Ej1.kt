import jakarta.persistence.*

/*Especificamos que cada clase es una entidad, y la relacionamos con una tabla de nuestra BD mediante @Table, además de relacionar
los atributos estas con sus tablas necesarias*/
@Entity
@Table(name = "direcciones")
class Direccion(
    // Especificamos el ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "calle")
    var calle: String,
    @Column(name = "numero")
    var numero: Int,
    @Column(name = "cp")
    var codigopostal: Int,

)

@Entity
@Table(name = "talleres")
class Taller(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "nombre")
    var nombre: String,
    @Column(name = "cif")
    var CIF: String,

    // Con la etiqueta OneToOne relacionamos una clave ajena a su tabla, además de proporcionar una regla para el borrado
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion: Direccion
)

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column (name = "nombre")
    var nombre: String,
    @Column (name = "edad")
    var edad: Int,
    @Column (name = "ciudad")
    var ciudad: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn (name = "id_direccion")
    var direccion: Direccion
)