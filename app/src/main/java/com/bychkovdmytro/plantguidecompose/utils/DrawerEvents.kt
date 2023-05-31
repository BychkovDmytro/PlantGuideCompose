package com.bychkovdmytro.plantguidecompose.utils

sealed class DrawerEvents{
    data class OnItemClick(val title: String, val index: Int) : DrawerEvents() {

    }
}
