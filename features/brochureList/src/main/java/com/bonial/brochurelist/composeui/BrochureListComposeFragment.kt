package com.bonial.brochurelist.composeui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.bonial.brochurelist.*
import com.bonial.domain.models.Brochure
import dagger.hilt.android.AndroidEntryPoint
import com.bonial.brochurelist.R

@AndroidEntryPoint
class BrochureListComposeFragment : Fragment() {

    private val viewModel: BrochureListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                if (!viewModel.isDataLoadedBefore) viewModel.getBrochureList()
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: BrochureListViewModel) {
    val brochuresState = viewModel.brochureListStateLiveData.observeAsState()

    when (brochuresState.value) {
        is DefaultState -> BrochureList((brochuresState.value as DefaultState).data)
        is ErrorState -> showError(
            (brochuresState.value as ErrorState).errorMessage, LocalContext.current
        )
        is LoadingState -> Loading()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BrochureList(list: List<Brochure>) {
    LazyVerticalGrid(cells = GridCells.Fixed(6 / integerResource(id = R.integer.brochures_column))) {
        items(list.size) { index ->
            val item = list[index]
            BrochureItem(item)

        }
    }

}

private fun showError(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun Loading() {
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp)
        )
    }
}


@Composable
fun BrochureItem(brochure: Brochure) {
    val painter = rememberImagePainter(brochure.image)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, top = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = brochure.retailerName,
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = brochure.retailerName.orEmpty(),
            color = Color.Gray
        )
    }

}

