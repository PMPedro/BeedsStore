package pt.coimbra.neuza.beedsstore.authentication.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import pt.coimbra.neuza.beedsstore.authentication.viewmodel.AuthViewModel

@Composable
fun SignUp(
    authViewModel: AuthViewModel,
    onLoginNav: () -> Unit,
    onCatalogNav: () -> Unit

){
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var username by remember {mutableStateOf("")}
    val context = LocalContext.current
    val result by authViewModel.authResult.observeAsState()

    Column(modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = username ,
            onValueChange = {username = it} ,
            label = { Text("Username" , color = Color.White) } ,
            modifier = Modifier.fillMaxWidth().padding(8.dp) ,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                focusedTextColor = Color.White ,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp) ,
        )

        OutlinedTextField(value = email ,
            onValueChange = {email = it} ,
            label = { Text("Email" , color = Color.White) } ,
            modifier = Modifier.fillMaxWidth().padding(8.dp) ,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                focusedTextColor = Color.White ,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(value = password ,
            onValueChange = {password = it} ,
            label = { Text("Password" , color = Color.White) } ,
            modifier = Modifier.fillMaxWidth().padding(8.dp) ,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                focusedTextColor = Color.White ,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp) ,
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            authViewModel.signUp(email = email, password = password, username = username)
            when (result) {
                is Result.Success -> {
                  //  onSignUpSucess()
                    onLoginNav()
                }is Result.Error -> {
                    Toast.makeText(context, "Error creating account, please try again", Toast.LENGTH_LONG).show()
                }else -> {

                }
            }
        } ,modifier = Modifier.fillMaxWidth(fraction = 0.5f).padding(16.dp) ,
            colors = ButtonColors(containerColor = colorResource(R.color.bt_color), contentColor = Color.White, disabledContentColor = Color.White , disabledContainerColor = Color.Gray)
        ){
            Text("Sign Up", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Already has an Account?" , color = Color.White , modifier = Modifier.clickable{
            onLoginNav()
        })

        Spacer(modifier = Modifier.padding(16.dp))

        Text("Just want to see the catalog", color = Color.White, modifier = Modifier.clickable{
            //todo Navigation
            onCatalogNav()
        })


    }
}