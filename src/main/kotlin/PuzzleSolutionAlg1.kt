/**
 * This algorithm tries to apply the first rule to the first house possible
 * and then continues on to the next rule
 * When a rule cannot be applied due ot conflicts then the algorithm goes back to the previous rule
 * and tries to apply it to the next house possible, etc.
 */
class PuzzleSolutionAlg1: PuzzleAlgorithm {

    override fun tryIt(rules: MutableList<Constraint>, houses: HousesList, debug: Boolean): HousesList? {
        var h = houses.clone()
        if (rules.isEmpty()) {
            if (debug)  println(">> all rules applied - puzzle solved")
            return h
        }
        if (debug)  println("** trying rule ${rules[0].name}")
        for (houseIndx in houses.theHouses.indices) {
            // try to apply this rule
            h = houses.clone()
            if (!h.applyConstraint(rules[0], houseIndx))
                continue    // could not apply rule - try next house
            if (debug) {
                println(">> rule ${rules[0].name} successfully applied for house $houseIndx")
                print(">> "); h.print()
            }
            val s = tryIt(rules.subList(1, rules.size), h, debug)   // go and try to apply the next rule
            if (s != null)
                return s    // good solution
        }
        ++count
        if (debug)  println(">> rule ${rules[0].name} could not be applied")
        return null   // run out of houses - cannot apply rule
    }
}