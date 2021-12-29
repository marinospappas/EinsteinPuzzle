package mpdev.perm

import kotlin.system.exitProcess

var count = 0L

var nationality = arrayOf("Norwegian", "English", "Japanese", "Ukranian", "Spanish")
var colour = arrayOf("Blue", "Green", "Red", "Ivory", "Yellow")
var drink = arrayOf("Coffee", "Tea", "Water", "Milk", "OrangeJuice")
var pet = arrayOf("Snails", "Dog", "Fox", "Zebra", "Horse")
var cigarette = arrayOf("OldGold", "Kool", "Chesterfield", "LuckyStrike", "Parliament")
var n = 5

private fun swap(input: Array<String>, a: Int, b: Int) {
    val tmp = input[a]
    input[a] = input[b]
    input[b] = tmp
}

private fun printPuzzle(input1: Array<String>, input2: Array<String>, input3: Array<String>,
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

fun allPermutations() {
    val indexes = IntArray(n)
    for (i in 0 until n) {
        indexes[i] = 0
    }
    var i = 0
    while (i < n) {
        if (indexes[i] < i) {
            swap(nationality, if (i % 2 == 0) 0 else indexes[i], i)
            colourPermutations()
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }

    println("Could not solve")
    printPuzzle(nationality, colour, drink, pet, cigarette)
    println("\nCount = $count")
}

fun colourPermutations() {
    val indexes = IntArray(n)
    for (i in 0 until n) {
        indexes[i] = 0
    }
    var i = 0
    while (i < n) {
        if (indexes[i] < i) {
            swap(colour, if (i % 2 == 0) 0 else indexes[i], i)
            drinkPermutations()
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }
}

fun drinkPermutations() {
    val indexes = IntArray(n)
    for (i in 0 until n) {
        indexes[i] = 0
    }
    //printArray(elements1, elements2, elements3)
    var i = 0
    while (i < n) {
        if (indexes[i] < i) {
            swap(drink, if (i % 2 == 0) 0 else indexes[i], i)
            petPermutations()
            ++count
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }
}

fun petPermutations() {
    val indexes = IntArray(n)
    for (i in 0 until n) {
        indexes[i] = 0
    }
    //printArray(elements1, elements2, elements3)
    var i = 0
    while (i < n) {
        if (indexes[i] < i) {
            swap(pet, if (i % 2 == 0) 0 else indexes[i], i)
            cigarettePermutations()
            ++count
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }
}

fun cigarettePermutations() {
    val indexes = IntArray(n)
    for (i in 0 until n) {
        indexes[i] = 0
    }
    //printArray(elements1, elements2, elements3)
    var i = 0
    while (i < n) {
        if (indexes[i] < i) {
            swap(cigarette, if (i % 2 == 0) 0 else indexes[i], i)
            ++count
            if (verifyPuzzle()) {
                println("Solved - count: $count")
                printPuzzle(nationality, colour, drink, pet, cigarette)
                exitProcess(0)
            }
            indexes[i]++
            i = 0
        } else {
            indexes[i] = 0
            i++
        }
    }
}


fun main (args: Array<String>) {
    allPermutations()
}

