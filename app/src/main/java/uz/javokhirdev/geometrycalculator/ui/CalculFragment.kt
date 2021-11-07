package uz.javokhirdev.geometrycalculator.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import uz.javokhirdev.geometrycalculator.R
import uz.javokhirdev.geometrycalculator.databinding.FragmentCalculBinding
import uz.javokhirdev.geometrycalculator.helpers.*
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaCircle
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaParallelogram1
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaParallelogram2
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaPolygon
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaRectangle
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaSquare
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaTrapezoid
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaTriangle1
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaTriangle2
import uz.javokhirdev.geometrycalculator.helpers.Formulas.areaTriangle3
import uz.javokhirdev.geometrycalculator.helpers.Formulas.perCircle
import uz.javokhirdev.geometrycalculator.helpers.Formulas.perPolygon
import uz.javokhirdev.geometrycalculator.helpers.Formulas.perRectangle
import uz.javokhirdev.geometrycalculator.helpers.Formulas.perSquare
import uz.javokhirdev.geometrycalculator.helpers.Formulas.perTriangle
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeCone
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeCylinder
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeEllipsoid
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeFrustum
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeParallelepiped
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumePyramid
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeSphere
import uz.javokhirdev.geometrycalculator.helpers.Formulas.volumeTorus

class CalculFragment : Fragment() {

    private val args by navArgs<CalculFragmentArgs>()

    private var _binding: FragmentCalculBinding? = null
    private val binding get() = _binding!!

    private var selectedShape: ShapeDetailResponse? = null

