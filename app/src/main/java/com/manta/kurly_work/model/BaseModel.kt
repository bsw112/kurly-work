package com.manta.kurly_work.model

import androidx.recyclerview.widget.DiffUtil

interface BaseModel<T : Any> {
    val itemCallback : DiffUtil.ItemCallback<T>
    val bindingVariableId : Int
}