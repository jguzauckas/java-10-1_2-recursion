# Recursion

In the past we have discussed methods that call other methods to accomplish their tasks. This is often done to break up a task into more manageable pieces to program. A simple, but classic example of this is an average algorithm that breaks it up into the pieces of summing values and dividing. We have often had implementations of this that writes a `sum` method and `average` method where the `average` method calls the `sum` method before doing its division. Here is an example of this implementation from the `NotesAverage.java` file:

```java
public static double average(int[] nums) {
    int sum = sum(nums);
    return (double) sum / nums.length;
}

public static int sum(int[] nums) {
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    return sum;
}
```

**Recursion** is an extension of this concept, where a method calls *itself* instead of another method.

---

## Recursion Basics

Here is the simplest possible implementation of recursion from the `NotesRecursion1.java` file:

```java
public static void method() {
    method();
}
```

When we call `method()` the first time, it calls `method()`, which then calls `method()`, and so on. This simple code results in an infinite loop, since every call to `method()` just calls `method()` again, with no way to stop.

In this form, this is not very useful. This code not only doesn't do anything, it will run forever! We can at least make it do something while it runs forever, by having it print something out each time it is called, like this example from the `NotesRecursion2.java` file:

```java
public static void method() {
    System.out.println("Hello, World!");
    method();
}
```

Now when we call `method()`, it prints `Hello, World!`, then calls `method()` again, which prints `Hello, World!` and then calls `method()` again, and so on. We again have an infinite loop, since every call to `method()` calls `method()` again, but this time it prints out a message each and every time!

While this is neat, the fact that this continues on infinitely is not very helpful. No realistic problems require us to do something forever! While these methods are recursive because they call themselves, they are not considered useful without another element: a **base case**.

You can think of a good recursive method as digging a `V`-shaped hole. By calling down the chain at the beginning of the problem you are creating the `\` on the left side of the `V`, but in order to answer a question you need to come back up to the surface, by finishing each of the calls that have been made, creating the `/` on the right side of the `V`.

So how do we know when we've hit the bottom of a hole? We would call that scenario the base case, meaning we have reached the "simplest" part of the problem to answer and can start moving back up to combine the work we've done. Essentially, the base case makes sure our hole is not bottomless!

---

## Recursion Example

Let's see this in action by creating a recursive method that prints out the whole numbers from `n` to `1` (where the user can plug in a value for `n`).
- In this case, our base case is going to be `1`, since that is the smallest value we want to print.
- In our design, we would consider that each step of our recursion will print a number (starting at `n`), and then call itself with a smaller number (`n - 1`).
- It will also need to check the number that was plugged in, in case we have made it down to `1`, in which case we shouldn't call the method anymore!

Putting this all together results in this example from the `NotesPrint1.java` file:

```java
public static void printFromNto1(int n) {
    System.out.println(n);
    if (n > 1) {
        printFromNto1(n - 1);
    }
}
```

We can analyze what is happening by breaking down the method call `printFromNto1(3)`, which should produce the following output:

```
3
2
1
```

Here are the steps that the computer goes through to produce this output:

```
printFromNto1(3)
print 3
3 is greater than 1, so call printFromNto1(3 - 1) which is equivalent to printFromNto1(2)

    printFromNto1(2)
    print 2
    2 is greater than 1, so call printNto1(2 - 1) which is equivalent to printFromNto1(1)

        printFromNto1(1)
        print 1
        1 is not greater than 1, so we do not do anything
        method ends

    method ends

method ends
```

Notice that we really utilized the left slope `\` of the `V`-shape with this method to print out the numbers.

Even though it didn't do anything except end the methods, there a right slope `/` of the `V`-shape. It is important to understand in a recursive call that every call still exists until we hit the base case and start working our way back up. Just because `printFromNto1(3)` calls `printFromNto1(2)`, doesn't mean that `printFromNto1(3)` disappears. It still has to "finish" whatever it has after the `printFromNto1(2)` call is done, hence the right slope `/`.

What would happen if we changed the order of our code? What if it checked if `n > 1` before it printed? Here is what that would look like from the `NotesPrint2.java` file:

```java
public static void printFromNto1(int n) {
    if (n > 1) {
        printFromNto1(n - 1);
    }
    System.out.println(n);
}
```

Notice that all the same code is present, we just changed the order. Here are the steps that the computer goes through to produce the output:

```
printFromNto1(3)
3 is greater than 1, so call printFromNto1(3 - 1) which is equivalent to printFromNto1(2)

    printFromNto1(2)
    2 is greater than 1, so call printNto1(2 - 1) which is equivalent to printFromNto1(1)

        printFromNto1(1)
        1 is not greater than 1, so we do not do anything
        print 1
        method ends

    print 2
    method ends

