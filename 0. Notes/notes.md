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

If we were searching for the number `11`, we would start at the middle index (the `length` / 2, which is `31 / 2 = 15` (remember, integer division truncates the decimal)). Looking at the value at index `15`, we find the number `39`. Since `11` is less than `39`, we know that our value must be in the left half of the data!