package mandat

data class User(var accessLevel: AccessLevel) {
    fun canAccess(file: File) =
        file.canBeAccessedBy(accessLevel)
}
