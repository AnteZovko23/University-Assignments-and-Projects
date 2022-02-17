temperature(ruston, 50).
temperature(monroe, 55).


convFtoC(F, C) :- C is (F - 32) * (5/9).

cityC(City, Cel) :- temperature(City, Y), convFtoC(Y, Cel).


isEven(N) :- 0 is rem(N, 2).

isEven2(N) :- M is N//2, 2*M =:= N.

isEvenNoErr(N) :- integer(N), 0 is rem(N, 2).


factorial(0, 1).
factorial(N, R) :- integer(N), N > 0, N1 is N - 1, factorial(N1, R1), R is N * R1.


choose(N, K, R) :- integer(N), integer(K), factorial(N, N1), factorial(K, K1), Dif is N-K, factorial(Dif, NK1), R is N1 / (K1 * NK1).