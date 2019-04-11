package Kotlin2

class Library : Book() {
    override fun setId(ID: Int) {
        bookId = ID
    }

    fun issueBook(borrower: String) {
        if (borrower == "Student")
            println("Book with ID $bookId issued to $borrower for a period of ${Student().getBorrowPeriod()} days")
        else
            println("Book with ID $bookId issued to $borrower for a period of ${Teacher().getBorrowPeriod()} days")

    }
}

abstract class Book {
    var bookId: Int = 0
    abstract fun setId(ID: Int)
}

class Student : BookBorrower {
    override fun getBorrowPeriod(): Int {
        return 7
    }
}

class Teacher : BookBorrower {
    override fun getBorrowPeriod(): Int {
        return 14
    }
}

interface BookBorrower {
    fun getBorrowPeriod(): Int
}

fun main(){
    val library = Library()
    library.setId(1056)
    library.issueBook("Teacher")
}
