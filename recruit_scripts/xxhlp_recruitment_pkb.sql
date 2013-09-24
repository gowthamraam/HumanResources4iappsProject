CREATE OR REPLACE package body APPS.xxhlp_recruitment_pkg is
 /*
   *****************************************************************************
   **
   **  Package        : xxhlp_recruitment_pkg
   **  Object type    : package spec
   **  Description   :  Contain all Recruitment related procedures  
   **                     
   **  change history
   **   
   **  date           name                   version         modification
   **  -------        -------                -------         ------------
   **  10-Apr         Navu - 4i Apps         1.0              
     *************************************************************************** 
 */
 
--//=============================================================
--// procedure to create Applicant as Employee in HR system
--//=============================================================
procedure create_employee(p_app_id       in     number,
                          p_err_code     in out varchar2,
                          p_err_msg      in out varchar2)                          
is 
 lc_employee_number                per_all_people_f.employee_number%type ; 
 ln_person_id                      per_all_people_f.person_id%type; 
 ln_assignment_id                  per_all_assignments_f.assignment_id%type; 
 ln_object_ver_number              per_all_assignments_f.object_version_number%type; 
 ln_asg_ovn                        number;   
 ld_per_effective_start_date       per_all_people_f.effective_start_date%type; 
 ld_per_effective_end_date         per_all_people_f.effective_end_date%type; 
 lc_full_name                      per_all_people_f.full_name%type; 
 ln_per_comment_id                 per_all_people_f.comment_id%type; 
 ln_assignment_sequence            per_all_assignments_f.assignment_sequence%type; 
 lc_assignment_number              per_all_assignments_f.assignment_number%type;   
 lb_name_combination_warning       boolean; 
 lb_assign_payroll_warning         boolean; 
 lb_orig_hire_warning              boolean;   
 
 -- Cursor to fetch the details of the Applicant
 cursor c1 is
 select xha.app_id,
       xha.employee_number,
       xha.first_name,
       xha.last_name,
       xha.gender,
       xha.date_of_birth,
       xha.id_number,
       xha.email_id,
       xha.country 
  from xxhlp_applicants xha
 where xha.app_id  = p_app_id;  
 
       r1            c1%rowtype;

begin 
    p_err_code := 0 ;
    p_err_msg  := 'Success' ;
   
    open  c1;
    fetch c1 into r1;
    close c1;

    hr_employee_api.create_employee 
           (   -- input data elements  
               -- ------------------------------ 
               p_hire_date                              => to_date(sysdate), 
               p_business_group_id                      => fnd_profile.value_specific('PER_BUSINESS_GROUP_ID'), 
               p_employee_number                        => r1.employee_number, 
               p_last_name                              => r1.last_name, 
               p_first_name                             => r1.first_name, 
               p_middle_names                           => null, 
               p_sex                                    => r1.gender, 
               p_national_identifier                    => r1.id_number, 
               p_date_of_birth                          => to_date(r1.date_of_birth), 
               p_known_as                               => r1.last_name,
               p_email_address                          => r1.email_id,
               p_country_of_birth                       => r1.country,  
               -- output data elements  
               -- -------------------------------- 
               p_person_id                              => ln_person_id, 
               p_assignment_id                          => ln_assignment_id, 
               p_per_object_version_number              => ln_object_ver_number, 
               p_asg_object_version_number              => ln_asg_ovn, 
               p_per_effective_start_date               => ld_per_effective_start_date, 
               p_per_effective_end_date                 => ld_per_effective_end_date, 
               p_full_name                              => lc_full_name, 
               p_per_comment_id                         => ln_per_comment_id, 
               p_assignment_sequence                    => ln_assignment_sequence, 
               p_assignment_number                      => lc_assignment_number, 
               p_name_combination_warning               => lb_name_combination_warning, 
               p_assign_payroll_warning                 => lb_assign_payroll_warning, 
               p_orig_hire_warning                      => lb_orig_hire_warning  
        );   
    commit;
exception 
      when others then 
        rollback;
        p_err_code := -1 ;
        p_err_msg  := 'Error in create_employee : ' || substr(sqlerrm, 1, 200) ;                
end create_employee; 

--//=============================================================
--// procedure to Delete Applicant
--//=============================================================
procedure delete_applicant(p_del_from     in     varchar2,
                           p_pk_id        in     number,
                           p_err_code     in out varchar2,
                           p_err_msg      in out varchar2 ) is

    l_del_from    varchar2(30) ;

