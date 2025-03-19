package com.allison.ahorasiquesi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.allison.ahorasiquesi.ui.theme.AhoraSiQueSiTheme
import com.allison.ahorasiquesi.ui.theme.SecondScreen
import com.allison.ahorasiquesi.ui.theme.ThirdScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AhoraSiQueSiTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("second") { SecondScreen(navController) } // We pass the navController to SecondScreen
        composable("third/{userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
            if (userName != null) {
                ThirdScreen(userName = userName)
            }
        }
    }
}

val lilyScriptOneFontFamily = FontFamily(
    Font(R.font.lilyscriptone_regular)
)
val LexendaFontFamily = FontFamily(
    Font(R.font.lexendexa_variablefont))
val LibreBarCode = FontFamily(
    Font(R.font.libre_barcode))


@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Asegura que ocupe toda la pantalla
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.background4), // Tu imagen de fondo
            contentDescription = "Fondo de la pantalla",
            modifier = Modifier.fillMaxSize(), // Hace que la imagen cubra toda la pantalla
            contentScale = ContentScale.Crop // Ajusta la imagen para que llene el espacio sin distorsión
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp), // Elimina márgenes generales en la columna
            verticalArrangement = Arrangement.Top, // Alinea todo hacia arriba
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp)) // Espacio arriba

            // LOGO CON MÁRGENES SIN REDUCIR SU TAMAÑO
            Box(
                modifier = Modifier
                    .fillMaxWidth() // La imagen ocupará todo el ancho
                    .padding(start = 0.dp, end = 0.dp, top = 70.dp) // Márgenes laterales y arriba
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de ScanStock",
                    modifier = Modifier
                        .fillMaxWidth() // Asegura que ocupe todo el ancho de la pantalla
                        .aspectRatio(1f) // Mantiene la proporción
                )
                Text(
                    text = "Bienvenido a",
                    fontSize = 23.sp,
                    fontFamily = LexendaFontFamily,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopCenter) // Alinea el texto en la parte superior y centrado
                        .padding(top = 329.dp) // Ajusta el espacio desde la parte superior
                        .padding(bottom = 200.dp)
                )
                Text(
                    text = "ScanStock",
                    fontSize = 50.sp,
                    color = Color(0xFFBE8CDB),
                    fontFamily = lilyScriptOneFontFamily,
                    modifier = Modifier
                        .align(Alignment.TopCenter) // Alinea el texto en la parte superior y centrado
                        .padding(top = 400.dp)
                ) // Ajusta el espacio desde la parte superior
            }

            Spacer(modifier = Modifier.height(40.dp))

            // BOTÓN para navegar a la segunda pantalla
            Button(
                onClick = { navController.navigate("second") }, // Navega a la segunda pantalla
                modifier = Modifier
                    .width(200.dp)  // Ajusta el tamaño del botón (ancho)
                    .height(60.dp), // Ajusta el tamaño del botón (alto)
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x80FFFFFF) // Color personalizado para el fondo del botón
                )
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 35.sp, // Tamaño de la fuente
                    fontFamily = LibreBarCode, // Usando la fuente personalizada
                    color = Color.Black // Color del texto
                )
            }
        }
    }
}

// 🔍 PREVIEW para Design View
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}



