package com.euryperez.devfest.app.features.talkDetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.euryperez.devfest.app.data.talks.Speaker
import com.euryperez.devfest.app.data.talks.Talk
import com.euryperez.devfest.app.theme.DevFestAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TalkDetailScreen(
    talkId: String,
    viewModel: TalkDetailViewModel = viewModel { TalkDetailViewModel(talkId) }
) {
    val viewState by viewModel.viewState.collectAsState()

    viewState.talk?.let {
        TalkDetailScreen(it)
    }
}

@Composable
private fun TalkDetailScreen(
    talk: Talk,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SpeakerCard(talk)
        }

        item {
            TalkInfoCard(talk)
        }
    }
}

@Composable
private fun TalkInfoCard(
    talk: Talk,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = talk.title,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = talk.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun SpeakerCard(
    talk: Talk,
    modifier: Modifier = Modifier
) {
    var isBioExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isBioExpanded = isBioExpanded.not()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(120.dp).clip(MaterialTheme.shapes.medium),
                painter = rememberAsyncImagePainter(talk.speaker.imageUrl),
                contentDescription = talk.speaker.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = talk.speaker.name,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = talk.speaker.headline,
                style = MaterialTheme.typography.caption,
            )

            Spacer(modifier = Modifier.height(16.dp))

            SpeakerBio(talk, isBioExpanded)
        }
    }
}

@Composable
private fun SpeakerBio(
    talk: Talk,
    isBioExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        isBioExpanded,
        transitionSpec = {
            (slideInVertically() + fadeIn()).togetherWith(slideOutVertically() + fadeOut())
        },
        modifier = modifier
    ) { isExpanded ->
        if (isExpanded) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = talk.speaker.bio,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(8.dp))

                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
            }
        } else {
            Icon(
                Icons.Default.KeyboardArrowDown, contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun TalkDetailScreenPreview() {
    DevFestAppTheme {
        TalkDetailScreen(
            Talk(
                id = "1",
                title = "Title",
                description = "Description",
                speaker = Speaker(
                    name = "Speaker",
                    imageUrl = "https://picsum.photos/200/300",
                    headline = "Headline",
                    bio = "Bio"
                )
            )
        )
    }
}