package uz.javokhirdev.geometrycalculator.ui.entry

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import uz.javokhirdev.geometrycalculator.R
import uz.javokhirdev.geometrycalculator.databinding.ActivityAppBinding
import uz.javokhirdev.geometrycalculator.helpers.hideKeyboard

class AppActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
    }

    private val binding by lazy { ActivityAppBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configureToolbar()

        with(binding) {
            setSupportActionBar(toolbar)
            title = null

            toolbar.setupWithNavController(navController)
            toolbar.setNavigationIcon(R.drawable.ic_menu)
            toolbar.setNavigationOnClickListener {

            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            hideKeyboard()

            when (destination.id) {
                R.id.homeFragment -> onMenu()
                else -> onBack()
            }
        }
    }

    private fun configureToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            title = null

            toolbar.setupWithNavController(navController)
        }
    }

    private fun onMenu() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_menu)
            setNavigationOnClickListener {
                Toast.makeText(this@AppActivity, "On menu clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onBack() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { navController.navigateUp() }
        }
    }
}