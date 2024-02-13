# Tutorial-1 Advance Programming
Name : Bryan Jeshua Mario Timung \
NPM : 2206027021 \

## First Week
### First reflection
My code follows clean code principles and secure coding practices by using meaningful names for functions. Furthermore, each function is small and focuses on doing one thing. Both of them are demonstrated through the implementation of method findById, update, and delete. Not only that, I avoid excessive and unnecessary comments in the code. 
Unused code is regularly removed to keep the codebase clean. Additionally, the layout of the code is clear and organized for easy understanding.
To enhance my code, I need to ensure the validity of the input. While writing this reflection, I've identified a loophole in my HTML code that permits string inputs as quantities, which must be addressed by restricting input to integers only. The effect of unrestricted quantity field input is the redirection to an error page since the instantiation of the product fails due to mistyped attribute values being inserted. After fixing that part, my code runs smoothly and even won't allow floats to be inserted.


### Second reflection
After writing the unit tests, I feel that I need to practice more in identifying loopholes. When building functionalities in our code, there is a risk of bias. We often assume that our code/program is already secure, but in reality, it is not yet secure. To reduce this bias, we must always be suspicious of our own code so that we can identify any loopholes. To ensure that our unit tests are sufficient, we need to implement code coverage metrics, considering function coverage, statement coverage, branch coverage, condition coverage, and line coverage. However, good code coverage does not necessarily indicate good unit tests. We still need to ensure the quality of the unit tests by checking all possible leaks/failures in our code.

Regarding the case study, instead of creating two different classes, I believe we could create a single class file with divided functions. This approach would be more efficient since we wouldn't need to reimport packages/libraries that are required. Additionally, we could obtain the result by running just a single class file instead of two. In terms of cleanliness, I think we should ensure that each unit test only tests one case and does not overlap. The naming of instances must be clear and self-explanatory.

## Second Week
1. There are several code quality issues that I fixed during exercise such as
   - Lorem ipsum dolor sit amet. To fix this, I ...
   - Lorame ipsum
2. In my opinion, yes my code currently following the principles of CI/CD.