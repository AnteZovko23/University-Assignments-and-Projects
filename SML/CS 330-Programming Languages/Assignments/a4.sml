(* fun test(s) = TextIO.openIn(s);

fun sum (s) = 
    let
      val file = test(s)
      val SOME b = TextIO.inputLine(file)
      val c = Int.fromString(b)
    in

    end; *)

fun closeFile(s) = TextIO.closeIn(s);
fun helper2(s) = if TextIO.endOfStream(s) then 0
else Option.valOf(Int.fromString(Option.valOf(TextIO.inputLine(s)))) + helper2(s); 

fun sum2 (s) = helper2(TextIO.openIn(s));

