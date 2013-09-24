CREATE OR REPLACE package APPS.xxhlp_recruitment_pkg is
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
--// procedure to Create Applicant as Employee in HR system
--//=============================================================
procedure create_employee(p_app_id       in     number,
                          p_err_code     in out varchar2,
                          p_err_msg      in out varchar2);


--//=============================================================
--// procedure to Delete Applicant
--//=============================================================
procedure delete_applicant(p_del_from     in     varchar2,
                           p_pk_id        in     number,
                           p_err_code     in out varchar2,
                           p_err_msg      in out varchar2 )  ;

--//=============================================================
--// procedure to Check for Duplicate ID Number 
--//=============================================================
procedure chk_for_dup_id_num(p_id_number    in     varchar2,
                             p_app_id       in     number,
                             p_err_code     in out varchar2,
                             p_err_msg      in out varchar2 ) ;

--//=============================================================
--// procedure to Check for Duplicate  Employee Number 
--//=============================================================
procedure chk_for_dup_emp_num(p_emp_number   in     varchar2,
                              p_app_id       in     number,
                              p_err_code     in out varchar2,
                              p_err_msg      in out varchar2 ) ;                             

--//=============================================================
--// function to check whether the applicant is interviewd or not 
--//=============================================================
function is_interviewed(p_app_id   in number, 
                        p_int_type in varchar2) 
         return varchar2 ;

end xxhlp_recruitment_pkg;
/
