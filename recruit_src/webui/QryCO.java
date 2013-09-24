/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxhlp.oracle.apps.xxhlp.recruit.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAMessageComponentLayoutBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;

import xxhlp.oracle.apps.xxhlp.recruit.poplist.server.eduLVORowImpl;
import xxhlp.oracle.apps.xxhlp.recruit.poplist.server.edusubLVORowImpl;
import xxhlp.oracle.apps.xxhlp.recruit.poplist.server.exptypeLVORowImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.applAMImpl;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import oracle.jbo.AttributeDef;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

import oracle.apps.fnd.framework.OAException;

import oracle.apps.fnd.framework.webui.beans.form.OADefaultShuttleBean;

import xxhlp.oracle.apps.xxhlp.CommonClass;

/**
 * Controller for ...
 */
public class QryCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    int eduCnt = 0 ;
    int subCnt = 0 ;
    int expCnt = 0 ;
    String eduDesc ;
    String edu ;
    String subDesc ;
    String sub ;
    String exptypeDesc ;
    String exptype ;
    String role = "" ;
    
  
  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
    applAMImpl aAM    = (applAMImpl)pageContext.getApplicationModule(webBean);
    //OAApplicationModule aAM =  (OAApplicationModule)pageContext.getApplicationModule(webBean);
     OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");
     
     /**
      * Set Enable / Disable Pages based on Role
      * ADMIN - can create or modify or view applicant details
      * OTHERS - can only view
      */
     role = pageContext.getParameter("Role");
     // role = "ADMIN" ;
     if (role == null | "".equals(role))
        role = "OTHERS";
     System.out.println("PR QryPG - role " + role); 
     aAM.setEnableDisable(tVO, role, "N") ;
     // -------------------
    
    //System.out.println("PR QryPG - before initQuery"); 
    //aAM.invokeMethod("initQuery") ;
    //System.out.println("PR QryPG - after initQuery"); 

    /**     
    -- Following Coding has been commented
    -- as the logic has been handled using Shuttle Region
    -- since the lookup data is huge enough to show as checkbox
    -- Navu - 10-Sep-2013 
    OAPageLayoutBean page = pageContext.getPageLayoutBean(); 
    //Adding Check boxes Dynamically for Courses
    OAViewObject eVO=(OAViewObject)aAM.findViewObject("eduLVO");
      
    eVO.executeQuery();
    eVO.reset();

    OAMessageComponentLayoutBean eRN=(OAMessageComponentLayoutBean)page.findIndexedChildRecursive("QEduRN");
    eduCnt = 0;
    while(eVO.hasNext()) 
    {
        eduLVORowImpl currow = (eduLVORowImpl)eVO.next();    
        eduCnt = eduCnt + 1;
        eduDesc = "EduDesc" + eduCnt;
        edu     = "Edu" + eduCnt;
        OAMessageCheckBoxBean oamcb = (OAMessageCheckBoxBean)createWebBean(pageContext, MESSAGE_CHECKBOX_BEAN, null, eduDesc);
        eRN.addIndexedChild(oamcb);
        OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(eduDesc);
        oamcb1.setPrompt(currow.getEduDesc());
        OAFormValueBean oafv = (OAFormValueBean)createWebBean(pageContext,OAWebBeanConstants.HIDDEN_BEAN, null, edu);
        page.addIndexedChild(oafv);
        OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(edu);
        oafv1.setValue(pageContext,currow.getEdu());
    }

      //Adding Check boxes Dynamically for Subject
      OAViewObject sVO=(OAViewObject)aAM.findViewObject("edusubLVO");
      sVO.executeQuery();
      sVO.reset();
      OAMessageComponentLayoutBean sRN=(OAMessageComponentLayoutBean)page.findIndexedChildRecursive("QSubjectRN");
      subCnt = 0 ;
      while(sVO.hasNext()) 
      {
          edusubLVORowImpl currow = (edusubLVORowImpl)sVO.next();    
          subCnt = subCnt + 1;
          subDesc = "SubDesc" + subCnt;
          sub     = "Sub" + subCnt;
          OAMessageCheckBoxBean oamcb = (OAMessageCheckBoxBean)createWebBean(pageContext, MESSAGE_CHECKBOX_BEAN, null, subDesc);
          sRN.addIndexedChild(oamcb);
          OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(subDesc);
          oamcb1.setPrompt(currow.getEdusubDesc());
          OAFormValueBean oafv = (OAFormValueBean)createWebBean(pageContext,OAWebBeanConstants.HIDDEN_BEAN, null, sub);
          page.addIndexedChild(oafv);
          OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(sub);
          oafv1.setValue(pageContext,currow.getEdusub());
      }
      //Adding Check boxes Dynamically for Experience Type
      OAViewObject etVO=(OAViewObject)aAM.findViewObject("exptypeLVO");
      etVO.executeQuery();
      etVO.reset();
      OAMessageComponentLayoutBean etRN=(OAMessageComponentLayoutBean)page.findIndexedChildRecursive("QExpTypeRN");
      expCnt = 0 ;
      while(etVO.hasNext()) 
      {
          exptypeLVORowImpl currow = (exptypeLVORowImpl)etVO.next();    
          expCnt = expCnt + 1 ;
          exptypeDesc = "exptypeDesc" + expCnt;
          exptype     = "exptype" + expCnt;
          OAMessageCheckBoxBean oamcb = (OAMessageCheckBoxBean)createWebBean(pageContext, MESSAGE_CHECKBOX_BEAN, null, exptypeDesc);
          etRN.addIndexedChild(oamcb);
          OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(exptypeDesc);
          oamcb1.setPrompt(currow.getExptypeDesc());
          OAFormValueBean oafv = (OAFormValueBean)createWebBean(pageContext,OAWebBeanConstants.HIDDEN_BEAN, null, exptype);
          page.addIndexedChild(oafv);
          OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(exptype);
          oafv1.setValue(pageContext,currow.getExptype());
      }
    */

  }  // end of processRequest() 

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  String EventName = null ;
  
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);
    applAMImpl aAM    = (applAMImpl)pageContext.getApplicationModule(webBean);
    CommonClass cc = new CommonClass() ;
    
      System.out.println("PFR QryPG - at Begining"); 
      
