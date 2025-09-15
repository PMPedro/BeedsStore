package pt.coimbra.neuza.beedsstore.adminpanel.mainAdminScreen.view


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.adminpanel.mainAdminScreen.model.ButtomBarIcons
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


@Composable
fun MainScreen(navController: NavController){
    val context = LocalContext.current
    val email = "azuentime@gmail.com"
    val devEmail = "pedromartinsgpsi1619@gmail.com"




    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.background_green))){
        Scaffold(bottomBar = {BottomBar(navController = navController)} , modifier = Modifier.fillMaxSize()){
                innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorResource(R.color.background_green)) ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally ,

            ){

                Text("Beed Catalog", fontSize = 30.sp , color = colorResource(R.color.component_purple))

                Text("By Azuen", fontStyle = FontStyle.Italic, fontSize = 60.sp, color = colorResource(R.color.component_hi_purple) )

                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = email,
                    fontSize = 16.sp,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$email")
                        }
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.padding(64.dp))

                Text("Developed by",  fontSize = 8.sp, color = Color.White,)

                Text(
                    text = devEmail,
                    fontSize = 8.sp,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$devEmail")
                        }
                        context.startActivity(intent)
                    }
                )


            }
        }


    }
}
@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = colorResource(R.color.component_purple),
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtomBarIcons.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, tint = colorResource(R.color.component_hi_purple)) },
                label = { Text(text = item.title, color = Color.White) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun catalPrev(){
    val navController = rememberNavController()
    MainScreen(navController)
}

