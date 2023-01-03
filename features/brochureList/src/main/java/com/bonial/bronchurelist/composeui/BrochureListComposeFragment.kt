package com.bonial.bronchurelist.composeui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.bonial.bronchurelist.*
import com.bonial.domain.models.Brochure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrochureListComposeFragment : Fragment() {
    //todo fix bugs
    //My time was limited and I couldn't complete this file
    private val viewModel: BrochureListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val brochuresState =
            viewModel.brochureListStateLiveData.observeAsState(initial = LoadingState())

        when (brochuresState.value) {
            is DefaultState -> BrochureList(brochuresState.value.data)
            is ErrorState -> showError()
            is LoadingState -> Loading()
        }
    }

    @Composable
    fun BrochureList(list: List<Brochure>) {
        LazyColumn {
            items(list.size, itemContent = {
                BrochureItem(list[it])
            })
        }
    }

    private fun showError() {
        TODO("Not yet implemented")
    }


    @Composable
    fun Loading() {
        val strokeWidth = 5.dp

        CircularProgressIndicator(
            modifier = Modifier.drawBehind {
                drawCircle(
                    Color.Red,
                    radius = size.width / 2 - strokeWidth.toPx() / 2,
                    style = Stroke(strokeWidth.toPx())
                )
            },
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }


    @Composable
    fun BrochureItem(brochure: Brochure) {
        val painter =
            rememberImagePainter(brochure.image)

        Image(
            painter = painter,
            contentDescription = brochure.retailerName,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

}