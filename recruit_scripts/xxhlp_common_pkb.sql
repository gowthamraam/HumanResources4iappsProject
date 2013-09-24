CREATE OR REPLACE package body APPS.xxhlp_common_pkg
as
    --+= ==============================================================================     +--
  --|                        4I APPS SOLUTION PVT LIMITED                                 |--
  --+= ==============================================================================     +--
  /*--------------------------------------------------------------------------------*\
  -- Module   :  BMHR Helpdesk Management                                                  --
  --                                                                                       --
  -- Source   :  XXHLP_COMMON_PKG                                                            --
  --                                                                                       --
  -- Purpose  :  create package xxhlp_comon_pkg in apps schema to
                 define commonly used procedure/functions                                                                  --
  --                                                                                       --
  -- Author   :  Vignesh M,4IAPPS                                               --
  
  --+==============================================================================+       --
  --|                                change history                                |       --
  --+==============================================================================+       --
  -- version  c/r no.        when              who             what                        --
  -- -------  -------        -----             ---             ----                        --
  -- 1.0                   31-july-2013      4IAPPS         Production                   --
  \*--------------------------------------------------------------------------------*/

   --//=============================================================
--// procedure to get request sequence number based on request
--//=============================================================
   procedure get_sequence_number (p_req_id in number, x_seq out varchar2)
   is
      cursor lc_req_mst
      is
         select lpad (req_next_num, 6, '0') req_next_num, req_prefix
           from xxhlp_request_master
          where req_id = p_req_id;

      lr_req_mst   lc_req_mst%rowtype;
   begin
      x_seq := null;

      open lc_req_mst;

      fetch lc_req_mst
       into lr_req_mst;

      close lc_req_mst;

      x_seq :=
            lr_req_mst.req_prefix
         || '-'
         || to_char (sysdate, 'YYYY')
         || '-'
         || lr_req_mst.req_next_num;

      update xxhlp_request_master
         set req_next_num = req_next_num + 1
       where req_id = p_req_id;
   exception
      when others
      then
         x_seq := null;
   end;

--//=================================================================
--// procedure to get request sequence number based on request group
--// there should be only one request for such groups
--//=================================================================
   procedure get_sequence_number (p_req_group in varchar2, x_seq out varchar2)
   is
      cursor lc_req_mst
      is
         select lpad (req_next_num, 6, '0') req_next_num, req_prefix
           from xxhlp_request_master
          where req_group = p_req_group;

      lr_req_mst   lc_req_mst%rowtype;
   begin
      x_seq := null;

      open lc_req_mst;

      fetch lc_req_mst
       into lr_req_mst;

      close lc_req_mst;

      x_seq :=
            lr_req_mst.req_prefix
         || '-'
         || to_char (sysdate, 'YYYY')
         || '-'
         || lr_req_mst.req_next_num;

      update xxhlp_request_master
         set req_next_num = req_next_num + 1
       where req_group = p_req_group;
   exception
      when others
      then
         x_seq := null;
   end;

--//=============================================================
--// procedure to get function name of request
--//=============================================================
   procedure get_call_page (p_request_id in number, p_call_page in out varchar2)
   is
      l_call_page   xxhlp_request_master.req_call_page%type;

      cursor c1
      is
         select rm.req_call_page
           from xxhlp_request_master rm, xxhlp_requests r
          where r.req_id = rm.req_id and r.request_id = p_request_id;
   begin
      l_call_page := null;

      open c1;

      fetch c1
       into l_call_page;

      close c1;

      p_call_page := l_call_page;
   exception
      when others
      then
         p_call_page := null;
   end get_call_page;

--//=============================================================
--// function to get user name
--//=============================================================
   function get_user_name (p_person_id in number)
      return varchar2
   is
      cursor lcu_get_user
      is
         select user_name
           from fnd_user
          where employee_id = p_person_id and end_date is null;

      lc_user_name   fnd_user.user_name%type;
   begin
      lc_user_name := null;

      open lcu_get_user;

      fetch lcu_get_user
       into lc_user_name;

      close lcu_get_user;

      return (lc_user_name);
   exception
      when others
      then
         lc_user_name := null;
   end get_user_name;

