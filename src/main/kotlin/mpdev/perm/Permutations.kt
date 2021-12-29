package mpdev.perm

class Permutations {

    var count = 0L

    var elements1 = arrayOf("Spanish", "English", "Japanese", "German", "Norwegian")
    var elements2 = arrayOf("Blue", "Black", "Red", "Orange", "White")
    var elements3 = arrayOf("Coffee", "Tea", "Water", "Soda", "Gin")
    var elements4 = arrayOf("Dog", "Cat", "Mouse", "Canary", "Horse")
    var elements5 = arrayOf("HP", "Dell", "IBM", "ASUS", "APPLE")
    var n = 5

    private fun swap(input: Array<String>, a: Int, b: Int) {
        val tmp = input[a]
        input[a] = input[b]
        input[b] = tmp
    }

    private fun printArray(input1: Array<String>, input2: Array<String>, input3: Array<String>,
                            input4: Array<String>, input5: Array<String>) {
        print('\n')
        for (j in input1) {
            print("$j ")
        }
        print('\n')
        for (j in input2) {
            print("$j ")
        }
        print('\n')
        for (j in input3) {
            print("$j ")
        }
        print('\n')
        for (j in input4) {
            print("$j ")
        }
        print('\n')
        for (j in input5) {
            print("$j ")
        }
        print('\n')
    }

    fun array1Permutations() {
        val indexes = IntArray(n)
        for (i in 0 until n) {
            indexes[i] = 0
        }
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements1, if (i % 2 == 0) 0 else indexes[i], i)
                array2Permutations()
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }

        printArray(elements1, elements2, elements3, elements4, elements5)
        println("\nCount = $count")
    }

    fun array2Permutations() {
        val indexes = IntArray(n)
        for (i in 0 until n) {
            indexes[i] = 0
        }
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements2, if (i % 2 == 0) 0 else indexes[i], i)
                array3Permutations()
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }
    }

    fun array3Permutations() {
        val indexes = IntArray(n)
        for (i in 0 until n) {
            indexes[i] = 0
        }
        //printArray(elements1, elements2, elements3)
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements3, if (i % 2 == 0) 0 else indexes[i], i)
                array4Permutations()
                ++count
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }
    }

    fun array4Permutations() {
        val indexes = IntArray(n)
        for (i in 0 until n) {
            indexes[i] = 0
        }
        //printArray(elements1, elements2, elements3)
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements4, if (i % 2 == 0) 0 else indexes[i], i)
                array5Permutations()
                ++count
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }
    }

    fun array5Permutations() {
        val indexes = IntArray(n)
        for (i in 0 until n) {
            indexes[i] = 0
        }
        //printArray(elements1, elements2, elements3)
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements5, if (i % 2 == 0) 0 else indexes[i], i)
                //printArray(elements1, elements2, elements3)
                ++count
                indexes[i]++
                i = 0
            } else {
                indexes[i] = 0
                i++
            }
        }
    }
}