# Coalesced-Chaining-Hashtable-
Lab assignment


Scott Roberts
Data Structures and Algorithms


Part One: Write a summary that discusses & compares the performance of the 2 hash functions. Which one would you recommend – based on their performance on these 100 titles?
	Before we begin with the summary, let us define performance for a hash function. Performance is related to two factors: collision reduction and computational time. Collisions occur when a function generates a key for a value that already exists in the hash table. Therefore, collision reduction is the function’s ability to reduce those collisions. Computational time is the CPU’s time required to calculate each key when the function is used. The less mathematical operations needed the faster/easier it is to compute.
Now, in terms of collision reduction, the multiplication method surpassed the division method, but not by much. Using the same 100 keys for both methods, the multiplication method had 18 collisions while the division method had 21 collisions. Since there was an issue with overflow, which I will discuss later, the multiplication method changed from the slide’s exact formula.
Computational time is more complicated to discuss, so I included the division and multiplication function codes down below. A nanosecond timer recorded the time required to insert the titles into each hash table. The division function took anywhere between 200,000 to 250,000 nanoseconds on average while the multiplication function took approximately 100,000 nanoseconds during each iteration. Both functions call paddingInput() before the hash function which ensures that the stringKey is at least 25 characters long. Therefore, the for loop in each function requires 25 iterations. Since the division function has an extra mod operation and multiplication operation inside the for loop, the entire insert will take longer; roughly two thirds as long. The multiplication function does have six mathematical operations/casting operations outside of the for loop which adds to the total computation, but the six is much less than the division function’s 50 extra operations inside the for loop. 
Based on performance, I would recommend the multiplication method due to its faster performance and fewer collisions.

Part 2: The write up should include a discussion & justification of how your program handles arithmetic overflow & rounding errors (if any).
At first I did not use any rounding techniques for the multiplication method and got 18 collisions while inserting(). After using rounding, I still got 18 collisions with a longer computation time. The rounding did not help much with my set of keys, so I removed it from the code. On the other hand, in a different set of titles, it might help remove a few collisions.
However, arithmetic overflow was a huge problem when calculating a hash table key. In the division method, the ascii characters (key[0], key[1], key[2],…) were multiplied by the corresponding powers of ‘r’ (r0, r1, r2,…). After 4 characters, the integer key overflowed. To avoid the overflow, each character was modded(%) by the table size(m); this ensured that the key stayed within the bounds of the table. In the multiplication method, to prevent the arithmetic overflow, the ‘r’ factor was abandoned to reduce computation time. Abandoning the ‘r’ factor does leave the hash function vulnerable to higher collisions if the values being hashed were permutations of each other.  Yet, the multiplication method still had less collisions than the division method overall.

