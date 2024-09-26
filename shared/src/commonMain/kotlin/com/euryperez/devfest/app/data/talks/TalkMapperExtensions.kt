package com.euryperez.devfest.app.data.talks

import dev.gitlive.firebase.firestore.DocumentSnapshot

internal fun List<DocumentSnapshot>.toTalkList() = map { it.toTalk() }

internal fun DocumentSnapshot.toTalk() = with(data<Talk>()) {
    Talk(
        id = this@toTalk.id,
        title = title,
        description = description,
        speaker = speaker,
    )
}