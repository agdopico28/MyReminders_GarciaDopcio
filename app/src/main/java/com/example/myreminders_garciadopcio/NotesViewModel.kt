package com.example.myreminders_garciadopcio

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
class NotesViewModel : ViewModel() {
    //Mutable list of notes that will be used to locally store notes retrieved from Firestore
    val notesList = mutableStateListOf<Note>()

    // Reference to the Firestore instance and the "notes" collection
    private val db = Firebase.firestore
    private val notesCollection = db.collection("notes")

    fun addNote(note: Note) {
    /**Add a note to the list*/

        val newNote = hashMapOf(
            "title" to note.title,
            "description" to note.description,
            "state" to note.state
        )

        notesCollection
            .add(newNote)
            .addOnSuccessListener { documentReference ->
                //After adding, update the local list
                addNoteToLocalList()
                Log.d("Firestore", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                // Handle the error
                Log.w("Firestore", "Error adding document", e)
            }
    }


    fun deleteNote(note: Note) {
    /**Delete a note from the list and from Firestore*/

        // Perform a query to find the document based on the title and description
        notesCollection.whereEqualTo("title", note.title)
            .whereEqualTo("description", note.description)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    // Delete the document found in Firestore
                    notesCollection.document(document.id)
                        .delete()
                        .addOnSuccessListener {
                            // After clearing, update the local list
                            addNoteToLocalList()

                            Log.d("Firestore", "DocumentSnapshot successfully deleted!")
                        }
                        .addOnFailureListener { e ->
                            // Handle the error
                            Log.w("Firestore", "Error deleting document", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                // Handle the error
                Log.w("Firestore", "Error querying documents", e)
            }
    }

    fun addNoteToLocalList(){
    /** Retrieve all notes from Firestore and update the local list*/

        notesList.clear()
        notesCollection.get()

            .addOnSuccessListener { notes ->

                for (note in notes) {
                    // Get the document data from Firestore and create an instance of the Note class
                    val title = note.getString("title") ?: ""
                    val description = note.getString("description") ?: ""
                    val state = note.getBoolean("state") ?: false

                    //Log the data obtained from Firestore
                    Log.d("Firestore", "Title: $title, Description: $description, State: $state")

                    // Create a new note and add it to the local list
                    val newNote = Note( title, description, state)
                    notesList.add(newNote)
                }
                //Success message
                Log.d("Firestore", "Products obtained successfully")
            }
            .addOnFailureListener { e ->
                // Handle the error
                Log.e("Firestore", "Failed to retrieve products", e)
            }
    }


    fun updateNoteTrue(note: Note) {
    /**Updates the status of a note to True in Firestore and the local list*/
        updateNoteState(note, true)
    }

    fun updateNoteFalse(note: Note) {
    /**Update the status of a note to False in Firestore and the local list*/
        updateNoteState(note, false)
    }

    private fun updateNoteState(note: Note, newState: Boolean) {
    /**Private function to update the status of a note*/

        // Perform a query to find the document based on the title and description
        notesCollection.whereEqualTo("title", note.title)
            .whereEqualTo("description", note.description)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val noteRef = notesCollection.document(document.id)

                    // Update the state in Firestore
                    noteRef
                        .update("state", newState)
                        .addOnSuccessListener {
                            Log.d("Firestore", "DocumentSnapshot successfully updated!")
                        }
                        .addOnFailureListener { e ->
                            // Handle the error
                            Log.w("Firestore", "Error updating document", e)
                        }

                    // Update the state in the local list
                    val updatedNote = note.copy(state = newState)
                    val noteIndex = notesList.indexOf(note)

                    if (noteIndex != -1) {
                        notesList[noteIndex] = updatedNote
                    }

                    // Success message
                    Log.d("Firestore", "Note state updated successfully")
                }
            }
            .addOnFailureListener { e ->
                // Handle the error
                Log.w("Firestore", "Error querying documents", e)
            }
    }



}



