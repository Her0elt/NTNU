local Push Peek Pop in 
    fun {Push List Element}
        Element|List
    end

    fun {Peek List}
        List.1
    end

    fun {Pop List}
        List.2
    end

    {Show {Pop [1 2 3]}}

end