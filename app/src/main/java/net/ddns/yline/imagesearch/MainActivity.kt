package net.ddns.yline.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.ddns.yline.imagesearch.databinding.ActivityMainBinding

// class name = .link--h3bPW

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}

//suspend fun getImageInformation():