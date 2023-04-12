# Graphing

I am planning to create **graphing** software.
This software could take in functions in terms of x 
or theta and this could be graphed in cartesian 
coordinates or polar coordinates (kind of like desmos).

This program could be used by math/eng/physics people
who want to graph functions and would like some more
visualization of integrals.

This project is interesting to me because I'm looking
into physics and math and how they could be integrated
with computer science. Computer
science is an extremely powerful tool and can
be helpful in working with abstract concepts. 






## User Stories

As a user, I want to be able to 
- input a function
- see an arbitrary amount of graphed points 
of the function (adding points to a curve)
- change the scope of the displayed graph
- integrate with different accuracies
- keep a history of functions 
- change the accuracy of the graph
- optionally save my history of functions
- optionally load my history of functions

## Instructions for Grader

- To start the program, run SwingGUI
- To start inputting, you can input an equation
as a function of x in the middle panel (you should enter
this function in java terms so that sinx would be 
Math.sin(x) and x^2 would be x * x)
- Clicking enter will draw the graph on the left panel
  (this may take some time to load so please be patient)
- Clicking enter will also add the input in the history
in the middle top panel that is a list of previous inputs
  (add X's, first required action)
- The "Clear History" button will clear the history by 
deleting all previous inputs (second required action)
- Saving can be done with the save button on the right
- Loading can be done with the load button on the right
- The definite integral will take an approximate integral
as a right riemann sum (which will be non-exact)
- 
EventLog and Event classes w/ tests come from AlarmController as required


## Phase 4: Task 3

Looking at the diagram I see a lot of coupling between
SwingGUI and the other classes. Some are essential but
some seem redundant. The SwingGUI class has a FunctionHistory,
a GraphPanel, and a Function as fields. However, both 
the GraphPanel and FunctionHistory have its own functions.
In the case of the GraphPanel, they are both referencing what
should be the same function. Moreover, the FunctionHistory's
last entry should also be the same function. With more time,
I would turn likely turn both the FunctionHistory and GraphPanel
into Observers so that I wouldn't need to have multiple
instances of what should be the same object. 

What isn't seen in the UML Class Diagram is the multitude of
dependencies in my program. It's quite bad and I'm 
tempted to use the Singleton Pattern or some other
way to have the many types converge. 