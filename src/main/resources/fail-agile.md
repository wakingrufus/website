# The Failures of Agile Software Development

## Background

If you work in software development, you have likely heard of Agile or Scrum. You might have heard someone talking about Agile at a conference, where they outline how great and empowering agile is, and you come away sold. But when you go to work, either management has no interest in it, or they say they do it, but it just becomes story points, Jira tickets, sprints, and other annoyances that get in the way of doing your job. If you are wondering why Agile in the workplace never measures up to the promise offered by its proponents, you are not alone. Even the authors of the manifesto seem consistently frustrated and confused by the refusal of companies to internalize the principles of agile software development. Many people blame the failure of Agile on flawed implementations. A whole industry of consultants and speakers have emerged trying to pin down how companies can implement agile correctly, yet still, it almost never works.  Perhaps there is more wrong than just the way we implement it. Perhaps even its proponents misunderstand what the [Agile Manifesto](https://agilemanifesto.org/) really means. Although it explicitly critiques the specific methods of waterfall-style development, the criticisms are implicitly critiquing something deeper. 

To get at the root of this, we first need to understand why agile came about in the first place.

### Waterfall
The term "waterfall" is used to refer a traditional model of development that has been taught for decades, but is rarely actually implemented with fidelity. The way it works is a linear progression:

1. Analysis: Analyze your systems required use cases to determine the required functionaliy and constraints. Also known as "requirements".
2. Design: Build high-level abstract designs of how your software will be structured.
3. Develop: Write the code.
4. Test: See if the code works.
5. Deployment: Deploy and/or distribute the code.

This model is built on some assumptions:

- You can discover all of the requirements up front
- Those requirements are static: they will not change
- You can estimate how long each of these phases will take at the beginning of the process

In most cases, these assumptions do not hold up when developing software. 

One of the biggest flaws is the dependence on "man-hours" style estimation. "The Mythical Man-Month" by Frederick Brooks was published in 1975, which pointed out that this way of running projects does not work, yet its author spent a lot of time afterwards speaking out about how people were still doing it wrong, and not following the advice in his book.

On top of that, because testing does not occur until the end, any bugs are very expensive to fix, since the longer it takes to discover a bug after it is written, the more expensive it is to fix.

As a practical method, waterfall does not work. However, it provides a framing of project management that is compatible with something called "Taylorism", and is therefore useful to management as an ideology.

### Taylorism

Taylorism is a methodology developed in the early 20th century that breaks every action, job, or task into small and simple segments which can be easily analyzed and taught. It was designed to optimize the efficiency of assembly-line factories. It prioritizes breaking work into well defined pieces of work which can be estimated precisely, and workers who fulfill a defined roll which can be trained for, and therefore easily replaceable.  This approach was hugely successful in manufacturing at reducing labor costs, but it should be noted that it had a downside in the fact that it alienates workers by (indirectly but substantially) treating them as easily replaceable factors of production.

In software development, traditionally a skill held by only an elite few artisans, management has always been trying to find to make software development a commodity. Higher level languages, and application platforms are artifacts of attempts to allow "non-programmers" to program.  Instead of changing the nature of programming, waterfall project management attempts to commodify software development by implementing Taylorist processes around it. If successful, this would reduce labor costs greatly for companies. In the end, it did not work. Software projects had an extraordinary high rate of failure. When it became obvious this would not work, management kicked off a wave of offshore outsourcing for their next attempt to reduce labor costs. However, veteran software engineers proposed other solutions.

### Agile Manifesto

Throughtout the 1990's many newer ways of developing software emerged, such as SCRUM, Kanban, and XP, among others. These all attempted to address the failures of waterfall in terms of running successful software projects. In 2001, Many prominent software developers met at a conference and pooled their thoughts on these newer methods. They wrote down some principles they noticed _actually_ work when developing software, and that many of these new methods had in common. This is known as the [Agile Manifesto](https://agilemanifesto.org/) .

The Manifesto outlines principles which, if followed, would steer you toward successful development projects, and away from the bad practices which cause so many projects to fail. It is important to note that it does not prescribe any specific practices.

### Agile in Practice

Now that there was a term which encompasses all of these new practices, it became more of a mass movement in the software industry. There was now much more pressure for tech organizations to become more agile. Mostly, companies just chose one of these existing frameworks, and adopted it. The most popular of these, by far, is SCRUM. There is now a whole industry of consultants which sell SCRUM and "SCRUM Master" licenses to businesses. Businesses also perceived that a gap in SCRUM exists for larger organizations, because it does not define how multiple teams can do SCRUM and still roll up into the organization hierarchy and project management, leaving an opening for the consultancy industry to create and sell "Scaled Agile Framework" or SAFe.

## Criticisms of Agile

Like in the time of waterfall, most developers naturally sense that something is still very wrong in they way we are running software projects. The discussion about the failures of Agile has been far-reaching, but some of the Manifesto signatories have termed phrases such as "Dark Agile" or "Flaccid Agile" to describe attempted implementations of Agile which fail to live up to the ideals of the manifesto. Most analyses of Agile failure ultimately trace the failure back to a lack of buy-in. It is universally recognized that Agile only works if you have buy-in across the entire organization, all the way to the top. This level of buy-in is almost never achieved, and this is usually what is blamed for the failings of Agile. 

Common wisdom amongst Agile proponents attribute the lack of buy-in to the fact that management just don't truly understand agile, or they fail to internalize it. But the reason may actually be that management _does_ understand what it is, and rejects it outright. At the same time, developers mis-understand it, and therefore don't grasp why management refuses to buy in. So to understand what is really going on, we need to examine what the manifesto is addressing, and what it is *failing* to address in its critique of waterfall/taylorism.

### Autonomy / Hierarchy

> The best architectures, requirements, and designs emerge from self organizing teams

One of the main tenets of the manifesto is "Individuals and interactions over processes and tools". Because of this, any concrete framework, such as SCRUM, is inherently NOT agile. Proponents of Agile contend that the only way to adapt to rapidly changing business conditions and requirements is to also be flexible in your process. They also recommend that finding motivated people and supporting them is a better approach than building a reproducible process. This statement is addressing autonomy in the workplace, and is directly opposed to Taylorism. If you place trust in your people, and give them the autonomy to execute on what they judge is best, you don't need strict processes and limiting tools to direct the work. 

Despite this, management is still highly resistant to these ideas. Taylorism is still rooted very deeply in the mindset of people making up the managerial class. Risk averse companies will never want to place trust in individual people, or even teams, no matter how beneficial it can be.  While many companies will pay lip service to these ideas when trying to "do agile", they just can't help but put in top-down processes which determine how work is done. 

The Agile Manifesto is intentionally vague on how to apply its principles in a growing, complex company, which leaves the opening for antithetical frameworks such as SCRUM and SAFe to offer answers to this problem in a way that leaves existing management structures in tact.

However, there is actually research into more concrete ways to achieve autonomy, and show the benefits of it. The best case is the Viable System Model (VSM) developed by Stafford Beer. The VSM states that as your organization becomes more complex, your process will become inherently more complex. The only way to escape this is to build autonomous teams which perform the valuable work, and only report on what they are doing up to a meta-coordination function. 

Underpinning these ideas in the VSM is the "Law of Requisite Variety". This principle states that in order to be a sustaining organization, you must maintain a equilibrium in variety. This means that if you allow for no freedom to try things differently to your teams, your organization will not be able to adjut to changing environmental conditions. If you allow too much variety, chaos will prevent you from moving forward. In order to maintain the control risk-adverse, hierarchal organizations require, companies will settle on a certain standard that will eventually fall on the rigid side of this equilibrium. Even moving the goal posts toward variety following best practices of something like SCRUM, you will still fall on the side of rigidity some of the time, as the point of equilibrium is always changing. The only way to stay at equilibrium is to give real autonomy to your value-producing teams, and support them with the information to know when to take more or less risks, but do not prescribe their actions beyond that.

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

In SCRUM, these principles are applied with the idea of Sprints. Each iteration (short, 1-2 weeks), work with the business to identify the most important things to do. Delivery them quickly, then repeat. This is an area where management will explicitly reject the agile principles. Most companies require longer term road maps with time estimates. Many even demand estimates on each individual ticket. In order to underscore how un-agile this is, the #noestimates movement has arisen, which highlights the fact that time spent estimating issues is, at best, not worth it, and at worst, counter-productive.  I personally have used JIRA ticket data (in the ten-thousands) to show that velocity projections would have been more accurate if every ticket was weighted the same rather than given a story point value. This was true for five out of the five teams I calculated it for. But no matter how much you show that estimates are inaccurate, and that the time spent on them is wasted, management will never accept eliminating them, because at the end of the day, they are still committed to Taylorism, and not willing to give up on this level of control over the workers.

### Pace

> Agile processes promote sustainable development. The sponsors, developers, and users should be able to maintain a constant pace indefinitely.

The agile manifesto advocates for a steady, sustainable work pace. It sells this idea on the principle that if you overwork developers, their output will be of a worse quality, and turnover will be high. Of course, this principle also offers protection to developers from miserable working conditions such as crunch time.
However, agile also promises:

> Deliver working software frequently, from a couple of weeks to a couple of months, with a preference to the shorter timescale.

For-profit corporations are always trying to optimize for growth. The wording of the latter principle is close enough to 'go faster' that managers are happy to twist its meaning into that, and completely disregard all of the other language regarding sustainable pace. Sustainable pace and the unbounded growth demanded by capitalism are incompatible concepts.

### Ethics

Ethics in software development has become a primary concern. The agile manifesto does not address this at all. As the public has noticed the need to establish ethics, many developers and "thought leaders" have been trying to come up with guidelines. But like the Agile Manifesto, companies will not adopt these ideas, unless it is to their benefit. But really, the authors knew this, deep down. This is why the authors were sure to state up front that the goal of the whole thing is to serve the business:

> Our highest priority is to satisfy the customer through early and continuous delivery of valuable software.

This is a clever way to to frame agile ideas in a light that could appeal to organizations, and show how these ideas could benefit business and workers alike. But this hoodwink did not work, and this hints at the true reason for agile failing. It provides a critique of all the things that cause alienation and a lack of agency in an organization, but proposes a fix that aligns to the existing management and mode of production of the organization. The only way to fix the issues called out by the manifesto is to attack the root cause. You must address the structure of a for-profit corporation within the capitalist mode of production. Trying to split the difference just falls flat. It is a proposal that both does not align to the true goals of a corporation, but also does not provide the workers with the ideas and tools they need to _force_ this change on organizations.

When attending conferences featuring agile experts, many developer attendees are left thinking "This sounds great, but how can I do this?" The answer is you can't. Individual workers are not empowered to help define how they work.

### Alienation

Taylorism, and capitalism, in general, is always trying to extract as much value from the worker for the minimum cost. To do this, managements goal is to treat autonomous, self-realized human beings, as an economic entities, automata. Through this process, the worker loses the right to think of themselves as the director of their own actions; to determine the character of said actions; to define relationships with other people; and to own those items of value from goods and services, produced by their own labor. This is what we are experiencing when we run into all of the problems outlined above. This is known as "Alienation". This is what we need to identify as the root cause of these problems, and what we need to fix if we want to improve conditions. Agile does not take this issue head on, which is why it fails in all the ways outlined above.

## Going Forward
So how can we finally achieve the goals of the manifesto? How can we give software developers agency to be successful, generate value for their companies, AND develop software which interacts with our society in an ethical way? There are 2 aspects we need to consider when forming a solution. First, we need a vision of what the technical solution should be. If waterfall and agile don't provide the system we want to see, we need to figure out a framework that does meet our goals. But just coming up with a good technical solution is not enough. There needs to be a social solution to enact change in our industry to implement this solution. So first, let's think about what a technical solution might look like.

### Viable System Model
The previously mentioned Viable System Model (aka VSM) is a system of organization developed by cybernetition Stafford Beer in the 1970's. It emphasizes autonomy among the workers providing the value to the company, with feedback loops going upward into meta systems for coordination, rather than top-down prescriptive management. This model fundamentally upends traditional relationships in the workplace. There is a lot of evidence that the model works very well, especially in industries that have rapidly changing environmental conditions, but has not seen wide adoption, probably because of the threat it poses to traditional management systems. 

### Worker Owned Co-ops
A worker-owned co-op is a company organized under different rules than a privately or publicly held for-profit or non-profit organization. Ownership is held by the workers. There are no other owners. Worker Co-ops also are governed by some form of democratic process, in which all workers, and no one else participate.  In tech, co-ops are usually small consulting firms, but the model could apply to other types of organizations as well.  A worker co-op structurally removes the incentives for fast deadlines and hierarchical management, and get them out of the way of running a successful software project.


### Collective Action
VSM and worker co-ops are two very different, but promising models of how we can enable teams to be successful and have agency in their work. But traditional companies will not voluntarily reorganize along these lines. Watering down these solutions to sell them as "productivity solutions", like has been done with Agile, would cause them to lose the essence of what they are. If they are to be adopted, they will need to be embraced whole-heatedly. The only way to force organizations to change in a way that is contrary to the traditional "fiduciary duty to shareholders" incentives of companies, it will need to come from the workers themselves. We will never convey the change we want to see with fidelity by laundering it through the MBA project management industry. These changes will need to be demanded directly and collectively by all of us.  It is sometimes said that tech workers don't need unions because we are well paid, have good benefits, and good working conditions. But unions are not just for increasing pay and benefits. They are a vehicle for any type of push back from the workers to a company, to make changes that will benefit the workers, and sometimes the company itself. A formal union is not even always needed to make these types of demands, but formal unions do provide protection against retaliation from the company on these types of activities. There is been a wave of tech organization lately, around a variety of issues, including pushing back on tech companies collaborating with oppressive government agencies, and terrible project management practices such as "crunch time". Organization in the tech industry is possible, it is happening, and it is a way to enact real change in our industry, including changing the way we work.

## Additional Resources

[Scientific Management](https://en.wikipedia.org/wiki/Scientific_management) (Taylorism)

[Taylorism](http://www.businessdictionary.com/definition/Taylorism.html)

[Podcast about VSM](http://generalintellectunit.net/e/038-the-viable-system-model/)

[The VSM Guide](https://www.esrad.org.uk/resources/vsmg_3/screen.php)

[CoTech](https://www.coops.tech/) A tech coop network

[Tech Co-op Network](https://www.techworker.coop/) A North American Coop Network

Discuss this post on [Mastodon](https://mastodon.technology/web/statuses/101241850991374660).