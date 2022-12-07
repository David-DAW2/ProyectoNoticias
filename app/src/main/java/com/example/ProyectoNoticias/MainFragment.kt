package com.example.ProyectoNoticias

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ProyectoNoticias.databinding.FragmentMainBinding
import com.example.ProyectoNoticias.detail.DetailFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels{ MainViewModelFactory(getString(R.string.api_key))}

    private lateinit var binding: FragmentMainBinding

    private val adapter = NoticiaAdapter(){ artista -> viewModel.navigateTo(artista)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Noticias Internacionales"

        
       viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) View.VISIBLE else View.GONE
            state.noticias?.let {
                adapter.noticias = state.noticias
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_NOTICIA to it)
                )
                viewModel.onNavigateDone()
            }



        }


    }



}



