package vander.gabriel.cadastro


data class Form(
    val fullName: String,
    val phoneNumber: String,
    val email: String,
    val signedUpForNewsletter: Boolean = false,
    val gender: String,
    val city: String,
    val state: String
)
