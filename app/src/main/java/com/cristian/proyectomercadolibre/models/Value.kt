package com.cristian.proyectomercadolibre.models

data class Value(
    var id: String,
    var name: String,
    var struct: Struct,
    var source: Object,
    var path_from_root: List<PathFromRoot>,
    var results: Int,
)