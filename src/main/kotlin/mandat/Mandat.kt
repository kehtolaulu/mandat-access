package mandat

interface Mandat {
    fun can(access: Access, file: String): Boolean

    fun canGrant(access: Access, file: String): Boolean

    fun grant(access: Access, file: String)
}
