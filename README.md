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


SAMPLE OUTPUT:

Part 1 using 100 keys:
Time required to insert 100 keys w/ Div function: 2488004 nanoseconds
Time required to insert 100 keys w/ Mult function: 1339937 nanoseconds
Multiplication Method:
=============================================
Element 3: Incest                    
Element 8: Donald Trump              
Element 15: India                     
Element 16: Algorithm                 
Element 19: Pornography               
Element 20: Game of Thrones           
Element 21: Cristiano Ronaldo         
Element 22: United Kingdom            
Element 23: OS-tan                    
Element 26: Floyd Mayweather Jr.      
Element 29: Vagina                    
Element 30: Paradox                   
Element 31: Penis                     
Element 32: The Beatles               
Element 33: Blowjob                   
Element 34: Ken Jennings              
Element 35: Freedom Tower             
Element 36: Jurassic World            
Element 37: Japan                     
Element 41: RFID                      
Element 42: Wikipedia                 
Element 43: List of sex positions     
Element 44: Computer science          
Element 45: Europe                    
Element 50: World War II              
Element 51: LOAF protocol             
Element 53: Islamic State of Iraq and
Element 57: GNU/Linux naming controve
Element 63: John Kerry                
Element 64: Anal sex                  
Element 68: Stephen Hawking           
Element 70: Artificial Intelligence   
Element 75: List of Bollywood films o
Element 77: United States             
Element 78: Crushing by elephant      
Element 81: Current events            
Element 82: Paul Walker               
Element 86: Music                     
Element 88: Sexual intercourse        
Element 94: Oral sex                  
Element 95: Three Laws of Robotics    
Element 102: Mitochondrial Eve         
Element 103: Alexandra Kerry           
Element 104: Paul Marshall Johnson Jr.
Element 106: Google                    
Element 108: Pictures                  
Element 109: 2004                      
Element 112: Recent deaths             
Element 115: Physics                   
Element 118: Federal Marriage Amendmen
Element 123: Saddam Hussein            
Element 124: Avengers: Age of Ultron   
Element 125: John Edwards              
Element 127: Ronda Rousey              
Element 134: Jew                       
Element 136: Porn                      
Element 137: Canada                    
Element 141: Leet                      
Element 142: Goatse.cx                 
Element 143: Germany                   
Element 144: Hash Table                
Element 145: Chris Kyle                
Element 147: Star Wars                 
Element 148: Louis Armstrong           
Element 149: Masturbation              
Element 151: Tour de France            
Element 153: Kanye West                
Element 155: Fifty Shades of Grey      
Element 156: Pablo Escobar             
Element 157: Teresa Heinz Kerry        
Element 158: The Answer to Life the Un
Element 159: Unit 731                  
Element 165: Facebook                  
Element 168: Liger                     
Element 170: Nick Berg                 
Element 171: Adolf Hitler              
Element 174: Mathematics               
Element 175: Turing Test               
Element 176: Clitoris                  
Element 177: Bobby Fischer             
Element 178: U.S. presidential electio
Element 179: Internet                  
Element 180: Barack Obama              
Element 183: Wiki                      
Element 185: Marlon Brando             
Element 187: Deaths in 2015            
Element 188: France                    
Element 191: Barbara and Jenna Bush    
Element 192: Coconut Crab              
Element 202: Kim Sun-il                
Element 203: Religion                  
Element 204: Main Page                 
Element 205: Christianity              
Element 207: Furious 7                 
Element 214: Harry Potter and the Half
Element 218: Star Wars: The Force Awak
Element 221: Julia Thorne              
Element 222: Vanessa Kerry             
Element 225: 2015 in film              
Element 226: George W. Bush            
Number of Elements: 100
=============================================
Collisions: 16