print 3
method ends
```

Interestingly, we get a reversed output:

```
1
2
3
```

This better demonstrates the right slope `/` of the `V`-shape. Before `printFromNto1(3)` prints anything, it calls `printFromNto1(2)`, which calls `printFromNto1(1)`. Then, `printFromNto1(1)` prints `1`, and passes it back up to `printFromNto1(2)`, which prints `2`, and passes it back up to `printFromNto1(3)`, which then prints `3` and everything ends.

This is the kind of subtelty that makes recursion difficult. A small change in organization produces vastly different results.

---

## Similarity to Loops

If you noticed that everything we did above was possible using loops/iteration, you are absolutely correct! Recursion is another way to accomplish something that would have used loops. As a matter of fact, any problem that is solved recursively can be solved through iteration!

The similarities can be even more clearly demonstrated when thinking about a loop control variable. In a problem like the one above, the loop control variable would have taken on the values `1`, `2`, and `3` for us to work with. Recursion uses parameter values as its loop control variable, allowing it to accomplish the same behavior.

This similarity continues with the fact that we can use recursion to traverse iterables like `String` values, arrays, or ArrayLists!

Here in an example of our `sum` method from earlier being done recursively from the `NotesTraverse1.java` file:

```java
public static int sum(int[] nums, int index) {
    int sum = nums[index];
    if (index < nums.length - 1) {
        sum += sum(nums, index + 1);
    }
    return sum;
}
```

To work this through, consider the `int` array `{2, 4, 3, 5}`. For clarity, I will relabel the `sum` variable as `sum1`, `sum2`, etc. for the recursive calls to `sum()`. Here are the steps that the computer goes through to produce the output:

```
sum(nums, 0)
sum1 is 2 because 2 is the value of nums at index 0
index 0 is less than 3 (4 - 1), so we call sum(nums, 0 + 1) which is equivalent to sum(nums, 1)

    sum(nums, 1)
    sum2 is 4 because 4 is the value of nums at index 1
    index 1 is less than 3 (4 - 1), so we call sum(nums, 1 + 1) which is equivalent to sum(nums, 2)

        sum(nums, 2)
        sum3 is 3 because 3 is the value of nums at index 2
        index 2 is less than 3 (4 - 1), so we call sum(nums, 2 + 1) which is equivalent to sum(nums, 3)

            sum(nums, 3)
            sum4 is 5 because 5 is the value of nums at index 3
            index 3 is not less than 3 (4 - 1), so we do not make another recursive call
            return 5

        sum3 gets added with 5 to now hold the value 3 + 5 = 8
        return 9

    sum2 gets added with 8 to hold the value 4 + 8 = 12
    return 12

