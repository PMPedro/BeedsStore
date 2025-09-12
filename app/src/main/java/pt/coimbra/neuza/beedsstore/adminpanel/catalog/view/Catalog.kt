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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.adminpanel.catalog.viewmodel.CatalogViewModel
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed

@Composable
fun Catalog(){
    //todo Repository fun to get items , and put in var
    val catalogViewModel: CatalogViewModel = viewModel()
    val beeds by catalogViewModel.beeds.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp) ,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
         modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.background_green))
    ) {
        if(beeds != null){
        items(beeds){beedItem ->
            CatalogItems(beedItem)
        }
        }
    }
}

@Composable
fun CatalogItems( beedItem : Beed)  {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp) ,
        elevation = CardDefaults.cardElevation(4.dp) ,

    ){
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
           ){

            //todo display image with Assymc Imgage
            AsyncImage(model = beedItem.imageURL, contentDescription = beedItem.title, modifier = Modifier.fillMaxWidth().height(150.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(beedItem.description , color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${beedItem.price}€")
            Spacer(modifier = Modifier.height(8.dp))
//            Row(modifier = Modifier.padding(top = 16.dp).clickable{
//                //for viewing porpuse only
//                //shopping only necessary on
//
//            }){
//                Icon(painterResource(id = R.drawable.shoppinh_card),
//                    contentDescription = "Buy" ,
//                    tint = colorResource(R.color.component_hi_purple)
//                )
//                Text("Buy Item", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.bt_color))
//            }
//

        }
    }
}

@Preview
@Composable
fun prevCatalog(){
    Catalog()
}