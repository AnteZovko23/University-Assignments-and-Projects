
% parent(anakin, luke).

% parent(anakin, leia).

% parent(padme, luke).

% parent(padme, leia).

% parent(leia, ben).

% parent(han, ben).


% grandparent(X, Y) :- parent(X, Z),parent(Z, Y).

% isParent(X) :- parent(X, _).

% areSiblings(X,Y) :- parent(Z, X), parent(Z, Y).

course(
    csc330, 
    day(mwf),
    time(1100, 1215),
    instructor(terry, jason),
    room(iesb224)).

course(
    math242,
    day(mwf),
    time(1230, 1345),
    instructor(terry, jason),
    room(iesb212)).


% getInstructor(X, Y) :- course(X, A, B, instructor(terry, jason), C).
getInstructor(Class, Inst) :- 
    course(
        Class,
        _,
        _,
        Inst,
        _).