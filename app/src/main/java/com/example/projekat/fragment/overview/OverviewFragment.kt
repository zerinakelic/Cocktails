package com.example.projekat.fragment.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.projekat.CocktailCategory
import com.example.projekat.R
import com.example.projekat.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.buttonMargarita.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment (CocktailCategory.MARGARITA.cocktailName))
        }
        binding.buttonCaipirinha.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment (CocktailCategory.CAIPIRINHA.cocktailName))
        }
        binding.buttonMartini.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment (CocktailCategory.MARTINI.cocktailName))
        }
        binding.buttonMojito.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment (CocktailCategory.MOJITO.cocktailName))
        }
        return binding.root
    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawermenu, menu)
    }

    //menu - ova funkcija radi za sve iteme u menu - samo id itema mora biti isti kao id aktivnosti s kojom ga povezujemo
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}