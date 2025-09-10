package pt.coimbra.neuza.beedsstore.adminpanel.catalog.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.coimbra.neuza.beedsstore.R

@Composable
fun Catalog(){
    //todo Repository fun to get items , and put in var

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp) ,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
         modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.background_green))
    ) {
        //todo after getting items, for item in items -> call func CatalogItem
    }
}

@Composable
fun CatalogItems()  {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp) ,
        elevation = CardDefaults.cardElevation(4.dp) ,

    ){
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
           ){

            //todo display image with Assymc Imgage
            Text("Image goes in here! ")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Desction! ")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Price! 0.0 ")
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(top = 16.dp).clickable{
                //todo list with shoping card , add that to the list
            }){
                Icon(painterResource(id = R.drawable.shoppinh_card),
                    contentDescription = "Buy" ,
                    tint = colorResource(R.color.component_hi_purple)
                )
                Text("Buy Item", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.bt_color))
            }


        }
    }
}

@Preview
@Composable
fun prevCatalog(){
    CatalogItems()
}