package com.euryperez.devfest.app.data.talks

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TalksRepository(
    fireStore: FirebaseFirestore = Firebase.firestore,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private val talksCollection = fireStore
        .collection("events")
        .document("gdg-ponferrada")
        .collection("talks")

    fun getAllTalksFlow() = talksCollection.snapshots
        .map { it.documents.toTalkList() }
        .conflate()
        .flowOn(defaultDispatcher)

    fun getTalkFlow(id: String) = talksCollection.document(id).snapshots.map { it.toTalk() }
}

object TalksRepositoryProvider {
    val talksRepository = TalksRepository()
}