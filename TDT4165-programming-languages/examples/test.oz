functor
import
    Application(exit:Exit)
    System
define
    fun {Factorial N}
        if N == 0 then
            1
        else
            N * {Factorial N-1}
        end
    end
    {System.showInfo {Factorial 5}}
    {Exit 0}
end
