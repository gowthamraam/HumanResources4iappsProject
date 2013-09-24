create or replace force view xxhlp_applicants_v (row_id,
                                                 app_id,
                                                 app_ref_number,
                                                 cv_received_on,
                                                 position_app_for,
                                                 referred_by,
                                                 referred_by_name,
                                                 position_name,
                                                 region,
                                                 region_desc,
                                                 last_name,
                                                 first_name,
                                                 app_name,
                                                 gender,
                                                 gender_desc,
                                                 date_of_birth,
                                                 age,
                                                 curr_salary,
                                                 exp_salary,
                                                 address1,
                                                 address2,
                                                 address3,
                                                 address4,
                                                 city,
                                                 country,
                                                 country_desc,
                                                 email_id,
                                                 contact_number,
                                                 id_number,
                                                 id_exp_date,
                                                 medical_done_yn,
                                                 rop_conduct_cert_yn,
                                                 app_status,
                                                 app_status_desc,
                                                 comm_sent,
                                                 comm_sent_desc,
                                                 comm_mode,
                                                 comm_mode_desc,
                                                 remarks,
                                                 employee_number,
                                                 person_id,
                                                 person_name,
                                                 employed_on,
                                                 attribute_category,
                                                 attribute1,
                                                 attribute2,
                                                 attribute3,
                                                 attribute4,
                                                 attribute5,
                                                 attribute6,
                                                 attribute7,
                                                 attribute8,
                                                 attribute9,
                                                 attribute10,
                                                 attribute11,
                                                 attribute12,
                                                 attribute13,
                                                 attribute14,
                                                 attribute15,
                                                 created_by,
                                                 creation_date,
                                                 last_updated_by,
                                                 last_update_date,
                                                 last_update_login,
                                                 interviewed,
                                                 tested,
                                                 entered_by,
                                                 entered_by_name
                                                )
as
   select ap.rowid, ap.app_id, ap.app_ref_number, ap.cv_received_on,
          ap.position_app_for,         -- l6.meaning || '-' || l6.description,
                              ap.referred_by, ap.referred_by_name,
          xxhlp_common_pkg.get_position_name (ap.referred_by), ap.region,
          l2.meaning, ap.last_name, ap.first_name,
          ap.last_name || ', ' || ap.first_name, ap.gender, l1.meaning,
          ap.date_of_birth,
          decode (ap.date_of_birth,
                  null, null,
                  round ((sysdate - ap.date_of_birth) / 365)
                 ),
          ap.curr_salary, ap.exp_salary, ap.address1, ap.address2,
          ap.address3, ap.address4, ap.city, ap.country,
          tr.territory_short_name, ap.email_id, ap.contact_number,
          ap.id_number, ap.id_exp_date, ap.medical_done_yn,
          ap.rop_conduct_cert_yn, ap.app_status, l3.meaning, ap.comm_sent,
          l4.meaning, ap.comm_mode, l5.meaning, ap.remarks,
          ap.employee_number, e.person_id, e.full_name, e.start_date,
          ap.attribute_category, ap.attribute1, ap.attribute2, ap.attribute3,
          ap.attribute4, ap.attribute5, ap.attribute6, ap.attribute7,
          ap.attribute8, ap.attribute9, ap.attribute10, ap.attribute11,
          ap.attribute12, ap.attribute13, ap.attribute14, ap.attribute15,
          ap.created_by, ap.creation_date, ap.last_updated_by,
          ap.last_update_date, ap.last_update_login,
          xxhlp_recruitment_pkg.is_interviewed (ap.app_id, 'INT'),
          xxhlp_recruitment_pkg.is_interviewed (ap.app_id, 'TEST'),
          usr.user_name, usr.description
     from xxhlp_applicants ap,
          fnd_lookup_values l1,
          fnd_lookup_values l2,
          fnd_lookup_values l3,
          fnd_lookup_values l4,
          fnd_lookup_values l5,
          -- fnd_lookup_values l6,
          per_all_people_f e,
          fnd_territories_tl tr,
          fnd_user usr
    where l1.lookup_type = 'XXHLP_GENDER'
      and l1.lookup_code = ap.gender
      and l2.lookup_type = 'XXHLP_REGION'
      and l2.lookup_code = ap.region
      and l3.lookup_type = 'XXHLP_REC_APP_STATUS'
      and l3.lookup_code = ap.app_status
      and l4.lookup_type = 'XXHLP_YES_NO'
      and l4.lookup_code = ap.comm_sent
      and l5.lookup_type(+) = 'XXHLP_REC_COMM_MODE'
      and l5.lookup_code(+) = ap.comm_mode
      -- and l6.lookup_type = 'xxhlp_positions'
      -- and l6.lookup_code = ap.position_app_for
      and ap.employee_number = e.employee_number(+)
      and sysdate between e.effective_start_date(+) and e.effective_end_date(+)
      and tr.territory_code(+) = ap.country
      and usr.user_id = ap.created_by;

