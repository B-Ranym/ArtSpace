package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    var currentArtworkIndex by remember { mutableStateOf(0) }


    val artworks = listOf(
        Artwork(
            imageResourceId = R.drawable.artwork1,
            title = "Starry Night",
            artist = "Vincent van Gogh",
            year = "1889"
        ),
        Artwork(
            imageResourceId = R.drawable.artwork2,
            title = "Mona Lisa",
            artist = "Leonardo da Vinci",
            year = "1503"
        ),
        Artwork(
            imageResourceId = R.drawable.artwork3,
            title = "The Persistence of Memory",
            artist = "Salvador Dalí",
            year = "1931"
        )

    )


    val currentArtwork = artworks[currentArtworkIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageResourceId),
                contentDescription = currentArtwork.title,
                modifier = Modifier
                    .padding(24.dp)
                    .shadow(8.dp)
                    .border(width = 2.dp, color = Color.Gray)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }


        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentArtwork.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${currentArtwork.artist} (${currentArtwork.year})",
                fontSize = 16.sp
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous button
            Button(
                onClick = {

                    currentArtworkIndex = if (currentArtworkIndex > 0) {
                        currentArtworkIndex - 1
                    } else {
                        artworks.size - 1
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Previous")
            }

            // Next button
            Button(
                onClick = {

                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}


data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}