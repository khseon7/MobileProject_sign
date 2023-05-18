package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    val TAG:String="Register"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var btn_register=findViewById<Button>(R.id.btn_register)
        var edit_id=findViewById<EditText>(R.id.edit_id)
        var edit_pw=findViewById<EditText>(R.id.edit_pw)
        var edit_pw_re=findViewById<EditText>(R.id.edit_pw_re)



        btn_register.setOnClickListener{
            var isExistBlank=false
            var isPWSame=false

            Log.d(TAG,"회원가입 버튼 클릭")

            val id=edit_id.text.toString()
            val pw=edit_pw.text.toString()
            val pw_re=edit_pw_re.text.toString()

            if(id.isEmpty() || pw.isEmpty() || pw_re.isEmpty()){
                isExistBlank=true
            }
            else{
                if(pw==pw_re){
                    isPWSame=true
                }
            }

            if(!isExistBlank && isPWSame){
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                val sharedPreference=getSharedPreferences("file name", Context.MODE_PRIVATE)
                val editor=sharedPreference.edit()
                editor.putString("id",id)
                editor.putString("pw",pw)
                editor.apply()

                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                if(isExistBlank){
                    dialog("blank")
                }
                else if(!isPWSame){
                    dialog("not same")
                }
            }
        }
    }
    fun dialog(type:String){
        val dialog= AlertDialog.Builder(this)
        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란은 모두 작성해주세요")
        }
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다.")
        }
        val dialog_listener=object: DialogInterface.OnClickListener{
            override fun onClick(dialog:DialogInterface?,which:Int){
                when(which){
                    DialogInterface.BUTTON_POSITIVE->Log.d(TAG,"다이얼로그")
                }
            }
        }
        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
    }
}
