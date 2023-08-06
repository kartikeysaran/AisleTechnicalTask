package kartikey.saran.aisle.Model

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferencePD>,
    val question: String
)