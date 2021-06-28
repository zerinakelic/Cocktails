package com.example.projekat.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.projekat.R
import com.example.projekat.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this


        //Ovdje sam mislila da kad se klikne na neki od buttona, da se posalje ovaj string preko safe args u FirstFragment i onda u FirstViewModel i onda
        // u ModelRepository da se uzima Api u zavisnosti koji smo button kliknuli
        //ne radi - ne znam kako iz firstFragmenta poslati ovaj string u ViewModel a da ne bude null //TODO

        binding.buttonMargarita.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment ("margarita"))
        }
        binding.buttonZorro.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment ("zorro"))
        }
        binding.buttonBijou.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment ("bijou"))
        }
        binding.buttonMelya.setOnClickListener{view: View ->
            view.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToFirstFragment ("melya"))
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