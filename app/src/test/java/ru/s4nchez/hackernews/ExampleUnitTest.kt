package ru.s4nchez.hackernews

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }

    @Test
    fun with_arguments() {
//        val c = mock(Comparable<*>::class.java)
//        `when`(c.compareTo("Test")).thenReturn(1)
//        assertEquals(1, c.compareTo("Test"))

        val str = Mockito.mock(String::class.java)
        Mockito.`when`(str == "Fuck").thenReturn(true)
        assertEquals(true, str == "Fuck")
    }
}
