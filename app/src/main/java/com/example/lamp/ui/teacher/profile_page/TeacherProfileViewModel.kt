import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.UserOnlineDataSourceImpl
import com.example.domain.model.UserResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherProfileViewModel : ViewModel() {
    var liveData = MutableLiveData<UserResponseDTO>()
    var errorMessage = MutableLiveData<String>()
    val userWebService = ApiManager.getUserApi()
    val userOnlineDataSource = UserOnlineDataSourceImpl(userWebService)
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