package app.lawnchair.lawnicons.ui.destination

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.lawnchair.lawnicons.BuildConfig
import app.lawnchair.lawnicons.R
import app.lawnchair.lawnicons.ui.component.Card
import app.lawnchair.lawnicons.ui.component.ClickableIcon
import app.lawnchair.lawnicons.ui.component.ContributorRow
import app.lawnchair.lawnicons.ui.component.SimpleListRow
import app.lawnchair.lawnicons.ui.component.TopBarWithInsets
import app.lawnchair.lawnicons.ui.util.Contributor
import app.lawnchair.lawnicons.ui.util.Destinations
import app.lawnchair.lawnicons.util.appIcon

private val getUpdatesAbout = listOf(
    Contributor(
        name = "Puffercat:ROM Telegram Channel (Preferred)",
        photoUrl = "https://cdn.puffercat.xyz/c/puffericons/assets/pufferrom.png",
        socialUrl = "https://pufferrom.t.me",
    ),
    Contributor(
        name = "Puffercat Apps",
        photoUrl = "https://cdn.puffercat.xyz/c/puffercat-apps/branding/logo-small.png",
        socialUrl = "https://apps.puffercat.xyz/details/PufferIcons",
    ),
    Contributor(
        name = "GitHub Releases",
        photoUrl = "https://cdn.puffercat.xyz/c/lawnicons-fork/assets/github.png",
        socialUrl = "https://github.com/KZacharski/PufferIcons/releases",
    ),
)

private val requestBugForm = listOf(
    Contributor(
        name = "Form (Preferred)",
        photoUrl = "https://cdn.puffercat.xyz/c/lawnicons-fork/assets/form.png",
        socialUrl = "https://pfrcat.link/puffericons-form",
    ),
    Contributor(
        name = "GitHub Issues",
        photoUrl = "https://cdn.puffercat.xyz/c/lawnicons-fork/assets/github.png",
        socialUrl = "https://github.com/KZacharski/PufferIcons/issues",
    ),
)

private val coreContributors = listOf(
    Contributor(
    name = "Kacper Zacharski (Puffercat)",
    username = "KZacharski",
    photoUrl = "https://cdn.puffercat.xyz/c/branding/logo-basic.png",
    socialUrl = "https://puffercat.xyz",
),
    Contributor(
        name = "paphonb",
        username = "paphonb",
        photoUrl = "https://avatars.githubusercontent.com/u/8080853",
        socialUrl = "https://twitter.com/paphonb",
    ),
    )

private val myApps = listOf(
    Contributor(
        name = "Check out my other apps",
        photoUrl = "https://cdn.puffercat.xyz/c/puffercat-apps/branding/logo-small.png",
        socialUrl = "https://apps.puffercat.xyz",
    ),
)

@Composable
@OptIn(ExperimentalMaterial3Api::class, androidx.compose.material.ExperimentalMaterialApi::class)
fun About(navController: NavController) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBarWithInsets(
                scrollBehavior = scrollBehavior,
                title = stringResource(id = R.string.about),
                navigationIcon = {
                    ClickableIcon(
                        onClick = { navController.popBackStack() },
                        imageVector = Icons.Rounded.ArrowBack,
                        size = 40.dp,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            bottom = 32.dp,
                        ),
                ) {
                    Image(
                        bitmap = context.appIcon().asImageBitmap(),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier.size(72.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.version_x, BuildConfig.VERSION_NAME),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = ContentAlpha.medium,
                        ),
                    )
                }
            }
            item {
                Card(
                    label = stringResource(id = R.string.updates),
                ) {
                    getUpdatesAbout.mapIndexed { index, it ->
                        ContributorRow(
                            name = it.name,
                            photoUrl = it.photoUrl,
                            socialUrl = it.socialUrl,
                            divider = index != getUpdatesAbout.lastIndex,
                        )
                    }
                }
            }
            item {
                Card(
                    label = stringResource(id = R.string.important),
                    modifier = Modifier.padding(top = 16.dp,),
                ) {
                    requestBugForm.mapIndexed { index, it ->
                        ContributorRow(
                            name = it.name,
                            photoUrl = it.photoUrl,
                            socialUrl = it.socialUrl,
                            divider = index != requestBugForm.lastIndex,
                        )
                    }
                }
            }
            item {
                Card(label = stringResource(id = R.string.core_contributors),
                    modifier = Modifier.padding(top = 16.dp,)) {
                    coreContributors.mapIndexed { index, it ->
                        ContributorRow(
                            name = it.name,
                            photoUrl = it.photoUrl,
                            profileUrl = it.socialUrl,
                            divider = index != coreContributors.lastIndex,
                        )
                    }
                }
            }
            item {
                Card(modifier = Modifier.padding(top = 16.dp)) {
                    SimpleListRow(
                        onClick = { navController.navigate(Destinations.CONTRIBUTORS) },
                        label = stringResource(id = R.string.see_all_contributors),
                        divider = false,
                    )
                }
            }
            item {
                Card(
                    modifier = Modifier.padding(top = 35.dp,),
                ) {
                    myApps.mapIndexed { index, it ->
                        ContributorRow(
                            name = it.name,
                            photoUrl = it.photoUrl,
                            socialUrl = it.socialUrl,
                            divider = index != myApps.lastIndex,
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 25.dp,),)
            }
        }
    }
}
