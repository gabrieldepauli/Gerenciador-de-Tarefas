package br.edu.ifsp.dmo1.gerenciadortarefasmvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo1.gerenciadortarefasmvvm.data.dao.TaskDao
import br.edu.ifsp.dmo1.gerenciadortarefasmvvm.data.model.Task

class MainViewModel : ViewModel() {
    private val dao = TaskDao

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private val _insertTask = MutableLiveData<Boolean>()
    val insertTask: LiveData<Boolean> = _insertTask

    private val _updateTask = MutableLiveData<Boolean>()
    val updateTask: LiveData<Boolean> = _updateTask

    private var currentFilter: String = "Todas as atividades"

    init {
        mock()
        load()
    }

    fun insertTask(description: String) {
        val task = Task(description, false)
        dao.add(task)
        _insertTask.value = true
        load()
    }

    fun updateTask(task: Task) {
        task.isCompleted = !task.isCompleted
        dao.update(task) // Atualiza a tarefa no DAO
        _updateTask.value = true
        load()
    }

    fun filterTasks(filter: String) {
        currentFilter = filter
        load()
    }

    private fun mock() {
        dao.add(Task("Arrumar a Cama", false))
        dao.add(Task("Retirar o lixo", false))
        dao.add(Task("Fazer trabalho de DMO1", true))
    }

    private fun load() {
        val allTasks = dao.getAll()

        _tasks.value = when (currentFilter) {
            "Todas as atividades" -> allTasks
            "Atividades pendentes" -> allTasks.filter { !it.isCompleted }
            "Atividades concluídas" -> allTasks.filter { it.isCompleted }
            else -> allTasks
        }
    }
}
