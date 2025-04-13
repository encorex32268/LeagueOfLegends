package com.lihan.leagueoflegends.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun CachedImage(
    modifier: Modifier = Modifier,
    key: Any? = null,
    onClick: () -> Unit = {},
    imageByteArray: ByteArray?=null,
    imageUrl: String?=null,
    loading: @Composable () -> Unit = {},
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current

    val imageRequest = remember(key) {
        ImageRequest.Builder(context).apply {
            if (imageByteArray != null) {
                data(imageByteArray)
                memoryCacheKey("${key}")
            } else {
                data(imageUrl)
            }
            crossfade(true)
        }.build()
    }
    SubcomposeAsyncImage(
        modifier = modifier.clickable {
            onClick()
        },
        model = imageRequest,
        contentDescription = null,
        loading = {
            loading()
        },
        contentScale = contentScale
    )

}