--//=============================================================
--// fuction to get line manager
--//=============================================================
   function get_line_manager (p_person_id in number)
      return number
   is
      cursor lcu_line_mgr
      is
         select paf.supervisor_id
           from per_all_assignments_f paf
          where paf.person_id = p_person_id
            and trunc (sysdate) between paf.effective_start_date
                                    and paf.effective_end_date
            and paf.primary_flag = 'Y';

--            and paf.assignment_type = 'e';
      ln_person_id   number;
   begin
      ln_person_id := 0;

      open lcu_line_mgr;

      fetch lcu_line_mgr
       into ln_person_id;

      close lcu_line_mgr;

      return (ln_person_id);
   exception
      when others
      then
         ln_person_id := -1;
   end get_line_manager;

--//=============================================================
--// procedure to insert action history table
--//=============================================================
   procedure ins_action_history (p_request_id in number)
   is
      cursor employee_dtls
      is
         select person_id,
                full_name
           from per_all_people_f pap,
                fnd_user fu
          where fu.employee_id                   = pap.person_id
            and fu.user_id                       = l_user_id;

      ln_person_id   number;
      lc_emp_role    varchar2(30);
      lc_full_name   varchar2 (240);
   begin
      open employee_dtls;

      fetch employee_dtls
       into ln_person_id, lc_full_name;

      close employee_dtls;
      
   --   lc_emp_role:=xxhlp_wf_pkg.get_wf_role('EMP');

      insert into xxhlp_wf_approver_hist
                  (approver_hist_id, request_id,
                   response, approver_id, approver_name, submission_date,
                   approver_role,created_by, creation_date,last_update_date,
                   last_updated_by,
                   last_update_login
                  )
           values (xxhlp_wf_approver_hist_s.nextval, p_request_id,
                   'Pending', ln_person_id, lc_full_name, sysdate,
                   lc_emp_role,l_user_id, sysdate, sysdate,
                   l_user_id,
                   l_login_id
                  );

      commit;
   end;

--//=============================================================
--// procedure to get address of an employee
--//=============================================================
   procedure get_employee_dtls (
      p_person_id     in       number,
      x_full_name     out      varchar2,
      x_sex           out      varchar2,
      x_dob           out      date,
      x_nationality   out      varchar2,
      x_house_no      out      varchar2,
      x_way_no        out      varchar2,
      x_pb_no         out      varchar2,
      x_pc            out      varchar2,
      x_city          out      varchar2,
      x_country       out      varchar2
   )
   is
      cursor c1
      is
         select pap.full_name, pap.sex, pap.date_of_birth, nat.meaning,
                address_line1 "HouseNo", address_line2 "WayNo",
                address_line3 "PBNo", postal_code "Pc", town_or_city "City",
                d_country "Country"
           from per_all_people_f pap,
                fnd_lookup_values nat,
                per_addresses_v pad,
                fnd_user
          where sysdate between pap.effective_start_date
                            and pap.effective_end_date
            and pap.nationality = nat.lookup_code
            and nat.lookup_type = 'NATIONALITY'
            and pap.person_id = p_person_id
            and pap.person_id = pad.person_id(+)
            and pad.date_to is null;
   begin
      open c1;

      fetch c1
       into x_full_name, x_sex, x_dob, x_nationality, x_house_no, x_way_no,
            x_pb_no, x_pc, x_city, x_country;

      close c1;
   end get_employee_dtls;

--//=============================================================
--// procedure to get account number of an employee
--//=============================================================
   procedure get_account_no (p_person_id in number, x_account_no out varchar2)
   is
      cursor c1
      is
         select pea.segment2
           from xxhlp_requests rq,
                per_all_people_f pap,
                per_all_assignments_f paa,
                (select assignment_id, external_account_id
                   from pay_personal_payment_methods_f
                  where sysdate between effective_start_date
                                    and effective_end_date) pppm,
                pay_external_accounts pea
          where pap.person_id = paa.person_id(+)
            and sysdate between pap.effective_start_date
                            and pap.effective_end_date
            and sysdate between paa.effective_start_date
                            and paa.effective_end_date
            and paa.assignment_id = pppm.assignment_id(+)
            and pppm.external_account_id = pea.external_account_id(+)
            and pap.person_id = p_person_id;
   begin
      open c1;

      fetch c1
       into x_account_no;

      close c1;
   end get_account_no;

