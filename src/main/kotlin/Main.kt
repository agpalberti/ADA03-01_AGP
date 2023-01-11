import jakarta.persistence.Persistence


fun main(args: Array<String>) {

    // Creamos una
    val entityManagerFactory = Persistence.createEntityManagerFactory("ADA03")
    val entityManager = entityManagerFactory.createEntityManager()

    // Empezamos la transacción para comenzar a persistir objetos hacia la base de datos
    val transaction = entityManager.transaction
    transaction.begin()

    // Creamos nuestros objetos Dirección
    val direccion1 = Direccion(calle = "Calle Sanchez", codigopostal = 11130, numero = 7)
    val direccion2 = Direccion(calle = "Calle Real", codigopostal = 11100, numero = 23)
    val direccion3 = Direccion(calle = "Calle Molino", codigopostal = 11130, numero = 8)
    val direccion4 = Direccion(calle = "Calle Almería", codigopostal = 11010, numero = 11)

    // Y los persistimos para hacerlos Managed
    entityManager.persist(direccion1)
    entityManager.persist(direccion2)

    // Lo mismo para Taller y Cliente
    val taller1 = Taller(nombre = "Taller Alfonso", CIF = "ASD1234", direccion = direccion1)
    val taller2 = Taller(nombre = "Taller Roberto", CIF = "CDS4321", direccion = direccion2)

    entityManager.persist(taller1)
    entityManager.persist(taller2)

    val cliente1 = Cliente(nombre = "Juan", ciudad = "Chiclana de la Frontera", edad = 22, direccion = direccion3)
    val cliente2 = Cliente(nombre = "Pablo", ciudad = "San Fernando", edad = 32, direccion = direccion4)

    entityManager.persist(cliente1)
    entityManager.persist(cliente2)

    // Una vez persistidos, guardamos los cambios en la base de datos con .commit
    transaction.commit()

    // Creamos nuestras querys y los resultados de cada una de nuestras clases, así obteniendo todos los objetos de la BD
    val direccionQuery = entityManager.createQuery("from Direccion", Direccion::class.java)
    val direccionResultList = direccionQuery.resultList

    val clienteQuery = entityManager.createQuery("from Cliente", Cliente::class.java)
    val clienteResultList = clienteQuery.resultList

    val tallerQuery = entityManager.createQuery("from Taller", Taller::class.java)
    val tallerResultList = tallerQuery.resultList

    // Y recorremos cada uno de los resultados, imprimiendo los valores por consola
    println("DIRECCIONES\n")
    for (result in direccionResultList){
        println("ID: ${result.id}")
        println(result.calle)
        println(result.codigopostal)
        println("Nª${result.numero}\n")
    }

    println("CLIENTES\n")
    for (result in clienteResultList){
        println("ID: ${result.id}")
        println(result.nombre)
        println(result.ciudad)
        println(result.edad)
        println("${result.direccion.calle}, Nº${result.direccion.numero}, ${result.direccion.codigopostal}\n")
    }

    println("TALLERES\n")
    for (result in tallerResultList){
        println("ID: ${result.id}")
        println(result.CIF)
        println(result.nombre)
        println("${result.direccion.calle}, Nº${result.direccion.numero}, ${result.direccion.codigopostal}\n")
    }


    // Cerramos las instancias de entityManager y de su Factory
    entityManager.close()
    entityManagerFactory.close()
}