//      OAApplicationModule aAM =  (OAApplicationModule)pageContext.getApplicationModule(webBean);

      if(pageContext.getParameter(EVENT_PARAM)!=null){
         EventName =  pageContext.getParameter(EVENT_PARAM).toString();
      }

      System.out.println("PFR QryPG - Event Name : " + EventName); 
      // To Create a New Applicant
      if(EventName.equalsIgnoreCase("CreateBut"))
      {
         System.out.println("PFR QryPG - CreateBut "); 
         System.out.println("PFR QryPG - CreateBut - role " + role); 
         pageContext.setForwardURL("OA.jsp?page=/xxhlp/oracle/apps/xxhlp/recruit/webui/RecruitPG&Role=" + role, 
                                    null, 
                                    OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                    null,
                                    null,
                                    false, 
                                    OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                    OAWebBeanConstants.IGNORE_MESSAGES);
      }

      // To Modify the Applicant Details
      if(EventName.equalsIgnoreCase("UpdateBut"))
      {
          String appid = pageContext.getParameter("QryAppId");    
          HashMap hmap = new HashMap();
          hmap.put("QryAppId", appid); 
   
          System.out.println("PFR QryPG - UpdateBut / appid : " + appid + "    Role:  " + role ); 

          pageContext.setForwardURL("OA.jsp?page=/xxhlp/oracle/apps/xxhlp/recruit/webui/RecruitPG&Role=" + role, 
                                     null, 
                                     OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                     null,
                                     hmap,
                                     true, 
                                     OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                     OAWebBeanConstants.IGNORE_MESSAGES);
      }

      // To Delete the Applicant
      if(EventName.equalsIgnoreCase("DeleteBut"))
      {
          String appid = pageContext.getParameter("QryAppId");    
          Serializable[] params = { "APPLICANT", appid };   
          System.out.println("PFR QryPG - DeleteBut / appid : " + appid); 
          aAM.invokeMethod("deleteApplicant", params) ;
      }
      
      
      // To Query the Applicants based on Search
      if(EventName.equalsIgnoreCase("QButGo"))
      {
          System.out.println("PFR Go Button - QButGo "); 
          
          String prefnum = pageContext.getParameter("qrefnum");
          String pidnum = pageContext.getParameter("qidnum");
          String pcontactnum = pageContext.getParameter("qcontactnum");
          String pappname = pageContext.getParameter("qappname");
          String pgender = pageContext.getParameter("qgender");
          String pregion = pageContext.getParameter("qregion");
          String pcrfrom = pageContext.getParameter("qcrfrom");
          String pcrto = pageContext.getParameter("qcrto");
          String prcvdfrom = pageContext.getParameter("qrcvdfrom");
          String prcvdto = pageContext.getParameter("qrcvdto");
          String pagefrom = pageContext.getParameter("qagefrom");
          String pageto = pageContext.getParameter("qageto");
          String pstatus = pageContext.getParameter("qstatus");
          String pemployed = pageContext.getParameter("qemployed");
          String pempfrom = pageContext.getParameter("qempfrom");
          String pempto = pageContext.getParameter("qempto");
          String pedutype = pageContext.getParameter("qedutype");
          String pedu = pageContext.getParameter("qedu");
          String psubject = pageContext.getParameter("qsubject");
          String pyearfm = pageContext.getParameter("qyearfm");
          String pyearto = pageContext.getParameter("qyearto");
          String pexptype = pageContext.getParameter("qexptype");
          String pposition = pageContext.getParameter("qposition");
          String pcompany = pageContext.getParameter("qcompany");
          String pexpyearfm = pageContext.getParameter("qexpyearfm");
          String pexpyearto = pageContext.getParameter("qexpyearto");
          String pintyn = pageContext.getParameter("qintyn");
          String pintfm = pageContext.getParameter("qintfm");
          String pintto = pageContext.getParameter("qintto");
          String presult = pageContext.getParameter("qresult");
          String pmarksfm = pageContext.getParameter("qmarksfm");
          String pmarksto = pageContext.getParameter("qmarksto");
          String pintby = pageContext.getParameter("qintby");
          String pmedyn = pageContext.getParameter("qmedyn"); 
          String propyn = pageContext.getParameter("qropyn"); 
          String pposappl = pageContext.getParameter("qposappl");

          System.out.println("eduCnt " + eduCnt + "   SubCnt "+subCnt + "   expCnt "+ expCnt);

          // Combine all selected Educations
          String eduSelected = null;
          OADefaultShuttleBean shuttle1 = (OADefaultShuttleBean)webBean.findChildRecursive("QEduRN");     
          String[] eductions =  shuttle1.getTrailingListOptionValues(pageContext, shuttle1);  
          if (eductions != null)  { 
             for (int i = 0; i < eductions.length; i++)  {  
                 eduSelected = eduSelected + ",'" + eductions[i] + "'";
             } 
          }
          System.out.println("eduSelected :" + eduSelected);          
          /**
          -- Following Coding has been commented
          -- as the logic has been handled using Shuttle Region
          -- since the lookup data is huge enough to show as checkbox
          -- Navu - 10-Sep-2013 
             for(int j=0; j<eduCnt; j++) 
           {
               eduDesc = "EduDesc" + j;
               edu     = "Edu" + j;
               OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(eduDesc);
               OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(edu);
               if(oamcb1!=null && "Y".equals(oamcb1.getValue(pageContext))) 
              {
                eduSelected = eduSelected + ",'" + oafv1.getValue(pageContext) + "'"; 
               }
           }
          */

          // Combine all selected Subjects
          String subSelected = null;
          OADefaultShuttleBean shuttle2 = (OADefaultShuttleBean)webBean.findChildRecursive("QSubjectRN");     
          String[] subjects =  shuttle2.getTrailingListOptionValues(pageContext, shuttle2);  
          if (subjects != null) { 
                for (int i = 0; i < subjects.length; i++) {  
                    subSelected = subSelected + ",'" + subjects[i] + "'";
                } 
          }
          System.out.println("subSelected :" + subSelected);
          /**
           -- Following Coding has been commented
           -- as the logic has been handled using Shuttle Region
           -- since the lookup data is huge enough to show as checkbox
           -- Navu - 10-Sep-2013 
          for(int j=0; j<subCnt; j++) 
          {
              subDesc = "SubDesc" + j;
              sub     = "Sub" + j;
              OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(subDesc);
              OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(sub);
              if(oamcb1!=null && "Y".equals(oamcb1.getValue(pageContext))) 
              {
                subSelected = subSelected + ",'" + oafv1.getValue(pageContext) + "'"; 
              }
          }
          */

          // Combine all selected Experience Types
          String expSelected = null;
          OADefaultShuttleBean shuttle3 = (OADefaultShuttleBean)webBean.findChildRecursive("QExpTypeRN");     
          String[] exptypes =  shuttle3.getTrailingListOptionValues(pageContext, shuttle3);  
          if (exptypes != null) { 
                for (int i = 0; i < exptypes.length; i++) {  
                    expSelected = expSelected + ",'" + exptypes[i] + "'";
                } 
          }
          System.out.println("expSelected :" + expSelected);
           /**
            -- Following Coding has been commented
            -- as the logic has been handled using Shuttle Region
            -- since the lookup data is huge enough to show as checkbox
            -- Navu - 10-Sep-2013 

          for(int j=0; j<expCnt; j++) 
          {
              exptypeDesc = "exptypeDesc" + j;
              exptype     = "exptype" + j;
              OAMessageCheckBoxBean oamcb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive(exptypeDesc);
              OAFormValueBean oafv1 = (OAFormValueBean)webBean.findChildRecursive(exptype);
              if(oamcb1!=null && "Y".equals(oamcb1.getValue(pageContext))) 
              {
                expSelected = expSelected + ",'" + oafv1.getValue(pageContext) + "'"; 
              }
          }
          */

          Serializable[] params = {prefnum,	pidnum,	pcontactnum, pappname,	pgender,	
                                    pregion,	pcrfrom,	pcrto,	prcvdfrom, prcvdto, pagefrom, pageto, pstatus,
                                    pmedyn, propyn, pposappl,
                                    pemployed,	pempfrom,	pempto,	pedutype,	
                                    eduSelected,	subSelected,	pyearfm,	pyearto,	
                                    expSelected,	pposition,	pcompany,	
                                    pexpyearfm,	pexpyearto,	pintyn,	pintfm,	pintto,	
                                    presult,	pmarksfm,	pmarksto,	pintby };   
          aAM.invokeMethod("queryApplicants", params) ;
      
      }          

      // To Clear the Query Criteria
      if(EventName.equalsIgnoreCase("QButClear"))
      {
          System.out.println("PFR Clear Button - QButClear "); 
          pageContext.forwardImmediatelyToCurrentPage(null, false, null);
         
      }

      // To Export Data
      if(EventName.equalsIgnoreCase("ExportBut"))
      {
          System.out.println("PFR ExportBut "); 
 
          String attrList[] = {"AppRefNumber", "CvReceivedOn", "CreationDate" , "ReferredByName", "RegionDesc","AppName",  "GenderDesc", "DateOfBirth", "Age", "City",  "CountryDesc", 
                               "EmailId", "ContactNumber", "IdNumber", "MedicalDoneYn", "RopConductCertYn",  "AppStatusDesc",  "Remarks", "EmployeeNumber", "EmployedOn", 
                               "EducationType", "Education", "MajorSubject", "College", "YearCompleted","GradePerc","FullEducation", 
                               "CompanyName",    "DateFrom",       "DateUpto", "ExpYears", "PositionHeld", "ExpTypeDesc", "FullExperience", "TotalExpYears", 
                               "Tested", "TestDate","TestMarks",    "TestResult",
                               "Interviewed","IntDate","IntMarks", "IntResult","IntBy"} ;
          
          String attrHdr[]  = {"Reference Number","Received On", "Created On","Referred By","Region","Name","Gender","Date Of Birth","Age","City","Country",
                               "Email ID","Contact Number","ID Number","Medical Done?","Rop Conduct Cert?","Status","Remarks","Employee Number","Employed On",
                               "Education Type","Education","Major Subject","College","Year Completed","Grade","Full Education",
                               "Company Name","Worked From","Worked Upto","Experience","Last Position Held","Experience Type", "Full Experience", "Total Experience",
                               "Tested","Test Date","Test Marks","Test Result",
                               "Interviewed","Interview Date","Interview Marks","Interview Result","Interviewd By" } ;

          cc.export2Excel(pageContext, "applFullVO", "excelFile", "MAX", attrList, attrHdr) ; 
      }

  }  // end of processFormRequest() 

}
