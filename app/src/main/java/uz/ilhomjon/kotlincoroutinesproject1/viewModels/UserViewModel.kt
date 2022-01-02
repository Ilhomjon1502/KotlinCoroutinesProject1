package uz.ilhomjon.kotlincoroutinesproject1.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.ilhomjon.kotlincoroutinesproject1.retrofit.ApiClient
import java.lang.StringBuilder

class UserViewModel : ViewModel(){

    private val liveData = MutableLiveData<String>()

    fun getUsers():LiveData<String>{
        val apiService1 = ApiClient.apiService(ApiClient.BASEURL1)
        val apiService2 = ApiClient.apiService(ApiClient.BASEURL2)

        viewModelScope.launch {
            val async1 = async { apiService1.getUsersFromJsonPlaceHolder() }
            val async2 = async { apiService2.getUsersFromGithub() }

            val await1 = async1.await()
            val await2 = async2.await()

            val stringBuilder = StringBuilder()

            for (user in await1) {
                stringBuilder.append(user.username).append("\n")
            }
            for (githubUser in await2) {
                stringBuilder.append(githubUser.login).append("\n")
            }
            liveData.postValue(stringBuilder.toString())
        }
        return liveData
    }
}