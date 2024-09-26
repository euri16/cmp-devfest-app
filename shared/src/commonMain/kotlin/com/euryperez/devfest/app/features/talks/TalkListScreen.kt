package com.euryperez.devfest.app.features.talks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.euryperez.devfest.app.data.talks.Talk

@Composable
fun TalkListScreen(
    viewModel: TalkListViewModel = viewModel { TalkListViewModel() },
    onTalkClick: (String) -> Unit
) {
    val state by viewModel.viewState.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "All Talks",
                style = MaterialTheme.typography.h2
            )
        }

        items(state.talks) { talk ->
            TalkView(talk, onTalkClick = onTalkClick)
        }
    }
}

@Composable
private fun TalkView(
    talk: Talk,
    onTalkClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth().clickable { onTalkClick(talk.id) }) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .height(IntrinsicSize.Min)
        ) {
            Image(
                modifier = Modifier.width(100.dp).fillMaxHeight().clip(MaterialTheme.shapes.small),
                painter = rememberAsyncImagePainter(talk.speaker.imageUrl),
                contentDescription = talk.speaker.name,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = talk.title,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = talk.speaker.name,
                    style = MaterialTheme.typography.caption
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = talk.description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
