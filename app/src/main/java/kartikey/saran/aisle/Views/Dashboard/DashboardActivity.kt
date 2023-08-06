package kartikey.saran.aisle.Views.Dashboard

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kartikey.saran.aisle.R
import kartikey.saran.aisle.databinding.ActivityDashboardBinding

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discover, R.id.navigation_notes, R.id.navigation_matches, R.id.navigation_profile
            )
        )
        navView.setupWithNavController(navController)

        var badgeNotes = navView.getOrCreateBadge(R.id.navigation_notes)
        badgeNotes.isVisible = true
        badgeNotes.number = 9

        var badgeMatches = navView.getOrCreateBadge(R.id.navigation_matches)
        badgeMatches.isVisible = true
        badgeMatches.number = 50

    }
}