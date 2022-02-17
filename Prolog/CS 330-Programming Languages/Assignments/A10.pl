


vowels(N) :- execute(0, N).

execute(N, R) :- get0(X), process(X, N, R).


process(42, N, N).
process(X, N, R) :- X \= 42, checkVowel(X), N1 is N + 1, execute(N1, R).
process(X, N, R) :- X \= 42, not(checkVowel(X)), execute(N, R).

checkVowel(97).
checkVowel(101).
checkVowel(105).
checkVowel(111).
checkVowel(117).
checkVowel(65).
checkVowel(69).
checkVowel(73).
checkVowel(79).
checkVowel(85).

 


