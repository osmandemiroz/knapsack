# Yet another optimization challenge: the 0–1 knapsack problem and the hard instances

## Problem description

The [0–1 knapsack](https://www.sciencedirect.com/science/article/pii/S0925231223007531) problem (KP01) is a classical NP-hard combinatorial optimization problem.
KP01 has many applications in real-world complex problems such as resource distribution, portfolio optimization, etc.
In KP01 problem, one is given a knapsack with an _integer_ capacity and a **set** of  items, which each have an integer profit and an integer weight.
The goal is to select a subset of items to put into the knapsack such that the total value is maximized and the total weight does not exceed the knapsack capacity.

The task is to Design and Implement the following(s)

Phase 1:  Create java class(es) to represent a 0–1 knapsack problem instance given in the format described in the [GitHub repository](https://github.com/JorikJooken/knapsackProblemInstances).
A dataset of 3240 problem instances is made publicly available by [Jooken, Leyman, and De Causmaecker (2022)](https://www.sciencedirect.com/science/article/pii/S037722172101016X). 
Each problem instance is distributed as a text file named *test.in*.
The first line of the file represents the number of items, *n*. Each of the *n* following lines describe an item and contains 3 integers: 
* the id of the item, 
* the profit of the item and 
* the weight of the item. 
The last line contains an integer describing the knapsack capacity, *c*.

For example, if the *test.in* file contains the following:

```
3
1 3 8
2 2 8
3 9 1
10
```

This describes a problem instance in which there are *n*=3 items and the knapsack has a capacity of *c*=10.
The item with id 1 has a profit of 3 and a weight of 8.
The item with id 2 has a profit of 2 and a weight of 8.
The item with id 3 has a profit of 9 and a weight of 1.

Hint: To represent an item, the following Record declaration could be used.
Record classes, which are a special kind of class, help to model plain data aggregates with less ceremony than normal classes.
A record declaration specifies in a header a description of its contents; the appropriate accessors, constructor, equals, hashCode, and toString methods are created automatically.
A record's fields are final because the class is intended to serve as a simple "data carrier"; thus Record classes are immutable.

``` java
public record Item(int id, long profit, long weight) {

    double unitValue() {
        return (double) profit / weight;
    }
}
```

Please notice that **long** is used here instead of **int**. 
This is because 3240 problem instances, which can be downloaded from the [GitHub repository](https://github.com/JorikJooken/knapsackProblemInstances),
contain large/big integer numbers that exceed the int boundary. Thus, the primitive type long is required.

Phase 2: Implement a method that checks for the trivial case of the KP01 problem. Also write a junit test case for it.
The method should return boolean=true when the bag that we are trying to fill is larger than the sum of the all items.
In other words; if all items fit in to our bag, then this is a trivial problem instance since the solution is to just pick all items!

Phase 3: Implement (i) a strategy that generates a random solution to the KP01 problem,
(ii) a [greedy algorithm](https://www.geeksforgeeks.org/greedy-algorithms/) to generate a greedy solution to the KP01 problem.

Random solution is a [randomized algorithm](https://www.slideshare.net/anniyappa/randomized-algorithms-ver-10), which generates different solution each time when it is run.
Greedy solution (hint: sort descending the items according to unit price [i.e. profit/weight] ) is deterministic on the other hand.

Phase 4: Generate one million random solutions and then print the best (i.e. maximum profit) of them for a given problem instance.
Of course do not keep all solutions in the memory! Here is a pseudocode
``` java
long maxProfit = Long.MIN_VALUE;
Solution best = null;
for(int i=0;i<1_000_000_000;i++)
{
   Solution randomSolution = generateRandomSolution();
   if(randomSolution.totalProfit() > maxProfit)
   {
        maxProfit = randomSolution.totalProfit();
        best = randomSolution;
   }
}
best.print();

int max = Integer.MIN_VALULE;

for(int i=0;i<n;i++)
{

if arr[i] > max
max = arr[i];

}


int max = -1;

for(int i=0;i<n;i++)
{
int aRandomNumber = new Random.nextInt();
if (aRandomNumber > max)
max = aRandomNumber;

}

sout max

int max = -1;

 IntStream.range(0, 1_000_000_000).boxed().parallel().forEach(i -> {
 
int aRandomNumber = new Random.nextInt();
syncohrixed(this){
if (aRandomNumber > max)
max = aRandomNumber;
 }
 }


AtomicInteger max = new AtomicInteger(-1);

 IntStream.range(0, 1_000_000_000).boxed().parallel().forEach(i -> {
 
int aRandomNumber = new Random.nextInt();

if (aRandomNumber > max.getPlain())
max.set(aRandomNumber);
 }

```
Phase 5: Repeat phase 4 using parallelism (e.g. multi-threads).
Hint: Read [atomic variables](https://www.baeldung.com/java-atomic-variables) and AtomicReference's methods. 

``` java
    public void oneMillionParallel() {
        long maxProfit = Long.MIN_VALUE;
        Solution best = null;

        IntStream.range(0, 1_000_000_000).boxed().parallel().forEach(i -> {

            Solution randomSolution = generateRandomSolution();
            if (randomSolution.totalProfit() > maxProfit) {
                maxProfit = randomSolution.totalProfit();
                best = randomSolution;
            }
        });

        best.print();
    }
```