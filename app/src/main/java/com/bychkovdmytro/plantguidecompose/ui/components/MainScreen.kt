package com.bychkovdmytro.plantguidecompose.ui.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.bychkovdmytro.plantguidecompose.utils.DrawerEvents
import com.bychkovdmytro.plantguidecompose.utils.IdArrayList
import com.bychkovdmytro.plantguidecompose.utils.ListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context, onClick: (ListItem) -> Unit) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val mainList = remember {
        mutableStateOf(getListItemsByIndex(0, context))
    }
    val topBarTitle = remember {
        mutableStateOf("Грибы")
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(title = topBarTitle.value, scaffoldState)
        }, drawerContent = {
            DrawerMenu() { event ->
                when (event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainList.value =
                            getListItemsByIndex(event.index, context)
                    }
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(mainList.value) { itemLazyColumn ->
                MainListItem(item = itemLazyColumn){ listItem ->
                    onClick(listItem)
                }
            }
        }
    }
}

private fun getListItemsByIndex(index: Int, context: Context): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                itemArray[0],
                itemArray[1],
                itemArray[2],
            )
        )
    }
    return list
}