package pt.coimbra.neuza.beedsstore.userpanel.shoppingList.view

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
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.adminpanel.mainAdminScreen.view.BottomBar
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed
import pt.coimbra.neuza.beedsstore.userpanel.catalog.view.CatalogItemsUser
import pt.coimbra.neuza.beedsstore.userpanel.catalog.viewModel.catalogViewModelUser
import pt.coimbra.neuza.beedsstore.userpanel.shoppingList.viewModel.ShopListViewModel

@Composable
fun ShoppingList(navController: NavController){
    //get ShopList Items
    val shopViewModel: ShopListViewModel = viewModel()
    val beeds by shopViewModel.beedsListShop.observeAsState(emptyList())
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background_green))) {
            Scaffold(bottomBar = {BottomBar(navController = navController)} , modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(colorResource(R.color.background_green)) ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally ,
                ){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2) ,
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.background_green))
                    ) {
                        if(beeds != null){
                            items(beeds){beedItem ->
                                ShopListItem(beedItem)
                            }
                        }
                    }
                }
            }
        }
    }



@Composable
fun ShopListItem(beed : Beed){
    Card(modifier = Modifier.fillMaxSize().padding(16.dp), elevation = CardDefaults.cardElevation(4.dp) ,
        colors = CardColors(contentColor = Color.White,
            containerColor = colorResource(R.color.bt_color),
            disabledContentColor = colorResource(R.color.component_purple) ,
            disabledContainerColor = colorResource(R.color.component_purple)
        )
    ){
        AsyncImage(model = beed.imageURL, contentDescription = beed.title, modifier = Modifier.fillMaxWidth().height(100.dp))
        Spacer(modifier = Modifier.height(8.dp))

        androidx.compose.material.Text(beed.description, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        androidx.compose.material.Text("${beed.price}€", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.padding(top = 16.dp).clickable{
            //todo remove item from shopList
        }){
            Icon(painterResource(id = R.drawable.icon_delete_),
                contentDescription = "Remove" ,
                tint = Color.Red)
            androidx.compose.material.Text(
                "Remove Item",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun shopPrev(){
    //ShopListItem()
}