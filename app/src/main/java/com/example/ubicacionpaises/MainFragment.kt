package com.example.ubicacionpaises

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ubicacionpaises.databinding.FragmentMainBinding
import com.example.ubicacionpaises.detail.DetailFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    private val adapter = ArtistaAdapter(){ artista -> viewModel.navigateTo(artista)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) View.VISIBLE else View.GONE
            state.artistas?.let {
                adapter.artistas = state.artistas
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_ARTISTA to it)
                )
                viewModel.onNavigateDone()
            }




        }
    }



}



