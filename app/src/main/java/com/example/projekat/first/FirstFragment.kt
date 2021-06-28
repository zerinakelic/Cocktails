package com.example.projekat.first

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat.R
import com.example.projekat.databinding.FirstFragmentBinding
import com.example.projekat.network.Model

class FirstFragment : Fragment() {

    //za search
    private lateinit var adapter: ArrayAdapter<*>




    //prebacivanje za api
    private var clickedDrink = "margarita"

    private val viewModel: FirstViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            ViewModelFactory(activity.application, clickedDrink)
        ).get(FirstViewModel::class.java)
    }

    private var viewModelAdapter: MyAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lista.observe(viewLifecycleOwner, Observer<List<Model>> { drinks ->
            drinks?.apply {
                viewModelAdapter?.drinks = drinks
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FirstFragmentBinding>(
            inflater, R.layout.first_fragment, container, false
        )
        binding.setLifecycleOwner(viewLifecycleOwner)

        //uzimanje argumenta poslanog iz OverviewFragmenta
        /* val args = FirstFragmentArgs.fromBundle(requireArguments())
         clickedDrink = args.clickedDrink*/

        binding.viewModel = viewModel
        viewModelAdapter = MyAdapter()

        binding.root.findViewById<RecyclerView>(R.id.id_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })


        return binding.root
    }




    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}
/*
class Click(val block: (Model) -> Unit) {
    fun onClick(video: Model) = block(video)
}*/