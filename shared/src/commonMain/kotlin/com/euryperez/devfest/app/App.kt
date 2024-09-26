package com.euryperez.devfest.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.euryperez.devfest.app.features.talkDetail.TalkDetailScreen
import com.euryperez.devfest.app.features.talks.TalkListScreen
import com.euryperez.devfest.app.theme.DevFestAppTheme
import devfest_app.shared.generated.resources.Res
import devfest_app.shared.generated.resources.app_name
import devfest_app.shared.generated.resources.back_button
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object HomeRoute

@Serializable
data class TalkRoute(val id: String)

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
) {
    DevFestAppTheme {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val canNavigateBack = remember(backStackEntry) { navController.previousBackStackEntry != null }
        val appBarTitle = if (canNavigateBack) {
            Res.string.back_button
        } else {
            Res.string.app_name
        }.let { stringResource(it) }


        Scaffold(
            topBar = {
                DevFestAppBar(
                    title = appBarTitle,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { padding ->
            DevFestAppNavHost(navController, modifier = Modifier.padding(padding))
        }
    }
}

@Composable
private fun DevFestAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        composable<HomeRoute>(
            enterTransition = { return@composable fadeIn(tween(1000)) },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) {
            TalkListScreen(onTalkClick = { talkId ->
                navController.navigate(TalkRoute(talkId))
            })
        }

        composable<TalkRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) { backStackEntry ->
            val route: TalkRoute = backStackEntry.toRoute()
            TalkDetailScreen(talkId = route.id)
        }
    }
}

@Composable
fun DevFestAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = if (canNavigateBack) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }
    )
}
