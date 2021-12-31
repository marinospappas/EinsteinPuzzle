/**
 * Einstein 5-houses puzzle solver
 * @author Marinos Pappas
 * @version 1.0 29.12.2021
 */
import kotlin.system.exitProcess

const val NUM_HOUSES = 5
const val NUM_RULES = 16

var houseList = Array(NUM_HOUSES) { House() }

var count = 1

fun printHouses(h: Array<House>) {
    println("Nationality\tColour\tPet\t\tDrink\tCigarettes")
    println("-------------------------------------------------")
    for (i in h.indices) {
        print("${h[i].nationality}\t")
        if (h[i].nationality == Nat.English || h[i].nationality == Nat.Spanish) print("\t")
        print("${h[i].colour}\t")
        if (h[i].colour == Col.Red) print("\t")
        print("${h[i].pet}\t")
        if (h[i].pet == Pet.Fox || h[i].pet == Pet.Dog) print("\t")
        print("${h[i].drink}\t")
        if (h[i].drink == Drnk.Tea || h[i].drink == Drnk.OJ) print("\t")
        print("${h[i].cigarettes}")
        println()
    }
}

fun cloneHouses(houses: Array<House>): Array<House> {
    val h = Array(houses.size) { House() }
    for (i in houses.indices) {
        h[i] = House(houses[i].nationality, houses[i].colour, houses[i].pet, houses[i].drink, houses[i].cigarettes)
    }
    return h
}

fun solveIt(rules: Array<Rule>, houses: Array<House>, ruleIndx: Int): Array<House>? {
    var h = cloneHouses(houses)
    if (ruleIndx <= NUM_RULES) {
        for (houseIndx in houses.indices) {
            // apply next rule
            h = cloneHouses(houses)
            val result = rules[ruleIndx].rule(h, houseIndx)
            if (!result)
                continue    // could not apply rule - try next house
            val s = solveIt(rules, h, ruleIndx+1)   // go and try to apply the next rule
            if (s != null) {
                return s    // good solution
            }
        }
        ++count
        return null   // run out of houses - cannot solve
    } else {
        return h
    }
}

fun main (args: Array<String>) {

    val rulesList = arrayListOf(rulesList0, rulesList1, rulesList2, rulesList3,
        rulesList4, rulesList5, rulesList6, rulesList7)

    val t1 = System.nanoTime()
    for (rules in rulesList) {  // try all 8 rule sets (different combinations of "next to" rules)
        val solution = solveIt(rules, houseList, 1)
        if (solution != null) {
            val t2 = System.nanoTime()
            val tdiff: Long = (t2-t1)/1000
            println("Solved Puzzle $count attempts $tdiff millisecs")
            printHouses(solution)
            exitProcess(0)
        }
    }
    println("Could not solve Puzzle")
}
