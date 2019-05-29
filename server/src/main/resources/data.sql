INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1001, 'Define' );
INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1002, 'Make' );
INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1003, 'Deliver');

INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2001, 'Roadmap', 'A planning strategy that finds the correct software technologies for the short-term and long-term goals of a project. https://en.wikipedia.org/wiki/Technology_roadmap', 1001);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2002, 'Scope', 'Determine the goals, tasks, deliverables, cost and timeline of the project during the planning phase. https://melsatar.blog/2017/05/14/software-scope-vs-requirement-specifications/', 1001);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2003, 'Architect', 'The process of formulating a structured software solution from specific software characteristics that complies to specific business and technical requirements. https://codeburst.io/software-architecture-the-difference-between-architecture-and-design-7936abdd5830', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2004, 'Implement', 'Software developers program the application by coding the software according to the requirements of the project. https://en.wikipedia.org/wiki/Software_development', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2005, 'Integrate', 'Combining the various software subsystems into one complete system. https://webcase.studio/blog/what-integration-software-development/', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2006, 'Release', 'The final version of a software product is distributed to a production environment. https://searchsoftwarequality.techtarget.com/definition/release', 1003);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY)
VALUES(2007, 'Support', 'The phase of the project where software bugs, security and usability errors are resolved and delivered in the form of updates and patches. http://www.businessdictionary.com/definition/software-support.html', 1003);

INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5001, 'Book' );
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5002, 'Book Section');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5003, 'Journal Article' );
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5004, 'Article in a Periodical' );
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5005, 'Conference Proceedings');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5006, 'Report');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5007, 'Web site');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5008, 'Document from Web site');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5009, 'Electronic Source');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5010, 'Art');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5011, 'Sound Recording');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5012, 'Performance');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5013, 'Film');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5014, 'Interview');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5015, 'Patent');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5016, 'Case');
INSERT INTO REFERENCE_SOURCE_TYPE (ID, NAME)
VALUES (5017, 'Miscellaneous');


INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6001, 'Ron Jeffries', 2011, 'https://ronjeffries.com/xprog/what-is-extreme-programming/', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6002, 'Alexander Sergeev', 2016, 'https://hygger.io/blog/4-steps-to-planning-game-in-extreme-programming/', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6003, 'Margaret Rouse', 0, 'https://searchsoftwarequality.techtarget.com/definition/Pair-programming', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6004, '', 0, 'https://en.wikibooks.org/wiki/Software_Engineering_with_an_Agile_Development_Framework/Iteration_One/System_metaphor', 5002);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6005, '', 1999, 'http://www.extremeprogramming.org/rules/collective.html', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6006, 'Richard Bellairs', 2018, 'https://www.perforce.com/blog/qac/9-best-practices-using-coding-standards', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6007, '', 0, 'https://codeship.com/continuous-integration-essentials', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6008, 'Bjorn W.', 2018, 'https://explainagile.com/agile/xp-extreme-programming/practices/10-minute-build/', 5007);
INSERT INTO REFERENCE (ID, AUTHOR, YEAR, REFERENCE, REFERENCE_SOURCE_TYPE)
VALUES (6009, '', 0, 'https://www.agilealliance.org/glossary/simple-design/', 5007);

INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3001, 'Planning Game', 'The prediction of what features will be accomplished by the target date and analyzing what requirements to do next.', 2002, 6001);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3002, 'Metaphor', 'An agreed upon idea of how the software application functions.', 2001, 6004);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3003, 'Simple Design', 'Construct software according to a simple yet sufficient design.', 2004, 6009);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3004, 'Pair Programming', 'The software application is coded by two programmers sitting together at the same time and same machine.', 2004, 6003);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3005, 'Collective Ownership', 'Any combination of pair programmers may enhance the software code at any given time.', 2004, 6005);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3006, 'Coding Standards', 'The software is coded according to an agreed upon set of standards to assist collective ownership and familiarity of the code.', 2004, 6006);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3007, 'Continuous Integration', 'Continually keeping the system integrated at all times. The team typically builds the system multiple times a day instead of only once a day.', 2005, 6007);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY, REFERENCE)
VALUES(3008, 'Ten-minute Build', 'All the tests of the code base must finish running within a ten minute time limit.', 2005, 6008);

INSERT INTO CONTEXT_ENTITY_TYPE (ID, NAME, DESCRIPTION)
VALUES (8001, 'Architect', 'Architect Team');
INSERT INTO CONTEXT_ENTITY_TYPE (ID, NAME, DESCRIPTION)
VALUES (8002, 'Developer', 'Developer Team');
INSERT INTO CONTEXT_ENTITY_TYPE (ID, NAME, DESCRIPTION)
VALUES (8003, 'Client', 'Client Team');
INSERT INTO CONTEXT_ENTITY_TYPE (ID, NAME, DESCRIPTION)
VALUES (8004, 'Tester', 'Tester Team');

INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4001, 'People' );
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4002, 'Place' );
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4003, 'Product');
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4004, 'Process');

INSERT INTO CONTEXT_VALUE_LEVEL (ID, VALUE, DESCRIPTION)
VALUES (7001, 1, 'Very low' );
INSERT INTO CONTEXT_VALUE_LEVEL (ID, VALUE, DESCRIPTION)
VALUES (7002, 2, 'Low' );
INSERT INTO CONTEXT_VALUE_LEVEL (ID, VALUE, DESCRIPTION)
VALUES (7003, 3, 'Medium' );
INSERT INTO CONTEXT_VALUE_LEVEL (ID, VALUE, DESCRIPTION)
VALUES (7004, 4, 'High' );
INSERT INTO CONTEXT_VALUE_LEVEL (ID, VALUE, DESCRIPTION)
VALUES (7005, 5, 'Very high' );


INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11001, 1, 'Same room' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11002, 2, 'Same building' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11003, 3, 'Same city' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11004, 4, 'Same country' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11005, 5, 'Different country' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11006, 6, 'Same time zone' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11007, 7, '< 4 hours overlap' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11008, 8, '< 6 hours overlap' );
INSERT INTO CONTEXT_VALUE_DISTANCE (ID, VALUE, DESCRIPTION)
VALUES (11009, 9, '>= 6 hours overlap' );


INSERT INTO CONTEXT_ENTITY (ID, CONTEXT_ENTITY_TYPE, CAPABILITY, MOTIVATION, EMPOWERMENT, CULTURAL_COHESION, PHYSICAL_DISTANCE, TEMPORAL_DISTANCE)
VALUES (9001, 8001, 7001, 7003, 7005, 7002, 11001, 11003);
INSERT INTO CONTEXT_ENTITY (ID, CONTEXT_ENTITY_TYPE, CAPABILITY, MOTIVATION, EMPOWERMENT, CULTURAL_COHESION, PHYSICAL_DISTANCE, TEMPORAL_DISTANCE)
VALUES (9002, 8002, 7002, 7004, 7001, 7005, 11007, 11009);

INSERT INTO CONTEXT_INTERFACE (ID, ENTITY1, ENTITY2, CULTURAL_COHESION, AVAILABILITY, PHYSICAL_DISTANCE, TEMPORAL_DISTANCE)
VALUES (10001, 9001, 9002, 7003, 7002, 11006, 11002);