package com.ideal.mobileexcercise.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ideal.mobileexcercise.R
import com.ideal.mobileexcercise.ui.MainActivity
import com.ideal.mobileexcercise.databinding.FragmentPhotosBinding
import com.ideal.mobileexcercise.ui.adapters.PhotosAdapter
import com.ideal.mobileexcercise.ui.view.PhotosViewModel
import com.ideal.mobileexcercise.utils.hideSoftKeyboard
import com.ideal.mobileexcercise.utils.showKeyBoard
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PhotosFragment : DaggerFragment(R.layout.fragment_photos) {

    private lateinit var binding: FragmentPhotosBinding
    var lastSearch = ""

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private  val photosViewModel:  PhotosViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotosBinding.bind(view)

//        old and other specific phone version still can't  retainInstance even with view model
//        by adding retainInstance = true it can help to retain item through configuration changees
        retainInstance = true

        initUI()
    }


    private fun initUI() {

        binding.recyclerView.adapter = photosViewModel.photosAdapter

        //this will set our recycler view into 3 columns
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        if (lastSearch.isEmpty()) {
            lastSearch = "laptops"
            loadImages(lastSearch)
        }
        setSearchView()
    }


    //this function will call our every time the user click search
    @SuppressLint("ClickableViewAccessibility")
    private fun setSearchView() {
        binding.searchView.setOnTouchListener { v, _ ->
            v.isFocusableInTouchMode = true
            false
        }
        binding.searchView.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (lastSearch != binding.searchView.text.toString().trim()) {
                    loadImages(binding.searchView.text.toString().trim())
                    lastSearch = binding.searchView.text.toString().trim()
                }
                return@OnEditorActionListener true
            }
            false
        })

        binding.searchView.setOnTouchListener(View.OnTouchListener { _, event ->

            val drawableRight = 2

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.searchView.right - binding.searchView.compoundDrawables[drawableRight].bounds.width()
                ) {
                    requireContext().showKeyBoard(binding.searchView) // this will show the keyboard
                    return@OnTouchListener true
                }
            }
            false
        })


    }

    private fun loadImages(search: String) {
        binding.progress.visibility = View.VISIBLE
        requireActivity().hideSoftKeyboard() // for hiding the keyboard every time the user click search

        //this function will observe and update our adapter's data
        photosViewModel.loadPhotos2(search).observe(viewLifecycleOwner, { list ->
            with(photosViewModel.photosAdapter) {
                photos.clear()
                photos.addAll(list)
                notifyDataSetChanged()
                binding.progress.visibility = View.GONE
            }

        })

    }


}