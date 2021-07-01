package com.example.projekat.fragment.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat.R
import com.example.projekat.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    //za search
    private lateinit var adapter: ArrayAdapter<*>
    private lateinit var clickedDrink: String
    val clickListener: ItemClickListener = object : ItemClickListener {
        override fun onCocktailClicked(name: String) {
            val view = view ?: return
            view.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToDetailsScreen(name))
        }
    }

    private val viewModel: FirstViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            FirstViewModelFactory(activity.application, clickedDrink)
        ).get(FirstViewModel::class.java)
    }

    private var viewModelAdapter: MyAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllDrinks().observe(viewLifecycleOwner, { drinks ->
            viewModelAdapter?.drinks = drinks
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FirstFragmentBinding>(
            inflater, R.layout.first_fragment, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        val args = FirstFragmentArgs.fromBundle(requireArguments())
        clickedDrink = args.clickedDrink

        binding.viewModel = viewModel
        viewModelAdapter = MyAdapter(clickListener)

        binding.root.findViewById<RecyclerView>(R.id.id_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }
}