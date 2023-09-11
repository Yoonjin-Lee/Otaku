package com.example.myapplication.src.main.search.map

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.databinding.ActivityMapBinding
import com.example.myapplication.src.main.search.post.PostActivity
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate),
    MapView.MapViewEventListener, MapView.POIItemEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding.mapTxtTitle.text = intent.getStringExtra("title")

        binding.mapTxtTitle.text = "주술회전"

        binding.mapBtnClose.setOnClickListener {
            this.finish()
        }

        val postList: ArrayList<PostData> = arrayListOf()

        postList.apply {
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
        }

        val postRVAdapter = PostRVAdapter(postList)
        binding.mapRv.adapter = postRVAdapter
        binding.mapRv.layoutManager = LinearLayoutManager(this)

        binding.mapKakaoMap.setMapViewEventListener(this)
        binding.mapKakaoMap.setPOIItemEventListener(this)

        //마커 등록
        binding.mapKakaoMap.addPOIItem(
            makeMarker(
                PostData(
                    R.drawable.ic_launcher_background,
                    "고죠 사토루 생카",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
        )
    }

    class CustomBalloonAdapter(layoutInflater: LayoutInflater) : CalloutBalloonAdapter {
        val customBalloonAdapter: View = layoutInflater.inflate(R.layout.map_balloon, null)
        val title = customBalloonAdapter.findViewById<TextView>(R.id.balloon_title)
        val name = customBalloonAdapter.findViewById<TextView>(R.id.balloon_name)

        override fun getCalloutBalloon(p0: MapPOIItem?): View {
            title.text = p0?.itemName
//            name.text = postList.get(p0!!.tag).name
            return customBalloonAdapter
        }

        override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
            title.text = p0?.itemName
//            name.text = postList.get(p0!!.tag).name
            return customBalloonAdapter
        }
    }

    //MapViewEventListener
    override fun onMapViewInitialized(p0: MapView?) {
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
    }

    //POIItemSelected
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
    }

    //말풍선 클릭 시
    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }

    //POIItem 등록
    fun makeMarker(postData: PostData): MapPOIItem {
        val marker = MapPOIItem()
        marker.apply {
            itemName = postData.title
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)
            isCustomImageAutoscale = true
            markerType = MapPOIItem.MarkerType.RedPin
        }
        return marker
    }
}