package pl.rutaz.bookstracker

import android.widget.EditText

open class Utils {
    companion object {
        const val EXTRAS = "extras"
        const val ISBN_INVALID = 1
        const val INPUT_EMPTY = 2
        const val NUM_OF_PAGES_INVALID = 3
        const val RATING_INVALID = 4
        const val VALIDATE_SUCCESS = 5

        fun validateInputs(titleEt: EditText, authorEt: EditText, isbnEt: EditText, numOfPagesEt: EditText, ratingEt: EditText ) : Int {

            val isbn = isbnEt.text.toString()
            val numOfPages = numOfPagesEt.text.toString().toIntOrNull()
            val rating = ratingEt.text.toString().toIntOrNull()

            if (titleEt.text.isEmpty() ||
                authorEt.text.isEmpty() ||
                isbnEt.text.isEmpty() ||
                numOfPagesEt.text.isEmpty() ||
                ratingEt.text.isEmpty()
            ) {
                return INPUT_EMPTY
            }

            if (numOfPages == null || numOfPages == 0){
                return NUM_OF_PAGES_INVALID
            }

            if (rating == null || rating !in 1..5 ) {
                return RATING_INVALID
            }

            if (!validateIsbn(isbn)){
                return ISBN_INVALID
            }

            return VALIDATE_SUCCESS
        }

        private fun validateIsbn(isbn : String) : Boolean {
            if (isbn.length != 13) {
                return false
            }

            val lastDigit = Integer.valueOf(isbn.toCharArray()[isbn.length-1].toString())
            var sum = 0

            for(i in 0..11){
                val number : Int = Integer.valueOf(isbn.toCharArray()[i].toString())
                sum += if (i % 2 == 0){
                    number
                } else {
                    number * 3
                }
            }

            var rest = sum % 10

            if (rest != 0){
                rest = 10 - rest
            }

            return (rest == lastDigit)
        }
    }

}