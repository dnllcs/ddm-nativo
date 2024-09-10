package com.example.sabadoletivonativo.domain.interfaces

import com.example.sabadoletivonativo.domain.entities.Vertebrado

interface IVertebradoDao {
    fun adicionar(vertebrado: Vertebrado?)
    fun ListarTodos(): List<Vertebrado?>?
    fun update(vertebrado: Vertebrado?)
    fun deletarPorId(id: Int?)
    fun recuperarPorId(id: Int?): Vertebrado?
}