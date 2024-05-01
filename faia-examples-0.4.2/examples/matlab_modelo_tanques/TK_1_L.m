function [t,h] = TK_1_L(A1,R1,h1_0,D_q1,ti,T)
% Un tanque con par�metros {A1,R1}, y C.I. h1_0.
% Simula T unidades de tiempo.
% El caudal de entrada es D_q1 fijo desde el 
% tiempo inicial ti hasta el tiempo final ti+T
%
% Salida: [t,h] es la evoluci�n temporal h(t)

tf=ti+T; tspam = [ti,tf]'; 
[t,h] = sim('TK_Lineal_1',tspam,[],...
    [ti,D_q1,A1,h1_0,R1;...
     tf,D_q1,A1,h1_0,R1]);
