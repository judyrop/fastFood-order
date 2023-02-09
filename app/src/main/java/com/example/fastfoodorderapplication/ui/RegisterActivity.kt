package com.example.fastfoodorderapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fastfoodorderapplication.R
import com.example.fastfoodorderapplication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.createB.setOnClickListener{
            signUpUser()
        }

        binding.loginTv.setOnClickListener{
//            startActivity(Intent(this,LoginActivity::class.java))
            onBackPressed()
        }

    }

    private fun signUpUser() {
        val email = binding.emailACinput.text.toString()
        val password = binding.passwordAcInput.text.toString()
        val confirmPassword = binding.cpasswordAcInput.text.toString()
        val phone = binding.contact.text.toString()
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Email ,Password and phone can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val firebaseUser: FirebaseUser = task.result!!.user!!
                firebaseUser.sendEmailVerification().addOnSuccessListener {
                    Toast.makeText(this, "email is verified $email", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "failed to verify ${it.message}", Toast.LENGTH_SHORT).show()

                }
                Toast.makeText(this, "You are registered successfully", Toast.LENGTH_SHORT).show()
                val  intent = Intent(this,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}