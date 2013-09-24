create table xxhlp_applicants
(
  app_id               number                   not null,
  app_ref_number       varchar2(60)        not null,
  cv_received_on       date,
  referred_by          varchar2(240),
  region               varchar2(30)        not null,
  last_name            varchar2(60)        not null,
  first_name           varchar2(60),
  gender               varchar2(30)        not null,
  date_of_birth        date,
  address1             varchar2(240),
  address2             varchar2(240),
  address3             varchar2(240),
  address4             varchar2(240),
  city                 varchar2(240),
  country              varchar2(240),
  email_id             varchar2(240),
  contact_number       varchar2(240)       not null,
  id_number            varchar2(240)       not null,
  app_status           varchar2(30)        not null,
  comm_sent            varchar2(30),
  comm_mode            varchar2(30),
  remarks              varchar2(4000),
  attribute_category   varchar2(240),
  attribute1           varchar2(240),
  attribute2           varchar2(240),
  attribute3           varchar2(240),
  attribute4           varchar2(240),
  attribute5           varchar2(240),
  attribute6           varchar2(240),
  attribute7           varchar2(240),
  attribute8           varchar2(240),
  attribute9           varchar2(240),
  attribute10          varchar2(240),
  attribute11          varchar2(240),
  attribute12          varchar2(240),
  attribute13          varchar2(240),
  attribute14          varchar2(240),
  attribute15          varchar2(240),
  created_by           number                   not null,
  creation_date        date                     not null,
  last_updated_by      number                   not null,
  last_update_date     date                     not null,
  last_update_login    number,
  id_exp_date          date,
  position_app_for     varchar2(240),
  curr_salary          varchar2(30),
  exp_salary           varchar2(30),
  medical_done_yn      varchar2(30),
  rop_conduct_cert_yn  varchar2(30),
  employee_number      varchar2(30),
  referred_by_name     varchar2(240)
) ;

create unique index xxhlp_applicants_pk on xxhlp_applicants
(app_id) ;

create unique index xxhlp_applicants_uk01 on xxhlp_applicants
(app_ref_number) ;

create index xxhlp_applicants_idx01 on xxhlp_applicants
(creation_date, cv_received_on) ;

create index xxhlp_applicants_idx02 on xxhlp_applicants
(id_number) ;

create index xxhlp_applicants_idx03 on xxhlp_applicants
(contact_number, email_id) ;

create index xxhlp_applicants_idx04 on xxhlp_applicants
(gender) ;

create index xxhlp_applicants_idx05 on xxhlp_applicants
(region) ;

create index xxhlp_applicants_idx06 on xxhlp_applicants
(app_status) ;

alter table xxhlp_applicants add (
  constraint xxhlp_applicants_pk primary key  (app_id)
    using index  ,
  constraint xxhlp_applicants_uk01 unique (app_ref_number)
    using index  );

--

create table xxhlp_app_educations
(
  app_edu_id          number                    not null,
  app_id              number                    not null,
  education_type      varchar2(30)         not null,
  education           varchar2(30)         not null,
  major_subject       varchar2(30)         not null,
  college             varchar2(30),
  year_completed      varchar2(4)          not null,
  grade_perc          varchar2(30),
  attribute_category  varchar2(240),
  attribute1          varchar2(240),
  attribute2          varchar2(240),
  attribute3          varchar2(240),
  attribute4          varchar2(240),
  attribute5          varchar2(240),
  attribute6          varchar2(240),
  attribute7          varchar2(240),
  attribute8          varchar2(240),
  attribute9          varchar2(240),
  attribute10         varchar2(240),
  attribute11         varchar2(240),
  attribute12         varchar2(240),
  attribute13         varchar2(240),
  attribute14         varchar2(240),
  attribute15         varchar2(240),
  created_by          number                    not null,
  creation_date       date                      not null,
  last_updated_by     number                    not null,
  last_update_date    date                      not null,
  last_update_login   number
) ;

create index xxhlp_app_educations_idx01 on xxhlp_app_educations
(app_id) ;

create index xxhlp_app_educations_idx02 on xxhlp_app_educations
(education_type) ;

create index xxhlp_app_educations_idx03 on xxhlp_app_educations
(education, major_subject) ;

create index xxhlp_app_educations_idx04 on xxhlp_app_educations
(college) ;

create index xxhlp_app_educations_idx05 on xxhlp_app_educations
(year_completed) ;

create unique index xxhlp_app_educations_pk on xxhlp_app_educations
(app_edu_id) ;

alter table xxhlp_app_educations add (
  constraint xxhlp_app_educations_pk  primary key (app_edu_id)
    using index  );

alter table xxhlp_app_educations add (
  constraint xxhlp_app_educations_fk01   foreign key (app_id) 
     references xxhlp_applicants (app_id));

--

create table xxhlp_app_employments
(
  app_emp_id          number                    not null,
  app_id              number                    not null,
  company_name        varchar2(240),
  date_from           date,
  date_upto           date,
  position_held       varchar2(60),
  country             varchar2(30),
  exp_type            varchar2(30),
  attribute_category  varchar2(240),
  attribute1          varchar2(240),
  attribute2          varchar2(240),
  attribute3          varchar2(240),
  attribute4          varchar2(240),
  attribute5          varchar2(240),
  attribute6          varchar2(240),
  attribute7          varchar2(240),
  attribute8          varchar2(240),
  attribute9          varchar2(240),
  attribute10         varchar2(240),
  attribute11         varchar2(240),
  attribute12         varchar2(240),
  attribute13         varchar2(240),
  attribute14         varchar2(240),
  attribute15         varchar2(240),
  created_by          number                    not null,
  creation_date       date                      not null,
  last_updated_by     number                    not null,
  last_update_date    date                      not null,
  last_update_login   number,
  exp_years           number
) ;

