heuristic: cost = 3.3969307170000005, 1 milliseconds
mine: cost = 1.3566775349999998, 65 milliseconds
backtrack: cost = 1.3566775349999998, 135 milliseconds

The improvement I chose to make was in the recursive backtracking
algorithm for solving this. I cut down on the amount of times the
for loop was called by moving the coniditional if statement outside
based on the pseudocode. This put in more pruning which is why it
was faster than the backtracking above, even though they have the
same end cost. These both have a better cost than the heurisitic,
however they are significantly slower, especially when it comes to
bigger files like big15.mtx, which takes around a minute for the
backtracking to run through completely.