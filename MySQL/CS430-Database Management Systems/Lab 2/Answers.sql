

SELECT S.Name, T.Grade FROM STUDENT S, Take T

WHERE S.SID = T.SID AND T.Grade > 3.0;


SELECT S.Name, (T.ProfessorEval + T.Grade)/2 AS Average FROM STUDENT S, Take T
    WHERE S.SID = T.SID
    ORDER BY Average ASC;



SELECT S.Name FROM
STUDENT S, (SELECT * FROM Take WHERE CourseNum = '101') AS T
WHERE T.SID = S.SID AND T.DeptName = 'Education';

SELECT C.CourseName FROM COURSE AS C, Take T
WHERE C.CourseNum = T.CourseNum AND T.CourseNum
NOT IN (SELECT CourseNum FROM PreReq);

select * 
from COURSE c
where EXISTS (select * 
                  from   PreReq p 
                  where  p.coursenum = c.coursenum);
