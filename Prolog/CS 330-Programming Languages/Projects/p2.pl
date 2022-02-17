/*
NAME(S): Timothy Oliver & Ante Zovko
DATE: 14 May 2021
COURSE: CSC 330-001
QUARTER: Spring 2021
PROJECT:  2
*/

lex(Infile,R) :- see(Infile), told, execute(R,Infile),
				seen,told.
				
execute(R,Infile) :-  get(X), checkNums(X,R), seen, see(Infile), write('Compilation Error.'),nl.


execute(R,Infile) :-  get(X), not(checkNums(X,R)), 
						seen, see(Infile), get(FirstC), process(FirstC,R).

/* if we want the final list written to a specified output stream 
getOutput(R) :- writeq(R),nl,seen,told.
*/
				
/* Preemptively check if there are any numbers in the file 
	to streamline getting an empty list and "Compilation error" message 
*/			

checkNums(N,[]) :- N \= -1, N > 32, N \= 43, N \= 45, N \= 42, N \= 47, N \= 61, N < 65,
					seen. 	
																/* if the current character is not an allowed character (number, '~', '"', etc.), send back empty list */
checkNums(N,[]) :- N \= -1, N > 90, N < 97,
					seen.																
checkNums(N,[]) :- N \= -1, N > 122,
					seen.
checkNums(N,R) :- N \= -1, N < 48, get(X), checkNums(X,R). /* otherwise, check the next character */
checkNums(N,R) :- N \= -1, N > 57, get(X), checkNums(X,R).

process(-1,[]).		/* if at end of file, send empty list to append tuples to */

/* following five cases are the reserved characters (self explanatory) */
process(N,[H|R2]) :- N \= -1, N = 43, H = ("+","PL"), get(X2), process(X2,R2).

process(N,[H|R2]) :- N \= -1, N = 45, H = ("-","MI"), get(X2), process(X2,R2).

process(N,[H|R2]) :- N \= -1, N = 42, H = ("*","TI"), get(X2), process(X2,R2).

process(N,[H|R2]) :- N \= -1, N = 47, H = ("/","DI"), get(X2), process(X2,R2).

process(N,[H|R2]) :- N \= -1, N = 61, H = ("=","EQ"), get(X2), process(X2,R2).

/* if we get a space (namely using get0/1 in buildString/3) process next character */

process(N,R2) :- N \= -1, N = 32, get(X2),process(X2,R2).


/* 
we call process with the ASCII value of a letter (capital or lowercase)
*/

process(N,[H|R2]) :- N \= -1, N > 64, N < 91, get0(L2), buildString(N,R,L2,R2), H= (R,"ID").

process(N,[H|R2]) :- N \= -1, N > 96, N < 123, get0(L2), buildString(N,R,L2,R2),
					H= (R,"ID").
					
/*
buildString is called with the following character being a letter
*/
buildString(Ltr,RString,L2,R2) :- char_code(C,Ltr), L2 > 64, L2 < 91, get0(L3), buildString(L2,RS2,L3,R2), 
								atom_concat(C,RS2,RString).
								
buildString(Ltr,RString,L2,R2) :- char_code(C,Ltr), L2 > 96, L2 < 123, get0(L3), buildString(L2,RS2,L3,R2), 
								atom_concat(C,RS2,RString).

buildString(Ltr,RString,NotLtr,R2) :- char_code(C,Ltr), NotLtr < 65, 
								atom_concat(C,'',RString), process(NotLtr,R2).

buildString(Ltr,RString,NotLtr,R2) :- char_code(C,Ltr), NotLtr > 90, NotLtr < 97, 
								atom_concat(C,'',RString), process(NotLtr,R2).
								
buildString(Ltr,RString,NotLtr,R2) :- char_code(C,Ltr), NotLtr > 122, 
								atom_concat(C,'',RString), process(NotLtr,R2).								


								
								

					