begin
    p_err_code := 0 ;
    p_err_msg  := 'Success' ;

    l_del_from := upper(p_del_from) ;

    if l_del_from = 'APPLICANT' then
       delete xxhlp_app_int_panels
       where  app_id = p_pk_id ;
    
       delete xxhlp_app_interviews
       where  app_id = p_pk_id ;

       delete xxhlp_app_educations
       where  app_id = p_pk_id ;
    
       delete xxhlp_app_employments
       where  app_id = p_pk_id ;
    
       delete xxhlp_applicants
       where  app_id = p_pk_id ;
    end if ;

    if l_del_from = 'INTERVIEW' then
       delete xxhlp_app_int_panels
       where  app_int_id = p_pk_id ;
    
       delete xxhlp_app_interviews
       where  app_int_id = p_pk_id ;
    end if ;
    
    if l_del_from = 'INT_PANEL' then
       delete xxhlp_app_int_panels
       where  int_panel_id = p_pk_id ;
    end if ;

    if l_del_from = 'EDUCATION' then
       delete xxhlp_app_educations
       where  app_edu_id = p_pk_id ;
    end if ;

    if l_del_from = 'EXPERIENCE' then
       delete xxhlp_app_employments
       where  app_emp_id = p_pk_id ;
    end if ;
    
    commit ;
    
exception
    when others then
        rollback;
        p_err_code := -1 ;
        p_err_msg  := 'Error in delete_applicant : ' || substr(sqlerrm, 1, 200) ;    
end delete_applicant;


--//=============================================================
--// procedure to Check for Duplicate ID Number 
--//=============================================================
procedure chk_for_dup_id_num(p_id_number    in     varchar2,
                             p_app_id       in     number,
                             p_err_code     in out varchar2,
                             p_err_msg      in out varchar2 ) is

    l_dummy number ; -- varchar2(240);
cursor c1 is
       select count(app_id) -- app_ref_number ||' - ' || last_name ||', '||first_name
       from   xxhlp_applicants
       where  app_id != p_app_id
       and    upper(id_number) = upper(p_id_number) ;
begin
    p_err_code := 0 ;
    p_err_msg  := 'Success' ;

    open  c1 ;
    fetch c1 into l_dummy ;
    close c1 ;
    
    if l_dummy > 0 then
       p_err_code := -1 ;
       p_err_msg  := 'This ID Number already exists...' ;
    end if ;
        
exception
    when others then
        p_err_code := -1 ;
        p_err_msg  := 'Error in chk_for_dup_id_num : ' || substr(sqlerrm, 1, 200) ;    
end chk_for_dup_id_num;


--//=============================================================
--// procedure to Check for Duplicate Employee Number 
--//=============================================================
procedure chk_for_dup_emp_num(p_emp_number   in     varchar2,
                              p_app_id       in     number,
                              p_err_code     in out varchar2,
                              p_err_msg      in out varchar2 ) is

    l_dummy number ;  -- varchar2(240);
cursor c1 is
       select count(person_id) -- app_ref_number ||' - ' || last_name ||', '||first_name
       from   per_all_people_f
       where  upper(employee_number) = upper(p_emp_number)
       and    sysdate between effective_start_date and effective_end_date ;

cursor c2 is
       select count(app_id) -- app_ref_number ||' - ' || last_name ||', '||first_name
       from   xxhlp_applicants
       where  app_id != p_app_id
       and    upper(employee_number) = upper(p_emp_number) ;

begin
    p_err_code := 0 ;
    p_err_msg  := 'Success' ;

    open  c1 ;
    fetch c1 into l_dummy ;
    close c1 ;
    
    if l_dummy > 0 then
       p_err_code := -1 ;
       p_err_msg  := 'Duplicate Employee Number...' ;

       return ;
    end if ;
       
    open  c2 ;
    fetch c2 into l_dummy ;
    close c2 ;
    
    if l_dummy > 0 then
       p_err_code := -1 ;
       p_err_msg  := 'Duplicate Employee Number...' ;
    end if ;
       
exception
    when others then
        p_err_code := -1 ;
        p_err_msg  := 'Error in chk_for_dup_emp_num : ' || substr(sqlerrm, 1, 200) ;    
end chk_for_dup_emp_num;

--//=============================================================
--// function to check whether the applicant is interviewd or not 
--//=============================================================
function is_interviewed(p_app_id   in number, 
                        p_int_type in varchar2) 
         return varchar2 is

    l_dummy varchar2(1) ;
cursor c1 is
       select 'Y'
       from   xxhlp_app_interviews
       where  app_id   = p_app_id
       and    int_type = p_int_type ;

begin
    open  c1 ;
    fetch c1 into l_dummy ;
    if c1%notfound then
       l_dummy := 'N' ;
    end if ;
    close c1 ;
    
    return l_dummy ;       
exception
    when others then
         return 'N' ;    
end is_interviewed ;



end xxhlp_recruitment_pkg;
/
