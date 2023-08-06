package kartikey.saran.aisle.Model

data class Invites(
    val pending_invitations_count: Int,
    val profiles: List<ProfileInvite>,
    val totalPages: Int
)