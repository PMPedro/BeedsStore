package pt.coimbra.neuza.beedsstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import pt.coimbra.neuza.beedsstore.authentication.viewmodel.AuthViewModel
import pt.coimbra.neuza.beedsstore.navigation.NavigationGrath
import pt.coimbra.neuza.beedsstore.ui.theme.BeedsStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel : AuthViewModel = viewModel()
            BeedsStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.background_green) // or your custom color
                ) {
                    // Your app content
                    //TODO add navigation
                    NavigationGrath(navController = navController, authViewModel = authViewModel)
                }
            }
        }
    }
}
