package id.utdi.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artPieces = remember { generateArtPieces() }
    var selectedArtPiece by remember { mutableStateOf(artPieces[0]) }
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtPieceWithButtonAndImage(selectedArtPiece)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    currentIndex = (currentIndex - 1 + artPieces.size) % artPieces.size
                    selectedArtPiece = artPieces[currentIndex]
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Previous", fontSize = 15.sp)
            }

            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % artPieces.size
                    selectedArtPiece = artPieces[currentIndex]
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Next", fontSize = 15.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(artPieces) { artPiece ->
                ArtPieceListItem(artPiece) {
                    selectedArtPiece = artPiece
                }
            }
        }
    }
}

@Composable
fun ArtPieceWithButtonAndImage(artPiece: ArtPiece) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artPiece.imageResId),
            contentDescription = artPiece.name,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        )

        Text(
            text = artPiece.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = artPiece.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Tambahkan aksi ketika tombol diklik */ },
        ) {
            Text(text = "Pilih antara Next atau Previous", fontSize = 24.sp)
        }
    }
}

@Composable
fun ArtPieceListItem(artPiece: ArtPiece, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = artPiece.imageResId),
            contentDescription = artPiece.name,
            modifier = Modifier.size(40.dp))
    }

    Spacer(modifier = Modifier.width(16.dp))

    Column {
        Text(
            text = artPiece.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Text(
            text = artPiece.description,
            fontSize = 16.sp
        )
    }
}


data class ArtPiece(val id: Int, val name: String, val description: String, val imageResId: Int)

fun generateArtPieces(): List<ArtPiece> {
    return listOf(
        ArtPiece(1, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(2, "Gunung", "Pemandangan gunung", R.drawable.my_icon2),
        ArtPiece(3, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(4, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(5, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(6, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(7, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(8, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(9, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(10, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        ArtPiece(11, "Danau", "Pemandangan danau", R.drawable.my_icon1),
        // Tambahkan art pieces lainnya di sini
    )
}







