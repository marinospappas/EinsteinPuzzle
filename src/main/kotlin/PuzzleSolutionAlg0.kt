/**
 * This algorithm tries to apply the first rule to the first house possible
 * and then continues on to the next rule
 * When a rule cannot be applied due ot conflicts then the algorithm goes back to the previous rule
 * and tries to apply it to the next house possible, etc.
 */
class PuzzleSolutionAlg0: PuzzleAlgorithm {


    val myArray0 = arrayOf(Nat.English, Nat.Spanish, Nat.Ukrainian, Nat.Norwegian, Nat.Japanese)
    val myArray1 = arrayOf(Col.Red, Col.Green, Col.Ivory, Col.Yellow, Col.Blue)
    val myArray2 = arrayOf(Pet.Dog, Pet.Snails, Pet.Fox, Pet.Horse, Pet.Zebra)
    val myArray3 = arrayOf(Drnk.Coffee, Drnk.Tea, Drnk.Milk, Drnk.OJ, Drnk.Water)
    val myArray4 = arrayOf(Cig.OldGold, Cig.Kools, Cig.Chesterfields, Cig.LuckyStrike, Cig.Parliaments)
/* the solution
    val myArray0 = arrayOf(Nat.Norwegian, Nat.Ukrainian, Nat.English, Nat.Spanish, Nat.Japanese)
    val myArray1 = arrayOf(Col.Yellow, Col.Blue, Col.Red, Col.Ivory, Col.Green)
    val myArray2 = arrayOf(Pet.Fox, Pet.Horse, Pet.Snails, Pet.Dog, Pet.Zebra)
    val myArray3 = arrayOf(Drnk.Water, Drnk.Tea, Drnk.Milk, Drnk.OJ, Drnk.Coffee)
    val myArray4 = arrayOf(Cig.Kools, Cig.Chesterfields, Cig.OldGold, Cig.LuckyStrike, Cig.Parliaments)
 */
    val n = NUM_HOUSES

    fun swap(input: Array<Any>, a: Int, b: Int) {
        val tmp = input[a]
        input[a] = input[b]
        input[b] = tmp
    }

