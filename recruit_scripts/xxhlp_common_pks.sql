CREATE OR REPLACE package APPS.xxhlp_common_pkg
as
 /*
   *****************************************************************************
   **
   **  file name      : XXHLP_COMMON_PKG
   **  object type    : package spec
   **  description    : create package XXHLP_COMMON_PKG in apps schema to 
                        define commonly used procedure/functions 
   **
   **
   **
   **
   **  change history
   **   
   **  date           name                   version                modification
   **  -------        -------                -------                ------------
   ** 
     *************************************************************************** 
 */
 
--//=============================================================
--// procedure to get request sequence number based on request
--//=============================================================

   l_user_id     number         := nvl(to_number (fnd_profile.value ('USER_ID')),-1);
   l_login_id    number         := nvl(to_number (fnd_profile.value ('LOGIN_ID')),-1);
   l_user_name   varchar2 (50)  := fnd_profile.value ('USERNAME');
 
procedure get_sequence_number(p_req_id in     number
                             ,x_seq       out varchar2);

--//=================================================================
--// procedure to get request sequence number based on request group
--// there should be only one request for such groups
--//================================================================= 
procedure get_sequence_number(
                              p_req_group in  varchar2
                             ,x_seq       out varchar2
                             ) ;                                 
--//=============================================================
--// procedure to get function name of request
--//=============================================================                             

procedure get_call_page(p_request_id    in     number,
                        p_call_page     in out varchar2);
                        
--//=============================================================
--// function to get user name
--//=============================================================                        
                        
function get_user_name(p_person_id in number)
return varchar2;

--//=============================================================
--// fuction to get line manager
--//=============================================================
                        
function get_line_manager(p_person_id in number)
return number; 

--//=============================================================
--// procedure to insert action history table
--//=============================================================

procedure ins_action_history(p_request_id in number);   

--//=============================================================
--// procedure to get address of an employee
--//=============================================================

procedure get_employee_dtls(p_person_id   in       number,
                      x_full_name   out      varchar2,
                      x_sex         out      varchar2,
                      x_dob         out      date,
                      x_nationality out      varchar2,
                      x_house_no    out      varchar2,
                      x_way_no      out      varchar2,
                      x_pb_no       out      varchar2,
                      x_pc          out      varchar2,
                      x_city        out      varchar2,
                      x_country     out      varchar2
                      );
                      
--//=============================================================
--// procedure to get account number of an employee
--//=============================================================                      

procedure get_account_no(
                          p_person_id in     number
                         ,x_account_no    out varchar2
                         );       
                         
--//=============================================================
--// procedure to insert check list transaction rows
--//=============================================================
procedure insert_check_list(p_request_id in number);

--//=============================================================
--// procedure to get appraisal rating of an employee
--//=============================================================  
procedure get_appraisal_rating(
                               p_person_id in number
                              ,x_rating    out varchar2
                               );       
                               
--//=============================================================
--// procedure to get grade of an employee
--//=============================================================  
   procedure get_grade(p_person_id in number,x_grade out varchar2);
                                                                                             
--//=============================================================
--// function to convert days to hh:mm
--//============================================================= 
function convert_days( p_days       in      number)
         return  varchar2 ; 

--//=============================================================
--// procedure to set flow status and request status as cancelled
--//=============================================================         
procedure cancel_request (p_request_id in number); 
--//=============================================================
--// procedure to save the request
--//============================================================= 
procedure save_request (p_request_id in number); 
--//=============================================================
--// function to get the account number of the person
--//============================================================= 
function get_account_no (p_person_id in number) return varchar2;  
--//=============================================================
--// function to get grade by person ID as parameter
--//============================================================= 
function get_grade(p_person_id in number) return varchar2;
--//=============================================================
--// procedure to get the salary details
--//============================================================= 
procedure get_salary_dtls(p_person_id in number
                         ,x_basic_sal out number
                         ,x_gross_sal out number
                         );
--//=============================================================
--// procedure to get age
--//=============================================================                          
procedure get_age (p_person_id in number, x_age out number);        
--//=============================================================
--// procedure to get Nationality of the person
--//=============================================================  
PROCEDURE get_nationality (p_person_id IN NUMBER, x_nationality OUT VARCHAR2);  
--//=============================================================
--// procedure to know the person is Male or Female
--//=============================================================  
PROCEDURE get_sex (p_person_id IN NUMBER, x_sex OUT VARCHAR2);  
--//=============================================================
--// function to get position Name
--//============================================================= 
function get_position_name(p_person_id in number) return varchar2;
--//=============================================================
--// procedure to know months between
--//=============================================================  
procedure get_six_month_valid(p_exp_date VARCHAR2,x_month OUT NUMBER);
--//=============================================================
--// function to check hours
--//============================================================= 
function check_hours (p_hours in number,p_time in number) return number;
--//====================================================================
--// Procedure to insert data into loan calculator tables from t24 tables
--//====================================================================
procedure insert_loan_calc(
                           p_request_id in number
                          ,p_username  in varchar2
                          );
--//====================================================================
--// Function to get person id from username
--//====================================================================         
function get_person_from_username(p_username in varchar2) return number; 

function add_weeks(in_dt in date,num_weeks in integer) return date;
function get_user_mode (p_person_id in number) return varchar2;
Function get_screen_entry_value(p_assignment_id  number,p_element_name  varchar2,
p_name  varchar2,p_effective_date date) return varchar2 ;
end xxhlp_common_pkg;
/
