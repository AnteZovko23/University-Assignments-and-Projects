
parent(anakin, luke).

parent(anakin, leia).

parent(padme, luke).

parent(padme, leia).

parent(leia, ben).

parent(han, ben).

% grandparent(X, Y) :- parent(X, Z),parent(Z, Y).

grandparent(X, Y) :- parent(X, Z),parent(Z, Y),write(Z), nl.