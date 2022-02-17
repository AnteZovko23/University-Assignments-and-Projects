
go :- write('What is your name'),nl , read(Name), greet(Name).
greet(Y) :- Y \= chance, write('Hi '), write(Y), write('. You are not as cool').
greet(chance) :- write('You are cool'), nl.




go2 :- write('Type in a series of chars ending with *: '), execute.
execute :- get0(X), process(X).
process(Y) :- Y \= 42, write(Y), nl, execute.


go3(R) :- write('Type in a series of chars ending with a *'), count(0,R).
count(M, R) :- get0(X), process2(X, M, R).
process2(42, M, M). 
process2(X, M, R) :- X \= 42, M1 is M + 1, count(M1, R).