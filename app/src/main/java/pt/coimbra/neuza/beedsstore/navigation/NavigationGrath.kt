package pt.coimbra.neuza.beedsstore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.view.AddCatalog
import pt.coimbra.neuza.beedsstore.adminpanel.catalog.view.Catalog
import pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.view.MainScreen
import pt.coimbra.neuza.beedsstore.authentication.view.LoginScreen
import pt.coimbra.neuza.beedsstore.authentication.view.SignUp
import pt.coimbra.neuza.beedsstore.authentication.viewmodel.AuthViewModel

@Composable
fun NavigationGrath(
    authViewModel: AuthViewModel,
    navController: NavHostController
){
    NavHost(navController = navController , startDestination = Screen.LoginScreen.route)
    {
        composable(Screen.LoginScreen.route){
            LoginScreen(
                onNavigateToSignUp = {navController.navigate(Screen.SignUpScreen.route)} ,
                authViewModel = authViewModel,
                onLoginSucessAdmin =  {navController.navigate(Screen.MainScreenAdmin.route)})
        }

        composable(Screen.SignUpScreen.route){
            SignUp(authViewModel = authViewModel, onLoginNav = {navController.navigate(Screen.LoginScreen.route)})
        }

        composable(Screen.MainScreenAdmin.route){
            MainScreen(navController = navController)
        }

        composable(Screen.CatalogAdmin.route){
            Catalog()
        }

        composable(Screen.AddCatalogAdmin.route){
            AddCatalog()
        }



    }
}