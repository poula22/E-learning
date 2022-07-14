import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.UserOnlineDataSourceImpl
import com.example.domain.model.UserResponseDTO
import com.example.domain.repos.data_sources.UserOnlineDataSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.File

class TeacherProfileViewModel : ViewModel() {
    val liveData = MutableLiveData<UserResponseDTO>()
    val errorMessage = MutableLiveData<String>()
    val userWebService = ApiManager.getUserApi()
    val userUpdateLiveData=MutableLiveData<UserResponseDTO>()
    val testLiveData=MutableLiveData<ResponseBody>()
    private val testService=ApiManager.getFileTransferApi()
    val userOnlineDataSource: UserOnlineDataSource = UserOnlineDataSourceImpl(userWebService)


    fun getImage(fileName: String) {
        viewModelScope.launch {
            try {
                testLiveData.value= testService.getImage(fileName)
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }

    fun changeUserImage(image: File) {
        runBlocking {
            try {

                val requestBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", image.name,image.asRequestBody("image/jpeg".toMediaTypeOrNull()))
                    .build()
                userUpdateLiveData.value=userOnlineDataSource.updatePhoto(CONSTANTS.user_id,requestBody)
//                fileLiveData.value=userOnlineSource.u(CONSTANTS.courseId, requestBody)


            }catch (t:Throwable){
                throw t
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                liveData.value = userOnlineDataSource.getUserById(CONSTANTS.user_id)
            }  catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "failed"
                    }
                }
            }

        }
    }

    fun updateUserInfo(id: Int, user: UserResponseDTO) {
        viewModelScope.launch {
            try {
                liveData.value = userOnlineDataSource.updateUserById(id, user)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "successful"
                    }
                }
            }

        }
    }
}