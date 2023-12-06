package com.example.knockoffspotify.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsTest {
    @Test
    fun `can convert date correctly`() {
        //given
        val dateString = "2023-10-20T00:00:00-07:00"

        //when
        val readableDate = dateString.toReadableDate("")

        //then
        assertEquals("20.10.2023", readableDate)
    }

    @Test
    fun `when date is not set returns error message`() {
        val dateString = ""

        val readableDate = dateString.toReadableDate("error")

        assertEquals("error", readableDate)
    }

    @Test
    fun `when date is set but in the wrong format returns error message`() {
        val dateString = "10-2020-23T00:00:00-07:00"

        val readableDate = dateString.toReadableDate("error")

        assertEquals("error", readableDate)
    }

    @Test
    fun `when date is set but in not parcelable returns error message`() {
        val dateString = "10202023T0000:00-07:00"

        val readableDate = dateString.toReadableDate("error")

        assertEquals("error", readableDate)
    }
}