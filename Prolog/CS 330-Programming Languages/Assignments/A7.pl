/*
NAME(S): 
DATE: 
COURSE:
QUARTER:
ASSIGNMENT 7 - Inventory

*/

inv(
	item(bread),
	price(0.90),
	quantity(50)
).

inv(
	item(milk),
	price(2.50),
	quantity(30)
).

inv(
	item(cheese),
	price(1.50),
	quantity(40)
).

inv(
	item(chips),
	price(1.00),
	quantity(50)
).

inv(
	item(apples),
	price(0.30),
	quantity(100)
).

/* Add your predicates here */

getPrice(Item, Price) :- 
    inv(
        item(Item),
        price(Price),
        _
        ).

getQuantity(Item, Quantity) :-
   inv(
        item(Item),
        _,
        quantity(Quantity)
        
        ).

lowerPrice(Item1, Item2) :- getPrice(Item1, A),getPrice(Item2, B), A < B.

higherPrice(Item1, Item2) :- getPrice(Item1, A),getPrice(Item2, B), A > B.


computeGross(Item, Gross) :- getPrice(Item, A), getQuantity(Item, B), Gross is A * B.