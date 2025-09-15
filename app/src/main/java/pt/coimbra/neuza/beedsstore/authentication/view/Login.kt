package pt.coimbra.neuza.beedsstore.authentication.view

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import pt.coimbra.neuza.beedsstore.authentication.model.UserType
import pt.coimbra.neuza.beedsstore.authentication.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    onLoginSucessAdmin: () -> Unit ,
    onNavigatetoCatalog: () -> Unit,
    onLoginSucessComon: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val result by authViewModel.authResult.observeAsState()
    val userTypeResult by authViewModel.isAdmin.observeAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){

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
            scope.launch {
            authViewModel.login(email, password)
            when(result){
                is Result.Success -> {
                authViewModel.userType(email)
                    when(userTypeResult){
                        is UserType.Admin ->{
                            onLoginSucessAdmin()
                        }
                        is UserType.Error -> {
                            Toast.makeText(context,"Error, please try again!", Toast.LENGTH_LONG ).show()
                        }
                        is UserType.General ->{
                            onLoginSucessComon()
                        }
                        else -> {}
                    }
                }is Result.Error-> {
                    Toast.makeText(context,"Password or Email are wrong!", Toast.LENGTH_LONG ).show()
                }else -> {

                    }
                }
            }
        } ,modifier = Modifier.fillMaxWidth(fraction = 0.5f).padding(16.dp) ,
            colors = ButtonColors(containerColor = colorResource(R.color.bt_color),
                contentColor = Color.White, disabledContentColor = Color.White ,
                disabledContainerColor = Color.Gray)
        ){
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Text("Don't Have an Account?", color = Color.White ,modifier = Modifier.clickable{
            onNavigateToSignUp()
        } )

        Spacer(modifier = Modifier.padding(16.dp))
        Text("Just Want to see catalog",color = Color.White , modifier = Modifier.clickable{
            onNavigatetoCatalog()
        } )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF25BC10)
@Composable
fun loginPrev(){
    val authViewModel : AuthViewModel = viewModel ()
    LoginScreen(authViewModel, {}, {}, {}, {})
}