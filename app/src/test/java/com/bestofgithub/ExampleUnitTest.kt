package com.bestofgithub

import com.bestofgithub.data.remote.json.Repository
import com.bestofgithub.domain.validation.RepoSearchResponseValidator
import com.google.gson.Gson
import junit.framework.Assert.assertTrue
import org.junit.Test

import java.io.FileReader



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val gson = Gson()

    @Test
    fun testValidationGood() {
        val file = javaClass.classLoader?.getResource("good_data.json")
        val repoData = gson.fromJson(FileReader(file?.file), Repository::class.java)
        assertTrue(RepoSearchResponseValidator().validateAndTransform(listOf(repoData)).size == 1)
    }

    @Test
    fun testValidationBad() {
        val file = javaClass.classLoader?.getResource("bad_data.json")
        val repoData = gson.fromJson(FileReader(file?.file), Repository::class.java)
        assertTrue(RepoSearchResponseValidator().validateAndTransform(listOf(repoData)).isEmpty())
    }
}