sum1 gets added with 12 to hold the value 2 + 12 = 14
return 14
```

Essentially, this method recursively stores each value in the array to get to the end (the left slope `\`), and then starts adding them up as it works its way back to the front to produce the final answer (the right slope `/`)!

---

## Binary Search

One of the uses of recursion is some new algorithmic approaches to searching and sorting. A new way to search is the concept of a **binary search**.

This type of searching requires the data to be sorted in advance of searching, making its uses a little more limited, but giving it a lot of power.

The concept is that if I am looking for a value, I should start in the middle and see which side it falls on. If it is greater than the middle value, I should look more closely at the right half of the data, whereas if it is less than the middle value, I should look more closely at the left half of the data.

Consider this data:

```
Index:  0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30
Value:  4   8   8   11  13  13  14  19  23  25  29  33  35  35  38  39  39  44  47  51  56  59  60  63  68  70  75  78  80  85  86
```

If we were searching for the number `11`, we would start at the middle index (the average of the starting and ending indices, which is `(30 + 0) / 2 = 15`). Looking at the value at index `15`, we find the number `39`. Since `11` is less than `39`, we know that our value must be in the left half of the data!

At this point, we do not care about the right half of the data, and can just zoom in and look at the left half:

```
Index:  0   1   2   3   4   5   6   7   8   9   10  11  12  13  14
Value:  4   8   8   11  13  13  14  19  23  25  29  33  35  35  38
```

Notice that since our value was strictly less than `39`, we can ignore the value at index `15` as well, leaving us with just `15` values left. This is the power of binary search: with just a single check, we eliminated half of the data.

This is the moment where binary search is more apparently a recursive algorithm. We can do the exact same procedure as before on this new smaller set of data to help us solve our problem. Our middle index now is `7` (the average of the starting and ending indices, which is `(14 + 0) / 2 = 7`), and the value there is `19`. Since `11` is less than `19`, we know that our value is the left half of this data (which could be considered one quarter of the original data).

At this point, we do not care about the right half of the data, and can just zoom in and look at the left half:

```
Index:  0   1   2   3   4   5   6
Value:  4   8   8   11  13  13  14
```

We repeat our procedure again. Our middle index is now `3` (the average of the starting and ending indices, which is `(6 + 0) / 2 = 3`), and the value there is `11`. `11` is equal to this middle index, so we would return the index `3` as the location of our match. This would return back up the two previous steps to communicate our final answer!

Here is what an implementation of binary search looks like:

```java
public static int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
    if (startIndex <= endIndex) {
        int halfwayIndex = (endIndex + startIndex) / 2;
        if (target < nums[halfwayIndex]) {
            return binarySearch(nums, startIndex, halfwayIndex - 1, target);
        } else if (target > nums[halfwayIndex]) {
            return binarySearch(nums, halfwayIndex + 1, endIndex, target);
        }
        return halfwayIndex;
    }
    return -1;
}
```

Some details about this algorithm:
- We check if `startIndex <= endIndex` because of the `+ 1` and `- 1` nature of recursive calls. If the value is not found, eventually `startIndex` and `endIndex` will be the same, resulting in a single value to check. If that fails, then it will call with either `startIndex` increased by `1` or `endIndex` decreased by `1`, resulting in `startIndex` being greater than `endIndex`, and at that point we know that we did not find the `target` value, and should return `-1`.
- If our `target` value is below the middle value (the value of `nums` at index `halfwayIndex`), then we need to do the same algorithm on the array with a modified ending location (since we eliminate the right half where `endIndex` was). Same thing if we eliminate the left side, where we modify the starting location.
- If the `target` value was not less than or greater than the current middle value, then it must be equal to it, so we can return that index as our final answer. This will get passed up the chain to answer the question.

Here are the steps that the computer goes through to produce the output for the list of numbers above searching for the value `60`:

```
binarySearch(nums, 0, 30, 60)
0 is less than or equal to 30 so we can continue
halfwayIndex is calculated as (30 + 0) / 2 = 30 / 2 = 15
60 is not less than the value in nums at index 15 (39)
60 is greater than the value in nums at index 15 (39)
call binarySearch(nums, 15 + 1, 30, 60) which is binarySearch(nums, 16, 30, 60)

    binarySearch(nums, 16, 30, 60)
    16 is less than or equal to 30 so we can continue
    halfwayIndex is calculated as (16 + 30) / 2 = 46 / 2 = 23
    60 is less than the value in nums at index 7 (63)
    call binarySearch(nums, 16, 23 - 1, 60) which is binarySearch(nums, 16, 22, 60)

        binarySearch(nums, 16, 22, 60)
        16 is less than or equal to 22 so we can continue
        halfwayIndex is calculated as (16 + 22) / 2 = 38 / 2 = 19
        60 is not less than the value in nums at index 19 (51)
        60 is greater than the value in nums at index 19 (51)
        call binarySearch(nums, 19 + 1, 22, 60) which is binarySearch(nums, 20, 22, 60)

            binarySearch(nums, 20, 22, 60)
            20 is less than or equal to 22 so we can continue
            halfwayIndex is calculated as (20 + 22) / 2 = 42 / 2 = 21
            60 is not less than the value in nums at index 21 (59)
            60 is greater than the value in nums at index 21 (59)
            call binarySearch(nums, 20 + 1, 22, 60) which is binarySearch(nums, 21, 22, 60)

                binarySearch(nums, 21, 22, 60)
                21 is less than or equal to 22 so we can continue
                halfwayIndex is calculated as (21 + 22) / 2 = 43 / 2 = 21 (integer division)
                60 is not less than the value in nums at index 21 (59)
                60 is greater than the value in nums at index 21 (59)
                call binarySearch(nums, 21 + 1, 22, 60) which is binarySearch(nums, 22, 22, 60)

                    binarySearch(nums, 22, 22, 60)
                    22 is less than or equal to 22 so we can continue
                    halfwayIndex is calculated as (22 + 22) / 2 = 44 / 2 = 22
                    60 is not less than the value in nums at index 22 (60)
                    60 is not greater than the value in nums at index 22 (60)
                    then 60 must be equal to the value in nums at index 22 (60)
                    return 22

                return 22

            return 22

        return 22

    return 22

