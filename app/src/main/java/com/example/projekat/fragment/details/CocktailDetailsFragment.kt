package com.example.projekat.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.projekat.R
import com.example.projekat.databinding.FragmentCocktailDetailsBinding

class CocktailDetailsFragment : Fragment() {

    private lateinit var cocktailName: String
    private lateinit var binding: FragmentCocktailDetailsBinding

    private val viewModel: CocktailDetailsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            DetailsViewModelFactory(activity.application, cocktailName)
        ).get(CocktailDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cocktail_details, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        val args = CocktailDetailsFragmentArgs.fromBundle(requireArguments())
        cocktailName = args.cocktail
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCocktail().observe(viewLifecycleOwner, { cocktail ->
            binding.cocktailName.text = cocktail.strDrink
            binding.instructions.text = cocktail.strInstructions
            binding.image.load(cocktail.strDrinkThumb) {
                crossfade(true)
                transformations(RoundedCornersTransformation(80f, 80f, 80f, 80f))
            }
        })
    }

}