package eu.cryptoapp.explorecountry.presentation
import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MyPictureRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    var imageUrls = mutableStateOf<List<String>>(emptyList())

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        MyTheme {
            val pagerState = rememberPagerState()
            val pages = imageUrls.value

            Box(modifier = Modifier
                .size(height = 190.dp, width = 400.dp)
                .clip(shape = RoundedCornerShape(8.dp))){
                HorizontalPager(
                    count = pages.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) { page ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        Surface(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = pages[page],
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        }


                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Color.White,
                    inactiveColor = Color.LightGray,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)
                )
            }

        }
    }

}
