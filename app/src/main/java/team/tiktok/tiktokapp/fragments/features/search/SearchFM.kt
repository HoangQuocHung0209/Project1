package team.tiktok.tiktokapp.fragments.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import team.tiktok.tiktokapp.R
import team.tiktok.tiktokapp.adapter.VideoAdapter
import team.tiktok.tiktokapp.data.Video
import team.tiktok.tiktokapp.databinding.FragmentExploreBinding
import team.tiktok.tiktokapp.databinding.FragmentHomeBinding
import team.tiktok.tiktokapp.databinding.FragmentProfileBinding
import team.tiktok.tiktokapp.databinding.FragmentSearchBinding


class SearchFM : Fragment() {
   lateinit var binding:FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        clickButton()
        return binding.root
    }

    private fun clickButton() {
        binding.ivBack.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_searchFM_to_homeFM)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding == null
    }
}