Division Method:
=============================================
Element 7: Pictures                  
Element 9: United States             
Element 10: Unit 731                  
Element 12: Physics                   
Element 17: Leet                      
Element 22: Japan                     
Element 25: Blowjob                   
Element 28: Three Laws of Robotics    
Element 29: Star Wars                 
Element 30: Stephen Hawking           
Element 31: GNU/Linux naming controve
Element 32: Vagina                    
Element 33: Fifty Shades of Grey      
Element 38: United Kingdom            
Element 39: Germany                   
Element 40: Mitochondrial Eve         
Element 49: Penis                     
Element 53: Crushing by elephant      
Element 54: Ronda Rousey              
Element 55: Bobby Fischer             
Element 56: Avengers: Age of Ultron   
Element 57: Deaths in 2015            
Element 58: Paul Marshall Johnson Jr.
Element 59: Tour de France            
Element 60: Paul Walker               
Element 61: Donald Trump              
Element 62: Pornography               
Element 63: George W. Bush            
Element 64: Computer science          
Element 65: Facebook                  
Element 67: LOAF protocol             
Element 68: Pablo Escobar             
Element 78: Main Page                 
Element 82: Clitoris                  
Element 85: Music                     
Element 86: Coconut Crab              
Element 89: Goatse.cx                 
Element 90: Religion                  
Element 95: Marlon Brando             
Element 98: Turing Test               
Element 103: Teresa Heinz Kerry        
Element 104: Masturbation              
Element 107: Mathematics               
Element 112: Adolf Hitler              
Element 113: Google                    
Element 116: Barack Obama              
Element 118: John Edwards              
Element 119: Liger                     
Element 124: Ken Jennings              
Element 127: Incest                    
Element 130: Louis Armstrong           
Element 134: Saddam Hussein            
Element 137: Islamic State of Iraq and
Element 138: 2015 in film              
Element 139: Federal Marriage Amendmen
Element 140: U.S. presidential electio
Element 145: Kanye West                
Element 146: Nick Berg                 
Element 147: Artificial Intelligence   
Element 148: Oral sex                  
Element 152: Harry Potter and the Half
Element 153: Floyd Mayweather Jr.      
Element 154: Game of Thrones           
Element 155: OS-tan                    
Element 156: Wikipedia                 
Element 157: Wiki                      
Element 158: Jew                       
Element 159: Europe                    
Element 161: Porn                      
Element 165: RFID                      
Element 170: Jurassic World            
Element 171: Sexual intercourse        
Element 175: Julia Thorne              
Element 176: List of sex positions     
Element 177: Cristiano Ronaldo         
Element 180: John Kerry                
Element 182: Kim Sun-il                
Element 185: Star Wars: The Force Awak
Element 186: Internet                  
Element 191: Vanessa Kerry             
Element 195: List of Bollywood films o
Element 197: Freedom Tower             
Element 203: 2004                      
Element 205: Paradox                   
Element 206: India                     
Element 207: World War II              
Element 210: Current events            
Element 215: Recent deaths             
Element 216: Canada                    
Element 217: Barbara and Jenna Bush    
Element 218: Hash Table                
Element 219: Anal sex                  
Element 220: The Beatles               
Element 223: Chris Kyle                
Element 225: The Answer to Life the Un
Element 226: Alexandra Kerry           
Element 227: Christianity              
Element 228: Furious 7                 
Element 229: France                    
Element 230: Algorithm                 
Number of Elements: 100
=============================================
Collisions: 22


Part 2 Using 40 Keys w/ Insert, Search, and Delete:
Time required to insert 100 keys w/ Div function: 1009587 nanoseconds
Time required to insert 100 keys w/ Mult function: 474531 nanoseconds
Multiplication Method:
=============================================
Element 8: Donald Trump              
Element 15: India                     
Element 20: Game of Thrones           
Element 23: OS-tan                    
Element 26: Floyd Mayweather Jr.      
Element 31: Penis                     
Element 36: Jurassic World            
Element 42: Wikipedia                 
Element 43: List of sex positions     
Element 50: World War II              
Element 53: Islamic State of Iraq and
Element 63: John Kerry                
Element 68: Stephen Hawking           
Element 75: List of Bollywood films o
Element 77: United States             
Element 78: Crushing by elephant      
Element 81: Current events            
Element 82: Paul Walker               
Element 88: Sexual intercourse        
Element 106: Google                    
Element 112: Recent deaths             
Element 124: Avengers: Age of Ultron   
Element 127: Ronda Rousey              
Element 142: Goatse.cx                 
Element 145: Chris Kyle                
Element 147: Star Wars                 
Element 153: Kanye West                
Element 155: Fifty Shades of Grey      
Element 156: Pablo Escobar             
Element 165: Facebook                  
Element 170: Nick Berg                 
Element 177: Bobby Fischer             
Element 183: Wiki                      
Element 187: Deaths in 2015            
Element 202: Kim Sun-il                
Element 204: Main Page                 
Element 207: Furious 7                 
Element 214: Harry Potter and the Half
Element 218: Star Wars: The Force Awak
Element 225: 2015 in film              
Number of Elements: 40
=============================================
Collisions: 1