--

create or replace force view xxhlp_app_educations_v (row_id,
                                                     app_edu_id,
                                                     app_id,
                                                     education_type,
                                                     education_type_desc,
                                                     education,
                                                     education_desc,
                                                     major_subject,
                                                     major_subject_desc,
                                                     college,
                                                     college_desc,
                                                     year_completed,
                                                     grade_perc,
                                                     attribute_category,
                                                     attribute1,
                                                     attribute2,
                                                     attribute3,
                                                     attribute4,
                                                     attribute5,
                                                     attribute6,
                                                     attribute7,
                                                     attribute8,
                                                     attribute9,
                                                     attribute10,
                                                     attribute11,
                                                     attribute12,
                                                     attribute13,
                                                     attribute14,
                                                     attribute15,
                                                     created_by,
                                                     creation_date,
                                                     last_updated_by,
                                                     last_update_date,
                                                     last_update_login
                                                    )
as
   select ae.rowid, ae.app_edu_id, ae.app_id, ae.education_type, l1.meaning,
          ae.education, l2.meaning, ae.major_subject, l3.meaning, ae.college,
          l4.meaning, ae.year_completed, ae.grade_perc, ae.attribute_category,
          ae.attribute1, ae.attribute2, ae.attribute3, ae.attribute4,
          ae.attribute5, ae.attribute6, ae.attribute7, ae.attribute8,
          ae.attribute9, ae.attribute10, ae.attribute11, ae.attribute12,
          ae.attribute13, ae.attribute14, ae.attribute15, ae.created_by,
          ae.creation_date, ae.last_updated_by, ae.last_update_date,
          ae.last_update_login
     from xxhlp_app_educations ae,
          fnd_lookup_values l1,
          fnd_lookup_values l2,
          fnd_lookup_values l3,
          fnd_lookup_values l4
    where l1.lookup_type(+) = 'XXHLP_EDUCATION_TYPE'
      and l1.lookup_code(+) = ae.education_type
      and l2.lookup_type = 'XXHLP_EDUCATION'
      and l2.lookup_code = ae.education
      and l3.lookup_type(+) = 'XXHLP_MAJOR_SUBJECT'
      and l3.lookup_code(+) = ae.major_subject
      and l4.lookup_type(+) = 'XXHLP_COLLEGE'
      and l4.lookup_code(+) = ae.college;

--

create or replace force view xxhlp_app_employments_v (row_id,
                                                      app_emp_id,
                                                      app_id,
                                                      company_name,
                                                      date_from,
                                                      date_upto,
                                                      exp_years,
                                                      position_held,
                                                      country,
                                                      country_desc,
                                                      exp_type,
                                                      exp_type_desc,
                                                      attribute_category,
                                                      attribute1,
                                                      attribute2,
                                                      attribute3,
                                                      attribute4,
                                                      attribute5,
                                                      attribute6,
                                                      attribute7,
                                                      attribute8,
                                                      attribute9,
                                                      attribute10,
                                                      attribute11,
                                                      attribute12,
                                                      attribute13,
                                                      attribute14,
                                                      attribute15,
                                                      created_by,
                                                      creation_date,
                                                      last_updated_by,
                                                      last_update_date,
                                                      last_update_login
                                                     )
as
   select ae.rowid, ae.app_emp_id, ae.app_id, ae.company_name, ae.date_from,
          ae.date_upto, ae.exp_years, ae.position_held, ae.country,
          tr.territory_short_name, ae.exp_type, l1.meaning,
          ae.attribute_category, ae.attribute1, ae.attribute2, ae.attribute3,
          ae.attribute4, ae.attribute5, ae.attribute6, ae.attribute7,
          ae.attribute8, ae.attribute9, ae.attribute10, ae.attribute11,
          ae.attribute12, ae.attribute13, ae.attribute14, ae.attribute15,
          ae.created_by, ae.creation_date, ae.last_updated_by,
          ae.last_update_date, ae.last_update_login
     from xxhlp_app_employments ae,
          fnd_lookup_values l1,
          fnd_territories_tl tr
    where l1.lookup_type(+) = 'XXHLP_EXPERIENCE_TYPE'
      and l1.lookup_code(+) = ae.exp_type
      and tr.territory_code(+) = ae.country;

--

