package pl.rutaz.bookstracker

class ValidateUtils {
    companion object {
        const val EXTRAS = "extras"
        const val ISBN_INVALID = 1
        const val INPUT_EMPTY = 2
        const val NUM_OF_PAGES_INVALID = 3
        const val RATING_INVALID = 4
        const val VALIDATE_SUCCESS = 5

        fun validateInputs(title: String, author: String, isbn: String, numOfPages: String, rating: String): Int {

            if (title.isEmpty() ||
                author.isEmpty() ||
                isbn.isEmpty() ||
                numOfPages.isEmpty() ||
                rating.isEmpty()
            ) {
                return INPUT_EMPTY
            }

            if (numOfPages.toIntOrNull() == null || numOfPages.toIntOrNull() == 0) {
                return NUM_OF_PAGES_INVALID
            }

            if (rating.toIntOrNull() == null || rating.toIntOrNull()!! !in 1..5) {
                return RATING_INVALID
            }

            if (!validateIsbn(isbn)) {
                return ISBN_INVALID
            }

            return VALIDATE_SUCCESS
        }


        private fun validateIsbn(isbn: String): Boolean {
            if (isbn.length != 13) {
                return false
            }

            val lastDigitString = isbn.toCharArray()[isbn.length - 1].toString()
            val lastDigit = lastDigitString.toIntOrNull() ?: return false

            var sum = 0

            for (i in 0..11) {
                val number: Int = Integer.valueOf(isbn.toCharArray()[i].toString())
                sum += if (i % 2 == 0) {
                    number
                } else {
                    number * 3
                }
            }

            var rest = sum % 10

            if (rest != 0) {
                rest = 10 - rest
            }

            return (rest == lastDigit)
        }
    }

}