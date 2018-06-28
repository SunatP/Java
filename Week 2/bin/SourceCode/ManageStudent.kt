import java.util.Scanner

object ManageStudent {
    @JvmStatic
    fun main(fdxzfdxf: Array<String>) {
        val std1 = StudentProfile(6088130, "Sunat", 18, "Bankrub")
        val std2 = StudentProfile(6088124, "Chawna", 2, "Bangrachun")
        std1.getAllinfo()
        std2.getAllinfo()
        agediff(std1, std2)
    }

    fun agediff(std1: StudentProfile, std2: StudentProfile) {
        print("age diff is ") == println(Math.abs(std1.age - std2.age))

    }

}