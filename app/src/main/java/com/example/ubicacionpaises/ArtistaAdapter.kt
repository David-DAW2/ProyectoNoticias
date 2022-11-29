package com.example.ubicacionpaises

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ubicacionpaises.databinding.ArtistaviewBinding


class ArtistaAdapter(val listener: (Artista) -> Unit):
    RecyclerView.Adapter<ArtistaAdapter.ViewHolder>() {

    var artistas = emptyList<Artista>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.artistaview, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artista=artistas[position]
        holder.bind(artista)
        holder.itemView.setOnClickListener {
            listener(artista)
        }
    }
    override fun getItemCount(): Int {
        return artistas.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding =ArtistaviewBinding.bind(view)
        fun bind(artista: Artista){
            binding.nombre.text = artista.nombre

            binding.foto.loadUrl(artista.urlImagen)
        }
    }
}