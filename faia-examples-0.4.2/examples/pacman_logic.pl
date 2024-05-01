 %
 % Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 % Luis Ignacio Larrateguy y Milton Pividori.
 % 
 % This program is free software: you can redistribute it and/or modify
 % it under the terms of the GNU General Public License as published by
 % the Free Software Foundation, either version 3 of the License, or
 % (at your option) any later version.
 % 
 % This program is distributed in the hope that it will be useful,
 % but WITHOUT ANY WARRANTY; without even the implied warranty of
 % MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 % GNU General Public License for more details.
 % 
 % You should have received a copy of the GNU General Public License
 % along with this program.  If not, see <http://www.gnu.org/licenses/>.
 %

% The code below is neccessary for the framework to modify these sentences.

:- dynamic actualSituation/1,perception/5,executedAction/2,position/3,
food/3,enemy/3,empty/3,energy/2.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Diagnostic rules                                    %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

enemy(X,Y,S) :- perception([enemy,_,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goleft,S).
enemy(X,Y,S) :- perception([_,enemy,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goright,S).
enemy(X,Y,S) :- perception([_,_,enemy,_],_,_,_),actualSituation(S),adjacent(X,Y,goup,S).
enemy(X,Y,S) :- perception([_,_,_,enemy],_,_,_),actualSituation(S),adjacent(X,Y,godown,S).

food(X,Y,S) :- perception([food,_,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goleft,S).
food(X,Y,S) :- perception([_,food,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goright,S).
food(X,Y,S) :- perception([_,_,food,_],_,_,_),actualSituation(S),adjacent(X,Y,goup,S).
food(X,Y,S) :- perception([_,_,_,food],_,_,_),actualSituation(S),adjacent(X,Y,godown,S).

empty(X,Y,S) :- perception([empty,_,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goleft,S).
empty(X,Y,S) :- perception([_,empty,_,_],_,_,_),actualSituation(S),adjacent(X,Y,goright,S).
empty(X,Y,S) :- perception([_,_,empty,_],_,_,_),actualSituation(S),adjacent(X,Y,goup,S).
empty(X,Y,S) :- perception([_,_,_,empty],_,_,_),actualSituation(S),adjacent(X,Y,godown,S).

energy(E,S) :- E is 50.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Causal rules                                        %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

knows(X,Y,S) :- empty(X,Y,S),!.
knows(X,Y,S) :- food(X,Y,S),!.
knows(X,Y,S) :- enemy(X,Y,S),!.

movementAction(S) :- executedAction(goup,S).
movementAction(S) :- executedAction(godown,S).
movementAction(S) :- executedAction(goright,S).
movementAction(S) :- executedAction(goleft,S).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Successor state axioms                              %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% When the executed action doesn't change the actual position
est(S1) :- S1 > 0,S is S1-1,executedAction(eat,S),position(X,Y,S),asserta(position(X,Y,S1)).
est(S1) :- S1 > 0,S is S1-1,executedAction(fight,S),position(X,Y,S),asserta(position(X,Y,S1)).

% When the executed action DOES change the actual position
est(S1) :- S1 > 0,S is S1-1,executedAction(goup,S),position(X,Y,S),addPosition(X,-1,X1),asserta(position(X1,Y,S1)).
est(S1) :- S1 > 0,S is S1-1,executedAction(godown,S),position(X,Y,S),addPosition(X,1,X1),asserta(position(X1,Y,S1)).
est(S1) :- S1 > 0,S is S1-1,executedAction(goright,S),position(X,Y,S),addPosition(Y,1,Y1),asserta(position(X,Y1,S1)).
est(S1) :- S1 > 0,S is S1-1,executedAction(goleft,S),position(X,Y,S),addPosition(Y,-1,Y1),asserta(position(X,Y1,S1)).

% Empty cells
est(S1) :- S1 > 0,S is S1-1,empty(X,Y,S),not(empty(X,Y,S1)),asserta(empty(X,Y,S1)).
est(S1) :- S1 > 0,S is S1-1,position(X,Y,S),executedAction(eat,S),asserta(empty(X,Y,S1)).
est(S1) :- S1 > 0,S is S1-1,position(X,Y,S),executedAction(fight,S),asserta(empty(X,Y,S1)).

% Food cells
est(S1) :-S1 > 0,S is S1-1,position(_,Y1,S),food(X,Y,S),Y=\=Y1,not(food(X,Y,S1)),asserta(food(X,Y,S1)).
est(S1) :-S1 > 0,S is S1-1,position(X1,_,S),food(X,Y,S),X=\=X1,not(food(X,Y,S1)),asserta(food(X,Y,S1)).
est(S1) :-S1 > 0,S is S1-1,position(X,Y,S),movementAction(S),food(X,Y,S),not(food(X,Y,S1)),asserta(food(X,Y,S1)).

% Enemy cells
est(S1) :-S1 > 0,S is S1-1,position(_,Y1,S),enemy(X,Y,S),Y=\=Y1,not(enemy(X,Y,S1)),asserta(enemy(X,Y,S1)).
est(S1) :-S1 > 0,S is S1-1,position(X1,_,S),enemy(X,Y,S),X=\=X1,not(enemy(X,Y,S1)),asserta(enemy(X,Y,S1)).
est(S1) :-S1 > 0,S is S1-1,position(X,Y,S),movementAction(S),enemy(X,Y,S),not(enemy(X,Y,S1)),asserta(enemy(X,Y,S1)).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Actions Evaluations                                 %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

excelent(eat,S) :- position(X,Y,S),food(X,Y,S).
excelent(fight,S) :- position(X,Y,S),enemy(X,Y,S).

% Mirar cómo está definida 'adjacent'. Esto se traduce a: es muy bueno
% moverse hacia una celda adyacente donde hay comida o un enemigo.
very_good(D,S) :- adjacent(Xa,Ya,D,S),food(Xa,Ya,S).
very_good(D,S) :- adjacent(Xa,Ya,D,S),enemy(Xa,Ya,S).

% Mirar cómo están definidas 'discoveryDirection', etc. Esto se
% traduce a: es bueno moverse hacia una celda en cual alguna de sus adyacentes
% no es conocida, o tiene comida, o tiene un enemigo.
good(D,S) :- discoveryDirection(D,S).
good(D,S) :- foodDirection(D,S).
good(D,S) :- enemyDirection(D,S).

% Si todo lo demás no funcionó, es una acción regular moverse hacia una celda
% adyacente vacía.
regular(D,S) :- adjacent(Xa,Ya,D,S),empty(Xa,Ya,S).


bestAction(noAction,S) :- goalReached(S),!.
bestAction(X,S) :- excelent(X,S),!.
bestAction(X,S) :- very_good(X,S),!.
bestAction(X,S) :- good(X,S),!.
bestAction(X,S) :- regular(X,S),!.




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Extra sentences                                     %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

addPosition(P,O,P1) :- O=:=(-1),P=:=0,P1 is 3,!.
addPosition(P,O,P1) :- O=:=(-1),P=\=0,P1 is P-1,!.
addPosition(P,O,P1) :- O=:=1,P=:=3,P1 is 0,!.
addPosition(P,O,P1) :- O=:=1,P=\=3,P1 is P+1,!.

adjacentCell(X,Y,X1,Y) :- addPosition(X,-1,X1).
adjacentCell(X,Y,X1,Y) :- addPosition(X,1,X1).
adjacentCell(X,Y,X,Y1) :- addPosition(Y,-1,Y1).
adjacentCell(X,Y,X,Y1) :- addPosition(Y,1,Y1).

adjacent(X,Y1,goleft,S) :- position(X,Y,S),addPosition(Y,-1,Y1).
adjacent(X,Y1,goright,S) :- position(X,Y,S),addPosition(Y,1,Y1).
adjacent(X1,Y,goup,S) :- position(X,Y,S),addPosition(X,-1,X1).
adjacent(X1,Y,godown,S) :- position(X,Y,S),addPosition(X,1,X1).

discoveryDirection(D,S) :- adjacent(Xa,Ya,D,S),adjacentCell(Xa,Ya,Xaa,Yaa),not(knows(Xaa,Yaa,S)).
foodDirection(D,S) :- adjacent(Xa,Ya,D,S),adjacentCell(Xa,Ya,Xaa,Yaa),food(Xaa,Yaa,S).
enemyDirection(D,S) :- adjacent(Xa,Ya,D,S),adjacentCell(Xa,Ya,Xaa,Yaa),enemy(Xaa,Yaa,S).

%% Para saber cuándo el agente alcanzó el objetivo
worldEmpty(S) :- empty(0,0,S),empty(0,1,S),empty(0,2,S),empty(0,3,S),
                 empty(1,0,S),empty(1,1,S),empty(1,2,S),empty(1,3,S),
                 empty(2,0,S),empty(2,1,S),empty(2,2,S),empty(2,3,S),
                 empty(3,0,S),empty(3,1,S),empty(3,2,S),empty(3,3,S).

goalReached(S) :- worldEmpty(S).




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Improvement proposal                                %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% The agent could save the average enery lost when fighting and moving. This way,
% when evaluating actions, it can decide to fight if and only if the average energy
% lost when fighting is less than the actual energy. The same can be done when
% it decides to move.

%%% Count elements in a list
% count([],0):-!.
% count([_|Ls],C):-count(Ls,T),C is T+1.

%%% Calculate the average in a list
% average(L,P):-count(L,C),C=:=0,P is 0,!.
% average(L,P):-sumlist(L,S), length(L,C), P is (S/C).

% fightingEnergyData([],1):-!.
% fightingEnergyData([D|Ds],S1):-S0 is S1-1,executedAction(pelear,S0),energy(E0,S0),energy(E1,S1),D is E1-E0,fightingEnergyData(Ds,S0),!.
% fightingEnergyData(D,S1):-S0 is S1-1,fightingEnergyData(D,S0),!.

% movingEnergyData([],1):-!.
% movingEnergyData([D|Ds],S1):-S0 is S1-1,movementAction(S0),energy(E0,S0),energy(E1,S1),D is E1-E0,movingEnergyData(Ds,S0),!.
% movingEnergyData(D,S1):-S0 is S1-1,movingEnergyData(D,S0),!.

% averageWhenFighting(0,1):-!.
% averageWhenFighting(P,S):-fightingEnergyData(Es,S),average(Es,P).

% averageWhenMoving(0,1):-!.
% averageWhenMoving(P,S):-movingEnergyData(Es,S),average(Es,P).

