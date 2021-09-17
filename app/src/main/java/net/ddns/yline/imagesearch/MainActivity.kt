package net.ddns.yline.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.ddns.yline.imagesearch.databinding.ActivityMainBinding

// class name = .link--h3bPW

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchImg.isSubmitButtonEnabled = true
        binding.searchImg.setOnQueryTextListener(QueryTextListener())

        getItemList()
    }

    private fun getItemList(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("coroutine main start", "${this}")

            getImageInformation()

            Log.d("coroutine main end", "end")
        }
    }

    inner class QueryTextListener:SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.d("||search view submit", "${query}")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            Log.d("||search view text change", "${newText}")
            return true
        }
    }
}

suspend fun getImageInformation():Boolean = withContext(Dispatchers.IO){
    Log.d("coroutine io start", this.toString())



    Log.d("coroutine io end", "end")
    true
}