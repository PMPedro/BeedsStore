package pt.coimbra.neuza.beedsstore.adminpanel.mainAdminScreen.model

import androidx.annotation.DrawableRes
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.navigation.Screen

data class BottomNav(
    val title: String ,
    val route: String ,
    @DrawableRes val icon: Int
)

val ButtomBarIcons = listOf(
    BottomNav("Add Catalog", Screen.AddCatalogAdmin.route, R.drawable.add_to_catalog_green) ,
    BottomNav("Catalog", Screen.CatalogAdmin.route, R.drawable.catalog_green),
    BottomNav("About Me", Screen.AboutMeAdmin.route, R.drawable.icon_about_me)

)
