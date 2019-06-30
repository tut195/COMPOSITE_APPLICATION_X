package com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils.utils.DummyEmployeeDataUtils
import kotlinx.android.synthetic.main.activity_recycler_view_diff_utils.*


class RecyclerViewDiffUtilsActivity : AppCompatActivity() {

    // Variables

    private var mRecyclerViewAdapter: EmployeeRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_diff_utils)

        mRecyclerViewAdapter = EmployeeRecyclerViewAdapter(DummyEmployeeDataUtils.employeeListSortedByRole)

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mRecyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> {
                mRecyclerViewAdapter?.updateEmployeeListItems(
                    DummyEmployeeDataUtils.employeeListSortedByName
                )
                return true
            }
            R.id.sort_by_role -> {
                mRecyclerViewAdapter?.updateEmployeeListItems(
                    DummyEmployeeDataUtils.employeeListSortedByRole
                )
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
