package mandat

data class User(val name: String, val accessLevel: Mandat) :
    Mandat by accessLevel {

    fun grant(access: Access, file: String, other: User) {
        if (this.canGrant(access, file)) {
            other.accessLevel.grant(access, file)
        }
    }
}
