package eu.cryptoapp.explorecountry.presentation
import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MyPictureRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    var imageUrls = mutableStateOf<List<String>>(emptyList())

    @Composable
    override fun Content() {
        MyTheme {
            LazyRow {
                items(imageUrls.value) { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = "",
                        modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                    )
                }
            }
        }
    }

}