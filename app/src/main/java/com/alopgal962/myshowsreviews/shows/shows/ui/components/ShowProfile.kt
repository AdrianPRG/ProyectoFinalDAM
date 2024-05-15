package com.alopgal962.myshowsreviews.shows.shows.ui.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.alopgal962.myshowsreviews.R
import java.lang.reflect.Modifier

@Composable
fun ReturnProfile(imageString:String):Painter {
    var painter = painterResource(id = R.drawable.user_signin)
    when (imageString) {
        "perfil1" -> {
           painter = painterResource(id = R.drawable.perfil1)
        }
        "perfil2" -> {
            painter = painterResource(id = R.drawable.perfil2)
        }
        "perfil3" -> {
            painter = painterResource(id = R.drawable.perfil3)
        }
    }
    return painter
}