package net.ddns.yline.imagesearch.item

data class IconInformation(
    val name:String,
    val src:String,
    val siteUrl:String
){
    override fun toString(): String {
        return "ICON : {\n" +
                "\t$name\n" +
                "\t$src\n" +
                "\t$siteUrl}\n\n"
    }
}
