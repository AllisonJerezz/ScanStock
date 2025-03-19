package com.allison.ahorasiquesi.ui.theme

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.allison.ahorasiquesi.R
import com.allison.ahorasiquesi.ScannerActivity

// Fuente personalizada
val LexendaFontFamily = FontFamily(
    Font(R.font.lexendexa_variablefont)
)

@Composable
fun ThirdScreen(userName: String) {
    val expanded = remember { mutableStateOf(false) }
    val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")
    val selectedOption = remember { mutableStateOf(options[0]) }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.background4),
            contentDescription = "Fondo de la pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Botón de menú (Esquina superior izquierda)
        IconButton(
            onClick = { expanded.value = !expanded.value },
            modifier = Modifier
                .padding(top = 63.dp, start = 20.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Menú",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        // Menú desplegable
        if (expanded.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { expanded.value = false }
                    .zIndex(1f)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 120.dp, start = 30.dp, end = 30.dp)
                    .zIndex(2f)
            ) {
                options.forEach { option ->
                    Button(
                        onClick = {
                            selectedOption.value = option
                            expanded.value = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE93D8))
                    ) {
                        Text(text = option, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // Mensaje de bienvenida
        Text(
            text = "¡Bienvenido, $userName!",
            fontSize = 24.sp,
            fontFamily = LexendaFontFamily,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp, start = 30.dp)
        )

        // Caja redonda con opciones en el centro
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Botones de opciones
                    options.forEach { option ->
                        Button(
                            onClick = { /* Acción */ },
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE93D8))
                        ) {
                            Text(text = option, fontSize = 16.sp, fontFamily = LexendaFontFamily)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // Botón para escanear código de barras
                    Button(
                        onClick = {
                            val intent = Intent(context, ScannerActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE93D8))
                    ) {
                        Text(text = "Escanear Código de Barras", fontSize = 16.sp, fontFamily = LexendaFontFamily)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThirdScreenPreview() {
    ThirdScreen(userName = "Allison")
}