create or replace force view xxhlp_app_interviews_v (row_id,
                                                     app_int_id,
                                                     app_id,
                                                     int_type,
                                                     int_type_desc,
                                                     int_date,
                                                     int_location,
                                                     numerical_marks,
                                                     english_marks,
                                                     essay_marks,
                                                     total_marks,
                                                     result,
                                                     result_desc,
                                                     attribute_category,
                                                     attribute1,
                                                     attribute2,
                                                     attribute3,
                                                     attribute4,
                                                     attribute5,
                                                     attribute6,
                                                     attribute7,
                                                     attribute8,
                                                     attribute9,
                                                     attribute10,
                                                     attribute11,
                                                     attribute12,
                                                     attribute13,
                                                     attribute14,
                                                     attribute15,
                                                     created_by,
                                                     creation_date,
                                                     last_updated_by,
                                                     last_update_date,
                                                     last_update_login
                                                    )
as
   select ai.rowid, ai.app_int_id, ai.app_id, ai.int_type, l1.meaning,
          ai.int_date, ai.int_location, ai.numerical_marks, ai.english_marks,
          ai.essay_marks, ai.total_marks, ai.result, l2.meaning,
          ai.attribute_category, ai.attribute1, ai.attribute2, ai.attribute3,
          ai.attribute4, ai.attribute5, ai.attribute6, ai.attribute7,
          ai.attribute8, ai.attribute9, ai.attribute10, ai.attribute11,
          ai.attribute12, ai.attribute13, ai.attribute14, ai.attribute15,
          ai.created_by, ai.creation_date, ai.last_updated_by,
          ai.last_update_date, ai.last_update_login
     from xxhlp_app_interviews ai, fnd_lookup_values l1, fnd_lookup_values l2
    where l1.lookup_type = 'XXHLP_INT_TYPE'
      and l1.lookup_code = ai.int_type
      and l2.lookup_type(+) = 'XXHLP_INT_RESULT'
      and l2.lookup_code(+) = ai.result;

--

create or replace force view xxhlp_app_int_panels_v (row_id,
                                                     int_panel_id,
                                                     app_int_id,
                                                     app_id,
                                                     person_id,
                                                     person_name,
                                                     remarks,
                                                     attribute_category,
                                                     attribute1,
                                                     attribute2,
                                                     attribute3,
                                                     attribute4,
                                                     attribute5,
                                                     attribute6,
                                                     attribute7,
                                                     attribute8,
                                                     attribute9,
                                                     attribute10,
                                                     attribute11,
                                                     attribute12,
                                                     attribute13,
                                                     attribute14,
                                                     attribute15,
                                                     created_by,
                                                     creation_date,
                                                     last_updated_by,
                                                     last_update_date,
                                                     last_update_login
                                                    )
as
   select ip.rowid, ip.int_panel_id, ip.app_int_id, ip.app_id, ip.person_id,
          ip.person_name, ip.remarks, ip.attribute_category, ip.attribute1,
          ip.attribute2, ip.attribute3, ip.attribute4, ip.attribute5,
          ip.attribute6, ip.attribute7, ip.attribute8, ip.attribute9,
          ip.attribute10, ip.attribute11, ip.attribute12, ip.attribute13,
          ip.attribute14, ip.attribute15, ip.created_by, ip.creation_date,
          ip.last_updated_by, ip.last_update_date, ip.last_update_login
     from xxhlp_app_int_panels ip;

--

create or replace force view xxhlp_applicants_full_v
(app_id, app_ref_number, cv_received_on, position_app_for, referred_by, 
 referred_by_name, position_name, region, region_desc, last_name, 
 first_name, app_name, gender, gender_desc, date_of_birth, 
 age, curr_salary, exp_salary, address1, address2, 
 address3, address4, city, country, country_desc, 
 email_id, contact_number, id_number, id_exp_date, medical_done_yn, 
 rop_conduct_cert_yn, app_status, app_status_desc, comm_sent, comm_sent_desc, 
 comm_mode, comm_mode_desc, remarks, employee_number, person_id, 
 person_name, employed_on, interviewed, tested, creation_date, 
 edu_id, education_type, education, major_subject, college, 
 year_completed, grade_perc, full_education, emp_id, company_name, 
 date_from, date_upto, exp_years, position_held, exp_type_desc, 
 full_experience, total_exp_years, int_id, int_type, int_date, 
 int_marks, int_result, int_by, test_id, test_type, 
 test_date, test_marks, test_result)
as 
select a.app_id,
a.app_ref_number,
a.cv_received_on,
a.position_app_for,
a.referred_by,
a.referred_by_name,
a.position_name,
a.region,
a.region_desc,
a.last_name,
a.first_name,
a.app_name,
a.gender,
a.gender_desc,
a.date_of_birth,
a.age,
a.curr_salary,
a.exp_salary,
a.address1,
a.address2,
a.address3,
a.address4,
a.city,
a.country,
a.country_desc,
a.email_id,
a.contact_number,
a.id_number,
a.id_exp_date,
a.medical_done_yn,
a.rop_conduct_cert_yn,
a.app_status,
a.app_status_desc,
a.comm_sent,
a.comm_sent_desc,
a.comm_mode,
a.comm_mode_desc,
a.remarks,
a.employee_number,
a.person_id,
a.person_name,
a.employed_on,
a.interviewed,
a.tested,
a.creation_date,
       ed.app_edu_id, ed.education_type_desc, ed.education_desc, ed.major_subject_desc, ed.college_desc, ed.year_completed, ed.grade_perc, ed.education,
       ex.app_emp_id, ex.company_name,ex.date_from, ex.date_upto, ex.exp_years, ex.position_held, ex.exp_type_desc, ex.full_exp, ex.total_exp_years,
       i.app_int_id, i.int_type_desc, i.int_date, i.total_marks, i.result_desc, i.interviewed_by,
       t.app_int_id, t.int_type_desc, t.int_date, t.total_marks, t.result_desc