create unique index xxhlp_app_employments_pk on xxhlp_app_employments
(app_emp_id) ;

create index xxhlp_app_employments_idx01 on xxhlp_app_employments
(app_id) ;

create index xxhlp_app_employments_idx02 on xxhlp_app_employments
(exp_type) ;

alter table xxhlp_app_employments add (
  constraint xxhlp_app_employments_pk primary key (app_emp_id)
    using index );

alter table xxhlp_app_employments add (
  constraint xxhlp_app_employments_fk01  foreign key (app_id) 
 references xxhlp_applicants (app_id));

--

create table xxhlp_app_interviews
(
  app_int_id          number                    not null,
  app_id              number                    not null,
  int_type            varchar2(30)         not null,
  int_date            date,
  int_location        varchar2(60),
  numerical_marks     number,
  english_marks       number,
  essay_marks         number,
  total_marks         number,
  result              varchar2(30)         not null,
  attribute_category  varchar2(240),
  attribute1          varchar2(240),
  attribute2          varchar2(240),
  attribute3          varchar2(240),
  attribute4          varchar2(240),
  attribute5          varchar2(240),
  attribute6          varchar2(240),
  attribute7          varchar2(240),
  attribute8          varchar2(240),
  attribute9          varchar2(240),
  attribute10         varchar2(240),
  attribute11         varchar2(240),
  attribute12         varchar2(240),
  attribute13         varchar2(240),
  attribute14         varchar2(240),
  attribute15         varchar2(240),
  created_by          number                    not null,
  creation_date       date                      not null,
  last_updated_by     number                    not null,
  last_update_date    date                      not null,
  last_update_login   number
) ;

create unique index xxhlp_app_interviews_pk on xxhlp_app_interviews
(app_int_id) ;

create index xxhlp_app_interviews_idx01 on xxhlp_app_interviews
(app_id) ;

create index xxhlp_app_interviews_idx02 on xxhlp_app_interviews
(int_date) ;

alter table xxhlp_app_interviews add (
  constraint xxhlp_app_interviews_pk  primary key  (app_int_id)
    using index  );

alter table xxhlp_app_interviews add (
  constraint xxhlp_app_interviews_fk01  foreign key (app_id) 
 references xxhlp_applicants (app_id));

--

create table xxhlp_app_int_panels
(
  int_panel_id        number                    not null,
  app_int_id          number                    not null,
  app_id              number                    not null,
  person_id           number,
  person_name         varchar2(240)        not null,
  remarks             varchar2(4000),
  attribute_category  varchar2(240),
  attribute1          varchar2(240),
  attribute2          varchar2(240),
  attribute3          varchar2(240),
  attribute4          varchar2(240),
  attribute5          varchar2(240),
  attribute6          varchar2(240),
  attribute7          varchar2(240),
  attribute8          varchar2(240),
  attribute9          varchar2(240),
  attribute10         varchar2(240),
  attribute11         varchar2(240),
  attribute12         varchar2(240),
  attribute13         varchar2(240),
  attribute14         varchar2(240),
  attribute15         varchar2(240),
  created_by          number                    not null,
  creation_date       date                      not null,
  last_updated_by     number                    not null,
  last_update_date    date                      not null,
  last_update_login   number
) ;

create unique index xxhlp_app_int_panels_pk on xxhlp_app_int_panels
(int_panel_id) ;

create index xxhlp_app_int_panels_idx01 on xxhlp_app_int_panels
(app_id) ;

create index xxhlp_app_int_panels_idx02 on xxhlp_app_int_panels
(app_int_id) ;

alter table xxhlp_app_int_panels add (
  constraint xxhlp_app_int_panels_pk  primary key (int_panel_id)
    using index );

alter table xxhlp_app_int_panels add (
  constraint xxhlp_app_int_panels_fk01  foreign key (app_id) 
        references xxhlp_applicants (app_id),
  constraint xxhlp_app_int_panels_fk02  foreign key (app_int_id) 
        references xxhlp_app_interviews (app_int_id));

--

create sequence xxhlp_app_id_s start with 1 nocycle  nocache ;
create sequence xxhlp_app_edu_id_s start with 1 nocycle  nocache ;
create sequence xxhlp_app_emp_id_s start with 1 nocycle  nocache ;
create sequence xxhlp_app_int_id_s start with 1 nocycle  nocache ;
create sequence xxhlp_int_panel_id_s start with 1 nocycle  nocache ;

--

grant all on xxhlp_applicants to apps;
grant all on xxhlp_app_educations to apps;
grant all on xxhlp_app_employments to apps;
grant all on xxhlp_app_interviews to apps;
grant all on xxhlp_app_int_panels to apps;

grant all on xxhlp_app_id_s to apps;
grant all on xxhlp_app_edu_id_s to apps;
grant all on xxhlp_app_emp_id_s to apps;
grant all on xxhlp_app_int_id_s to apps;
grant all on xxhlp_int_panel_id_s to apps;
