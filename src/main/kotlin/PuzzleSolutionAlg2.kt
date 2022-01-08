/**
 * This algorithm first scans the rules and applies those rules that can be applied only on one house
 * (e.g. Norwegian in the first house) it then removes these rules from the list and tries rules that
 * can be applied only on one house.
 * It then applies the next rule to the first house possible and tries again if any rule can be
 * applied only on one house, etc.
 * If a rule cannot be applied, then it goes back to the previous one and tries to apply it to the next house
 */
class PuzzleSolutionAlg2: PuzzleAlgorithm {

    /** check if the rule can be applied only in one house */
    private fun checkRuleCanBeAppliedOnlyOnce(r: Constraint, houses: HousesList): Int {
        var appliedCount = 0
        var index = -1
        for (i in houses.theHouses.indices) {
            val myHouses = houses.clone()
            if (myHouses.applyConstraint(r, i)) {
                appliedCount++
                index = i
            }
        }
        return if (appliedCount == 1) index else -1
    }

    /** apply all the rules in the list that can be applied only once */
    fun applyRulesThatHaveOnlyOneSolution(rules: MutableList<Constraint>, houses: HousesList, debug: Boolean): MutableList<Constraint> {
        var myRules = mutableListOf<Constraint>()
        myRules.addAll(rules)
        for (r in myRules) {
            val houseIndx = checkRuleCanBeAppliedOnlyOnce(r, houses)
            if (houseIndx >= 0) {
                if (debug)  println("!! rule ${r.name} can be applied only once in house $houseIndx")
                houses.applyConstraint(r, houseIndx)
                myRules.remove(r)
                myRules = applyRulesThatHaveOnlyOneSolution(myRules, houses, debug)
                break
            }
        }
        return myRules
    }

    /** puzzle solution */
    override fun tryIt(rules: MutableList<Constraint>, houses: HousesList, debug: Boolean): HousesList? {
            if (debug) {
                println("** trying to solve:"); print(">> "); houses.print()
                println(">> with these rules"); printRules(rules)
            }
        val h = houses.clone()
        val myRules = applyRulesThatHaveOnlyOneSolution(rules, h, debug)
            if (debug) {
                println(">> after the rules that can be applied only once:")
                h.print(); printRules(myRules)
            }
        if (myRules.isEmpty()) {
            if (debug)  println(">> solved puzzle")
            return h       // solved
        }
        // else try the first rule in each house
        for (houseIndx in houses.theHouses.indices) {
            val hTemp = h.clone()
            if (!hTemp.applyConstraint(myRules[0], houseIndx))
                continue    // could not apply rule - try next house
            if (debug) println(">> applied rule ${myRules[0].name} to house $houseIndx")
            // else go and try to solve for the remaining rules
            val s = tryIt(myRules.subList(1,myRules.size), hTemp, debug)
            if (s != null)
                return s
        }
        ++count
        if (debug) println(">> cannot solve this instance - attempt $count")
        return null     // cannot solve
    }
}