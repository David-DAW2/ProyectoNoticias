package com.example.ubicacionpaises.model


object ArtistaProvider {
        fun getArtistas(): List<Artista> {
            Thread.sleep(2000)
            return (1..100).map {Artista("artista $it",
                "https://loremflickr.com/240/320/paris?lock=$it"
                )
            }
        }
    }
