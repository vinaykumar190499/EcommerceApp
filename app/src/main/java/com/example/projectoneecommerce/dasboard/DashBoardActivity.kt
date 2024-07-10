package com.example.projectoneecommerce.dasboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectoneecommerce.NavigateToCategoryActivity
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.SecuredSharedPreferenceManager
import com.example.projectoneecommerce.Utils
import com.example.projectoneecommerce.data.DashBoard
import com.example.projectoneecommerce.databinding.ActivityDashBoardBinding
import com.example.projectoneecommerce.login.LoginActivity

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashBoardBinding
    private lateinit var dashBoardAdapter: DashBoardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews(){
        with(binding){
            dashboardRecyclerView.layoutManager = GridLayoutManager(this@DashBoardActivity, 2)
            val sintent = Intent(this@DashBoardActivity, NavigateToCategoryActivity::class.java)
            dashboardRecyclerView.adapter = DashBoardAdapter(getData()){ dashboardItem ->
                sintent.putExtra(Utils.DASHBOARD_CATEGORY_INFO, dashboardItem)
                startActivity(sintent)
            }
            btnLogout.setOnClickListener{

                val builder = AlertDialog.Builder(this@DashBoardActivity)
                builder.apply {
                    setTitle("Logout")
                    setMessage("Are you sure to logout?")
                    setPositiveButton("Okay") { _, _ ->
                        SecuredSharedPreferenceManager.clearAllPref()
                        startActivity(Intent(this@DashBoardActivity, LoginActivity::class.java))
                        finish()
                    }
                    setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

            }
        }
    }

    private fun getData(): List<DashBoard> {
        return listOf<DashBoard>(
            DashBoard(
                categoryImg = R.drawable.smartphone,
                category = "Smart Phones"
            ),
            DashBoard(
                categoryImg = R.drawable.laptopimg,
                category = "Laptop"
            ),
            DashBoard(
                categoryImg = R.drawable.menswearimg,
                category = "Mens Wear"
            ), DashBoard(
                categoryImg = R.drawable.womenswearimg,
                category = "Womens Wear"
            )
            , DashBoard(
                categoryImg = R.drawable.kidwearimg,
                category = "Kids Wear"
            ),
            DashBoard(
                categoryImg = R.drawable.groceryimg,
                category = "Groceries"
            )
        )
    }
}