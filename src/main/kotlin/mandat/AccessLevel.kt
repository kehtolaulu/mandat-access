package mandat

class AccessLevel(
    private val base: Mandat = BaseMandat,
    private val accessList: MutableMap<String, Access> = mutableMapOf()
) : Mandat {
    constructor(base: Mandat = BaseMandat, vararg accesses: Pair<String, Access>) :
            this(base, accesses.toMap().toMutableMap())

    override fun can(access: Access, file: String): Boolean {
        return (accessList[file] ?: Access.Denied).can(access) || base.can(access, file)
    }

    override fun canGrant(access: Access, file: String): Boolean {
        return (accessList[file] ?: Access.Denied).canGrant(access) || base.canGrant(access, file)
    }

    override fun grant(access: Access, file: String) {
        accessList[file] = (accessList[file] ?: Access.Denied) or access
    }
}
