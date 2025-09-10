package pt.coimbra.neuza.beedsstore.authentication.model

sealed class UserType {
    object Admin : UserType()
    object General : UserType()
    data class Error(val exception: Exception) : UserType()
}