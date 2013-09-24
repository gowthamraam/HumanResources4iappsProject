create or Replace procedure xx_lookup_table_hr  
as
Begin

/*************************** 1. Region Table *****************************/
Execute Immediate('create table XXHLP_REGION(lookup_code number(2), lookup_meaning varchar(30))');

/*************************** 2.. Applicant Staus Table *****************************/
Execute Immediate('create table XXHLP_REC_APP_STATUS(lookup_code number(1), lookup_meaning varchar(30))');

/*************************** 3. Communication Mode Table *****************************/
Execute Immediate('create table XXHLP_REC_COMM_MODE(lookup_code number(1), lookup_meaning varchar(30))');

/*************************** 4. College  Table *****************************/
Execute Immediate('create table XXHLP_COLLEGE(lookup_code number(5), lookup_meaning varchar(30))');

/*************************** 5. Interview Type  Table *****************************/
Execute Immediate('create table XXHLP_INT_TYPE(lookup_code number(1), lookup_meaning varchar(30))');

/*************************** 6. Communication Mode Table *****************************/
Execute Immediate('create table XXHLP_INT_RESULT(lookup_code number(1), lookup_meaning varchar(30))');


/*************************** 7. Countries Table *****************************/
Execute Immediate('create table XXHLP_COUNTRIES(region_id number(2), lookup_code number(3), lookup_meaning varchar(30))');


/*************************** 8. Education Types Table *****************************/
Execute Immediate('create table XXHLP_EDUCATION_TYPE (lookup_code number(1), lookup_meaning varchar(30))');




/*************************** 9. Education  such as (BE, B.a) Table *****************************/
Execute Immediate('create table XXHLP_EDUCATION (Edu_type_id number(1), lookup_code number(2), lookup_meaning varchar(30))');



/*************************** 10. Major Subject Table *****************************/
Execute Immediate('create table XXHLP_MAJOR_SUBJECT(edu_id number(2), lookup_code number(4), lookup_meaning varchar(30))');


end;



create or Replace procedure xx_lookup_table_hr_insert_data
as
Begin



end;


create or Replace procedure xx_lookup_table_hr_drop  
as

Begin

/*************************** 1. Region Table *****************************/
Execute Immediate('Drop table XXHLP_REGION');

/*************************** 2.. Applicant Staus Table *****************************/

Execute Immediate('Drop table XXHLP_REC_APP_STATUS');
/*************************** 3. Communication Mode Table *****************************/
Execute Immediate('Drop table XXHLP_REC_COMM_MODE');
/*************************** 4. College  Table *****************************/
Execute Immediate('Drop table XXHLP_COLLEGE');
/*************************** 5. Interview Type  Table *****************************/
Execute Immediate('Drop table XXHLP_INT_TYPE');
/*************************** 6. Communication Mode Table *****************************/
Execute Immediate('Drop table XXHLP_INT_RESULT');

/*************************** 7. Countries Table *****************************/
Execute Immediate('Drop table XXHLP_COUNTRIES');

/*************************** 8. Education Types Table *****************************/
Execute Immediate('Drop table XXHLP_EDUCATION_TYPE');

/*************************** 9. Education  such as (BE, B.a) Table *****************************/
Execute Immediate('Drop table XXHLP_EDUCATION');


/*************************** 10. Major Subject Table *****************************/
Execute Immediate('Drop table XXHLP_MAJOR_SUBJECT');







end;





begin
xx_lookup_table_hr ;
end;



begin
xx_lookup_table_hr_drop;
end;