return 22
```

Binary search is a powerful tool because it often works better than other searching algorithms (like linear search).

[Here](https://www.cs.usfca.edu/~galles/visualization/Search.html) is a great tool that visualizes a binary search on smaller and larger datasets to get a better idea of how they work. Play around with it by entering the search target in the top left, and turning down the animation speed slider at the bottom.

Advanced Note: The maximum iterations it takes for binary search to find is equal to the log base `2` of the size of the dataset (rounded up) plus `1`. This data set had `31` values, and log base `2` of `31` rounded up is `5`, and plus `1` is `6`. You can see above that we had a worst-case scenario value in the example, since it ultimately called the method `6` times. Our example earlier was a better scenario, where we only called it `3` times.

---

## Merge Sort

Another tool that is made easier by recursion is **merge sort**.

This algorithm is built on the basis of merging two sorted lists together into one larger sorted list. 
This portion of the concept is not recursive, and we can make a `merge` method that combines two sorted arrays into one larger sorted array.

Merging already sorted lists makes sense, but how do lists get sorted to begin with? That's the power of merge sort. By splitting the list up into two pieces, and then splitting it up into two pieces again, and again, and again, we get to the point of having individual values. Technically, the `merge` method above can merge two arrays that have one value each, and then can merge two arrays with two values each and so on.

Here is what this would look like happening to a set of values:

```
Original:       3   2   7   6   5   3

Split in Half:  3   2   7       6   5   3

Split in Half:  3   2       7       6   5       3

Split in Half:  3       2       7       6       5       3

Merge Halfs:    2   3       7       5   6       3

Merge Halfs:    2   3   7       3   5   6

