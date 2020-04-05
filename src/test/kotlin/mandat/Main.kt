package mandat

import java.util.*

val cleanManager = BaseMandat

val student = AccessLevel(
    cleanManager,
    "cabinet" to Access.Read,
    "schedule" to Access.Read
)

val teacher = AccessLevel(student, "course" to Access.ReadWrite)

val decanat = AccessLevel(teacher, "schedule" to Access.ReadWrite)

val director = AccessLevel(
    decanat,
    "cabinet" to Access.Full,
    "course" to Access.Full
)

val users = mapOf(
    "emily" to User("Emily", student),
    "setin" to User("S Macsetin", teacher),
    "abramsky" to User("MM", director),
    "sosnovskaya" to User("E Sosnovskaya", decanat)
)

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        print("Your name: ")
        val name = scanner.next()
        val currentUser = users[name] ?: throw IllegalArgumentException("No such user")
        println("Objects are cabinet, schedule, course\n")
        println("What you wanna do?")
        try {
            when (val command = scanner.next()) {
                "Grant" -> {
                    val access = Access.valueOf(scanner.next())
                    val file = scanner.next()
                    if (currentUser.canGrant(access, file)) {
                        val toWhom = users[scanner.next()] ?: throw IllegalArgumentException("No such user")
                        currentUser.grant(access, file, toWhom)
                        println("Success")
                    } else {
                        println("Access denied!")
                    }
                }
                else -> {
                    val file = scanner.next()
                    val access = Access.valueOf(command)
                    if (currentUser.can(access, file)) {
                        println("Success")
                    } else {
                        println("Access denied!")
                    }
                }
            }
        } catch (e: IllegalArgumentException) {
            println("error happened")
            println(e.message)
            println(e)
        }
    }

}
