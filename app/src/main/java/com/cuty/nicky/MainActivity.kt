package com.cuty.nicky

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var back_pressed:Long=200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        //NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    /*
    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            val count = supportFragmentManager.backStackEntryCount
            if (count == 0) {
                super.onBackPressed()
                //finish()
                //additional code
            } else {
                supportFragmentManager.popBackStack()
            }
            //finish()
            /*val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)*/
        } else {
            Toast.makeText(
                    getBaseContext(), "Presione una vez más para salir",
                    Toast.LENGTH_SHORT
            ).show()
            back_pressed = System.currentTimeMillis()
        }

    }
    */

    /*
     val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
     */
}