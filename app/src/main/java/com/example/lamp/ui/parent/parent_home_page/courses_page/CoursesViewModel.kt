import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.model.ParentChildCoursesResponseDTO
import kotlinx.coroutines.launch


class CoursesViewModel : ViewModel() {

    var courseService = ApiManager.getCourseApi()
    var courseDataSource = CourseOnlineDataSourceImpl(courseService)
    var courseLiveData = MutableLiveData<List<ParentChildCoursesResponseDTO>>()


    fun getCoursesByStudentId(studentId: Int): List<ParentChildCoursesResponseDTO> {
        viewModelScope.launch {
            try {
                val response = courseDataSource.getCoursesByStudentIdForParent(studentId)
                courseLiveData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return courseLiveData.value!!
    }

}