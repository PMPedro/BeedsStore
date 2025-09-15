package pt.coimbra.neuza.beedsstore.navigation

sealed class Screen(val route: String){
    object LoginScreen:Screen("loginscreen")
    object SignUpScreen:Screen("signupscreen")

    object MainScreenAdmin:Screen("mainscreen")

    object AddCatalogAdmin:Screen("addcatalogadmin")

    object AboutMeAdmin:Screen("aboutmeadmin")

    object CatalogAdmin:Screen("catalogadmin")

    object CatalogUser:Screen("cataloguser")



    object ShoppingListUser:Screen("shoppinglistuser")


}