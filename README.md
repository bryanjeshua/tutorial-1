# Tutorial-1 Advance Programming
Name : Bryan Jeshua Mario Timung \
NPM : 2206027021 
<details>
<summary>First Week</summary>

## First Week

### First reflection
My code follows clean code principles and secure coding practices by using meaningful names for functions. Furthermore, each function is small and focuses on doing one thing. Both of them are demonstrated through the implementation of method findById, update, and delete. Not only that, I avoid excessive and unnecessary comments in the code. 
Unused code is regularly removed to keep the codebase clean. Additionally, the layout of the code is clear and organized for easy understanding.
To enhance my code, I need to ensure the validity of the input. While writing this reflection, I've identified a loophole in my HTML code that permits string inputs as quantities, which must be addressed by restricting input to integers only. The effect of unrestricted quantity field input is the redirection to an error page since the instantiation of the product fails due to mistyped attribute values being inserted. After fixing that part, my code runs smoothly and even won't allow floats to be inserted.


### Second reflection
After writing the unit tests, I feel that I need to practice more in identifying loopholes. When building functionalities in our code, there is a risk of bias. We often assume that our code/program is already secure, but in reality, it is not yet secure. To reduce this bias, we must always be suspicious of our own code so that we can identify any loopholes. To ensure that our unit tests are sufficient, we need to implement code coverage metrics, considering function coverage, statement coverage, branch coverage, condition coverage, and line coverage. However, good code coverage does not necessarily indicate good unit tests. We still need to ensure the quality of the unit tests by checking all possible leaks/failures in our code.

Regarding the case study, instead of creating two different classes, I believe we could create a single class file with divided functions. This approach would be more efficient since we wouldn't need to reimport packages/libraries that are required. Additionally, we could obtain the result by running just a single class file instead of two. In terms of cleanliness, I think we should ensure that each unit test only tests one case and does not overlap. The naming of instances must be clear and self-explanatory.
</details>

<details>
<summary>Second Week</summary>

## Second Week
1. There are several code quality issues that I fixed during exercise such as
   - I remove public modifier of create and findAll in ProductService interface. It is a default that for every interface, the method by default is public. 
   - I enforce policy of braces in several conditionals in Product class
   - In ci.yml, I added token permission as a result following recommendation from scorecard
2. In my opinion, yes, my code currently follows the principles of CI/CD. I can see Continuous Integration in action through the process of integrating code from several branches, as if created by multiple programmers. Then, the test suite is being executed to know whether the update(s) of the code will safely running even though there are some edge cases being prepared (it is executed with ci.yml). After that, the machine automatically reviews some parts to ensure the program follows standards and conventions, ensuring it is safe and runs smoothly. This is executed through pmd and scorecards settings. The Continuous Deployment aspect can be seen in the deployment process after the CI phase is completed. The entire software release process is automated with Kobey, enabling one or more people to work faster.

</details>

<details>
<summary>Third Week</summary>

## Third Week
> 1. Explain what principles you apply to your project!
- Single Responsibility Principle
This principle emphasize the importance of a module only being responsible to only one actor.
-- I create a HomeController class file that is only responsible for controlling the home page. 
-- I divide ProductController and CarController into two different class files.

- Open Closed Principle
The principle emphasizes the openness of entities to extension but their closeness to modification. Seeing many similarities between the Product and Car models, I make Car a child of Product. Car has a unique attribute for color.

- Liskov Substitution Principle
In the project, we can observe that the ProductServiceImpl, as a child, is also a ProductService. It implements all the methods defined in ProductService.

- Interface Segregation Principle
Building upon the provided templates, the interfaces are already segregated into CarService and ProductService. Therefore, I only need to implement them in the controller file.

- Depedency Inversion Principle
Following this principle, which prefers using abstraction, I use interfaces instead of concrete implementations for CarService and ProductService in the controller.

> 2. Explain the advantages of applying SOLID principles to your project with examples.
- Increase modularity
SOLID principles break down the program into simpler and more manageable parts, like Lego bricks. I can change a part or function without significantly affecting other parts, thus avoiding disruption to the entire program. Additionally, I can reuse created parts to build other components. For example, creating Car models that extend Product allows Car to inherit all Product attributes.

- Improve readability
By dividing the program into simpler and more manageable components, each function or class becomes smaller and more straightforward. Understanding a function or class becomes faster as they are divided by functionality. For instance, knowing that the HomeController file is only responsible for navigating the Home page.

- Make testing easier
With SOLID principles, testing becomes easier because each function only performs one task. Identifying mistakes and fixing them becomes simpler since the code is modular, requiring changes to only small parts. For example, if I make a mistake in finding car by Id, I only have to focus on fixing the findById in CarService and CarServiceImpl, not on the other part

> 3. Explain the disadvantages of not applying SOLID principles to your project with examples.
- Complicate testing
Without applying SOLID principles, classes and functions tend to handle multiple tasks, making testing challenging. For instance, having a single repository class for two different models complicates managing instances and debugging errors related to specific model instances.

- Reduce readability
Failure to apply SOLID principles results in code that is harder to read. For instance, incorporating all controllers into a single class file makes the file lengthy, making it difficult to navigate or modify paths if necessary.

- Reduce reusability
Neglecting SOLID principles often leads to redundant code, requiring recreation of similar components. For example, if Car and Product share common attributes, but are not structured as parent-child, creating two separate models with nearly identical attributes and behaviors becomes redundant.

Credit : ChatGPT for correcting my grammatical errors.

 </details>