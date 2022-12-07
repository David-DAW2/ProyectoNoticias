package com.example.ProyectoNoticias

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ProyectoNoticias.databinding.NoticiaviewBinding
import com.example.ProyectoNoticias.model.Noticia


class NoticiaAdapter(val listener: (Noticia) -> Unit):
    RecyclerView.Adapter<NoticiaAdapter.ViewHolder>() {

    var noticias = emptyList<Noticia>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.noticiaview, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artista=noticias[position]
        holder.bind(artista)
        holder.itemView.setOnClickListener {
            listener(artista)
        }
    }
    override fun getItemCount(): Int {
        return noticias.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = NoticiaviewBinding.bind(view)
        fun bind(noticia: Noticia){
            binding.nombre.text = noticia.nombre

            binding.foto.loadUrl(noticia.urlImagen)
        }
    }
}