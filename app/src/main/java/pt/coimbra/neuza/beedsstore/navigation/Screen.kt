package pt.coimbra.neuza.beedsstore.navigation

sealed class Screen(val route: String){
    object LoginScreen:Screen("loginscreen")
    object SignUpScreen:Screen("signupscreen")

    object MainScreenAdmin:Screen("mainscreen")

    object AddCatalogAdmin:Screen("addcatalogadmin")

    object AddHighlightsAdmin:Screen("addhighlightsadmin")

    object CatalogAdmin:Screen("catalogadmin")


}