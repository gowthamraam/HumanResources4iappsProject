/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxhlp.oracle.apps.xxhlp.recruit.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import java.sql.SQLException;
import java.sql.Types;

import oracle.jbo.Row;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
//import oracle.apps.fnd.framework.server.common.OAApplicationModule;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.webui.OAUrl;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.nav.OALinkBean;

import oracle.cabo.ui.RenderingContext;

import oracle.jdbc.OracleCallableStatement;

import xxhlp.oracle.apps.xxhlp.CommonClass;
import xxhlp.oracle.apps.xxhlp.recruit.server.applAMImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.applVOImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.eduVOImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.expVOImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.interVOImpl;


/**
 * Controller for ...
 */
public class RecruitCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

   String role     = "" ;
   String readonly = "" ;
  
  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);

    // OAApplicationModule aAM =  (OAApplicationModule)pageContext.getApplicationModule(webBean);
    applAMImpl aAM    = (applAMImpl)pageContext.getApplicationModule(webBean);
    applVOImpl applVO = (applVOImpl) aAM.getapplVO(); //findViewObject("applVO") ;
    OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");
     
    System.out.println("PR RecruitPG "); 
    
//    /**
//     * Set Enable / Disable Pages based on Role
//     * ADMIN - can create or modify or view applicant details
//     * OTHERS - can only view
//     */
//    role = pageContext.getParameter("Role");
//    if (role == null | "".equals(role))
//       role = "OTHERS";
//    System.out.println("PR recruitPG - role " + role);
//    aAM.setEnableDisable(tVO, role) ;
    
    String appid = pageContext.getParameter("QryAppId"); 
    System.out.println("PR RecruitPG - QryAppId : " + appid); 
    if (appid != null && !"".equals(appid)) {
      // Query the Selected Applicant detail for Update
       System.out.println("PR RecruitPG - Calling QryApplRow  "); 

      Serializable[] params = { appid };   
      aAM.invokeMethod("QryApplRow", params) ;

      // System.out.println("person id " + applVO.first().getAttribute("PersonId"));
       /**

       if (applVO.getCurrentRow().getAttribute("PersonId") != null)
           readonly = "Y" ;
       else
           readonly = "N" ;  
        **/
       readonly = "N" ;  
       applVO.first();
       if (applVO.getCurrentRow() != null) {
          if (applVO.getCurrentRow().getAttribute("PersonId") != null) {
             readonly = "Y" ;
          }
       }
      
      System.out.println("PR RecruitPG readonly - " + readonly);
      
      //  System.out.println("PR RecruitPG 1");
      // Row row = applVO.first();
      //  System.out.println("PR RecruitPG 2");
      //  Ref_Num = applVO.getCurrentRow().getAttribute("AppRefNumber").toString() ; //(String)row.getAttribute("AppRefNumber");

     // Ref_Num = pageContext.getParameter("AppRefNum");  
     // System.out.println("PR RecruitPG - Ref_Num : " + Ref_Num);
      
    } else {
      // Create New Applicant
      System.out.println("PR RecruitPG - Calling AddApplRow  "); 
      readonly = "N" ;  
       
      aAM.addrows(applVO, "PR", null, null);
    }

      /**
       * Set Enable / Disable Pages based on Role
       * ADMIN - can create or modify or view applicant details
       * OTHERS - can only view
       */
      role = pageContext.getParameter("Role");
      if (role == null | "".equals(role))
          role = "OTHERS";
      System.out.println("PR recruitPG - role " + role);
      aAM.setEnableDisable(tVO, role, readonly) ;
      System.out.println("PR RecruitPG - End...  "); 
  }

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
   
  String EventName = null; 
  
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);

    // OAApplicationModule aAM =  (OAApplicationModule)pageContext.getApplicationModule(webBean);
    applAMImpl aAM        = (applAMImpl)pageContext.getApplicationModule(webBean);

    applVOImpl applVO     = (applVOImpl) aAM.findViewObject("applVO") ;
    eduVOImpl eduVO       = (eduVOImpl) aAM.findViewObject("eduVO") ;
    expVOImpl expVO       = (expVOImpl) aAM.findViewObject("expVO") ;
    interVOImpl intVO     = (interVOImpl) aAM.findViewObject("interVO") ;
    OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");
    CommonClass cc       = new CommonClass() ;


    String appid = applVO.getCurrentRow().getAttribute("AppId").toString();
    
    String rf = pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE) ;
    System.out.println("Curr Row Ref " + rf);
    
    if(pageContext.getParameter(EVENT_PARAM)!=null){
       EventName =  pageContext.getParameter(EVENT_PARAM).toString();
    }
    System.out.println("event @ PFR : " + pageContext.getParameter(EVENT_PARAM));

    /**
    if(EventName.equalsIgnoreCase("Dob")) {
      Row row = aAM.findRowByRef(rf) ;
      if (row.getAttribute("DateOfBirth") != null) {
            String dob = row.getAttribute("DateOfBirth").toString() ;
            long age = cc.daysBetween(row.getAttribute("DateOfBirth"), txn.getCurrentDBDate().dateValue()) / 365 ;

        }
    }
    */

    if(EventName.equalsIgnoreCase("AddEduBut")) {
        // aAM.invokeMethod("AddEduRows") ;
        aAM.addrows(eduVO, EventName, appid, null);
    }

    if(EventName.equalsIgnoreCase("AddExpBut")) {
        // aAM.invokeMethod("AddExpRows") ;
        aAM.addrows(expVO, EventName, appid, null);
    }
        
    if(EventName.equalsIgnoreCase("AddInterBut")) {
        // aAM.invokeMethod("AddInterRows") ;
        aAM.addrows(intVO, EventName, appid, null);
    }

    if(EventName.equalsIgnoreCase("PanelBut")) {
    
        System.out.println("event :  PanelBut - role " + role + "  readonly=" + readonly);
        // Save the data before navigating to Panel Page
        aAM.invokeMethod("saveBut") ; 
        System.out.println("event :  PanelBut 1");
        // Pass Parameter AppIntId to fetch corresponding Panel Details
        String appintid = pageContext.getParameter("AppIntId");  
        appid    = pageContext.getParameter("QryAppId"); 
        System.out.println("event :  PanelBut 2");
       // System.out.println("Value of AppRefNumber" + applVO.first().getAttribute("AppRefNumber").toString());
        System.out.println("Before Hashmap");
        HashMap hmap = new HashMap();
        hmap.put("AppIntId", appintid);
        hmap.put("QryAppId", appid); 
        System.out.println("RefNum:"+applVO.first().getAttribute("AppRefNumber"));
        hmap.put("RefNumDisp", applVO.first().getAttribute("AppRefNumber")) ;
        hmap.put("AppNameDisp", applVO.first().getAttribute("AppName"));
        hmap.put("AppNameDisp", applVO.first().getAttribute("LastName") + ", " + applVO.first().getAttribute("FirstName"));
        System.out.println("After Hashmap");
        System.out.println("event :  PanelBut 3");
        // Call Panel Page
        pageContext.setForwardURL("OA.jsp?page=/xxhlp/oracle/apps/xxhlp/recruit/webui/PanelPG&Role=" + role + "&ReadOnly=" + readonly, 
                                  null, 
                                  OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null,
                                  hmap,
                                  true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                  OAWebBeanConstants.IGNORE_MESSAGES); 
    }

    if(EventName.equalsIgnoreCase("saveBut"))
    {
       aAM.invokeMethod("saveBut") ;  
    }
    
    if(EventName.equalsIgnoreCase("cancelBut"))
    {
       aAM.invokeMethod("cancelBut") ;  
        pageContext.setForwardURL("OA.jsp?page=/xxhlp/oracle/apps/xxhlp/recruit/webui/QryPG&Role=" + role,  
                                   null, 
                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                   null,
                                   null,
                                   false, 
                                   OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                   OAWebBeanConstants.IGNORE_MESSAGES);
    }

        if(EventName.equalsIgnoreCase("delEdu"))
        {
            String pkid = pageContext.getParameter("AppEduId");    
            Serializable[] params = { "EDUCATION", pkid };   
            System.out.println("PFR RecruitPG - delEdu pkid : " + pkid); 
            aAM.invokeMethod("deleteApplicant", params) ;
        }

        if(EventName.equalsIgnoreCase("delExp"))
        {
            System.out.println("PFR RecruitPG - delExp1"); 
            String pkid = pageContext.getParameter("AppEmpId"); 
            System.out.println("PFR RecruitPG - delExp2"); 
            Serializable[] params = { "EXPERIENCE", pkid };   
            System.out.println("PFR RecruitPG - delExp pkid : " + pkid); 
            aAM.invokeMethod("deleteApplicant", params) ;            
        }

        if(EventName.equalsIgnoreCase("delInt"))
        {
            String pkid = pageContext.getParameter("AppIntId"); 
            Serializable[] params = { "INTERVIEW", pkid };   
            System.out.println("PFR RecruitPG - delInt pkid : " + pkid); 
            aAM.invokeMethod("deleteApplicant", params) ;            
        }

        if ((EventName.equalsIgnoreCase("ExpDateFrom")) |
            (EventName.equalsIgnoreCase("ExpDateUpto")))
        {
             Row row = aAM.findRowByRef(rf) ;
             aAM.calcExperience(row) ;
        }

        if (EventName.equalsIgnoreCase("ExpDateFrom")) {
            Row row = aAM.findRowByRef(rf) ;
            if (row.getAttribute("DateFrom") != null) {
                String dtfm = row.getAttribute("DateFrom").toString() ;
                System.out.println("dtfm : " + dtfm);
                aAM.setExpYear(tVO, dtfm) ;
            }
        }

        if (EventName.equalsIgnoreCase("IntType")) {
            Row row = aAM.findRowByRef(rf) ;
            
            if (row.getAttribute("IntType")!=null){
                String iflag = row.getAttribute("IntType").toString(); // pageContext.getParameter("intFlag"); 
                System.out.println("iflag : " + iflag);
                aAM.setInterviewMarks(intVO, iflag) ;
            }

        }

        if(EventName.equalsIgnoreCase("calcMarks"))
        {
            Row row = aAM.findRowByRef(rf) ;
            aAM.calcMarks(row) ;
        }

        if(EventName.equalsIgnoreCase("CreateEmpBut"))
        {
           /** Check for Employee Number */
           if (applVO.getCurrentRow() != null) {
               if (applVO.getCurrentRow().getAttribute("EmployeeNumber") == null || "".equals(applVO.getCurrentRow().getAttribute("EmployeeNumber"))) {
                   throw new OAException ("Please assign Employee Number before Creating Employee in HR System", OAException.ERROR);
               }
                System.out.println("Event CreateEmpBut appid: " + appid);
    
                /** Save the changes first */
                aAM.invokeMethod("saveBut") ;
                
                /** Create Employee in HR System */
                aAM.createEmployee(appid) ;
                
                /** Re-query after Create Employee */
                
                pageContext.putParameter("QryAppId", appid); 
                pageContext.forwardImmediatelyToCurrentPage(null, false, null);
                System.out.println("Event CreateEmpBut after calling createEmployee()" );

           }
        }

    }

}
