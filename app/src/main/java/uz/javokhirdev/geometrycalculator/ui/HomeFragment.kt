package uz.javokhirdev.geometrycalculator.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.javokhirdev.geometrycalculator.R
import uz.javokhirdev.geometrycalculator.helpers.ShapeResponse
import uz.javokhirdev.geometrycalculator.databinding.FragmentHomeBinding
import uz.javokhirdev.geometrycalculator.helpers.ConstHelper
import uz.javokhirdev.geometrycalculator.helpers.flex

class HomeFragment : Fragment(), HomeAdapter.HomeListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeAdapter = HomeAdapter(this)

    private var selectedShape: ShapeResponse? = null

    private var is3Dselected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvShapes.flex().adapter = homeAdapter

            buttonCalcul.setOnClickListener { navigateToCalcul() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.optionView -> {
                is3Dselected = !is3Dselected
                requireActivity().invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val optionView = menu.findItem(R.id.optionView)

        optionView.setIcon(if (is3Dselected) R.drawable.ic_2d else R.drawable.ic_3d)
        optionView.title = getString(if (is3Dselected) R.string.geometry else R.string.stereometry)

        setToolbarTitle()
        setShapes()

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onShapeClick(item: ShapeResponse) {
        setSelectedShape(item)
    }

    private fun setSelectedShape(shape: ShapeResponse? = null) {
        with(binding) {
            selectedShape = shape ?: getShapes().firstOrNull()
            selectedShape?.icon?.let { imageShape.setImageResource(it) }
        }
    }

    private fun setShapes() {
        val shapes = getShapes()
        homeAdapter.submitList(shapes)
        setSelectedShape(shapes.firstOrNull())
    }

    private fun getShapes(): ArrayList<ShapeResponse> {
        return if (is3Dselected) ConstHelper.get3DShapes() else ConstHelper.get2DShapes()
    }

    private fun setToolbarTitle() {
        (requireActivity() as AppActivity).setToolbarTitle(
            if (is3Dselected) getString(R.string.stereometry) else getString(R.string.geometry),
            if (is3Dselected) getString(R.string.calculator) else getString(R.string.calculator)
        )
    }

    private fun navigateToCalcul() {
        selectedShape?.id?.let {
            val direction = HomeFragmentDirections.homeToCalcul(it)
            findNavController().navigate(direction)
        }
    }
}