package uz.javokhirdev.geometrycalculator.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import uz.javokhirdev.geometrycalculator.R
import uz.javokhirdev.geometrycalculator.databinding.ActivityAppBinding
import uz.javokhirdev.geometrycalculator.helpers.*

class AppActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
    }

    private val binding by lazy { ActivityAppBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configureDrawer()
        configureToolbar()

        with(binding) {
            setSupportActionBar(toolbar)
            title = null

            toolbar.setupWithNavController(navController)

            buttonClose.setOnClickListener { layoutDrawer.closeDrawer(GravityCompat.START) }
            buttonApps.setOnClickListener { link(PLAY_STORE_DEV) }
            buttonRateApp.setOnClickListener { link(PLAY_STORE) }
            buttonShare.setOnClickListener { share() }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            hideKeyboard()

            when (destination.id) {
                R.id.homeFragment -> onMenu()
                else -> onBack()
            }
        }
    }

    private fun configureDrawer() {
        with(binding) {
            val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this@AppActivity, layoutDrawer, R.string.open, R.string.close
            ) {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    layoutContent.translationX = drawerView.width * slideOffset
                }
            }

            layoutDrawer.apply {
                drawerElevation = 0f
                addDrawerListener(actionBarDrawerToggle)
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

    fun setToolbarTitle(title: String, subtitle: String) {
        with(binding) {
            toolbar.title = title
            toolbar.subtitle = subtitle
        }
    }

    private fun onMenu() {
        with(binding) {
            toolbar.setNavigationIcon(R.drawable.ic_menu)
            toolbar.setNavigationOnClickListener { layoutDrawer.openDrawer(GravityCompat.START) }
        }
    }

    private fun onBack() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { navController.navigateUp() }
        }
    }
}