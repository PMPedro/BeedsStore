package pt.coimbra.neuza.beedsstore.userpanel.catalog.model

import androidx.annotation.DrawableRes
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.navigation.Screen

data class BottomNavUser(
    val title: String ,
    val route: String ,
    @DrawableRes val icon: Int
)

val ButtomBarUserIcons = listOf(
    BottomNavUser("About Me", Screen.AddCatalogAdmin.route, R.drawable.add_to_catalog_green) ,
    BottomNavUser("ShoppingList", Screen.CatalogAdmin.route, R.drawable.catalog_green),


)