Division Method:
=============================================
Element 9: United States             
Element 29: Star Wars                 
Element 30: Stephen Hawking           
Element 33: Fifty Shades of Grey      
Element 49: Penis                     
Element 53: Crushing by elephant      
Element 54: Ronda Rousey              
Element 55: Bobby Fischer             
Element 56: Avengers: Age of Ultron   
Element 57: Deaths in 2015            
Element 60: Paul Walker               
Element 61: Donald Trump              
Element 65: Facebook                  
Element 68: Pablo Escobar             
Element 78: Main Page                 
Element 89: Goatse.cx                 
Element 113: Google                    
Element 137: Islamic State of Iraq and
Element 138: 2015 in film              
Element 145: Kanye West                
Element 146: Nick Berg                 
Element 152: Harry Potter and the Half
Element 153: Floyd Mayweather Jr.      
Element 154: Game of Thrones           
Element 155: OS-tan                    
Element 156: Wikipedia                 
Element 157: Wiki                      
Element 170: Jurassic World            
Element 171: Sexual intercourse        
Element 176: List of sex positions     
Element 180: John Kerry                
Element 182: Kim Sun-il                
Element 185: Star Wars: The Force Awak
Element 195: List of Bollywood films o
Element 206: India                     
Element 207: World War II              
Element 210: Current events            
Element 215: Recent deaths             
Element 223: Chris Kyle                
Element 228: Furious 7                 
Number of Elements: 40
=============================================
Collisions: 5

//The menu is implemented to modify the division hash table.


What would you like to do? 
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
2
What is the name of the key?
Jurassic World
Jurassic World is a 2015 American science-fiction 
adventure film and the fourth installment of the J
urassic Park series. The film was directed and co-
written by Colin Trevorrow, produced by Frank Mars
hall and Patrick Crowley, and stars Chris Pratt an
d Bryce Dallas Howard. The production companies we
re Steven Spielberg's Amblin Entertainment, also r
esponsible for the rest of the Jurassic Park franc
hise, and Thomas Tull's Legendary Pictures. Set 22
 years after the events of Jurassic Park, Jurassic
 World takes place on the same fictional island of
 Isla Nublar, off the Pacific coast of Central Ame
rica, where a theme park populated with cloned din
osaurs has operated for ten years. The park plunge
s into chaos when a genetically created dinosaur b
reaks loose and goes on a rampage across the islan
d.
true
Operation took: 957872 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
2
What is the name of the key?
Kanye West
Kanye Omari West (/ˈkɑːnjeɪ/; born June 8, 1977) i
s an American rapper, songwriter, record producer,
 fashion designer, and entrepreneur. Born in Atlan
ta and raised in Chicago, West first became known 
as a producer for Roc-A-Fella Records in the early
 2000s, producing hit singles for artists such as 
Jay Z and Alicia Keys. Intent on pursuing a solo c
areer as a rapper, West released his debut album T
he College Dropout in 2004 to widespread critical 
and commercial success. He went on to pursue a var
iety of different styles on subsequent albums Late
 Registration (2005), Graduation (2007), and the p
olarizing 808s & Heartbreak (2008). In 2010, he re
leased his fifth album My Beautiful Dark Twisted F
antasy, and the following year he collaborated wit
h Jay Z on the joint LP Watch the Throne (2011). W
est released his abrasive sixth album, Yeezus, to 
further critical praise in 2013. His seventh album
, The Life of Pablo, was released in 2016.
true
Operation took: 1398519 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
2
What is the name of the key?
Google
Google is an American multinational technology com
pany specializing in Internet-related services and
 products that include online advertising technolo
gies, search, cloud computing, software, and hardw
are.
true
Operation took: 572091 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
1			// Inserting a key
What is the name of the key?
Scott
Operation took: 47742 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
2
What is the name of the key?
Scott
Null			 //There is not an article linked to this title
true
Operation took: 160105 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
3
What is the name of the key?
Scott
First entry.
True						//Delete worked successfully
Operation took: 210033 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
2
What is the name of the key?	//Search for a recently deleted key
Scott
false
Operation took: 187021 nanoseconds.
What would you like to do?
1. Insert new key
2. Search for key
3. Delete key
4. Print table
0. Quit
