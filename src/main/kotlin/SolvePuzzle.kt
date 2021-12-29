import java.sql.Time
import java.util.*
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
            //println(">>>>> rule $ruleIndx house $houseIndx result $result")
            if (!result)
                continue    // could not apply rule - try next house
            //printHouses(h)
            val s = solveIt(rules, h, ruleIndx+1)
            if (s != null) {
                //println(">>>>> ruleIndex $ruleIndx returning GOOD solution")
                //printHouses(s)
                return s    // good solution
            }
        }
        //println(">>>>> ruleIndx $ruleIndx returning null")
        ++count;
        return null   // run out of houses - cannot solve
    } else {
        // all rules applied - returned the solved puzzle
        //println(">>>>> ruleIndx $ruleIndx returning solution")
        //printHouses(h)
        return h
    }
}

fun main (args: Array<String>) {

    val rulesList = arrayListOf(rulesList0, rulesList1, rulesList2, rulesList3,
        rulesList4, rulesList5, rulesList6, rulesList7)
    // apply the three definite rules
    rulesList[0][1].rule(houseList, 0)
    rulesList[0][2].rule(houseList, 0)
    rulesList[0][3].rule(houseList, 2)

    val t1 = System.nanoTime()
    for (rules in rulesList) {
        val solution = solveIt(rules, houseList, 4)
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