    // the permutations of the 5 nationalities
    fun permutations0(houses: HousesList): HousesList? {
        val indexes = Array(n) { 0 }
        var h = permutations1(houses)
        if (h != null)
            return h
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(myArray0 as Array<Any>, if (i % 2 == 0) 0 else indexes[i], i)
                h = permutations1(houses)
                if (h != null)
                    return h
                indexes[i]++
                i = 0
            }
            else
                indexes[i++] = 0
        }
        return null
    }
    // the permutations of the 5 colours
    fun permutations1(houses: HousesList): HousesList? {
        val indexes = Array(n) { 0 }
        var h = permutations2(houses)
        if (h != null)
            return h
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(myArray1 as Array<Any>, if (i % 2 == 0) 0 else indexes[i], i)
                h = permutations2(houses)
                if (h != null)
                    return h
                indexes[i]++
                i = 0
            }
            else
                indexes[i++] = 0
        }
        return null
    }
    // the permutations of the 5 pets
    fun permutations2(houses: HousesList): HousesList? {
        val indexes = Array(n) { 0 }
        var h = permutations3(houses)
        if (h != null)
            return h
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(myArray2 as Array<Any>, if (i % 2 == 0) 0 else indexes[i], i)
                h = permutations3(houses)
                if (h != null)
                    return h
                indexes[i]++
                i = 0
            }
            else
                indexes[i++] = 0
        }
        return null
    }
    // the permutations of the 5 drinks
    fun permutations3(houses: HousesList): HousesList? {
        val indexes = Array(n) { 0 }
        var h = permutations4(houses)
        if (h != null)
            return h
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(myArray3 as Array<Any>, if (i % 2 == 0) 0 else indexes[i], i)
                h = permutations4(houses)
                if (h != null)
                    return h
                indexes[i]++
                i = 0
            }
            else
                indexes[i++] = 0
        }
        return null
    }
    // the permutations of the 5 cigarette brands
    fun permutations4(houses: HousesList): HousesList? {
        val indexes = Array(n) { 0 }
        var h = onePermutation(houses)
        if (h != null)
            return h
        var i = 0
        while (i < n) {
            if (indexes[i] < i) {
                swap(myArray4 as Array<Any>, if (i % 2 == 0) 0 else indexes[i], i)
                h = onePermutation(houses)
                if (h != null)
                    return h
                indexes[i]++
                i = 0
            }
            else
                indexes[i++] = 0
        }
        return null
    }

    // the 14 rules
    fun rule1(myH: HousesList) = myH.getAttrPos(Nat.English) == myH.getAttrPos(Col.Red)
    fun rule2(myH: HousesList) = myH.getAttrPos(Nat.Spanish) == myH.getAttrPos(Pet.Dog)
    fun rule3(myH: HousesList) = myH.getAttrPos(Drnk.Coffee) == myH.getAttrPos(Col.Green)
    fun rule4(myH: HousesList) = myH.getAttrPos(Nat.Ukrainian) == myH.getAttrPos(Drnk.Tea)
    fun rule5(myH: HousesList) = myH.getAttrPos(Col.Green) == myH.getAttrPos(Col.Ivory)+1
    fun rule6(myH: HousesList) = myH.getAttrPos(Cig.OldGold) == myH.getAttrPos(Pet.Snails)
    fun rule7(myH: HousesList) = myH.getAttrPos(Cig.Kools) == myH.getAttrPos(Col.Yellow)
    fun rule8(myH: HousesList) = myH.getAttrPos(Drnk.Milk) == 2
    fun rule9(myH: HousesList) = myH.getAttrPos(Nat.Norwegian) == 0
    fun rule10(myH: HousesList) = (myH.getAttrPos(Cig.Chesterfields)-myH.getAttrPos(Pet.Fox) == 1)
            || (myH.getAttrPos(Cig.Chesterfields)-myH.getAttrPos(Pet.Fox) == -1)
    fun rule11(myH: HousesList) = (myH.getAttrPos(Cig.Kools)-myH.getAttrPos(Pet.Horse) == 1)
            || (myH.getAttrPos(Cig.Kools)-myH.getAttrPos(Pet.Horse) == -1)
    fun rule12(myH: HousesList) = myH.getAttrPos(Cig.LuckyStrike) == myH.getAttrPos(Drnk.OJ)
    fun rule13(myH: HousesList) = myH.getAttrPos(Nat.Japanese) == myH.getAttrPos(Cig.Parliaments)
    fun rule14(myH: HousesList) = myH.getAttrPos(Nat.Norwegian) == myH.getAttrPos(Col.Blue)-1

    // check the 14 rules against a specific combination
    fun onePermutation(houses: HousesList): HousesList? {
        ++count
        if (count % 100000000 == 0L) {
            val t2 = System.currentTimeMillis()
            println("count: ${count / 1000000} million - time: ${(t2-t1)/1000/60} minutes")
        }
        val myH = houses.clone()
        for (i in 0..NUM_HOUSES-1)
            myH.theHouses[i] = House(myArray0[i], myArray1[i], myArray2[i], myArray3[i], myArray4[i])

        if (rule1(myH) && rule2(myH) && rule3(myH) && rule4(myH) && rule5(myH) && rule6(myH) && rule7(myH) && rule8(myH)
            && rule9(myH) && rule10(myH) && rule11(myH) && rule11(myH) && rule12(myH) && rule13(myH) && rule14(myH))
            return myH
        else
            return null
    }

    /**
     * the parameter rules is not used in the brute force algorithm
     * the rules have been implemented here as simple if checks
     */
    override fun tryIt(rules: MutableList<Constraint>, houses: HousesList, debug: Boolean): HousesList? {
        return permutations0(houses)
    }

}