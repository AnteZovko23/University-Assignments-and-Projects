

replace([], _, _, []).
replace([C|T], C, C1, [C1|T]).
replace([C1|T], C1, C, [C1|T]).