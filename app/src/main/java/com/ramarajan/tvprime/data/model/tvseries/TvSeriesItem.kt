package com.ramarajan.tvprime.data.model.tvseries
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TvSeriesItem(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_name")
    val originalTitle: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirAt: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(overview)
        parcel.writeString(id)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(firstAirAt)
        parcel.writeValue(popularity)
        parcel.writeValue(voteAverage)
        parcel.writeValue(adult)
        parcel.writeValue(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvSeriesItem> {
        override fun createFromParcel(parcel: Parcel): TvSeriesItem {
            return TvSeriesItem(parcel)
        }

        override fun newArray(size: Int): Array<TvSeriesItem?> {
            return arrayOfNulls(size)
        }
    }
}