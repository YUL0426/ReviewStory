package com.example.reviewstory.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewstory.R
import com.example.reviewstory.STAMP
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_item_stamp.view.*

/* ResultFragment에서 검색 결과를 리사이클러뷰에 데이터를 보여주는 어댑터  */
class SearchAdapter(val items: ArrayList<STAMP>, val fbFirestore: FirebaseFirestore) : RecyclerView.Adapter<ItemViewHolder>() {

    /* 뷰홀더를 생성하여 반환 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //list_item_fresh 뷰 inflate
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_stamp, parent, false)
        return ItemViewHolder(rootView)
    }

    //뷰홀더에 데이터 바인딩(bindItems() 함수를 호출)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(items[position],fbFirestore)
    }

    //어댑터에서 관리할 아이템 갯수를 반환
    override fun getItemCount() = items.size
}

//뷰홀더 클래스 선언
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(stamp: STAMP?, fbFirestore: FirebaseFirestore) {
        stamp?.let {
            itemView.txt_gongpan_info.text = stamp.address
            itemView.txt_unit.text =
                "${stamp.s_name}"
            itemView.txt_min_price.text = "메세지 수: "
            itemView.txt_avg_price.text =
                "방문날짜: " + "${stamp.s_date}"
            itemView.txt_max_price.text = "발신처: " + "${stamp.user_num}"
            itemView.setOnClickListener{
                fbFirestore.collection("stamp").document("${stamp.s_num}").delete()
                Log.d("place", "stamp ${stamp.s_name} is deledte")
            }
        }
    }
}//end of ItemViewHolder