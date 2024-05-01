 % Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 % y Milton Pividori.
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

:- include(pop).
:- dynamic holds/2.

% OPERATORS

% putOn(X,Y) : put cube X on cube Y
preconditions( putOn(X,Y), [holding(X), clear(Y)] ).
achieves( putOn(X,Y), on(X,Y) ).
achieves( putOn(X,_Y), clear(X) ).
achieves( putOn(_X,_Y), free_robot_arm ).
deletes( putOn(X,_Y), holding(X) ).
deletes( putOn(_X,Y), clear(Y) ).

% dropFrom(X,Y) : drop cube X from Y
preconditions( dropFrom(X,Y), [on(X,Y), clear(X), free_robot_arm] ).
achieves( dropFrom(X,_Y), holding(X) ).
achieves( dropFrom(_X,Y), clear(Y) ).
deletes( dropFrom(X,Y), on(X,Y) ).
deletes( dropFrom(X,_Y), clear(X) ).
deletes( dropFrom(_X,_Y), free_robot_arm ).

% putOnTable(X) : put cube X on the table
preconditions( putOnTable(X), [holding(X)] ).
achieves( putOnTable(X), on_table(X) ).
achieves( putOnTable(X), clear(X) ).
achieves( putOnTable(_X), free_robot_arm ).
deletes( putOnTable(X), holding(X) ).

% takeFromTable(X) : take cube X from the table
preconditions( takeFromTable(X), [on_table(X), clear(X), free_robot_arm] ).
achieves( takeFromTable(X), holding(X) ).
deletes( takeFromTable(X), on_table(X) ).
deletes( takeFromTable(X), clear(X) ).
deletes( takeFromTable(_X), free_robot_arm ).


%----------------------------------
% DOMAIN PREDICATES

primitive( on(_,_) ).
primitive( on_table(_) ).
primitive( holding(_) ).
primitive( clear(_) ).
primitive( free_robot_arm ).


%----------------------------------
% INITIAL STATE

holds(on_table(a),init).
holds(on(b,a),init).
holds(clear(b),init).
holds(holding(c),init).

achieves(init,X) :-
   holds(X,init).
   

% bestAction(X)
bestAction(Action) :-
	solve([on(a,b)],P,6),
	seq(P,[Init,Action|S]),!.


%
% executeAction(X)
%
% Executes the action X.
%

executeAction(putOn(Cube1,Cube2)) :-
	retract(holds(holding(Cube1),init)),
	retract(holds(clear(Cube2),init)),
	assert(holds(on(Cube1,Cube2),init)),
	assert(holds(clear(Cube1),init)),
	assert(holds(free_robot_arm,init)).
	
executeAction(dropFrom(Cube1,Cube2)) :-
	assert(holds(holding(Cube1),init)),
	assert(holds(clear(Cube2),init)),
	retract(holds(on(Cube1,Cube2),init)),
	retract(holds(clear(Cube1),init)),
	retract(holds(free_robot_arm,init)).

executeAction(putOnTable(Cube1)) :-
	assert(holds(on_table(Cube1),init)),
	assert(holds(clear(Cube1),init)),
	assert(holds(free_robot_arm,init)),
	retract(holds(holding(Cube1),init)).

executeAction(takeFromTable(Cube1)) :-
	retract(holds(on_table(Cube1),init)),
	retract(holds(clear(Cube1),init)),
	retract(holds(free_robot_arm,init)),
	assert(holds(holding(Cube1),init)).

executeAction(end).

% Tests:
% solve([holding(a)],P,4),seq(P,S).
% solve([holding(a)],P,6),seq(P,S).
% solve([on(a,b)],P,6),seq(P,S).
% solve([on(a,c),on(c,b)],P,8),seq(P,S).
% solve([on(c,a)],P,6),seq(P,S).

