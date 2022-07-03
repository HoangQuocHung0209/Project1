package team.tiktok.tiktokapp.fragments.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import team.tiktok.tiktokapp.R
import team.tiktok.tiktokapp.adapter.VideoAdapter
import team.tiktok.tiktokapp.data.Video
import team.tiktok.tiktokapp.databinding.FragmentHomeBinding
import team.tiktok.tiktokapp.databinding.ItemVideoBinding


class HomeFM : Fragment() ,VideoAdapter.OnClickItemInRecyclerView{
   lateinit var binding:FragmentHomeBinding
    private lateinit var adapter :VideoAdapter
    lateinit var mediaController: MediaController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        loadData()
        return binding.root
    }


    private fun loadData() {
        val mDataBase = Firebase.database.getReference("videos")
        val options = FirebaseRecyclerOptions.Builder<Video>()
            .setQuery(mDataBase,Video::class.java)
            .build()
//        adapter = VideoAdapter(options, VideoAdapter.ClickItemListener {})
        adapter = VideoAdapter(options)
        binding.vpHome.adapter = adapter
        adapter.setOnClickItem(this)
    }

    override fun onStart() {
        super.onStart()
        var handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            binding.wait.visibility = View.GONE
            adapter.startListening()
        },500)
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    override fun onItemClick(itemVideoBinding: ItemVideoBinding, view: View) {
        val id = view.id
        if (id==R.id.civUser){
            findNavController().navigate(R.id.action_homeFM_to_detailUserFM)
        }
        if (id==R.id.tvFollowing){
            findNavController().navigate(R.id.action_homeFM_to_followingFM)
            itemVideoBinding.tvFollowing.setTextColor(ContextCompat.getColor(view.context,R.color.white))


        }
        if (id==R.id.ivComment){
            findNavController().navigate(R.id.action_homeFM_to_commentBottomSheetFM)
        }
        if (id==R.id.ivSave){
            Log.e("Following","failure")
            Log.d("Following","success")
            Toast.makeText(requireContext(),"save",Toast.LENGTH_SHORT).show()
        }
        if (id==R.id.ivShare){
            Log.e("Following","failure")
            Log.d("Following","success")
            Toast.makeText(requireContext(),"share",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFM_to_shareBottomSheetFM)
        }
    }


}