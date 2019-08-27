package pl.rutaz.bookstracker

import org.hamcrest.CoreMatchers.any
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


/**
 * ValidateUtils class unit test
 *
 */
class ValidateUtilsTest {

    private val validNumOfPages: String = "12"
    private val validRandomRating: Int = (1..5).shuffled().first()
    private val validIsbn: String = "9788374248327"
    private val anyString = any(String::class.java).toString()
    private val success: Int = ValidateUtils.VALIDATE_SUCCESS

    @Test
    fun `validate inputs with empty input`() {
        val result: Int = ValidateUtils.INPUT_EMPTY
        val anyString = any(String::class.java).toString()

        assertEquals(
            result, ValidateUtils.validateInputs(
                "",
                anyString,
                anyString,
                anyString,
                anyString
            )
        )

        assertEquals(
            result, ValidateUtils.validateInputs(
                anyString,
                "",
                anyString,
                anyString,
                anyString
            )
        )

        assertEquals(
            result, ValidateUtils.validateInputs(
                anyString,
                anyString,
                "",
                anyString,
                anyString
            )
        )

        assertEquals(
            result, ValidateUtils.validateInputs(
                anyString,
                anyString,
                anyString,
                "",
                anyString
            )
        )

        assertEquals(
            result, ValidateUtils.validateInputs(
                anyString,
                anyString,
                anyString,
                anyString,
                ""
            )
        )
    }

    @Test
    fun `validate inputs with invalid number of pages input`() {
        val result: Int = ValidateUtils.NUM_OF_PAGES_INVALID

        assertEquals(
            result, ValidateUtils.validateInputs(
                anyString,
                anyString,
                validIsbn,
                anyString,
                validRandomRating.toString()
            )
        )
    }

    @Test
    fun `validate inputs with invalid rating input`() {
        val result: Int = ValidateUtils.RATING_INVALID

        assertTrue(validRandomRating >= 1)
        assertTrue(validRandomRating <= 5)

        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                anyString,
                validNumOfPages,
                anyString
            )
        )
    }

    @Test
    fun `validate isbn`() {
        val result: Int = ValidateUtils.ISBN_INVALID

        //valid ISBN
        assertEquals(
            success,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                validIsbn,
                validNumOfPages,
                validRandomRating.toString()
            )
        )

        //isbn = any String
        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                anyString,
                validNumOfPages,
                validRandomRating.toString()
            )
        )

        //isbn length OK but not valid
        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                "1234567890123",
                validNumOfPages,
                validRandomRating.toString()
            )
        )

        //isbn too short
        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                "123456789",
                validNumOfPages,
                validRandomRating.toString()
            )
        )

        //isbn not numbers, length OK
        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                "xxxxxxxxxxxxx",
                validNumOfPages,
                validRandomRating.toString()
            )
        )

        //isbn not numbers, too short
        assertEquals(
            result,
            ValidateUtils.validateInputs(
                anyString,
                anyString,
                "x",
                validNumOfPages,
                validRandomRating.toString()
            )
        )
    }

}