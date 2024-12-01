package br.edu.ifsp.dmo1.gerenciadortarefasmvvm.data.dao

import br.edu.ifsp.dmo1.gerenciadortarefasmvvm.data.model.Task

object TaskDao {
    private var tasks: MutableList<Task> = mutableListOf()

    fun add(task: Task) {
        tasks.add(task)
    }

    fun getAll(): List<Task> {
        return tasks.sortedBy { it.isCompleted }
    }

    fun get(id: Long): Task? {
        return tasks.stream()
            .filter{t -> t.id == id}
            .findFirst()
            .orElse(null)
    }

    // Método utilizado para atualiar o DAO
    fun update(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }

        if (index != -1) {
            tasks[index] = task
        }
    }
}