package com.example.myreminders_garciadopcio

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
class NotesViewModel : ViewModel() {
    val notesList = mutableStateListOf<Note>()
    private val db = Firebase.firestore
    private val notesCollection = db.collection("notes")

    // Agrega una nota a la lista
    fun addNote(note: Note) {

        val newNote = hashMapOf(
            "id" to note.id,
            "title" to note.title,
            "description" to note.description,
            "state" to note.state
        )

        notesCollection
            .add(newNote)
            .addOnSuccessListener { documentReference ->
                // Manejar el éxito, por ejemplo, guardar el ID del documento
                // o actualizar la interfaz de usuario
                addNoteToLocalList()
                Log.d("Firestore", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                // Manejar el error
                Log.w("Firestore", "Error adding document", e)
            }
    }



    // Elimina una nota de la lista y de Firestore
    fun deleteNote(note: Note) {
        notesCollection.document(note.id)
            .delete()
            .addOnSuccessListener {
                // Manejar el éxito, por ejemplo, actualizar la UI o el estado local
                Log.d("Firestore", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                // Manejar el error
                Log.w("Firestore", "Error deleting document", e)
            }
    }

    fun addNoteToLocalList(){
        notesList.clear()
        notesCollection.get()
            .addOnSuccessListener { notes ->

                for (note in notes) {
                    val title = note.getString("title") ?: ""
                    val description = note.getString("description") ?: ""
                    val state = note.getBoolean("state") ?: false

                    Log.d("Firestore", "Title: $title, Description: $description, State: $state")


                    val newNote = Note( note.id,title, description, state)

                    notesList.add(newNote)
                }
                //Success message
                Log.d("Firestore", "Products obtained successfully")
            }
            .addOnFailureListener { e ->
                //Failure message
                Log.e("Firestore", "Failed to retrieve products", e)
            }
    }


    fun updateNoteTrue(note: Note) {
        val noteRef = notesCollection.document(note.id)

        // Actualiza el estado en Firestore
        noteRef
            .update("state", true)
            .addOnSuccessListener {
                // Manejar el éxito, por ejemplo, actualizar la UI o el estado local
                Log.d("Firestore", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                // Manejar el error
                Log.w("Firestore", "Error updating document", e)
            }

        // Actualiza el estado en la lista local
        val updatedNote = note.copy(state = true)
        val noteIndex = notesList.indexOf(note)

        if (noteIndex != -1) {
            notesList[noteIndex] = updatedNote
        }

        //Success message
        Log.d("Firestore", "Note state updated successfully")
    }

    fun updateNoteFalse(note: Note) {
        val noteRef = notesCollection.document(note.id)

        // Actualiza el estado en Firestore
        noteRef
            .update("state", false)
            .addOnSuccessListener {
                // Manejar el éxito, por ejemplo, actualizar la UI o el estado local
                Log.d("Firestore", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                // Manejar el error
                Log.w("Firestore", "Error updating document", e)
            }

        // Actualiza el estado en la lista local
        val updatedNote = note.copy(state = false)
        val noteIndex = notesList.indexOf(note)

        if (noteIndex != -1) {
            notesList[noteIndex] = updatedNote
        }

        //Success message
        Log.d("Firestore", "Note state updated successfully")
    }
    private fun removeNoteFromLocalList(note: Note) {
        notesList.remove(note)
    }

    fun loadAllNotes() {
        viewModelScope.launch {
            try {
                val allNotes = db.collection("notes")
                    .get()
                    .await()
                    .documents
                    .mapNotNull { document ->
                        //document.toObject(Note::class.java)?.apply { id = document.id }
                    }
            } catch (e: Exception) {
                Log.w("Firestore", "Error loading all notes", e)
            }
        }
    }
}


data class Note(
    var id: String,
    val title: String = "",
    val description: String = "",
    val state: Boolean = false
)

