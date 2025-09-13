package pt.coimbra.neuza.beedsstore.adminpanel.addCatalogScreen.view

import android.Manifest
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.coimbra.neuza.beedsstore.R
import pt.coimbra.neuza.beedsstore.adminpanel.addCatalogScreen.viewmodel.AddCatalogViewModel
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed

@Composable
fun AddCatalog(){
    val context = LocalContext.current
    val viewModel : AddCatalogViewModel = viewModel ()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        Toast.makeText(context, "Uri selecionada: $uri", Toast.LENGTH_LONG).show()

    }
    val uiState by viewModel.uiState.collectAsState()

    var hasGalleryPermission by remember { mutableStateOf(false) }
    //launcher to ask for permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasGalleryPermission = isGranted
        if(!isGranted) {
            Toast.makeText(context, "Permission denied!", Toast.LENGTH_SHORT).show()
        }
    }
    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    Column(modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.background_green)) ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center) {

        Column (modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(value = title ,
                onValueChange = {title = it} ,
                label = { Text("Title" , color = Color.White) } ,
                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                    focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                    focusedTextColor = Color.White ,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp) ,
            )

            OutlinedTextField(value = description ,
                onValueChange = {description = it} ,
                label = { Text("Description" , color = Color.White) } ,
                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                    focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                    focusedTextColor = Color.White ,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp) ,
            )

            OutlinedTextField(value = price ,
                onValueChange = {input ->
                    // allow only digits
                    if (input.all { it.isDigit() }) {
                        price = input} },
                label = { Text("Price" , color = Color.White) } ,
                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(id = R.color.component_purple) ,
                    focusedContainerColor = colorResource(id = R.color.component_hi_purple) ,
                    focusedTextColor = Color.White ,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp) ,
            )

            Button(onClick = {
                if(hasGalleryPermission)
                    imagePickerLauncher.launch("image/*")
                else
                    permissionLauncher.launch(permission)
            } ,
                  modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonColors(containerColor = colorResource(R.color.bt_color),
                    contentColor = Color.White, disabledContentColor = Color.White ,
                    disabledContainerColor = Color.Gray )  ){
            Text("Select an Image", color = Color.White)
            }

            Button(onClick = {
                if(selectedImageUri != null ){
                    val beed = Beed(title = title, description = description, price = price.toDouble())
                    viewModel.onPickAndSave(selectedImageUri!!, beed, context)
                    title = ""
                    description = ""
                    price = ""
                    selectedImageUri = null

                } else
                    Toast.makeText(context, "Nao Meteu imagem corretamente", Toast.LENGTH_LONG).show()
            } ,
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonColors(containerColor = colorResource(R.color.bt_color),
                    contentColor = Color.White, disabledContentColor = Color.White ,
                    disabledContainerColor = Color.Gray )  ){
                Text("Create Beed", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevAddCatalog(){
    AddCatalog()
}



