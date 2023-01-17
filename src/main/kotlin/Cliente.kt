import jakarta.persistence.*

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @Column(name = "dni")
    var dni: String,
    @Column(name = "nombre")
    var nombre: String,
    @Column(name = "edad")
    var edad: Int,
    @Column(name = "ciudad")
    var ciudad: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion: Direccion,

    @ManyToMany
    @JoinTable(
        name = "cliente_taller",
        joinColumns = [JoinColumn(name = "dni")],
        inverseJoinColumns = [JoinColumn(name = "cif")]
    )
    var talleres: Set<Taller>? = null,
)