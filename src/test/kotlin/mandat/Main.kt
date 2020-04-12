package mandat

class AccessLevel(private val level: Int = 0) {
    constructor(base: AccessLevel) : this(base.level + 1)

    operator fun compareTo(other: AccessLevel) =
        this.level.compareTo(other.level)
}

class File(private val requiredAccessLevel: AccessLevel) {
    fun canBeAccessedBy(accessLevel: AccessLevel): Boolean {
        return accessLevel >= requiredAccessLevel
    }
}

open class User(var accessLevel: AccessLevel) {
    open fun canAccess(file: File) =
        file.canBeAccessedBy(accessLevel)
}

fun mandat(currentUser: User, function: Mandat.() -> Unit) =
    function(Mandat(currentUser))

fun show(it: Boolean) = println(if (it) "Access permitted." else "Access denied.")

class Mandat(val user: User) {
    val currentUser: User =
        object : User(user.accessLevel) {
            override fun canAccess(file: File) =
                super.canAccess(file).also(::show)
        }
}

val staff = AccessLevel()
val policeman = AccessLevel(staff)
val agent = AccessLevel(policeman)
val president = AccessLevel(agent)
val god = AccessLevel(president)

val nina = User(staff)
val david = User(policeman)
val smith = User(agent)
val trump = User(president)
val jesus = User(god)

val badge = File(staff)
val protocol = File(policeman)
val dossier = File(agent)
val secretData = File(president)
val thoughts = File(god)

fun main() =
    mandat(david) {
        currentUser.canAccess(protocol)
        currentUser.canAccess(secretData)
    }
