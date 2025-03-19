// SecondScreen.kt
package com.allison.ahorasiquesi.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allison.ahorasiquesi.MainScreen
import com.allison.ahorasiquesi.R


val LexendexaFontFamily: FontFamily
    get() = FontFamily(
        Font(R.font.lexendexa_variablefont))

@Composable
fun SecondScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.background4), // Tu imagen de fondo
            contentDescription = "Fondo de la pantalla",
            modifier = Modifier.fillMaxSize(), // Hace que la imagen cubra toda la pantalla
            contentScale = ContentScale.Crop // Ajusta la imagen para que llene el espacio sin distorsión
        )

        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(0.8f) // Ancho limitado a 80% de la pantalla
                .fillMaxHeight(0.5f)
                .background(
                    color = Color.White, // Color de fondo del rectángulo
                    shape = RoundedCornerShape(16.dp) // Bordes redondeados
                )
                .padding(20.dp) // Espacio interno
                .align(Alignment.Center) // Alineación al centro de la pantalla
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp), // Elimina márgenes generales en la columna
                verticalArrangement = Arrangement.Center, // Alinea todo hacia arriba
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp)) // Espacio arriba

                Spacer(modifier = Modifier.height(40.dp))

                // Título "Inicia sesión"
                Text(
                    text = "Inicia sesión",
                    fontSize = 28.sp,
                    fontFamily = LexendexaFontFamily,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Campo para el correo electrónico
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp) // Bordes redondeados
                )

                // Campo para la contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp) // Agrega bordes redondeados
                )
                Spacer(modifier = Modifier.height(20.dp)) // Espacio entre campos y el botón

                // Botón "Iniciar sesión" para navegar a la tercera pantalla
                Button(
                    onClick = {
                        val userName = "Allison" // El nombre del usuario
                        navController.navigate("third/$userName") // Navega a la tercera pantalla
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCE93D8)
                    )
                ) {
                    Text(
                        text = "Continuar",
                        fontSize = 14.sp,
                        fontFamily = LexendexaFontFamily,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ThirdScreen() {
    // Here goes the content of your third screen
    Text("¡Welcome to the third screen!", fontSize = 30.sp, color = Color.Black)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("second") { SecondScreen(navController) } // We pass the navController to SecondScreen
        composable("third/{userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
            ThirdScreen()
        }
    }
}

// 🔍 PREVIEW for Design View
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController = rememberNavController())
}