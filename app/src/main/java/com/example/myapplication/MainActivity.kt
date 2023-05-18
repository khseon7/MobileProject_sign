package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_login=findViewById<Button>(R.id.btn_login)
        var edit_id=findViewById<EditText>(R.id.edit_id)
        var edit_pw=findViewById<EditText>(R.id.edit_pw)
        var btn_register=findViewById<Button>(R.id.btn_register)

        btn_login.setOnClickListener{
            var id=edit_id.text.toString()
            var pw=edit_pw.text.toString()

            val sharedPreference=getSharedPreferences("file name", Context.MODE_PRIVATE)
            val savedId=sharedPreference.getString("id","")
            val savedPw=sharedPreference.getString("pw","")

            if(id==savedId && pw==savedPw){
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                dialog("success")
            }else{
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                dialog("fail")
            }
        }
        btn_register.setOnClickListener{
            val intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
    fun dialog(type:String){
        var dialog=AlertDialog.Builder(this)
        if(type.equals("success")){
            dialog.setTitle("로그인 성공")
            dialog.setMessage("로그인 성공!")
        }
        else if(type.equals("fail")){
            dialog.setTitle("로그인 실패")
            dialog.setMessage("아이디와 비밀번호를 확인해주세요")
        }
        var dialog_listener=object:DialogInterface.OnClickListener{
            override fun onClick(dialog:DialogInterface?,which:Int){
                when(which){
                    DialogInterface.BUTTON_POSITIVE-> Log.d(TAG,"")
                }
            }
        }
    }
}