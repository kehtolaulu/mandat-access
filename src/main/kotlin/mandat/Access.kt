package mandat

enum class Access(private val bits: Int) {
    Denied(0b000),
    Read(0b001),
    Write(0b010),
    ReadWrite(0b011),
    Grant(0b100),
    ReadGrant(0b101),
    WriteGrant(0b110),
    Full(0b111);

    fun can(access: Access): Boolean {
        return (this.bits and access.bits) != 0
    }

    fun canGrant(access: Access): Boolean {
        return (this.bits and Grant.bits) != 0 && this.can(access)
    }

    infix fun or(other: Access): Access {
        return fromInt(this.bits or other.bits)
    }

    private fun fromInt(int: Int) = values()[int]
}
