%task 3 a)
local X Y Z in
    Y = 300
    Z = 30
    X = Y * Z
end


% 3 b)

% This code works because you print Y in a thread, so when the program runs the print code
% It opens a new thread that waits for Y to be assigned and then when Y is assigned to X the thread prints Y
local X Y in 
    X = "This is a string"
    thread {System.showInfo Y} end
    Y = X
end