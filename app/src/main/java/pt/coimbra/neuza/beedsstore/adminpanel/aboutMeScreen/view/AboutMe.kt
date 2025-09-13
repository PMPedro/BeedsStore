package pt.coimbra.neuza.beedsstore.adminpanel.aboutMeScreen.view

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.coimbra.neuza.beedsstore.R
import androidx.compose.ui.graphics.Brush

@Composable
fun AboutMe(){
    val offset = Offset(5.0f, 10.0f)
    val loremIpsumText = "\n" +
            "\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eu sagittis nisi. Integer urna ipsum, convallis vel augue vitae, ultrices viverra tortor. In sed fermentum diam. Donec vel dolor nec nisl semper lacinia nec in eros. Maecenas vitae ligula suscipit leo fermentum semper. Morbi vulputate elit neque, eget dictum elit aliquet nec. Aenean bibendum elementum dictum.\n" +
            "\n" +
            "Praesent pulvinar enim nec dui tempor, id volutpat leo placerat. Donec aliquet posuere velit, ac faucibus mauris fermentum ut. Donec volutpat ipsum tristique, commodo lacus nec, tempor quam. Nunc mattis vitae nibh sed viverra. Suspendisse rhoncus orci non dui ornare vehicula. Duis ullamcorper sodales varius. Suspendisse ac ultrices nunc. Ut tristique ut erat nec placerat. Integer porttitor mattis sapien, eu dictum velit fermentum quis. Nam tortor sem, dignissim vitae ante vel, varius ultricies sem. In luctus risus ac porta ultrices. Donec in nunc commodo, suscipit nisi eget, lacinia sem. Sed ac rhoncus velit. Cras efficitur turpis at diam aliquet, ut congue metus hendrerit. "

    val gradientColor = listOf<Color>(Color.White,  colorResource(R.color.component_hi_purple) , colorResource(R.color.white) )

    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background_green)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        Text("About Azuen",
            fontWeight = FontWeight.Bold,
            style = TextStyle (
                fontSize = 30.sp,
                color = Color.White,
                shadow = Shadow(
                    color = colorResource(R.color.bt_color), offset = offset, blurRadius = 2f
                    )
                ),
            )

        Card(modifier = Modifier.fillMaxWidth().padding(16.dp) ,
            colors = CardColors(containerColor = colorResource(R.color.bt_color),
                contentColor = Color.White ,
                disabledContainerColor = colorResource(R.color.component_purple) ,
                disabledContentColor = colorResource(R.color.component_purple)
            )
        ){
            Text("An image goes here")
            //todo Fetch an image from the database an  isert it here wirth assyncImage
        }

        //todo fetch data from data base , and inser data here
        Text(loremIpsumText,
            style = TextStyle(
                fontSize = 20.sp,
                brush = Brush.linearGradient(colors = gradientColor)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutMePrev(){
    AboutMe()
}