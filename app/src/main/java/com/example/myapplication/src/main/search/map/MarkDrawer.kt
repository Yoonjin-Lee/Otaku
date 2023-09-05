package com.example.myapplication.src.main.search.map

import net.daum.mf.map.api.MapPOIItem

fun MarkDrawer() : MapPOIItem{
    val marker = MapPOIItem()

    marker.markerType = MapPOIItem.MarkerType.RedPin

    return marker
}