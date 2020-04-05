package mandat

object BaseMandat : Mandat {
    override fun can(access: Access, file: String): Boolean {
        return false
    }

    override fun canGrant(access: Access, file: String): Boolean {
        return false
    }

    override fun grant(access: Access, file: String) {}
}
