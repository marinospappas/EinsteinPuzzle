/**
 * Einstein 5-houses puzzle solver
 * @author Marinos Pappas
 * @version 3.0 09.01.2022
 */

import kotlin.system.exitProcess

// unsuccessful attempt count
var count: Long = 0

// the solution algorithm (we have more than one)
interface PuzzleAlgorithm {
    fun tryIt(rules: MutableList<Constraint>, houses: HousesList, debug: Boolean = false): HousesList?
}
lateinit var puzzleSolution: PuzzleAlgorithm

// debug flag
var debug = false

// usage message
const val usage = "usage: PuzzleMain alg0|alg1|alg2 [-debug]"
// abort program
fun abort(msg: String) {
    System.err.println(msg)
    exitProcess(1)
}

// process command line arguments
fun checkCmdlineArgs(args: Array<String>) {
    if (args.isEmpty() || args.size > 2)
        abort(usage)
    when (args[0]) {
        "alg0" -> puzzleSolution = PuzzleSolutionAlg0()
        "alg1" -> puzzleSolution = PuzzleSolutionAlg1()
        "alg2" -> puzzleSolution = PuzzleSolutionAlg2()
        else -> abort("invalid parameter ${args[0]}\n$usage")
    }
    if (args.size == 2)
        if (args[1] == "-debug")
            debug = true
        else
            abort("invalid option ${args[1]}\n$usage")
}

val t1 = System.currentTimeMillis()

// main program
fun main (args: Array<String>) {
    val houseList = Array(NUM_HOUSES) { House() }
    val myHouses = HousesList(houseList)
    val rulesList = arrayListOf(rulesList0, rulesList1, rulesList2, rulesList3)

    checkCmdlineArgs(args)
    println("Trying to solve...")
    println("\nNationality\tColour\tPet\t\tDrink\tCigarettes")
    var solutionsCount = 0

    for (rules in rulesList) {  // try all 4 rule sets (different combinations of "next to" rules)
        ///////////////////////////////////////////////////////////////
        var solvedPuzzle = puzzleSolution.tryIt(rules, myHouses, debug)
        ///////////////////////////////////////////////////////////////
        if (solvedPuzzle != null) {
            solvedPuzzle = puzzleSolution.tryIt(questionsList, solvedPuzzle)
            if (solvedPuzzle != null) {
                ++solutionsCount
                println("\nafter $count attempts")
                solvedPuzzle.print()
                break
            }
        }
    }
    val t2 = System.currentTimeMillis()
    val tdiff: Long = t2-t1
    if (solutionsCount > 0)
        println("\nSolved Puzzle $solutionsCount solution(s)")
    else
        println("\nCould not solve Puzzle")
    println("$count attempts $tdiff millisecs")
}
