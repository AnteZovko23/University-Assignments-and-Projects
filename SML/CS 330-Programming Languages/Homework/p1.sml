(*
Name(s): Timothy Oliver, Ante Zovko
Date: 12 April 2021
Course: CSC 310-001
Quarter: Spring Quarter 2021
Project #: 1
*)

(* helper function to open file
*)
fun openFile (s) = TextIO.openIn(s);

(* function to get rid of Option notation for the elem options read from file. *)
fun noOption (SOME c) = c
|   noOption (NONE) = chr(255);

(* returns the approprate lexeme for each CHARACTER; if the character is a letter, 
    send a special lexeme so we know to build a string *)
fun readNext (#"+") = "PL"
|	readNext (#"-") = "MI" 
|	readNext (#"*") = "TI" 
|	readNext (#"/") = "DI" 
|	readNext (#"=") = "EQ" 
(* if the character is a digit, give the lexeme "Error", else, give "C" denoting a single letter *)
|	readNext (c) = if ord(c)>47 andalso ord(c)<58 then "Error" else "C"
(* might not be able to use Char.isDigit *)

fun reverseTuples (nil) = nil
(* ignores instances where the empty string following an operator would be its own string of letters *)
|   reverseTuples (("ID","")::xs) = reverseTuples(xs)
(* switches the character/string and its lexeme for the appropriate output *)
|   reverseTuples ((x,y)::xs) = [(y, x)]@reverseTuples(xs)

(* Whenever there is an empty space, consume it so we can move on to the next character.
     There is the issue of empty space after an operator (the space is not concatenated with the operator string).
	 I chose to clean it up in the final (string * string) list formatting.*)
fun getRidOfSpace(file) = (
							TextIO.input1(file);
							"" 
							(* or if the char before " " was an operator: checkChar(file, s); actual correction moved to reverseTuple() *)
							
							);

(* after finding a letter, go forward in file until finding " " or an operator 
    and join the letters for a string.
    Might not be able to use Option structure.	*)
fun checkChar (file) = if
						TextIO.endOfStream(file)
						then
						""
						else
						if readNext(noOption(TextIO.lookahead(file))) = "C"
						then
							if str(noOption(TextIO.lookahead(file))) = " "
							then getRidOfSpace(file)
							else
							str(noOption(TextIO.input1(file)))^checkChar(file)
						else
						""
						 
(* helper function that reads file and returns list of lexemes *)
fun getLexemes (file) = if 
						TextIO.endOfStream(file) 
						(* if we get a number, should make whole list a []; might go in other function*)
						(* orelse Ord(Option.valOf(TextIO.lookahead(file)))>47 andalso Ord(Option.valOf(TextIO.lookahead(file)))<58*)
					   then
						[]
						else
						if readNext(noOption(TextIO.lookahead(file))) = "C"
						then
						(* since character is a letter, get string and put its relevant tuple in list (reversed) *)
					    [("ID", checkChar(file))]@getLexemes(file)
						else 
						(* since character is an operator, put operator and its relevant tuple in list (reversed) *)
					    [(readNext(noOption(TextIO.lookahead(file))), str(noOption(TextIO.input1(file))))]@getLexemes(file)
						
					   

(* if called, we already know we have an error (no parameters); print "Compilation error." and return [] *)
fun getError (_) = (
					print("Compilation error.\n");
					nil
					);

(* do we have any errors in the (lexeme--a string-- * string) list?
	function is boolean for question. *)
fun isError (nil) = false
|   isError (("Error", b)::xs) = true
|   isError ((a,b)::xs) = isError(xs)
 

(* main function; string -> (string * string) list
*)
fun lex (S) = let
				val file = openFile(S)
				val lexes = getLexemes(file)
				val finalList = reverseTuples(lexes)(* not need in final version *)
			  in
			  if isError(lexes) then getError() else reverseTuples(lexes)
			  end;

		  
