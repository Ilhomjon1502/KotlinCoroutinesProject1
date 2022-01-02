package uz.ilhomjon.kotlincoroutinesproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import uz.ilhomjon.kotlincoroutinesproject1.databinding.ActivityMainBinding
import uz.ilhomjon.kotlincoroutinesproject1.viewModels.UserViewModel


// documentation link: https://github.com/MindorksOpenSource/Kotlin-Coroutines-Android-Examples
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var userViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUsers().observe(this, {
            binding.tv.text = it
        })
    }
}