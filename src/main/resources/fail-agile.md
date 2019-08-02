# The Failures of Agile Software Development

## Background

The [Agile Manifesto](https://agilemanifesto.org/) was published in 2001 and has inspired a number of new software development methodologies. Now, nearly all software development shops claim to be agile to some degree. However, in almost every organization which attempts to adopt it, the implementation falls short of the promise offered by the approach. A whole industry of consultants and speakers have emerged trying to pin down how companies can implement agile correctly, yet still, it almost never works. Even the authors of the manifesto seem consistently frustrated and confused by the refusal of companies to internalize the principles of agile software development. This may be due to a mass misunderstanding of what the Agile Manifesto really means. Although it explicitly critiques the specific methods of waterfall-style development, the criticisms are implicitly critiquing something deeper.

### Waterfall
The term "waterfall" is used to refer a traditional model of development that has been taught for decades, but is rarely actually implemented with fidelity. The way it works is a linear progression:

1. Analysis: Analyze your systems required use cases to determine the required functionaliy and constraints. Also known as "requirements"
2. Design: Build high-level abstract designs of how your software will be structured
3. Develop: Write the code
4. Test: See if the code works
5. Deployment: Deploy and/or distribute the code

This model is built on some assumptions:

- You can discover all of the requirements up front
- Those requirement are static: they will not change
- You can estimate how log each of these phases will take at the beginning of the process

In most cases, these assumptions do not hold up when developing software. 

One of the biggest flaws is the dependence on "man-hours" style estimation. "The Mythical Man-Month" by Frederick Brooks was published in 1975, which pointed out that this way of running projects does not work, yet its author spent a lot of time after works speaking out about how people were still doing it wrong, and not following the advice in his book.

On top of that, because testing does not occur until the end, any bugs are very expensive to fix, since the longer it takes to discover a bug after it is written, the more expensive it is to fix.

As a practical method, waterfall does not work. however, it provides a framing of project management that is compatible with something called "Taylorism", and is therefore useful to management as an ideology.

### Taylorism

Taylorism is a methodology developed in the early 20th century that breaks every action, job, or task into small and simple segments which can be easily analyzed and taught. It was designed to optimise the efficiency of assembly-line factories. It prioritizes breaking work into well defined pieces of work which can be estimated precisely, and workers who fulfill a defined roll which can be trained for, and therefore easily replaceable.

Although rightly criticized for alienating workers by (indirectly but substantially) treating them as mindless, emotionless, and easily replicable factors of production, Taylorism was a critical factor in the unprecedented scale of US factory output that led to Allied victory in Second World War, and the subsequent US dominance of the industrial world. See also Fordism.

Read more: http://www.businessdictionary.com/definition/Taylorism.html

### Agile Manifesto

The fact that Waterfall and Taylorism are fundamentally incompatible with developing software cased an extraordinary high rate of failure in software projects. This finally became untenable by the industry. Many prominent software developers met at a conference any pooled their thoughts on this, and put down dome principles they noticed that _actually_ work when developing software. This is known as the "Agile Manifesto."

The Manifesto outlines principles which, if followed, would steer you toward successful development projects, and away from the bad practices which cause so many projects to fail. It is important to note that it does not prescribe any specific practices.

### Agile in Practice

In order to explain how agile principles should transform the way businesses should develop software, its proponents started to bundle existing and new best-practices into concrete frameworks. Examples of these are SCRUM, Kanban, and XP. The most popular of these, by far, is SCRUM. There is now a whole industry of consultants which sell SCRUM and "SCRUM Master" licenses to businesses. Businesses also perceive (wrongly) that a gap in SCRUM exists for larger organizations, because it does not define how multiple teams can do SCRUM and still roll up into the organization hierarchy and project management, leaving an opening for the consultancy industry to create and sell "Scaled Agile Framework" or SAFe.

## Criticisms of Agile

Like in the time of waterfall, most developers naturally sense that something is still very wrong in they way we are running software projects. The discussion about the failures of Agile has been far-reaching, but some of the Manifesto signatories have termed phrases such as "Dark Agile" or "Flaccid Agile" to describe attempted implementations of Agile which fail to live up to the ideals of the manifesto. Most analyses of Agile failure ultimately trace the failure back to a lack of buy-in. It is universally recognized that agile only works if you have buy-in across the entire organization, all the way to the top. This level of buy-in is almost never achieved, and this is usually what is blamed for the failings of Agile. Common wisdom amongst agile proponents attribute the lack of buy-in to the fact that management just don't truly understand agile, or they fail to internalize it. But the reason may actually be that management _does_ understand what it is, and rejects it outright. At the same time, developers mis-understand it, and therefore don't grasp why management refuses to buy in. So to understand what is really going on, we need to examine what the manifesto is addressing, and what it is *failing* to address in its critique of waterfall/taylorism.

### Autonomy
One of the main tenets of the manifesto is "Individuals and interactions over processes and tools". Because of this, any concrete framework, such as SCRUM, is inherently NOT agile. This statement is actually addressing autonomy in the workplace.
If you place trust in your people, and give them the autonomy to execute on what they judge is best, you don't need strict processes and limiting tools to direct the work.

- customer collaboration over contract negotiation
- business people and developers must work together daily throught the project"
- trust
- Work at constant pace
- formalized in VSM, a non-taylorist management model

### Relationships
the most efficient and effective method of conveying information to and within a development team is face to face conversation


### Hierarchy
the best architectures, requirements, and designs emerge from self organizing teams

### Planning

The Manifesto has these principles to offer when thinking about how to plan a project:

> Responding to change over following a plan:  
>
> Welcome changing requirements, even late in development. Agile processes harness change for the customer's competitive advantage. 
>
> Deliver working software frequently, from a couple of weeks to a couple of months, with a preference to the shorter timescale. 
>
> Business people and developers must work together daily throughout the project. 

In other words, throw away your Gannt charts and roadmaps. Instead, delivery high value products continuously.

In SCRUM, these principles are applied with the idea of Sprints. Each iteration (short, 1-2 weeks), work with the business to identify the most important things to do. Delivery them quickly, then repeat. This is an area where management will explicitly reject the agile principles. Most companies require longer term road maps with time estimates. Many even demand estimates on each individual ticket. In order to underscore how un-agile this is, the #noestimates movement has arisen, which highlights the fact that time spend estimating issues is, at best, not worth it, and at worst, counter-productive.  I personally have used JIRA ticket data (in the ten-thousands) to show that velocity projections would have been more accurate if every ticket was weighted the same rather than given a story point value. This was true for five out of the five times I calculated it for. But no matter how much you show that estimates are in accurate, and that the time spent on them is wasted, management will never accept eliminating them, because at the end of the day, they are still committed to Taylorism, and not willing to give up on this level of control over the workers.

### Pace

> Agile processes promote sustainable development. The sponsors, developers, and users should be able to maintain a constant pace indefinitely.

he agile manifesto advocates for a steady, sustainable work pace.
It sells this idea on the principle that if you overwork developers, their output will be of a worse quality, and turnover will be high.
Of course, this principle also offers protection to developers from miserable working conditions such as crunch time.
However, agile also promises:

> Deliver working software frequently, from a couple of weeks to a couple of months, with a preference to the shorter timescale.

For profit corporations are always trying to optimise for growth.
The wording of the above principle is close enough to 'go faster' that managers are happy to twist its meaning into that, and completely disregard all of the other language regarding sustainable pace.
Sustianable pace and unbounded growth are incompatible concepts.
            
### Alienation

This all adds up to a concept known as "Alienation".
The theoretical basis of alienation within the capitalist mode of production is that the worker invariably loses the ability to determine life and destiny when deprived of the right to think (conceive) of themselves as the director of their own actions; to determine the character of said actions; to define relationships with other people; and to own those items of value from goods and services, produced by their own labour. Although the worker is an autonomous, self-realized human being, as an economic entity this worker is directed to goals and diverted to activities that are dictated by the bourgeoisie—who own the means of production—in order to extract from the worker the maximum amount of surplus value in the course of business competition among industrialists.

## Going Forward

## Additional Resources
[Scientific Management](https://en.wikipedia.org/wiki/Scientific_management) (Taylorism)
Discuss this post on [Mastodon](https://mastodon.technology/web/statuses/101241850991374660).