    private var firstValue: Int? = null
    private var secondValue: Int? = null
    private var thirdValue: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        selectedShape = ConstHelper.getShapeDetail(args.shapeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedShape?.let {
            setToolbarTitle(it)

            with(binding) {
                imageShape.setImageResource(it.icon)

                inputOne.onTextChanged {
                    firstValue = toIntOrNull()
                    calculate(it)
                }
                inputTwo.onTextChanged {
                    secondValue = toIntOrNull()
                    calculate(it)
                }
                inputThree.onTextChanged {
                    thirdValue = toIntOrNull()
                    calculate(it)
                }
            }

            configureCalc(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_reset, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reset) reset()
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbarTitle(shape: ShapeDetailResponse) {
        (requireActivity() as AppActivity).setToolbarTitle(
            shape.name,
            if (shape.isGeometric) getString(R.string.per_area) else getString(R.string.volume_calculator)
        )
    }

    private fun configureCalc(shape: ShapeDetailResponse) {
        try {
            with(binding) {
                layoutOne.beGone()
                layoutTwo.beGone()
                layoutThree.beGone()

                when (shape.id) {
                    1 -> {
                        textOne.color(R.color.colorRed)
                        textOne.colorOf("a (m)", "(m)")

                        layoutOne.beVisible()
                    }
                    2 -> {
                        textOne.color(R.color.colorBlue)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorWarning)
                        textTwo.colorOf("b (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    3 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("r (m)", "(m)")

                        layoutOne.beVisible()
                    }
                    4 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("h (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("b (m)", "(m)")

                        layoutPerimeter.beGone()
                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    5 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorWarning)
                        textThree.colorOf("α (deg)", "(deg)")

                        layoutPerimeter.beGone()
                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    6 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorWarning)
                        textThree.colorOf("c (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    7 -> {
                        textOne.color(R.color.colorBlue)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorGreen)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorWarning)
                        textThree.colorOf("h (m)", "(m)")

                        layoutPerimeter.beGone()
                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    8 -> {
                        textOne.color(R.color.colorBlue)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorGreen)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorWarning)
                        textThree.colorOf("α (deg)", "(deg)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    9 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("h (m)", "(m)")

                        textTwo.color(R.color.colorGreen)
                        textTwo.colorOf("b (m)", "(m)")

                        layoutPerimeter.beGone()
                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    10 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("n (sided)", "(sided)")

                        textTwo.color(R.color.colorRed)
                        textTwo.colorOf("b (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    11 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorWarning)
                        textThree.colorOf("c (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    12 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("r (m)", "(m)")

                        layoutOne.beVisible()
                    }
                    13 -> {
                        textOne.color(R.color.colorRed)
                        textOne.colorOf("r (m)", "(m)")

                        textTwo.color(R.color.colorWarning)
                        textTwo.colorOf("h (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    14 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("h (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("r (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    15 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("h (m)", "(m)")

                        textTwo.color(R.color.colorBlue)
                        textTwo.colorOf("A (m²)", "(m²)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    16 -> {
                        textOne.color(R.color.colorGreen)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorWarning)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorBlue)
                        textThree.colorOf("h (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                    17 -> {
                        textOne.color(R.color.colorWarning)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorGreen)
                        textTwo.colorOf("b (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                    }
                    18 -> {
                        textOne.color(R.color.colorBlue)
                        textOne.colorOf("a (m)", "(m)")

                        textTwo.color(R.color.colorRed)
                        textTwo.colorOf("b (m)", "(m)")

                        textThree.color(R.color.colorGreen)
                        textThree.colorOf("c (m)", "(m)")

                        layoutOne.beVisible()
                        layoutTwo.beVisible()
                        layoutThree.beVisible()
                    }
                }

                textPerimeter.colorOf("Perimeter (m)", "(m)")
                textArea.colorOf("Area (m²)", "(m²)")
                textVolume.colorOf("Volume (m³)", "(m³)")

                layoutPerArea.beVisibleIf(shape.isGeometric)
                layoutVolume.beVisibleIf(!shape.isGeometric)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun calculate(shape: ShapeDetailResponse) {
        try {
            with(binding) {
                when (shape.id) {
                    1 -> {
                        val firstValue = firstValue ?: return

                        textPerimeterValue.text = perSquare(firstValue)
                        textAreaValue.text = areaSquare(firstValue)
                    }
                    2 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textPerimeterValue.text = perRectangle(firstValue, secondValue)
                        textAreaValue.text = areaRectangle(firstValue, secondValue)
                    }
                    3 -> {
                        val firstValue = firstValue ?: return

                        textPerimeterValue.text = perCircle(firstValue)
                        textAreaValue.text = areaCircle(firstValue)
                    }
                    4 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textAreaValue.text = areaTriangle1(firstValue, secondValue)
                    }
                    5 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textAreaValue.text = areaTriangle2(firstValue, secondValue, thirdValue)
                    }
                    6 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textPerimeterValue.text = perTriangle(firstValue, secondValue, thirdValue)
                        textAreaValue.text = areaTriangle3(firstValue, secondValue, thirdValue)
                    }
                    7 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textAreaValue.text = areaTrapezoid(firstValue, secondValue, thirdValue)
                    }
                    8 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textPerimeterValue.text = perRectangle(firstValue, secondValue)
                        textAreaValue.text = areaParallelogram1(firstValue, secondValue, thirdValue)
                    }
                    9 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textAreaValue.text = areaParallelogram2(firstValue, secondValue)
                    }
                    10 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textPerimeterValue.text = perPolygon(firstValue, secondValue)
                        textAreaValue.text = areaPolygon(firstValue, secondValue)
                    }
                    11 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textVolumeValue.text =
                            volumeParallelepiped(firstValue, secondValue, thirdValue)
                    }
                    12 -> {
                        val firstValue = firstValue ?: return

                        textVolumeValue.text = volumeSphere(firstValue)
                    }
                    13 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textVolumeValue.text = volumeCylinder(firstValue, secondValue)
                    }
                    14 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textVolumeValue.text = volumeCone(firstValue, secondValue)
                    }
                    15 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textVolumeValue.text = volumePyramid(firstValue, secondValue)
                    }
                    16 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textVolumeValue.text = volumeFrustum(firstValue, secondValue, thirdValue)
                    }
                    17 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return

                        textVolumeValue.text = volumeTorus(firstValue, secondValue)
                    }
                    18 -> {
                        val firstValue = firstValue ?: return
                        val secondValue = secondValue ?: return
                        val thirdValue = thirdValue ?: return

                        textVolumeValue.text = volumeEllipsoid(firstValue, secondValue, thirdValue)
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun reset() {
        try {
            with(binding) {
                inputOne.text = "".toEditable()
                inputTwo.text = "".toEditable()
                inputThree.text = "".toEditable()

                textPerimeterValue.text = ""
                textAreaValue.text = ""
                textVolumeValue.text = ""
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}