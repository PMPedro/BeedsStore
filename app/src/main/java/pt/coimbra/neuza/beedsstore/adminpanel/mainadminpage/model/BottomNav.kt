package pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.model

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
    BottomNav("Add Highlights", Screen.AddHighlightsAdmin.route, R.drawable.add_highlights_green)

)
