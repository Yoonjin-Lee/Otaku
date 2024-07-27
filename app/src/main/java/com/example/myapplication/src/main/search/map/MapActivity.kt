package com.example.myapplication.src.main.search.map

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.databinding.ActivityMapBinding
import com.example.myapplication.src.main.home.add.addInfo.model.CategoryData
import com.example.myapplication.src.main.home.add.addInfo.model.SubjectData
import com.example.myapplication.src.main.search.post.PostActivity
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.json.JSONObject

class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate),
    MapView.MapViewEventListener, MapView.POIItemEventListener, MapActivityView {

    private val postList: ArrayList<PostData> = arrayListOf()
    private val postRVAdapter = PostRVAdapter(postList, this)
    // 위치와 이벤트 내용 일치를 위한 리스트
    private var mapList : ArrayList<MapPOIItem> = arrayListOf()
    // 위치와 이벤트 내용 일치를 위한 인덱스
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subjectId = intent.getIntExtra("subjectId", 0)
        MapService(this).tryGetList(subjectId)

        // 화면 유입 경로에 따른 지도 시점 설정
        // 카테고리로 들어 왔을 경우
        if (intent.getStringExtra("address").isNullOrEmpty()){
            binding.mapKakaoMap.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(37.539182674872, 127.074711902268),
                true)
            Log.d("Retrofit", "초기 위치 없음")
        }else{ // 특정 이벤트의 위치를 보고 싶은 경우
            // intent로 받은 주소 값으로 시점 설정
            MapService(this).tryGetFirstLocation(
                "KakaoAK b091b92d0d3d2a989a7759df2fa60ec5",
                intent.getStringExtra("address").toString())
        }

        binding.mapTxtTitle.text = intent.getStringExtra("title")


        binding.mapBtnClose.setOnClickListener {
            this.finish()
        }

        binding.mapRv.adapter = postRVAdapter
        binding.mapRv.layoutManager = LinearLayoutManager(this)

        binding.mapKakaoMap.setMapViewEventListener(this)
        binding.mapKakaoMap.setPOIItemEventListener(this)

        binding.mapKakaoMap.zoomIn(true)
        binding.mapKakaoMap.zoomOut(true)

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

    // 말풍선 클릭 시
    // 이벤트 페이지로 이동
    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("eventId", p1?.tag)
        startActivity(intent)
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }

    //POIItem 등록
    fun makeMarker(postData: String, eventId: Int, latitude: Double, longitude: Double): MapPOIItem {
        val marker = MapPOIItem()
        marker.apply {
            itemName = postData
            mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            isCustomImageAutoscale = true
            markerType = MapPOIItem.MarkerType.RedPin
            tag = eventId
        }
        Log.d("MapMarker", "마커 등록 완료")
        return marker
    }

    // 이벤트 리스트 수신
    override fun onGetListSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        val array = data.getJSONArray("content")
        Log.d("Retrofit", "$array")
        for(i in 0 until array.length()){
            val obj = array.getJSONObject(i)
            var donation = false
            if (obj.getString("status") != "UNDEFINED"){
                donation = true
            }
            val postData = PostData(
                obj.getInt("eventId"),
                obj.getString("featuredImage"),
                obj.getString("name"),
                obj.getString("xNickname"),
                obj.getString("xId"),
                obj.getString("subjectName"),
                obj.getBoolean("wishList"),
                donation
            )
            postList.add(postData)

            val mapPOIItem = MapPOIItem()
            mapPOIItem.apply {
                itemName = postData.title
                isCustomImageAutoscale = true
                markerType = MapPOIItem.MarkerType.RedPin
                tag = postData.eventId
            }
            mapList.add(mapPOIItem)

            //마커 표시
            //위도 경도 받고
            if(obj.getString("address") != "null"){
                MapService(this).tryGetLocation("KakaoAK b091b92d0d3d2a989a7759df2fa60ec5", obj.getString("address"))
            }else{
                Log.d("Retrofit", "주소 데이터 없음")
            }
        }
        postRVAdapter.notifyDataSetChanged()
    }

    override fun onGetListFail(message: String) {
        Log.d("Retrofit", message)
    }

    // 이벤트 주소의 위도, 경도로 마커 표시하기
    override fun onGetLocationSuccess(response: String) {
        //데이터 받기
        val data = JSONObject(response).getJSONArray("documents")
        // 데이터가 있으면
        if (data.length() > 0){
            val obj = data.getJSONObject(0)
            val latitude = obj.getDouble("y")
            val longitude = obj.getDouble("x")
            Log.d("Retrofit", "$latitude $longitude")
            // index 순서의 MapPOIItem를 가져옴
            val marker = mapList[index]
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            // 지도에 표시
            binding.mapKakaoMap.addPOIItem(marker)
            index += 1
        } else{
            showToast("옳지 않은 주소입니다.")
        }
    }

    override fun onGetLocationFail(message: String) {
        Log.d("Retrofit", message)
    }

    // 초기 시점 위치 설정
    override fun onGetFirstLocationSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("documents")
        if (data.length() > 0){
            val obj = data.getJSONObject(0)
            val firstLatitude = obj.getDouble("y")
            val firstLongitude = obj.getDouble("x")
            Log.d("Retrofit", "위치 변경")
            binding.mapKakaoMap.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(firstLatitude, firstLongitude), true)
        } else{
            showToast("옳지 않은 주소입니다.")
        }
    }

    override fun onGetFirstLocationFail(message: String) {
        Log.d("Retrofit", message)
    }
}