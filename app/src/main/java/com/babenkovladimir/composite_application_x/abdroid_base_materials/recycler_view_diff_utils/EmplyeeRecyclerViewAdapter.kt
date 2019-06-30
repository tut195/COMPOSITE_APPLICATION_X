package com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.babenkovladimir.composite_application_x.R
import kotlinx.android.synthetic.main.list_item.view.*


class EmployeeRecyclerViewAdapter(employeeList: List<Employee>) : RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder>() {

    // Variables

    private val mEmployees = arrayListOf<Employee>()

    // Public

    fun updateEmployeeListItems(employees: List<Employee>) {
        val diffCallback = EmployeeDiffCallback(this.mEmployees, employees)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.mEmployees.clear()
        this.mEmployees.addAll(employees)
        diffResult.dispatchUpdatesTo(this)
    }

    init {
        this.mEmployees.addAll(employeeList)
    }

    // Live

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mEmployees[position])

    }

    override fun getItemCount(): Int {
        return mEmployees.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(employee: Employee) {
            itemView.employee_name.text = employee.name
            itemView.employee_role.text = employee.role
        }
    }
}