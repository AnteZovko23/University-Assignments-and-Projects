

(* fun cube(n:real) = n * n * n; 


fun rev(a:int, b:int, c:int) = (c, b, a);

fun smallest(a:int, b:int, c:int) = if b > a andalso c > a then b
                                    else if c > b andalso a > b then b
                                    else c; *)


fun even(0) = true | even(n) = odd(n-1)
and
    odd (0) = false | odd(n) = even(n-1);
(* 
val a = 2;
fun f(b) = a*b;
val b = 3;
fun g(a) = a+b; *)