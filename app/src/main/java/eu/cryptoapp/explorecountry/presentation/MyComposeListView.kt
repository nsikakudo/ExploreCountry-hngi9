package eu.cryptoapp.explorecountry.presentation

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import eu.cryptoapp.explorecountry.models.Country


class MyComposeListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    var countries = mutableStateOf<List<Country>>(emptyList())

//    var countriesGroupedByInitials = mutableStateOf<Map<String, List<Country>>>(emptyMap())

    var onClick by mutableStateOf<(Country) -> Unit>({})

    @Composable
    override fun Content() {
        MyTheme {
            val countriesGroupedByInitials =
                countries.value.groupBy { it.name.common[0].toString() }.toSortedMap()
            CountriesListView(grouped = countriesGroupedByInitials, onItemClick = onClick)
        }
    }


    @Composable
    fun CountriesListView(
        grouped: Map<String, List<Country>>,
        onItemClick: (Country) -> Unit,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp, vertical = 38.dp)
        ) {

            grouped.forEach { key, value ->
                item {
                    Text(text = key)
                }
                items(value) { item ->
                    Country(country = item)

                }

            }


        }
    }

    @Composable
    fun Country(country: Country) {
        Row(
            Modifier.clickable { onClick(country) },

            ) {
            AsyncImage(
                model = country.flags.png,
                contentDescription = "Flag",
                modifier = Modifier
                    .size(45.dp)
                    .padding(vertical = 3.dp, horizontal = 3.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = country.name.common, fontSize = 15.sp, lineHeight = 22.18.sp)

                Text(
                    text = country.capital?.firstOrNull() ?: "",
                    fontSize = 10.sp,
                    lineHeight = 22.18.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }


// ..
// ..
}

@Composable
fun MyTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorSchemecolors =
        if (!useDarkTheme) {
            LightColorsScheme
        } else {
            DarkColorsScheme
        }
    MaterialTheme(
        colorScheme = colorSchemecolors,
        content = content
    )
}


val md_theme_light_primary = Color(0xFF476810)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFC7F089)
val md_theme_light_onPrimaryContainer = Color(0xFFFFFFFF)

// ..
// ..

val md_theme_dark_primary = Color(0xFFACD370)
val md_theme_dark_onPrimary = Color(0xFF213600)
val md_theme_dark_primaryContainer = Color(0xFF324F00)
val md_theme_dark_onPrimaryContainer = Color(0xFF213600)

private val LightColorsScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
// ..
)
private val DarkColorsScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
// ..
)