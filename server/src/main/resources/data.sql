INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1001, 'Define' );
INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1002, 'Make' );
INSERT INTO PRACTICE_CATEGORY (ID, NAME)
VALUES ( 1003, 'Deliver');

INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2001, 'Roadmap', 'A planning strategy that finds the correct software technologies for the short-term and long-term goals of a project. https://en.wikipedia.org/wiki/Technology_roadmap', 1001);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2002, 'Scope', 'Determine the goals, tasks, deliverables, cost and timeline of the project during the planning phase. https://melsatar.blog/2017/05/14/software-scope-vs-requirement-specifications/', 1001);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2003, 'Architect', 'The process of formulating a structured software solution from specific software characteristics that complies to specific business and technical requirements. https://codeburst.io/software-architecture-the-difference-between-architecture-and-design-7936abdd5830', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2004, 'Implement', 'Software developers program the application by coding the software according to the requirements of the project. https://en.wikipedia.org/wiki/Software_development', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2005, 'Integrate', 'Combining the various software subsystems into one complete system. https://webcase.studio/blog/what-integration-software-development/', 1002);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2006, 'Release', 'The final version of a software product is distributed to a production environment. https://searchsoftwarequality.techtarget.com/definition/release', 1003);
INSERT INTO PRACTICE_SUB_CATEGORY (ID, NAME, DESCRIPTION, PRACTICE_CATEGORY_ID)
VALUES(2007, 'Support', 'The phase of the project where software bugs, security and usability errors are resolved and delivered in the form of updates and patches. http://www.businessdictionary.com/definition/software-support.html', 1003);

INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3001, 'Planning Game', 'The prediction of what features will be accomplished by the target date and analyzing what requirements to do next. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2002);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3002, 'Metaphor', 'An agreed upon idea of how the software application functions. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2001);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3003, 'Simple Design', 'Construct software according to a simple yet sufficient design. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2004);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3004, 'Pair Programming', 'The software application is coded by two programmers sitting together at the same time and same machine. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2004);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3005, 'Collective Ownership', 'Any combination of pair programmers may enhance the software code at any given time. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2004);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3006, 'Coding Standards', 'The software is coded according to an agreed upon set of standards to assist collective ownership and familiarity of the code. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2004);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3007, 'Continuous Integration', 'Continually keeping the system integrated at all times. The team typically builds the system multiple times a day instead of only once a day. https://ronjeffries.com/xprog/what-is-extreme-programming/', 2005);
INSERT INTO PRACTICE (ID, NAME, DESCRIPTION, PRACTICE_SUB_CATEGORY_ID)
VALUES(3008, 'Ten-minute Build', 'All the tests of the code base must finish running within a ten minute time limit. https://explainagile.com/agile/xp-extreme-programming/practices/10-minute-build/', 2005);

INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4001, 'People' );
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4002, 'Place' );
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4003, 'Product');
INSERT INTO CONTEXT_DIMENSION (ID, NAME)
VALUES (4004, 'Process');

INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5001, 'Book' );
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5002, 'Book Section');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5003, 'Journal Article' );
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5004, 'Article in a Periodical' );
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5005, 'Conference Proceedings');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5006, 'Report');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5007, 'Web site');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5008, 'Document from Web site');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5009, 'Electronic Source');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5010, 'Art');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5011, 'Sound Recording');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5012, 'Performance');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5013, 'Film');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5014, 'Interview');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5015, 'Patent');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5016, 'Case');
INSERT INTO REFERENCE_TYPE (ID, NAME)
VALUES (5017, 'Miscellaneous');