--//=============================================================
--// procedure to get appraisal rating of an employee
--//=============================================================
   procedure get_appraisal_rating (p_person_id in number, x_rating out varchar2)
   is
      cursor get_rating
      is
         select substr (attribute3, 1, 1)
           from per_appraisals
          where appraisee_person_id = p_person_id
            and attribute5 = 'STNDJAN' || (to_char (sysdate, 'yyyy') - 1)
            and attribute3 is not null;
   begin
      open get_rating;

      fetch get_rating
       into x_rating;

      close get_rating;
   end;
   
--//=============================================================
--// procedure to get grade of an employee
--//=============================================================  
   procedure get_grade(p_person_id in number,x_grade out varchar2)
   is
   
       cursor c1
       is
       select substr(pg.name,1,1) grade
       from   per_all_people_f pap
             ,per_all_assignments_f paa
             ,per_grades pg
       where  sysdate between pap.effective_start_date and pap.effective_end_date
       and    sysdate between paa.effective_start_date and paa.effective_end_date
       and    pg.date_to is null 
       and    pap.person_id = paa.person_id(+)
       and    paa.grade_id = pg.grade_id(+)
       and    pap.current_employee_flag = 'Y' 
       and    paa.assignment_type = 'E'
       and    pap.person_id = p_person_id;
   
   begin
   
        open c1;
        fetch c1 into x_grade;
        close c1;
        
   end;
   
--//=============================================================
--// Function to get grade of an employee
--//=============================================================  
   function get_grade(p_person_id in number) return varchar2
   is   
       cursor c1
       is
       select pg.name grade
       from   per_all_people_f pap
             ,per_all_assignments_f paa
             ,per_grades pg
       where  sysdate between pap.effective_start_date and pap.effective_end_date
       and    sysdate between paa.effective_start_date and paa.effective_end_date
       and    pg.date_to is null 
       and    pap.person_id                      = paa.person_id(+)
       and    paa.grade_id                       = pg.grade_id(+)
       and    pap.current_employee_flag          = 'Y' 
       and    paa.assignment_type                = 'E'
       and    pap.person_id                      = p_person_id;
       
       l_grade    varchar2(240);
   
   begin
   
        open c1;
        fetch c1 into l_grade;
        close c1;
        
        return l_grade;
        
   end;

--//=============================================================
--// procedure to insert check list transaction rows
--//=============================================================
   procedure insert_check_list (p_request_id in number)
   is
      cursor c1
      is
         select cl_id, cl_req_id
           from xxhlp_check_list_master cm, xxhlp_requests rq
          where rq.req_id = cm.cl_req_id and rq.request_id = p_request_id;
   begin
      for i in c1
      loop
         insert into xxhlp_request_check_lists
                     (rcl_id, request_id, cl_id,
                      req_id, created_by, creation_date,
                      last_updated_by, last_update_date,
                      last_update_login
                     )
              values (xxhlp_rcl_id_s.nextval, p_request_id, i.cl_id,
                      i.cl_req_id, l_user_id, sysdate,
                      l_user_id, sysdate,
                      l_login_id
                     );
      end loop;
   end insert_check_list;

--//=============================================================
--// function to convert days to hh:mm
--//=============================================================
   function convert_days (p_days in number)
      return varchar2
   is
      l_days     number;
      l_h1       number;
      l_hours    number;
      l_min1     number;
      l_min      number;
      l_sec1     number;
      l_sec      number;
   begin
      if p_days is null then
         return null ;
      else
          l_days := trunc (p_days);
          l_h1 := (p_days - l_days) * 24;
          l_hours := trunc (l_h1);
          l_min1 := (l_h1 - l_hours) * 60;
          l_min  := trunc(l_min1);
          l_sec1 := (l_min1-l_min) *60;
          l_sec  := trunc(l_sec1);

          if (l_days = 0 and l_h1 = 0 and l_hours = 0 and l_min = 0)
          then
             return null;
          else
             return (l_days || 'd ' || l_hours || 'h ' || l_min || 'm ' || l_sec || 's');
          end if;
      end if ;
   exception
      when others
      then
         return null;
   end convert_days;
   
