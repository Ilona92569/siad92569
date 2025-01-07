package com.example.feature.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature.R
import com.example.feature.databinding.FragmentExchangeBinding
import com.example.feature.ui.exchange.adapter.RateAdapter
import com.example.testeffective.feature.extensions.addVerticalGaps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModel<ExchangeViewModel>()

    private lateinit var adapterRate: RateAdapter

    private val cities = listOf("Брест", "Витебск", "Гомель", "Гродно", "Минск", "Могилёв")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentExchangeBinding.inflate(inflater, container, false)
            .also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            adapterRate = RateAdapter(requireContext())

            val adapter = ArrayAdapter(requireContext(), R.layout.item_city, cities)
            (selectCity.editText as? AutoCompleteTextView)?.setAdapter(adapter)

            textCity.setText(cities[0], false)

            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    textError.visibility = View.GONE
                    listExchange.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                } else {
                    viewModel.isError.observe(viewLifecycleOwner) { isError ->
                        if (isError) {
                            textError.visibility = View.VISIBLE
                            listExchange.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        } else {
                            progressBar.visibility = View.GONE
                            listExchange.visibility = View.VISIBLE
                            textError.visibility = View.GONE
                        }
                    }

                }
            }

            viewModel.error.observe(viewLifecycleOwner) { error ->
                textError.text = error
            }



            textCity.setOnItemClickListener { parent, view, position, id ->
                val selectedCity = parent.getItemAtPosition(position) as String
                updateDataCity(selectedCity)
            }

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                viewModel.getAllTickets(cities[0])
            }
            viewModel.listCityData.observe(viewLifecycleOwner) {
                adapterRate.submitList(it)
            }

            listExchange.addVerticalGaps()
            listExchange.adapter = adapterRate
            listExchange.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }

    private fun updateDataCity(city: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getAllTickets(city)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}