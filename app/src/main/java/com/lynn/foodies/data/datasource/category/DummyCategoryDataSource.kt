package com.lynn.foodies.data.datasource.category

import com.lynn.foodies.data.model.Category

class DummyCategoryDataSource : CategoryDataSource {
    override fun getCategory(): List<Category> {
        return listOf(
            Category(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/category_img/img_makanan.jpeg",
                name = "Makanan"
            ),
            Category(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/category_img/img_mie.jpeg",
                name = "Mie"
            ),
            Category(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/category_img/img_minuman.jpg",
                name = "Minuman"
            ),
            Category(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/category_img/img_snack.jpg",
                name = "Snack"
            )
        )
    }

}