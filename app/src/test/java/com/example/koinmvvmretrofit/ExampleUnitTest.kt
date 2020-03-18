package com.example.koinmvvmretrofit

import android.os.Build
import com.example.koinmvvmretrofit.data.model.Repo
import com.example.koinmvvmretrofit.data.rest.RepoService
import com.example.koinmvvmretrofit.ui.ListPostViewModel
import com.google.gson.Gson
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.net.ssl.HttpsURLConnection
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ExampleUnitTest : BaseTest() {
    private val mRepoService: RepoService by inject()
    private val mGson: Gson by inject()

    private lateinit var mListPostViewModel: ListPostViewModel
    private lateinit var EXPECTED_USER: List<Repo>


    override fun isMockServerEnabled(): Boolean = true

    override fun setUp() {
        super.setUp()
        mListPostViewModel = ListPostViewModel(mRepoService)
        getUserList()
    }


    @Test
    fun getUser() {
        this.mockHttpResponse("getUserList_whenSuccess.json", HttpsURLConnection.HTTP_OK)
        assertEquals(
            null,
            this.mListPostViewModel.repos.value,
            "UserList should be null because stream not started yet"
        )

        mListPostViewModel.fetchRepos()
        mListPostViewModel.repos.observeForever {

            assertEquals(EXPECTED_USER, this.mListPostViewModel.repos.value, "User must be matched")
            assertEquals(
                false,
                this.mListPostViewModel.loading.value,
                "Should be reset to 'false' because stream ended"
            )
            assertEquals(
                false,
                this.mListPostViewModel.repoLoadError.value,
                "No error must be found"
            )


            //Error Test Cases
            assertEquals(
                null,
                this.mListPostViewModel.repos.value,
                "User List must be null because of http error"
            )
            assertEquals(
                false,
                this.mListPostViewModel.loading.value,
                "Should be reset to 'false' because stream ended"
            )
            assertEquals(
                false,
                this.mListPostViewModel.repoLoadError.value,
                "Error value must not be empty"
            )

        }

    }

    private fun getUserList() {
        val json = getJson("getUserList_whenSuccess.json")
        EXPECTED_USER = mGson.fromJson(json, Array<Repo>::class.java).toList()
    }


    @Test
    fun loginValidator_checkEmail() {
        val email = "aakash@alchemytech.ca"
        assertTrue(
            String.format("Email validity test failed for %s", email),
            mListPostViewModel.checkEmail(email)
        )
    }

    @Test
    fun loginValidator_checkPassword() {
        val password = ""
        return assertTrue(
            String.format("Password validity test failed for %s", password),
            mListPostViewModel.checkPassword(password)
        )
    }
}
