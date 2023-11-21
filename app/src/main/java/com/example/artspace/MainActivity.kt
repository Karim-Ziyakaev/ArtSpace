package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = 0xFFd3a27f.toInt()
            ArtSpaceLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_palette_24),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.app_bar),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffb87333),
                    titleContentColor = Color(0xFFf0cba8)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ImageAndText()
        }
    }
}

@Composable
fun ImageAndText(
    modifier: Modifier = Modifier
) {
    var slideNumber by remember { mutableStateOf(1) }

    var imageRes: Int = R.drawable.kul_sharif
    var titleRes: Int = R.string.kul_sharif
    var descriptionRes: Int = R.string.kul_sharif_description

    when(slideNumber) {
        1 -> {
            imageRes = R.drawable.kul_sharif
            titleRes = R.string.kul_sharif
            descriptionRes = R.string.kul_sharif_description
        }
        2 -> {
            imageRes = R.drawable.kazan_kremlin
            titleRes = R.string.kremlin
            descriptionRes = R.string.kremlin_description
        }
        3 -> {
            imageRes = R.drawable.farmers_palace
            titleRes = R.string.palace
            descriptionRes = R.string.palace_description
        }
        4 -> {
            imageRes = R.drawable.old_tatar
            titleRes = R.string.old_tatar
            descriptionRes = R.string.old_tatar_description
        }
        5 -> {
            imageRes = R.drawable.kaban_lake
            titleRes = R.string.kaban_lake
            descriptionRes = R.string.kaban_lake_description
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffb87333))
    ) {
        IconButton(
            onClick = { if (slideNumber > 1) slideNumber-- else slideNumber = 5 },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                contentDescription = null
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(8f)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(20.dp, Color(0xFFf0cba8)),
                    RectangleShape
                )
                .background(Color(0xFFf0cba8))
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(titleRes),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff6e543c),
                fontSize = 24.sp
            )
            Text(
                text = stringResource(descriptionRes),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(20.dp),
                color = Color(0xff6e543c)
            )
        }
        IconButton(
            onClick = { if (slideNumber < 5) slideNumber++ else slideNumber = 1 },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}