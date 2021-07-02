package com.example.projekat.fragment.first

import android.os.Bundle
import android.view.*
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
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
    private var isFilterEnabled = false
    private val clickListener: ItemClickListener = object : ItemClickListener {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        if (isFilterEnabled) {
            menu.add(R.string.remove_filters).setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        } else {
            menu.add(R.string.show_ordinary_drinks).setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != 0) {
            return false
        }

        if (isFilterEnabled) {
            viewModel.refreshDataFromRepository()
        } else {
            val items = viewModelAdapter?.drinks ?: return false
            viewModelAdapter?.drinks = items.filter { ORDINARY_COCKTAIL_TYPE.equals(it.strCategory, true) }
        }
        isFilterEnabled = !isFilterEnabled
        activity?.invalidateOptionsMenu()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val ORDINARY_COCKTAIL_TYPE = "Ordinary Drink"
    }
}