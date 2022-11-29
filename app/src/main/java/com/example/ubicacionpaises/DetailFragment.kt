package com.example.ubicacionpaises

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ubicacionpaises.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    companion object {
        const val EXTRA_ARTISTA= "DetailActivity:Ubicacion"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val artista = arguments?.getParcelable<Artista>(EXTRA_ARTISTA) ?: throw
        IllegalStateException()


        if (artista != null) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = artista.nombre
            binding.imagen.loadUrl(artista.urlImagen)
        }

    }


}