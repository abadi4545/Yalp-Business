package com.arkam.yelp.model

import com.google.gson.annotations.SerializedName

// NOTE: use @SerializedName when the name of the key does not match the name of the local variable

data class YelpSearchResult (
    val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

data class YelpAutoCompleteResult(
    @SerializedName("terms") val terms: List<YelpTerm>,
    @SerializedName("categories") val categories: List<YelpCategory>
)

data class YelpRestaurant (
    val id: String,
    val name: String,
    val rating: Double,
    val price: String,
    @SerializedName("review_count") val numReviews: Int,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String,
    val categories: List<YelpCategory>,
    val location: YelpLocation,
    @SerializedName("coordinates") val coordinate: YelpCoordinate
) {
    fun displayDistance(): String {
        val milesPerMeter = 0.000621371
        val distanceInMiles = "%.2f".format(distanceInMeters * milesPerMeter)
        return "$distanceInMiles mi"
    }
}

data class YelpRestaurantDetail (
    val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_claimed") val isClaimed: String,
    @SerializedName("is_closed") val isClosed: String,
    @SerializedName("url") val url: String,
    @SerializedName("display_phone") val phone: String,
    @SerializedName("review_count") val numReviews: Int,
    val categories: List<YelpCategory>,
    val rating: Double,
    val location: YelpLocation,
    val photos: List<String>,
    val price: String,
    @SerializedName("coordinates") val coordinate: YelpCoordinate,
    // hours
    val transactions: List<String>
)


data class YelpCategory (
    val title: String
)
data class YelpTerm(
    val text: String
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)
data class YelpCoordinate(
    @SerializedName("latitude") val lat : Float,
    @SerializedName("longitude") val lon: Float
)
data class YelpReviews (
    val reviews: List<YelpReview>,
    val total: Int
)

data class YelpReview(
    val id: String,
    val rating: Double,
    val user: YelpUser,
    val text: String,
    @SerializedName("time_created") val timestamp: String,
    val url: String
)

data class YelpUser(
    val id: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    val name: String
)