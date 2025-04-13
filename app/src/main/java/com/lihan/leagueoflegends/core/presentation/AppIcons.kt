package com.lihan.leagueoflegends.core.presentation

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.lihan.leagueoflegends.R



val Search: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.vector_search_24)

val Translate: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.vector_translate_24)

val Close: ImageVector
    get() = Icons.Default.Close