--//=============================================================
--// procedure to set flow status and request status as cancelled
--//=============================================================   
       procedure cancel_request (p_request_id in number)
   is      
   begin
      
      update xxhlp_requests
         set 
--             flow_status                         = decode(flow_status,'N','C',flow_status)
            flow_with                           = decode(flow_status,'N','',flow_with)
--            ,request_status                      = decode(flow_status,'N','C',request_status)
            ,last_update_date                    = sysdate
            ,last_updated_by                     = fnd_profile.value('USER_ID')
            ,last_update_login                   = fnd_profile.value('LOGIN_ID') 
       where request_id                          = p_request_id;
         commit;
         end cancel_request;
         
--//=============================================================
--// procedure to set flow status and request status as saved
--//=============================================================   

  procedure save_request (p_request_id in number)
   is    
   
   ln_cnt  NUMBER;
   
   cursor c1  is
   select count(*) cnt 
   from xxhlp_request_master xrm,xxhlp_requests xr
   where xrm.req_id = xr.req_id
   and xr.request_id = p_request_id
   and xrm.req_group= 'TRANSFER';
     
   begin
   
   open c1;
   fetch c1 into ln_cnt;
   close c1;
   
   if ln_cnt > 0 then
   
      update xxhlp_requests
         set flow_status                         = 'PWRS',
             last_update_date                    = sysdate,
             last_updated_by                     = fnd_profile.value('USER_ID'),
             last_update_login                   = fnd_profile.value('LOGIN_ID') 
       where request_id                          = p_request_id;
         
         else
      
      update xxhlp_requests
         set flow_status                         = 'PWE',
             last_update_date                    = sysdate,
             last_updated_by                     = fnd_profile.value('USER_ID'),
             last_update_login                   = fnd_profile.value('LOGIN_ID') 
       where request_id                          = p_request_id;
         
         end if;
         
         commit;
   end save_request; 
--//=============================================================
--// function to get the account number of the person
--//=============================================================    
   function get_account_no (p_person_id in number)
return varchar2
as

l_account_no   varchar2(150);

cursor c1
      is
         select pea.segment2
           from per_all_people_f pap,
                per_all_assignments_f paa,
                (select assignment_id, external_account_id
                   from pay_personal_payment_methods_f
                  where sysdate between effective_start_date
                                    and effective_end_date) pppm,
                pay_external_accounts pea
          where pap.person_id = paa.person_id(+)
            and sysdate between pap.effective_start_date
                            and pap.effective_end_date
            and sysdate between paa.effective_start_date
                            and paa.effective_end_date
            and paa.assignment_id = pppm.assignment_id(+)
            and pppm.external_account_id = pea.external_account_id(+)
            and pap.person_id = p_person_id;
            
 
   begin
      open c1;

      fetch c1
       into l_account_no;

      close c1;
      return l_account_no;
   end get_account_no;  
--//=============================================================
--// procedure to get the salary details
--//=============================================================    
   procedure get_salary_dtls(p_person_id in number
                            ,x_basic_sal out number
                            ,x_gross_sal out number
                            )
   is
   
      cursor c1
      is  
        select a.proposed_salary_n basic_salary
              ,bmw_gross_salary_f1(a.assignment_id,to_char(change_date,'DD-MON-YYYY')) gross_salary 
        from   bm_sshr_salary_history_v a
              ,bm_all_assignment_f b
        where  a.employee_number = b.employee_number
          and  a.change_date = b.salary_date
          and  a.employee_number = (select employee_number
                                      from per_all_people_f
                                     where sysdate between effective_start_date and effective_end_date
                                       and person_id = p_person_id);

   
   begin
   
       open c1;
       fetch c1 into x_basic_sal,x_gross_sal;
       close c1;

