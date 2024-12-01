package br.edu.ifsp.dmo1.gerenciadortarefasmvvm.data.model

class Task(var description: String, var isCompleted: Boolean) {

    private companion object {
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init {
        id = lastId
        lastId += 1L
    }
}