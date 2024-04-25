
functor
import
   Application
   System
define
   fun {Fact N}
      if N==0 then 1 else N*{Fact N-1} end
   end
   fun {Comb N K}
      {Fact N} div ({Fact K}*{Fact N-K})
   end


   H=5
   T=[6 7 8]
   L=[5 6 7 8]
   case L of H|T then {System.showInfo H} {System.showInfo T} end
   {Application.exit 0}
end
