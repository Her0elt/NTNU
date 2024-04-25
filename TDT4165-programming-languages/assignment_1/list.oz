declare  Length Take Drop Append Member Position Push Peek Pop in
fun {Length List}
    case List of _|Tail then
        1 + {Length Tail}
    else
        0
    end
end

% 7 b)
fun {Take List Count}
    case List of Head|Tail then
        if Count == 0 then
            nil
        else
            Head|{Take Tail Count-1}
        end
    else
        nil
    end
end


% 7 c)
fun {Drop List Count}
    case List of _|Tail then
        if Count == 0 then
            List
        else
            {Drop Tail Count-1}
        end
    else
        nil
    end
end

%7 d)
    
fun {Append List1 List2}
    if {Length List1} \= 0 then
        List1.1|{Append List1.2 List2}
    else if {Length List2} \= 0 then
        List2.1|{Append List1 List2.2}
    else
        nil end
    end
end

%7 e)

fun {Member List Element}
    case List of Head|Tail then
        if Head == Element then
            true
        else
            {Member Tail Element}
        end
    else
        false
    end
end

fun {Position List Element}
    if List.1 == Element then
        0
    else
        1 + {Position List.2 Element}
    end
end

fun {Push List Element}
    Element|List
end

fun {Peek List}
    List.1
end

fun {Pop List}
    List.2
end
