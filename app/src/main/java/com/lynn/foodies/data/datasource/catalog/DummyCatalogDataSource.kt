package com.lynn.foodies.data.datasource.catalog

import com.lynn.foodies.data.model.Catalog

class DummyCatalogDataSource : CatalogDataSource {
    override fun getCatalog(): List<Catalog> {
        return listOf(
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_kentang.jpg",
                price = 15000.00,
                rating = 4.0,
                name = "Kentang Goreng",
                desc = "Kentang goreng adalah hidangan yang terbuat dari potongan kentang yang dipotong tipis atau kotak, kemudian digoreng dalam minyak panas hingga kecokelatan dan renyah di luar namun lembut di dalamnya. Makanan ini dikenal dengan cita rasa yang gurih dan tekstur yang memikat, sering disajikan sebagai camilan atau pendamping hidangan utama. Kentang goreng dapat disantap dengan berbagai bumbu atau saus, menjadikannya pilihan yang populer dan fleksibel bagi berbagai selera.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_martabakmanis.jpg",
                price = 20000.00,
                rating = 4.0,
                name = "Martabak Manis",
                desc = "Martabak manis adalah makanan tradisional Indonesia yang terkenal dengan rasa manis dan tekstur lembutnya. Terbuat dari adonan yang dicampur dengan gula, telur, dan santan, kemudian dipanggang hingga matang. Martabak manis sering kali diisi dengan berbagai pilihan topping seperti cokelat, keju, kacang, atau selai, yang menambahkan variasi rasa dan tekstur. Makanan ini sering disajikan sebagai camilan atau hidangan penutup yang lezat dan memuaskan.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_martabakasin.jpg",
                price = 20000.00,
                rating = 4.0,
                name = "Martabak Asin",
                desc = "Martabak asin adalah hidangan tradisional Indonesia yang terkenal dengan rasa gurih dan teksturnya yang renyah. Terbuat dari adonan yang diisi dengan campuran daging cincang, telur, bawang, dan rempah-rempah, kemudian adonan tersebut dipanggang atau digoreng hingga matang. Martabak asin seringkali disajikan dengan saus pedas atau acar sebagai pelengkap, memberikan tambahan cita rasa yang menyegarkan. Hidangan ini menjadi favorit sebagai camilan atau hidangan utama yang nikmat dan mengenyangkan.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_nasi.jpeg",
                price = 5000.00,
                rating = 4.0,
                name = "Nasi",
                desc = "Nasi adalah makanan pokok yang sangat penting dalam banyak budaya di seluruh dunia, terutama di Asia. Terbuat dari biji-bijian padi yang diproses dan dimasak, nasi memiliki tekstur lembut dan rasa yang netral, membuatnya menjadi pendamping ideal untuk berbagai hidangan dari daging, sayuran, atau saus. Selain sebagai sumber karbohidrat utama, nasi juga kaya akan nutrisi seperti karbohidrat kompleks, serat, dan sejumlah kecil protein. Nasi dapat disajikan dalam berbagai bentuk, mulai dari nasi putih yang sederhana hingga nasi goreng yang kaya rasa, menjadikannya makanan serbaguna yang sangat disukai dan dikonsumsi di seluruh dunia.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_esteh.jpg",
                price = 3000.00,
                rating = 4.0,
                name = "Es Teh Manis",
                desc = "Es teh manis adalah minuman yang menyegarkan dan populer di berbagai belahan dunia, terutama di Asia Tenggara. Minuman ini terdiri dari teh hitam atau teh hijau yang diseduh kemudian disajikan dingin dengan tambahan gula atau pemanis lainnya sesuai selera. Es teh manis sering disajikan dengan es batu untuk memberikan sensasi kesegaran yang lebih nikmat. Rasanya yang manis dengan sentuhan aroma teh yang khas menjadikan es teh manis sebagai minuman yang cocok dinikmati di berbagai kesempatan, baik sebagai penyejuk di cuaca panas maupun sebagai teman santai di waktu istirahat.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_mie.jpeg",
                price = 10000.00,
                rating = 4.0,
                name = "Mie Goreng",
                desc = "Mie goreng adalah hidangan yang populer dan lezat yang berasal dari Indonesia, namun telah meraih ketenaran di seluruh dunia. Hidangan ini terbuat dari mie yang dimasak dan digoreng bersama dengan berbagai bumbu dan tambahan seperti bawang putih, bawang merah, cabai, sayuran, telur, daging, atau seafood. Mie goreng memiliki cita rasa yang kaya dan bervariasi, mulai dari pedas, gurih, hingga manis tergantung pada preferensi pembuatnya. Hidangan ini sering disajikan panas-panas dan merupakan pilihan yang populer sebagai makanan ringan atau hidangan utama di berbagai kesempatan.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_ondeonde.jpg",
                price = 5000.00,
                name = "Onde Onde",
                rating = 4.0,
                desc = "Onde onde adalah jajanan khas Indonesia yang terkenal dengan kelezatan dan keunikan teksturnya. Terbuat dari adonan tepung ketan yang dibentuk bulat dan diisi dengan gula merah cair, kemudian direbus atau digoreng hingga matang. Setelah matang, onde onde biasanya dilapisi dengan taburan kelapa parut yang memberikan sentuhan tekstur yang lembut dan aroma yang khas. Rasanya manis dari gula merah dan gurih dari kelapa parut membuat onde onde menjadi camilan yang populer dan disukai oleh banyak orang, baik sebagai teman minum teh atau kopi, maupun sebagai hidangan penutup yang lezat.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_pisangmolen.jpg",
                price = 5000.00,
                rating = 4.0,
                name = "Pisang Molen",
                desc = "Pisang molen adalah makanan ringan yang terbuat dari pisang yang dibalut dengan kulit lumpia dan digoreng hingga kecokelatan. Biasanya disajikan sebagai camilan atau hidangan penutup, pisang molen memiliki tekstur renyah di luar dan lembut serta manis di dalamnya. Rasanya yang lezat dan cara penyajiannya yang praktis menjadikannya favorit banyak orang.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_tahupetis.jpg",
                price = 10000.00,
                name = "Tahu Petis",
                rating = 4.0,
                desc = "Tahu petis adalah hidangan Indonesia yang terdiri dari tahu yang digoreng dan disajikan dengan saus petis, sebuah campuran saus yang terbuat dari udang, kecap manis, bawang putih, dan rempah-rempah lainnya. Tahu yang digunakan biasanya memiliki tekstur renyah di luar dan lembut di dalamnya. Kombinasi antara rasa gurih tahu yang kontras dengan kekentalan dan cita rasa khas saus petis menjadikan hidangan ini sangat menggugah selera.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Catalog(
                imageUrl = "https://raw.githubusercontent.com/UlinNuha89/foodies-assets/main/catalog_img/img_lumpia.jpg",
                price = 5000.00,
                name = "Lumpia",
                rating = 4.0,
                desc = "Lumpia adalah makanan yang terdiri dari gulungan kulit lumpia yang diisi dengan campuran sayuran, daging, atau seafood yang sudah diolah, kemudian digoreng hingga kecokelatan. Lumpia sering disajikan dengan saus sambal atau saus kacang sebagai pelengkapnya. Hidangan ini memiliki cita rasa yang lezat dengan kombinasi antara tekstur renyah kulit lumpia dan isian yang gurih dan beragam. Lumpia dapat dinikmati sebagai camilan atau hidangan utama dan merupakan salah satu makanan yang populer di banyak negara Asia Tenggara.",
                location = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            )
        )
    }

}