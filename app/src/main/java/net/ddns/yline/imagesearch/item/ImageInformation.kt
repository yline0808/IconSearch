package net.ddns.yline.imagesearch.item

data class ImageInformation(
    val imgUrl:String,
    val originalUrl:String,
    val tag:MutableList<String>
)
