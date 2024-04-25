local Circle in
    proc {Circle R} A D C PI in
        PI = 335.0 / 113.0
        A = PI * {Number.pow R 2.0} 
        D = 2.0*R
        C = PI * D
        {System.showInfo "A is "#A}
        {System.showInfo "D is "#D}
        {System.showInfo "C is "#C}
    end
    {Circle 2.0}
end