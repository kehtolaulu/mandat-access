package mandat

class File(private val requiredAccessLevel: AccessLevel) {
    fun canBeAccessedBy(accessLevel: AccessLevel): Boolean {
        return accessLevel >= requiredAccessLevel
    }
}
