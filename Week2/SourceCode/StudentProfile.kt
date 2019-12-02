import java.time.Clock.system
import java.util.Scanner

class StudentProfile(var studentID: Int, var name: String?, var age: Int, var address: String?) {
    fun getAllinfo() {
        print(message = studentID )
        print(" ")== print(age)
        print(" ")  == println(address)
    }

    companion object {
        private val count: Int = 0
    }
}