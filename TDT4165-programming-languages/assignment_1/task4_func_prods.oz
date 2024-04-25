local Min in

    fun {Min X Y}
        if X < Y then
            X
        else
            Y
        end
    end

    {System.showInfo {Min 10 89}}
end

local Max PrintGreater in
    %4a)
    fun {Max X Y}
        if X > Y then
            X
        else
            Y
        end
    end

    %4b)
    proc {PrintGreater X Y}
        if X > Y then
            {Show X}
        else
            {Show Y}
        end
    end

    {System.showInfo {Max 10 89}}
    {PrintGreater 10 89}
end