Merge Halfs:    2   3   3   5   6   7
```

Notice that we keep splitting until we have just individual values, then start merging into the groups they were split up from. We repeat the merging until we are back to a full and finished list.

The algorithm to merge two sorted lists together is the non-recursive portion, and just requires some logic as to what value goes next. Here is an example from the `NotesMerge1.java` file:

```java
public static int[] merge(int[] a, int[] b) {
    int[] result = new int[a.length + b.length];
    int resultIndex = 0;
    int aIndex = 0;
    int bIndex = 0;
    while (resultIndex < result.length) {
        if (aIndex == a.length) {
            result[resultIndex] = b[bIndex];
            bIndex++;
        } else if (bIndex == b.length) {
            result[resultIndex] = a[aIndex];
            aIndex++;
        } else if (a[aIndex] <= b[bIndex]) {
            result[resultIndex] = a[aIndex];
            aIndex++;
        } else {
            result[resultIndex] = b[bIndex];
            bIndex++;
        }
        resultIndex++;
    }
    return result;
}
```

Essentially, this method just goes through a larger result array and keeps track of the value it is looking at in the two parameter arrays `a` and `b`. If either of the arrays had been used up completely, just use the other. Otherwise, compare the current values and determine which one should be used to maintain a sorted list.

This was designed with a couple of specific goals in mind:
- It works on parameter arrays of any size.
- It works on parameter arrays with sizes different from each other.
- It depends on the two parameter arrays being sorted (which is the whole premise of this algorithm).

Here is the method that actually performs the merge sort by calling itself and using the `merge` method above when it needs to combine the sorted parts.

```java
public static int[] mergeSort(int[] arr, int from, int to) {
    if (from < to) {
        int middle = (from + to) / 2;
        int[] lower = mergeSort(arr, from, middle);
        int[] upper = mergeSort(arr, middle + 1, to);
        return merge(lower, upper);
    }
    return new int[] {arr[from]};
}
```

While we haven't seen it before, this method calls itself twice recursively. Once to do the lower half and once to do the upper half.

If `from` is not less than `to`, it means that `from` must be equal to `to` (due to us only adding one each time, it can't be greater than). In that case, return just that single value (since a single value is sorted with itself).

Here is the sample list from above:

```
Index:  0   1   2   3   4   5
Value:  3   2   7   6   5   3
```

Here are the steps that the computer goes through to process `mergeSort(arr, 0, 5)`:

```
mergeSort(arr, 0, 5)
0 is less than 5 so we can continue
middle is calculated as (0 + 5) / 2 = 5 / 2 = 2 (integer division)
call mergeSort(arr, 0, 2)

    mergeSort(arr, 0, 2)
    0 is less than 2 so we can continue
    middle is calculated as (0 + 2) / 2 = 2 / 2 = 1
    call mergeSort(arr, 0, 1)
    
        mergeSort(arr, 0, 1)
        0 is less than 1 so we can continue
        middle is calculated as (0 + 1) / 2 = 1 / 2 = 0 (integer division)
        call mergeSort(arr, 0, 0)

            mergeSort(arr, 0, 0)
            0 is not less than 0
            return the array {3}
        
        set lower to the array {3}
        call mergeSort(arr, 1, 1)

            mergeSort(arr, 1, 1)
            1 is not less than 1
            return the array {2}

        set upper to the array {2}
        merge the two arrays to make {2, 3}
        return the array {2, 3}
    
    set lower to the array {2, 3}
    call mergeSort(arr, 2, 2)

        mergeSort(arr, 2, 2)
        2 is not less than 2
        return the array {7}
    
    set upper to the array {7}
    merge the two arrays to make {2, 3, 7}
    return the array {2, 3, 7}

set lower to the array {2, 3, 7}
call mergeSort(arr, 3, 5)

    mergeSort(arr, 3, 5)
    3 is less than 5 so we can continue
    middle is calculated as (3 + 5) / 2 = 8 / 2 = 4
    call mergeSort(arr, 3, 4)

        mergeSort(arr, 3, 4)
        3 is less than 4 so we can continue
        middle is calculated as (3 + 4) / 2 = 7 / 2 = 3 (integer division)
        call mergeSort(arr, 3, 3)

            mergeSort(arr, 3, 3)
            3 is not less than 3
            return the array {6}

        set lower to the array {6}
        call mergeSort(arr, 4, 4)

            mergeSort(arr, 4, 4)
            4 is not less than 4
            return the array {5}

        set upper to the array {5}
        merge the two arrays to make {5, 6}
        return the array {5, 6}

    set lower to the array {5, 6}
    call mergeSort(arr, 5, 5)

        mergeSort(arr, 5, 5)
        5 is not less than 5
        return the array {3}

    set upper to the array {3}
    merge the two arrays to make {3, 5, 6}

set upper to the array {3, 5, 6}
merge the two arrays to make {2, 3, 3, 5, 6, 7}
return the array {2, 3, 3, 5, 6, 7}
```

[Here](https://opendsa-server.cs.vt.edu/embed/mergesortAV) is a step-by-step visualization that will walk you through a merge sort on a set of up to 12 values. [Here](https://antoniosarosi.github.io/Merge-Sort-Visualization/) is a visualization of merge sort that runs much faster on a smaller set. Play around with these to get a better sense of what the algorithm does!