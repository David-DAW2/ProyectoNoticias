package com.example.ubicacionpaises.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ubicacionpaises.R
import com.example.ubicacionpaises.databinding.FragmentDetailBinding
import com.example.ubicacionpaises.loadUrl
import com.example.ubicacionpaises.model.Artista

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Artista>(EXTRA_ARTISTA)!!)
    }

    companion object {
        const val EXTRA_ARTISTA= "DetailActivity:Artista"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)


        viewModel.artista.observe(viewLifecycleOwner){ artista ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = artista.nombre
            artista.urlImagen?.let { binding.imagen.loadUrl(it)
                binding.contenido.text=artista.contenido
            binding.autor.text="Autor: "+artista.autor}
        }



    }


}