--         x_basic_sal := 10000.00;
--         x_gross_sal := 10000.00;
          
   end get_salary_dtls;
--//=============================================================
--// procedure to get age
--//=============================================================      
  procedure get_age (p_person_id in number, x_age out number)
  is
   cursor get_age_diff
   is
   select round ((sysdate - date_of_birth) / 365) age
    from per_all_people_f pap
   where person_id = p_person_id
     and sysdate between pap.effective_start_date and pap.effective_end_date;
   begin
       open get_age_diff;
       fetch get_age_diff into x_age;
       close get_age_diff;
   end;
--//=============================================================
--// procedure to get Nationality of the person
--//=============================================================   
   procedure get_nationality (p_person_id in number, x_nationality out varchar2)
   is
   cursor get_nlty_check
   is
      select pap.nationality
        from per_all_people_f pap
       where sysdate between pap.effective_start_date and pap.effective_end_date
         and pap.current_employee_flag = 'Y'
         and pap.person_id = p_person_id;
   begin
       open get_nlty_check;
       fetch get_nlty_check into x_nationality;
       close get_nlty_check;
   end;
--//=============================================================
--// procedure to know the person is Male or Female
--//=============================================================    
procedure get_sex (p_person_id in number, x_sex out varchar2)
is
   cursor get_age
   is
      select pap.sex
        from per_all_people_f pap
       where pap.current_employee_flag = 'Y'
         and sysdate between pap.effective_start_date and pap.effective_end_date
         and pap.person_id = p_person_id;
begin
   open get_age;

   fetch get_age
    into x_sex;

   close get_age;
end;
--//=============================================================
--// function to get position Name
--//============================================================= 
   function get_position_name(p_person_id in number)
            return varchar2 is
   
      cursor c1 is
        select position_name
        from   bm_all_assignment_f 
        where  person_id = p_person_id;   
        
        l_position_name varchar2(240);
   
   begin
       open  c1;
       fetch c1 into l_position_name ;
       if c1%notfound then
          l_position_name := null ;
       end if ;
       close c1;
       
       return l_position_name ;
   exception
       when others then
            return null ;
   end get_position_name;
--//=============================================================
--// procedure to know months between
--//=============================================================    
   procedure get_six_month_valid(p_exp_date varchar2,x_month OUT NUMBER) is
   
   cursor c1 is
   select to_number(months_between(to_date(p_exp_date,'YYYY-MM-DD'),sysdate)) from dual;
   begin
   open c1;
   fetch c1 into x_month;
   close c1;              
    
   exception when others then 
   x_month:=null;
   end get_six_month_valid;
   
--//=============================================================
--// function to check hours
--//=============================================================   
    function check_hours (p_hours in number,p_time in number) return number
    is

    l_hours number;

    begin

     if    p_time = 0  and p_hours > 0   and p_hours < 24   then l_hours:= p_hours;
     elsif p_time = 0  and p_hours > 24  and p_hours <= 48  then l_hours:= null;
     elsif p_time = 0  and p_hours > 48                    then l_hours:= null;
     elsif p_time = 24 and p_hours > 0   and p_hours < 24   then l_hours:= null;
     elsif p_time = 24 and p_hours > 24  and p_hours <= 48  then l_hours:= p_hours;
     elsif p_time = 24 and p_hours > 48                    then l_hours:= null;
     elsif p_time = 48 and p_hours > 0   and p_hours < 24   then l_hours:= null;
     elsif p_time = 48 and p_hours > 24  and p_hours <= 48  then l_hours:= null;
     elsif p_time = 48 and p_hours > 48                    then l_hours:= p_hours; 
     end if;

    return l_hours;

    exception when others then
    return null;

    end;   
   
