package pt.coimbra.neuza.beedsstore.userpanel.catalog.model

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
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.view.Catalog
import pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.view.CatalogItems
import pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.viewmodel.CatalogViewModel
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed

@Composable
fun CatalogViewUser () {
    val catalogViewModel: CatalogViewModel = viewModel()
    val beeds by catalogViewModel.beeds.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2) ,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.background_green))
    ) {
        if(beeds != null){
            items(beeds){beedItem ->
                CatalogItemsUser(beedItem,catalogViewModel)
            }
        }
    }
}

@Composable
fun CatalogItemsUser( beedItem : Beed, viewModel: CatalogViewModel)  {
    Card(modifier = Modifier.fillMaxWidth().padding(16.dp) ,
        elevation = CardDefaults.cardElevation(4.dp) ,
        colors = CardColors(contentColor = Color.White,
            containerColor = colorResource(R.color.bt_color),
            disabledContentColor = colorResource(R.color.component_purple) ,
            disabledContainerColor = colorResource(R.color.component_purple)
        )
    ){
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            AsyncImage(model = beedItem.imageURL, contentDescription = beedItem.title, modifier = Modifier.fillMaxWidth().height(150.dp))
            Spacer(modifier = Modifier.height(8.dp))

            Text(beedItem.description , color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))

            Text("${beedItem.price}€", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.padding(top = 16.dp).clickable{
                viewModel.deleteBeed(beedItem)
            }){
                Icon(painterResource(id = R.drawable.shoppinh_card),
                    contentDescription = "Buy" ,
                    tint = Color.Red)
                Text("Delete Item", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

        }
    }
}


@Preview
@Composable
fun prevCatalog(){
    Catalog()
}
