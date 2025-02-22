package com.jetpackcompose.workout

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.workout.ui.theme.WorkOutTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
                MyApp(windowSizeClass)

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text("Search")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}


@Composable
fun AlignBody(modifier: Modifier=Modifier, @DrawableRes drawable: Int, @StringRes text: Int,){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CardCollection(modifier: Modifier=Modifier,
                   @DrawableRes drawable: Int,@StringRes text: Int){
    Surface(
        shape=MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
                    modifier=Modifier.width(255.dp)){
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
               modifier=Modifier.size(80.dp)
            )
            Text(stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier=Modifier.padding(horizontal = 16.dp))
        }

    }
}

@Composable
fun AlignBodyRow(modifier: Modifier = Modifier) {
    val alignBodyItems = listOf(
        AlignBodyData(R.drawable.inversion2, R.string.ab1_inversion),
        AlignBodyData(R.drawable.quick_yoga, R.string.quick_yoga),
        AlignBodyData(R.drawable.streching, R.string.streching),
        AlignBodyData(R.drawable.tabata, R.string.tabata),
        AlignBodyData(R.drawable.hiit, R.string.hiit)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignBodyItems, key = { it.text }) { item ->
            AlignBody(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CollectionGrid(modifier: Modifier = Modifier) {
    val collectionCardItem = listOf(
        CollectionCardData(R.drawable.short_mantaras, R.string.mantras),
        CollectionCardData(R.drawable.nature_meditation, R.string.nature),
        CollectionCardData(R.drawable.stress, R.string.stress),
        CollectionCardData(R.drawable.self_massage, R.string.self),
        CollectionCardData(R.drawable.overwhelmed, R.string.overwhelmed),
        CollectionCardData(R.drawable.nighty_wind, R.string.nighty),
    )

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(collectionCardItem, key = { it.text }) { item ->
            CardCollection(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun HomeSection(@StringRes title:Int, modifier: Modifier=Modifier,
                content: @Composable ()->Unit){

    Column {
        Text(stringResource(title),
            style = MaterialTheme.typography.titleMedium ,
            modifier=Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp))


        content()
    }

}

@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column {
        Spacer(Modifier.padding(top = 16.dp))
        SearchBar(modifier=Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align) {
            AlignBodyRow(modifier = Modifier.padding(8.dp))
        }
        HomeSection(title = R.string.fav) {
            CollectionGrid(modifier=Modifier.padding(8.dp))
        }
        Spacer(Modifier.padding(top = 16.dp))
    }
}

@Composable
fun CustomNavigationBar(modifier: Modifier=Modifier){
    NavigationBar(modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text("Profile")
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MyAppScaffold(modifier: Modifier=Modifier){
    Scaffold(
        bottomBar = { CustomNavigationBar() }
    ) { padding->
        HomeScreen(Modifier.padding(padding))
    }

}

@Composable
fun CustomNavigationRail(modifier: Modifier=Modifier){
    NavigationRail(modifier=modifier.padding(start=8.dp,top=8.dp),
        containerColor = MaterialTheme.colorScheme.background) {
        Column (
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Home")
                },
                selected = true,
                onClick = {}
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Profile")
                },
                selected = false,
                onClick = {}
            )
        }
        }
}

@Composable
fun Landscape(modifier: Modifier=Modifier){
    Surface(color=MaterialTheme.colorScheme.background) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CustomNavigationRail()

            // Make HomeScreen scrollable
            Column(
                modifier = Modifier
                    .weight(1f) // Allow HomeScreen to take available space
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()) // Enable scrolling
            ) {
                HomeScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MyApp(windowSize: WindowSizeClass){
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MyAppScaffold()
        }
        WindowWidthSizeClass.Medium -> {
            // Handling medium size properly
            Landscape()
        }
        WindowWidthSizeClass.Expanded -> {
            Landscape()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun LandscapePreview(){
    WorkOutTheme {
        Landscape()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview(){
    WorkOutTheme {
        CustomNavigationRail()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun MyAppScaffoldPreview(){
    WorkOutTheme {
        MyAppScaffold()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationPreview(){
    WorkOutTheme {
        CustomNavigationBar()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeScreenPreview(){
    WorkOutTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    WorkOutTheme {
        HomeSection(title = R.string.align) {
            AlignBodyRow(modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor =0xFFF5F0EE )
@Composable
fun CollectionGridPreview(){
    WorkOutTheme {
        CollectionGrid()
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignBodyRowPreview(){
    WorkOutTheme {
        AlignBodyRow(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun CardCollectionPreview(){
    WorkOutTheme {
        CardCollection(drawable = R.drawable.nature_meditation,
            text=R.string.nature,
            modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignBodyPreview(){
    WorkOutTheme {
        AlignBody(text = R.string.ab1_inversion, drawable = R.drawable.inversion2
            , modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    WorkOutTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkOutTheme {
        Greeting("Android")
    }
}

