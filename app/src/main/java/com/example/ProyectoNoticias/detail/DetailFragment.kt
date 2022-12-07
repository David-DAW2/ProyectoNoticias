package com.example.ProyectoNoticias.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ProyectoNoticias.R
import com.example.ProyectoNoticias.databinding.FragmentDetailBinding
import com.example.ProyectoNoticias.loadUrl
import com.example.ProyectoNoticias.model.Noticia


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Noticia>(EXTRA_NOTICIA)!!)
    }

    companion object {
        const val EXTRA_NOTICIA= "DetailActivity:Artista"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)


        viewModel.noticia.observe(viewLifecycleOwner){ noticia ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = noticia.nombre
            noticia.urlImagen?.let { binding.imagen.loadUrl(it)
                binding.contenido.text=noticia.contenido
            binding.autor.text="Autor: "+noticia.autor}
        }



    }


}