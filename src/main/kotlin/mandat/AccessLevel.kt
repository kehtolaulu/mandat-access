package mandat

class AccessLevel(private val level: Int = 0) {
    constructor(base: AccessLevel) : this(base.level + 1)

    operator fun compareTo(other: AccessLevel) =
        this.level.compareTo(other.level)
}
