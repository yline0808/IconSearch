package net.ddns.yline.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.ddns.yline.imagesearch.databinding.ActivityMainBinding
import net.ddns.yline.imagesearch.item.IconInformation
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val iconList = hashMapOf<String, IconInformation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener(){
        binding.apply {
            searchIcon.also {
                it.isSubmitButtonEnabled = true
                it.setOnQueryTextListener(OnQueryTextListener())
            }
        }
    }

    // 이미지를 가져오고 textview에표시하는 함수
    private fun getItemList(searchText: String){
        // 메인 코루틴 스코프
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("coroutine Dispatchers.Main start", "test")
            val isEnd = getIconInformation(iconList, searchText, 1)
            Log.d("coroutine Dispatchers.Main end", "test")
            Toast.makeText(applicationContext, iconList.size.toString(), Toast.LENGTH_SHORT).show()

            // 크롤링한 정보 표시
            binding.textviewTest.text = if(isEnd) "검색한 항목이 없습니다." else iconList.toString()
        }
    }

    inner class OnQueryTextListener:SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.d("coroutine activity start", "test")
            // 이미지를 가져오고 textview에표시
            if(query == null){
                Toast.makeText(
                    applicationContext,
                    "검색한 단어가 없습니다.",
                    Toast.LENGTH_SHORT).show()
                return false
            }else{
                // 임시로 크롤링한 데이터 매번 지우기
                iconList.clear()
                getItemList(query)
            }
            Log.d("coroutine activity end", "test")

            return true
        }

        // 지금은 사용 안함
        override fun onQueryTextChange(newText: String?): Boolean {

            return false
        }
    }
}

// 이미지 크롤링 하는 함수
suspend fun getIconInformation(
    iconList:HashMap<String, IconInformation>, searchText:String, page:Int
):Boolean = withContext(Dispatchers.IO){
    Log.d("coroutine Dispatchers.IO start", "test")
    // 페이지 끝 여부
    var isEnd = false

    try {
        // 주어진 검색어와 페이지를 검색
        val jsoup = Jsoup.connect(String.format(BuildConfig.URL_ICON_SITE, page, searchText))
        val doc:Document = jsoup.get()
        // 크롤링 하고자 하는 엘리먼트들을 저장
        val elements:Elements = doc
            .select("section.search-result.icons-search-result")
            .select("ul.icons")
            .select("div.icon--holder")
            .select("a.view.link-icon-detail")
        //만일 해당 엘리먼트가 없으면 페이지에 마지막에 도달한 것으로 간주
        if(elements.size == 0) isEnd = true
        else{
            for(element in elements){
                element.run {
                    iconList[attr("data-id")] = IconInformation(
                        attr("title"),
                        attr("href"),
                        select("img").attr("src")
                    )
                }
            }
        }
    }
    // status오류
    catch (httpStatusException : HttpStatusException){
        isEnd = true
        Log.e("getIconInformation", httpStatusException.message.toString())
        httpStatusException.printStackTrace()
    }
    // 기타 오류
    catch (exception:Exception){
        isEnd = true
        Log.e("getIconInformation", exception.message.toString())
        exception.printStackTrace()
    }
    Log.d("coroutine Dispatchers.IO end", "test")
    isEnd
}