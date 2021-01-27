package com.example.dataactivity.studentAdapter

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.dataactivity.R
import com.example.dataactivity.ViewStudent
import com.example.dataactivity.db.StudentDb
import com.example.dataactivity.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentAdapter(val context: Context, val lstStudent:MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvStdName : TextView
        var tvStdage : TextView
        var tvStdaddress : TextView
        var tvStdGender : TextView
        var btnEdit: ImageView
        var btnDelete: ImageView
        init {

            tvStdName = v.findViewById(R.id.tvname)
            tvStdage = v.findViewById(R.id.tvage)
            tvStdaddress = v.findViewById(R.id.tvaddress)
            tvStdGender = v.findViewById(R.id.tvgender)
            btnEdit = v.findViewById(R.id.imgedit)
            btnDelete = v.findViewById(R.id.imgdelete)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_layout,parent,false)
        return StudentViewHolder(view)
    }
    override fun getItemCount(): Int {
        return lstStudent.size
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val studentData = lstStudent[position]
        holder.tvStdName.text = studentData.stdName //FROM THE student.kt class
        holder.tvStdaddress.text = studentData.stdAddress
        holder.tvStdage.text = studentData.stdAge.toString()
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.delete_layout)//make this and insert from layout
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        var dialog2 = Dialog(context)
        dialog2.setContentView(R.layout.update_layout) //make this and insert from layout
        dialog2.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog2.setCancelable(false)
        var btnYes : Button = dialog.findViewById(R.id.btnYes)//from delete layout
        var btnNo : Button = dialog.findViewById(R.id.btnNo)
        var etFn : EditText = dialog2.findViewById(R.id.etFn)
        var etAge : EditText = dialog2.findViewById(R.id.etAge)//form the update layout
        var etAddress : EditText = dialog2.findViewById(R.id.etAddress)
        var etProfile : EditText = dialog2.findViewById(R.id.etProfile)
        var rgGroup : RadioGroup = dialog2.findViewById(R.id.rgGroup)
        var gender = "Male"
        var btnUpdate : Button = dialog2.findViewById(R.id.btnUpdate)
        var btnCancel : Button = dialog2.findViewById(R.id.btnCancel)
        btnYes.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                StudentDb.getInstance(context)!!.getStudentDAO().deleteStudent(studentData)

                withContext(Main){
                    lstStudent.removeAt(position)
                    notifyDataSetChanged()
                    dialog.dismiss()

                }
            }
        }
        btnNo.setOnClickListener {
            dialog.cancel()
        }
        holder.btnDelete.setOnClickListener{
            dialog.show()
        }
        rgGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId)
            {
                R.id.rbMale->{
                    gender ="Male"
                }
                R.id.rbFemale->{
                    gender ="Female"
                }
                R.id.rbOther ->{
                    gender= "Others"
                }
            }
        }
        holder.btnEdit.setOnClickListener {
            etFn.setText(studentData.stdName)//from the student.kt class
            etAddress.setText(studentData.stdAddress)
            etAge.setText(studentData.stdAge.toString())
            when(gender)
            {
                "Male"->{
                    rgGroup.check(R.id.rbMale)
                }
                "Female" ->{
                    rgGroup.check(R.id.rbFemale)
                }
                "Others"->{
                    rgGroup.check(R.id.rbOther)
                }
            }
            dialog2.show()
        }
        btnCancel.setOnClickListener {
            dialog2.cancel()
        }
        btnUpdate.setOnClickListener {
            if(TextUtils.isEmpty(etFn.text))
            {
                etFn.error = "Enter Firstname"
                etFn.requestFocus()
            }
            else if(TextUtils.isEmpty(etAge.text))
            {
                etAge.error = "Enter Age"
                etAge.requestFocus()
            }
            else if(TextUtils.isEmpty(etAddress.text))
            {
                etAddress.error = "Enter Address"
                etAddress.requestFocus()
            }
            else if(TextUtils.isEmpty(etProfile.text))
            {
                etProfile.error = "Enter Address"
                etProfile.requestFocus()
            }
            else if(!Regex("""[a-z][a-z]+""").matches(etFn.text.toString().toLowerCase().replace(" ","")))
            {
                etAddress.error = "Full name should not contain any numbers"
                etAddress.requestFocus()
            }
            else if(etAge.text.toString().toInt() > 40 || etAge.text.toString().toInt() < 18)
            {
                etAge.error = "Age should be in between 18-40"
                etAge.requestFocus()
            }
            else
            {
                lstStudent[position].stdName = etFn.text.toString()
                lstStudent[position].stdAddress = etAddress.text.toString()
                lstStudent[position].stdAge = etAge.text.toString().toInt()
                lstStudent[position].stdGender = gender
                notifyDataSetChanged()
                dialog2.cancel()
            }
        }
    }


}