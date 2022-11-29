package com.example.ubicacionpaises


    object ArtistaProvider {
        fun getMovies(): List<Artista> {
            Thread.sleep(2000)
            return (1..100).map {
                Artista(
                    "ubicacion $it",
                    "https://loremflickr.com/240/320/paris?lock=$it"
                )
            }
        }
    }
