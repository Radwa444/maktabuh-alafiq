import android.os.Parcel
import android.os.Parcelable

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val price: Double,
    val publisher: String,
    val rating: Double,
    val image: String,
    val yearOfPublication: Int,
    val pages: Int
) : Parcelable {
    constructor() : this(0, "", "", "", 4.5, "", 4.8, "", 45, 45)

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeString(publisher)
        parcel.writeDouble(rating)
        parcel.writeString(image)
        parcel.writeInt(yearOfPublication)
        parcel.writeInt(pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
