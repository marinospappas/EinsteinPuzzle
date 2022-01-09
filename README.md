# Einstein-Puzzle

## Einstein's 5 houses puzzle solver

```
There are five houses. 
  The Englishman lives in the red house 
  The Spaniard owns the dog...
  ...
...who owns the zebra?
```

Complete puzzle rules are [here](PUZZLE.md)

Please note:

- there are two rules that mention "next to"; each of them has been implemented in two variants as "next to" can be interpreted in two different ways: either house(n-1) or house(n+1)
- the rule "Norwegian lives in the first house" has been interpreted as Norwegian in house(0)
- the rule "green house is immediately to the right of ivory house" has been interpreted as house(n) = ivory and house(n+1) = green.

Based on the above we have 4 combinations of the 14 rules

In addition, please note that even if the "green-ivory" rule is reversed the answer is still the same!

## How to run it

```
   PuzzleMain alg0|alg1|alg2 [-debug]
```
The first command line parameter tells the program which algorithm to use (0, 1 or 3) - see below for details.

The `-debug` parameter when present activates debug information.

## Version History

### Version 0

Brute force (as dumb as it gets). 

Start with a list of houses and a set of attributes (nationality, colour, drink, pet, cigarettes) with random values. 
The order that the various attributes appear in the puzzle rules has been chosen as the starting point.

Produce all the possible permutations of the 5 values of the 5 attributes. The number of permutations of 5 items is 5! = 120. 
So all possible combinations are 120 to the power of 5 = just under 25 billion. 

For each combination all the rules are checked one by one. Once a combination for which all the rules are true is identified, the puzzle is solved.

The initial run of this super-dumb algorithm took around **3.6 billion attempts and 18 minutes** executed on Intel i7 2.8Ghz with 8GB RAM.

A small (and obvious) improvement (version 0.1) was to stop applying more rules onto a certain combination once a rule has failed.
This reduced the time to solve to **9 minutes**

Obviously the time to solve with the brute force algorithm depends on the starting combination.
The time to go through all the possible 25 billion combinations would be around 70 minutes.

### Version 1

Apply the rules (but with no specific intelligence)

Start with an empty collection and attempt to apply the rules one by one. 
When a rule is applied on a house (i.e. the attributes defined in the rule are set on that house) then move on to the next rule.
If a rule cannot be applied then move up one level and try to apply the previous rule on another house and continue on.
When all the rules have been successfully applied, we have a solution.

This algorithm arrives at a solution after **3,500 attempts or around 70 milliseconds**, 
which obviously is a significant improvement compared to the super-dumb algorithm above.

There is still room for improvement though. 
In this algorithm the rules are being treated equally and are attempted in the order they have been defined in the puzzle description.
E.g. the rule "Milk in the middle house" is being tried against the first, the second and the third house where it succeeds.
When a rule below this one fails and the algorithm has to go back a level above the "milk" rule, then the "milk" rule is reapplied again on the first and second house until it succeeds again on the third one.
Not to mention that this rule may even fail if e.g. the "Coffee is drunk in the Green house"
has happened to be applied on the middle house in this particular attempt.

It is clear that this logic is not very intelligent.

### Version 2

Intelligent application of the rules - simulates the human method

Start again with an empty collection.
Go through all the rules and check which ones can be applied on **one house** only. 
There two of them in the first pass ("Milk in the middle house" and "Norwegian in the first house").
Apply these rules and take them out of the list.
Then try again to see if any of the remaining rules can be applied on one house only.
And in this pass we are getting the "Norwegian next to the blue house".
Apply these rules and remove them from the list and repeat this process until there no rules left that can be applied on one house only.
(Please note that at this stage we have already reduced the rules from 14 to 11 and this has cut down the number of attempts significantly)

When all the rules that can be applied on only one house have been applied (and forgotten about)
then we implement the next rule on the first house that is possible to implement and start all over, i.e. look for any rules that can be implemented on only one house, and on and on.

The algorithm ends when all the rules have been successfully applied.

This method gets to the solution in only **28 attempts** (a reduction by a factor of more than 100 compared to v1)
but only in half the time. This is more-or-less the number of attempts a puzzle-inclined human would take to solve it.

The reason for the negligible improvement in the time to solve is that the logic used to apply the rules is now significantly more complicated,
so an attempt to apply a rule takes much longer than in version one. The significant reduction in the number of attempts makes it worth it though.

### Version 3

Improved the definition of the rules. In the previous versions the code to apply the rules had been coded in each rule 
which made the program (a) very long and (b) not easy to scale.

In version 3 the logic that applies the rules has been coded as a generic function and this allows each rule to be 
encoded as a set of data (that this function is called to apply)