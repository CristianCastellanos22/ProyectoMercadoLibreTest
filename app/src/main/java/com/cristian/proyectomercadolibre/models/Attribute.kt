package com.cristian.proyectomercadolibre.models

data class Attribute(
    var value_id: String,
    var id: String,
    var name: String,
    var value_name: String,
    var value_struct: ValueStruct,
    var values: List<Value>,
    var attribute_group_id: String,
    var attribute_group_name: String,
    var source: Object,
)