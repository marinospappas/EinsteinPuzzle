/**
 * Einstein 5-houses puzzle solver
 * @author Marinos Pappas
 * @version 2.0 01.01.2022
 */

const val NUM_HOUSES = 5
const val NUM_RULES = 16

var houseList = Array(NUM_HOUSES) { House() }

var count = 1

fun printHouses(h: Array<House>) {
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

    val rulesList = arrayListOf(rulesList0a, rulesList1a, rulesList2a, rulesList3a,
        rulesList4a, rulesList5a, rulesList6a, rulesList7a,
        rulesList0b, rulesList1b, rulesList2b, rulesList3b,
        rulesList4b, rulesList5b, rulesList6b, rulesList7b)

    println("Trying all solutions")
    println("\nNationality\tColour\tPet\t\tDrink\tCigarettes")
    val t1 = System.currentTimeMillis()
    var solutionsCount = 0
    for (rules in rulesList) {  // try all 16 rule sets (different combinations of "next to" rules)
        val solution = solveIt(rules, houseList, 1)
        if (solution != null) {
            ++solutionsCount
            printHouses(solution)
        }
    }
    val t2 = System.currentTimeMillis()
    val tdiff: Long = t2-t1
    if (solutionsCount > 0)
        println("\nSolved Puzzle $solutionsCount solutions")
    else
        println("\nCould not solve Puzzle")
    println("$count attempts $tdiff millisecs")
}
