-- Get job_history rows where the job_id belongs to one of these specific
-- jobs (verified via a subquery on the jobs table) AND the department_id
-- belongs to one of these specific departments (verified via a subquery
-- on the departments table)
SELECT *
FROM job_history
WHERE job_id IN (
    SELECT job_id FROM jobs
    WHERE job_id IN ('AD_ASST','FI_MGR','FI_ACCOUNT','AC_MGR',
                      'AC_ACCOUNT','SA_MAN','SA_REP','PU_MAN')
)
AND department_id IN (
    SELECT department_id FROM departments
    WHERE department_name IN ('Administration','Marketing','Purchasing',
                               'Human Resources','Shipping')
);