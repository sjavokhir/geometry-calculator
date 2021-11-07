package uz.javokhirdev.geometrycalculator.ui.calcul

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.javokhirdev.geometrycalculator.R

class CalculFragment : Fragment() {

    companion object {
        fun newInstance() = CalculFragment()
    }

    private lateinit var viewModel: CalculViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calcul, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalculViewModel::class.java)
        // TODO: Use the ViewModel
    }

}