--//====================================================================
--// Procedure to insert data into loan calculator tables from t24 tables
--//====================================================================

   procedure insert_loan_calc(
                              p_request_id in number
                             ,p_username  in varchar2
                             )
   is
    
   cursor c1
   is
   select ecl_loan_types
         ,ecl_os_amount
         ,ecl_inst_amount
    from   xxhlp_emp_curr_loans_v
    where  ecl_person_id = get_person_from_username(p_username);   
    
    ln_basic_salary number;
    ln_gross_salary number;
    ln_esb          number;
    lc_emp_hr       varchar2(30);
    
   begin
   
       ln_esb := bmw_gratuity_f(get_person_from_username(p_username),SYSDATE);
      
       get_salary_dtls(get_person_from_username(p_username)
                      ,ln_basic_salary
                      ,ln_gross_salary
                      );
                  
      for i in 1..2 loop
       
          if i=1 then
          
           lc_emp_hr := 'E';
           
          elsif i=2 then
          
           lc_emp_hr := 'H';

           end if;
                     
              insert into xxhlp_loan_calc_sum(
                                              lcs_id
                                             ,request_id
                                             ,basic_salary
                                             ,gross_salary
                                             ,end_serv_benefit
                                             ,emp_hr
                                             ,created_by
                                             ,creation_date
                                             ,last_updated_by
                                             ,last_update_date
                                             ,last_update_login                                     
                                             )
                                       values(
                                              xxhlp_lcs_id_s.nextval
                                             ,p_request_id
                                             ,ln_basic_salary
                                             ,ln_gross_salary
                                             ,ln_esb
                                             ,lc_emp_hr
                                             ,l_user_id
                                             ,sysdate
                                             ,l_user_id
                                             ,sysdate
                                             ,l_login_id
                                             );
                                             
           commit;
           
          for i in c1 loop
      
                  insert into xxhlp_loan_calc(
                                              lc_id
                                             ,lcs_id
                                             ,request_id
                                             ,emp_hr
                                             ,liab_details
                                             ,curr_os_amt
                                             ,curr_inst_amt
                                             ,created_by
                                             ,creation_date
                                             ,last_updated_by
                                             ,last_update_date
                                             ,last_update_login                                     
                                             )
                                       values(
                                              xxhlp_lc_id_s.nextval
                                             ,xxhlp_lcs_id_s.currval                                              
                                             ,p_request_id
                                             ,lc_emp_hr
                                             ,i.ecl_loan_types
                                             ,i.ecl_os_amount
                                             ,i.ecl_inst_amount
                                             ,l_user_id
                                             ,sysdate
                                             ,l_user_id
                                             ,sysdate
                                             ,l_login_id
                                             );   
                                             
              commit;                                                    
      
          end loop; 
                    
      end loop;                                         
   
   end;   
   
--//====================================================================
--// Function to get person id from username
--//====================================================================         
   
   function get_person_from_username(p_username in varchar2)
   return number
   is   
       cursor c1
       is
       select employee_id
       from   fnd_user
       where  user_name  = p_username;
       
       ln_person_id number;
   
   begin
   
       open c1;
       fetch c1 into ln_person_id;
       close c1;
       
       return ln_person_id;
   
   exception
       
       when others then
          
            return -1;
   
   end;        
   
function add_weeks(in_dt in date,num_weeks in integer) return date is
  cursor c1 is
     select in_dt + (num_weeks * 7) from dual;   
   l_out_weeks date;
 begin
       open c1;
       fetch c1 into l_out_weeks;
       close c1;
        
   return (l_out_weeks);
   exception       
       when others then          
            return null;
  end add_weeks;   
  
  function get_user_mode (p_person_id in number) return varchar2 as
 
  l_rev_id number;
  l_sup_id number;
  l_mode   varchar2(30);

cursor c1(p_rev_id in number) is
        select paf1.person_id 
          from per_all_people_f paf1,
               per_all_assignments_f paa,
               per_all_people_f paf2,
               fnd_user fu1,
               fnd_user fu2
         where paf1.current_employee_flag        = 'Y'
           and fu1.employee_id                   = paf1.person_id
           and fu1.end_date                      is null
           and sysdate                           between paf1.effective_start_date and paf1.effective_end_date
           and paa.person_id                     = paf1.person_id
           and sysdate                           between paa.effective_start_date and paa.effective_end_date
           and paf2.current_employee_flag        = 'Y'
           and paf2.person_id                    = paa.supervisor_id
           and sysdate                           between paf2.effective_start_date and paf2.effective_end_date
           and fu2.employee_id                  = paf2.person_id
           and paf2.person_id                   = p_rev_id
           and fu2.end_date                     is null;

