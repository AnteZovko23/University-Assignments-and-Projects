(* 
Name: Ante Zovko
Version: Mar 22, 2021

FUnctions that uplicate every element of the list, concatinate an option list of strings and an 
option list of chars
 *)


fun dup(nil) = []
| dup(x::xs) = x::x::dup(xs); 


fun sconc(L) = case L of
    [] => "" | (SOME x)::xs => x^sconc(xs) | NONE::xs => sconc(xs);



fun coconc(L) = case L of 
    [] => "" | (SOME x)::xs => str(x)^coconc(xs) | NONE::xs => coconc(xs);