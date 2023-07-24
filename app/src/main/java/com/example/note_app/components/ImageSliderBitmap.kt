package com.example.note_app.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import java.io.File


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderBitmap(imageList:List<String>){

    if (imageList.isNotEmpty()) {


        val pagerState = rememberPagerState()
        Box(
            modifier =
            Modifier

                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            Card(
                elevation = 10.dp,
                border = BorderStroke(5.dp, Color.Gray)
            ) {
                HorizontalPager(
                    pageCount = imageList.size,
                    state = pagerState,
                ) {
//                Image(painter = rememberImagePainter(data =imageList[it],
//                    builder = {
//                        scale(Scale.FILL).crossfade(true).build()
//                    }
//
//                ), contentDescription = null)
//               Image(painter = rememberAsyncImagePainter(it), contentDescription =null )
                    val path:String=imageList.reversed()[it]
                    var imgBitmap: Bitmap? = null
                    if(path!=""){
                        val imgFile = File(path)

                        // on below line we are checking if the image file exist or not.

                        if (imgFile.exists()) {
                            // on below line we are creating an image bitmap variable
                            // and adding a bitmap to it from image file.
                            imgBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                        }
                    }


                    Image(
                        painter = rememberImagePainter(data = imgBitmap),
                        contentDescription = null,

                        )
                }
            }

        }

    }
}