from   xxhlp_applicants_v a, 
       -- *** education records *** ===
        (select t1.app_id, t1.app_edu_id, t1.education_type_desc, t1.education_desc, t1.major_subject_desc, t1.college_desc, t1.year_completed, t1.grade_perc, t1.education
         from   (select app_id, app_edu_id, education_type_desc, education_desc, major_subject_desc, college_desc, year_completed, grade_perc,
                       listagg ( education_desc ||'('||major_subject_desc||')('||year_completed||')', ', ')    
                               within group (order by year_completed desc)
                               over (partition by app_id)  education
                from   xxhlp_app_educations_v ) t1
                left outer join xxhlp_app_educations_v t2
                     on   t1.app_id = t2.app_id 
                     and  ( (t1.year_completed < t2.year_completed) or 
                            (t1.year_completed = t2.year_completed and t1.app_edu_id < t2.app_edu_id)
                          )
         where t2.app_id is null ) ed,     
        -- *** employment records *** ===
        (select t3.app_id, t3.app_emp_id, t3.company_name,t3.date_from, t3.date_upto, t3.exp_years, t3.position_held, t3.exp_type_desc, t3.full_exp, t3.total_exp_years
         from   (select app_id, app_emp_id, company_name,date_from, date_upto, exp_years, position_held, exp_type_desc,
                       listagg ( company_name ||'('||exp_type_desc||')', ', ')    
                               within group (order by nvl(date_upto,sysdate) desc)
                               over (partition by app_id)  full_exp,
                       sum(exp_years) over (partition by app_id) total_exp_years
                from   xxhlp_app_employments_v ) t3
         left outer join xxhlp_app_employments_v t4
             on   t3.app_id = t4.app_id
             and  ( (nvl(t3.date_upto,sysdate) < nvl(t4.date_upto,sysdate)) or 
                    (nvl(t3.date_upto,sysdate) = nvl(t4.date_upto,sysdate) and t3.app_emp_id < t4.app_emp_id)
                  )
         where t4.app_id is null ) ex,
        -- *** interview records *** ===
        (select t5.app_id, t5.app_int_id, t5.int_type_desc, t5.int_date, t5.total_marks, t5.result_desc, t5.interviewed_by
         from   (select i.app_id, i.app_int_id, i.int_type_desc, i.int_date, i.total_marks, i.result_desc,
                       listagg(ip.person_name, ', ') within group (order by ip.person_name) interviewed_by
                from   xxhlp_app_interviews_v i, xxhlp_app_int_panels_v ip
                where  i.app_int_id = ip.app_int_id (+)
                and    i.int_type   = 'INT'
                group by i.app_id, i.app_int_id, i.int_type_desc, i.int_date, i.total_marks, i.result_desc ) t5
         left outer join xxhlp_app_interviews_v t6
             on   t5.app_id = t6.app_id
             and  t6.int_type = 'INT' 
             and  ( (nvl(t5.int_date,sysdate) < nvl(t6.int_date,sysdate)) or 
                    (nvl(t5.int_date,sysdate) = nvl(t6.int_date,sysdate) and t5.app_int_id < t6.app_int_id)
                  )
         where t6.app_id is null) i,
        -- *** test records *** ===
        (select t7.app_id, t7.app_int_id, t7.int_type_desc, t7.int_date, t7.total_marks, t7.result_desc
         from   (select app_id, app_int_id, int_type_desc, int_date, total_marks, result_desc
                from   xxhlp_app_interviews_v
                where  int_type   = 'TEST' ) t7
         left outer join xxhlp_app_interviews_v t8
             on   t7.app_id = t8.app_id
             and  t8.int_type = 'TEST' 
             and  ( (nvl(t7.int_date,sysdate) < nvl(t8.int_date,sysdate)) or 
                    (nvl(t7.int_date,sysdate) = nvl(t8.int_date,sysdate) and t7.app_int_id < t8.app_int_id)
                  )
         where t8.app_id is null ) t       
where  a.app_id = ed.app_id (+)
and    a.app_id = ex.app_id (+)
and    a.app_id = i.app_id (+)
and    a.app_id = t.app_id (+);

