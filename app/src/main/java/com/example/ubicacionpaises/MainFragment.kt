package com.example.ubicacionpaises

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ubicacionpaises.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment(R.layout.fragment_main) {
    private val adapter = ArtistaAdapter(){ ubicacion -> navigateTo(ubicacion)}
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        if (adapter.itemCount == 0){
            loadItems()
        }
    }

    private fun loadItems() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.progress.visibility =View.VISIBLE
            val movies = withContext(Dispatchers.IO){ ArtistaProvider.getMovies() }
            adapter.artistas = artistas
            adapter.notifyDataSetChanged()
            binding.progress.visibility = View.GONE
        }
    }
    private fun navigateTo(artista: Artista) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.EXTRA_ARTISTA to artista)
        )

    }

}
private val artistas = (1..100).map {
    Artista(
        "artista $it",
        "https://loremflickr.com/240/320/artist?lock=$it")
}



