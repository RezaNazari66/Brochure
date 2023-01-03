package com.bonial.bronchurelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bonial.bronchurelist.databinding.FragmentBrochureListBinding
import com.bonial.domain.models.Brochure
import com.bonial.domain.models.ContentTypeEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrochureListFragment : Fragment() {

    private val viewModel: BrochureListViewModel by viewModels()
    lateinit var binding: FragmentBrochureListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrochureListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.brochureListStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DefaultState -> {
                    implementRecycler(state.data)
                    hideLoading()
                }
                is ErrorState -> {
                    showError(state)
                    hideLoading()
                }
                is LoadingState -> showLoading()
            }
        }

    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun showError(state: ErrorState) {
        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun implementRecycler(brochureList: List<Brochure>) {
        val adapter = BrochuresAdapter(requireContext()).apply { submitList(brochureList) }
        binding.recyclerViewBrochures.apply {
            setHasFixedSize(true)
            layoutManager = getLayoutManager(brochureList)
            this.adapter = adapter
        }

    }

    private fun getLayoutManager(
        brochureList: List<Brochure?>,
    ): LayoutManager {
        val spanCount = 6
        val oneColumn = spanCount / 1
        val twoColumns = resources.getInteger(R.integer.brochures_column)

        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (brochureList[position]?.contentType) {
                    ContentTypeEnum.BROCHURE -> twoColumns
                    else -> oneColumn
                }
            }
        }
        return layoutManager

    }
}