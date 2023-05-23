package com.pavelt.ghrelin.domain

object FoodMock {

    val pizzaUrl = "https://i2.photo.2gis.com/images/branch/0/30258560074074185_a400.jpg"
    val rizottoImg = "https://avatars.dzeninfra.ru/get-zen_doc/1705212/pub_5d642196a98a2a00aef8ba3b_5d64219bfe289100adb4bc61/scale_1200"
    val lazaniaImg = "https://avatars.dzeninfra.ru/get-zen_doc/1101166/pub_5d642196a98a2a00aef8ba3b_5d642198bd45c000ad9d5d08/scale_1200"
    val niokkiImg = "https://avatars.dzeninfra.ru/get-zen_doc/112297/pub_5d642196a98a2a00aef8ba3b_5d6421982f1e4409e4e40101/scale_1200"
    val minestroneImg = "https://avatars.dzeninfra.ru/get-zen_doc/1654267/pub_5d642196a98a2a00aef8ba3b_5d6421985eb26800ac99e16f/scale_1200"
    val pastaImg = "https://avatars.dzeninfra.ru/get-zen_doc/1612125/pub_5d642196a98a2a00aef8ba3b_5d642198dfa9ce00ad0b3442/scale_1200"
    val aranichiImg = "https://avatars.dzeninfra.ru/get-zen_doc/1900274/pub_5d642196a98a2a00aef8ba3b_5d642198028d6800ad089e85/scale_1200"

    val data = mutableListOf(
        Food(0, "Пепперони", "Еда", "", 1000, pizzaUrl),
        Food(1, "Ризотто", "Еда", "", 560, rizottoImg),
        Food(2, "Ньокки", "Еда", "", 350, niokkiImg),
        Food(3, "Минестроне", "Еда", "", 750, minestroneImg),
        Food(4, "Паста", "Еда", "", 1000, pastaImg),
        Food(5, "Лазанья", "Еда", "", 600, lazaniaImg),
        Food(6, "Аранчини", "Еда", "", 800, aranichiImg)
    )

    fun getFoodById(id: Int): Food? =
        data.find { it.id == id }
}