cursor c2(p_sup_id in number) is
        select paf1.person_id 
          from per_all_people_f paf1,
               per_all_assignments_f paa,
               per_all_people_f paf2,
               fnd_user fu1,
               fnd_user fu2
         where paf1.current_employee_flag        = 'Y'
           and fu1.employee_id                   = paf1.person_id
           and fu1.end_date                      is null
           and sysdate                           between paf1.effective_start_date and paf1.effective_end_date
           and paa.person_id                     = paf1.person_id
           and sysdate                           between paa.effective_start_date and paa.effective_end_date
           and paf2.current_employee_flag        = 'Y'
           and paf2.person_id                    = paa.supervisor_id
           and sysdate                           between paf2.effective_start_date and paf2.effective_end_date
           and fu2.employee_id                  = paf2.person_id
           and paf2.person_id                   = p_sup_id
           and fu2.end_date                     is null;

    cursor c3 is      
select count(*) cnt
  from wf_local_roles wlr,
       wf_user_roles wur,
       fnd_user fu,
       per_all_people_f pap
 where wlr.display_name                          = 'HLP - LND Officer'
   and wlr.name                                  = wur.role_name 
   and wur.user_name                             = fu.user_name
   and fu.employee_id                            = pap.person_id
   and pap.person_id                             = p_person_id
   and sysdate between pap.effective_start_date and pap.effective_end_date;
   
   r3   c3%rowtype;


  begin

       l_rev_id:=  p_person_id;
       
        open c3;
       fetch c3 into r3;
       close c3;
       
       if r3.cnt > 0 then
        
       l_mode:= 'LND';
       
       else

       l_mode:= 'EMP';


     for i in c1(l_rev_id) 
     loop
       l_mode:= 'SUP';
       l_sup_id:= i.person_id;

       for j in c2(l_sup_id)
       loop
         l_mode:='REV';


       end loop;

     end loop;
     
     end if;
     
  return l_mode;

  exception when others then
  return l_mode;
  end get_user_mode;          
  
  Function GET_SCREEN_ENTRY_VALUE(P_Assignment_ID  Number,P_Element_Name  VARCHAR2,P_Name  VARCHAR2,p_effective_date   Date) Return varchar2 As
   x_Screen_Entry_Value   VarChar2(15);
Begin
    Begin
         Select Screen_Entry_Value Into x_Screen_Entry_Value
         From Pay_Element_Types_F A, Pay_Input_Values_F B,
         Pay_Element_Links_F C,
         Pay_Element_Entries_F D,
         Pay_Element_Entry_Values_F E,
         PER_ALL_ASSIGNMENTS_F F
         Where A.Element_Type_ID = B.Element_Type_ID And
         A.Element_Name = P_Element_Name AND
          a.business_group_id = fnd_profile.value('PER_BUSINESS_GROUP_ID')  
AND
         B.Name = P_Name And
         A.Element_Type_ID = C.Element_Type_ID And
         C.Element_Link_ID = D.Element_Link_ID And
         D.Element_Entry_ID = E.Element_Entry_ID And
         E.Input_Value_ID = B.Input_Value_ID And
         D.Assignment_ID = F.Assignment_ID And
         F.Assignment_ID = P_Assignment_ID And
         p_effective_date Between A.Effective_Start_Date And A.Effective_End_Date And
         p_effective_date Between B.Effective_Start_Date And B.Effective_End_Date And
         p_effective_date Between C.Effective_Start_Date And C.Effective_End_Date And
         p_effective_date Between F.Effective_Start_Date And F.Effective_End_Date And
         p_effective_date between e.EFFECTIVE_START_DATE and e.EFFECTIVE_END_DATE;
         Exception
         When Others Then
              x_Screen_Entry_Value := 0;
         End;
         Return x_Screen_Entry_Value;
     End GET_SCREEN_ENTRY_VALUE; 
 end xxhlp_common_pkg;
/
