go :- tell('output.txt'), write('Hello world '), told().
go2(Filename, Outfile) :- see(Filename), tell(Outfile), read(X), write(X), nl, read(X2), write(X2), seen, told. 


do(Filename) :- see(Filename), told, read(X), process(X, 0, Sum), write(Sum), seen, told.


process(end_of_file, Sum, Sum).

process(X, CurrentSum, Sum) :- X \= end_of_file, NewCurrentSum is CurrentSum + X, read(X2), process(X2, NewCurrentSum, Sum).
