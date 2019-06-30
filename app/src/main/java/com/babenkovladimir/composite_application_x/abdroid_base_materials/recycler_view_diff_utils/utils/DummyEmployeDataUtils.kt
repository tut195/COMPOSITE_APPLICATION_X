package com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils.utils

import com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils.Employee
import java.util.*


object DummyEmployeeDataUtils {
    val employeeListSortedByName: List<Employee>
        get() {
            val employeeList = employeeList

            Collections.sort(employeeList, object : Comparator<Employee> {
                override fun compare(a1: Employee, a2: Employee): Int {
                    return a1.name!!.compareTo(a2.name!!)
                }
            })

            return employeeList
        }

    val employeeListSortedByRole: List<Employee>
        get() {
            val employeeList = employeeList

            Collections.sort(employeeList, object : Comparator<Employee> {
                override fun compare(a1: Employee, a2: Employee): Int {
                    return a2.role.compareTo(a1.role)
                }
            })
            return employeeList
        }

    private val employeeList: List<Employee>
        get() {
            val employees = arrayListOf<Employee>()

            employees.add(Employee(1, "Employee 1", "Developer"))
            employees.add(Employee(2, "Employee 2", "Tester"))
            employees.add(Employee(3, "Employee 3", "Support"))
            employees.add(Employee(4, "Employee 4", "Sales Manager"))
            employees.add(Employee(5, "Employee 5", "Manager"))
            employees.add(Employee(6, "Employee 6", "Team lead"))
            employees.add(Employee(7, "Employee 7", "Scrum Master"))
            employees.add(Employee(8, "Employee 8", "Sr. Tester"))
            employees.add(Employee(9, "Employee 9", "Sr. Developer"))
            return employees
        }

}