/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxhlp.oracle.apps.xxhlp.recruit.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

import oracle.jbo.Row;

import xxhlp.oracle.apps.xxhlp.CommonClass;
import xxhlp.oracle.apps.xxhlp.recruit.server.applAMImpl;

/**
 * Controller for ...
 */
public class ReportCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);

    applAMImpl aAM    = (applAMImpl)pageContext.getApplicationModule(webBean);
    OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");

    tVO.executeQuery();
    tVO.first();
    if(tVO.getCurrentRow() != null) {
      tVO.getCurrentRow().setAttribute("DisableGroup02", Boolean.TRUE);
      tVO.getCurrentRow().setAttribute("DisableGroup03", Boolean.TRUE);
      tVO.getCurrentRow().setAttribute("DisableGroup04", Boolean.TRUE);
    }    
    
  } // end of processRequest

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
    applAMImpl aAM = (applAMImpl)pageContext.getApplicationModule(webBean);
    OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");
    OAMessageChoiceBean G2 = (OAMessageChoiceBean)webBean.findChildRecursive("Group02");
    OAMessageChoiceBean G3 = (OAMessageChoiceBean)webBean.findChildRecursive("Group03");
    OAMessageChoiceBean G4 = (OAMessageChoiceBean)webBean.findChildRecursive("Group04");
    CommonClass cc = new CommonClass() ;
    String rf = pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE) ;

    System.out.println("PFR ReportPG - at Begining"); 
    if (pageContext.getParameter(EVENT_PARAM) != null) {
        EventName =  pageContext.getParameter(EVENT_PARAM).toString();
    }
    System.out.println("PFR ReportPG - Event Name : " + EventName); 

    /** Call the Report **/
    if (EventName.equalsIgnoreCase("ButGo")) {
       System.out.println("PFR Go Button - ButGo "); 
       String crdtfm = pageContext.getParameter("CrDateFrom");
       String crdtto = pageContext.getParameter("CrDateTo");
       String rdtfm  = pageContext.getParameter("RcvdDateFrom");
       String rdtto  = pageContext.getParameter("RcvdDateTo");
       String g1     = pageContext.getParameter("Group01");
       String g2     = pageContext.getParameter("Group02");
       String g3     = pageContext.getParameter("Group03");
       String g4     = pageContext.getParameter("Group04");
       String mexp   = pageContext.getParameter("MinExp");

       aAM.startReport(pageContext,crdtfm, crdtto, rdtfm, rdtto, g1, g2, g3, g4, mexp);
    }

    // To Clear the Query Criteria
    if (EventName.equalsIgnoreCase("ButClear")) {
       System.out.println("PFR Clear Button - ButClear "); 
       pageContext.forwardImmediatelyToCurrentPage(null, false, null);
    }

      Row row = aAM.findRowByRef(rf) ;
      /** Action at Group 01 **/
       if (EventName.equalsIgnoreCase("Group01")) {
           if (tVO.getCurrentRow() != null) {
             if (pageContext.getParameter("Group01") != null && !"".equals(pageContext.getParameter("Group01"))) {
               tVO.getCurrentRow().setAttribute("DisableGroup02", Boolean.FALSE);
             }
             else {
               tVO.getCurrentRow().setAttribute("DisableGroup02", Boolean.TRUE);
               tVO.getCurrentRow().setAttribute("DisableGroup03", Boolean.TRUE);
               tVO.getCurrentRow().setAttribute("DisableGroup04", Boolean.TRUE);
               G2.setValue(pageContext,null);
               G3.setValue(pageContext,null);
               G4.setValue(pageContext,null);
             }
           }
       }

      /** Action at Group 02 **/
      if (EventName.equalsIgnoreCase("Group02")) {
          if (tVO.getCurrentRow() != null) {
              if (pageContext.getParameter("Group02") != null && !"".equals(pageContext.getParameter("Group02"))) {
                tVO.getCurrentRow().setAttribute("DisableGroup03", Boolean.FALSE);
              }
              else {
                tVO.getCurrentRow().setAttribute("DisableGroup03", Boolean.TRUE);
                tVO.getCurrentRow().setAttribute("DisableGroup04", Boolean.TRUE);
                G3.setValue(pageContext,null);
                G4.setValue(pageContext,null);              }
          }
      }

      /** Action at Group 03 **/
      if (EventName.equalsIgnoreCase("Group03")) {
          if (tVO.getCurrentRow() != null) {
              if (pageContext.getParameter("Group03") != null && !"".equals(pageContext.getParameter("Group03"))) {
                tVO.getCurrentRow().setAttribute("DisableGroup04", Boolean.FALSE);
              }
              else {
                tVO.getCurrentRow().setAttribute("DisableGroup04", Boolean.TRUE);
                G4.setValue(pageContext,null);              }
          }
      }

      /** Action at Minimum Experience **/
      if (EventName.equalsIgnoreCase("MinExp")) {
         if (cc.isNegative(pageContext.getParameter("MinExp"))) {
             throw new OAException ("Enter Positive Values for Minimum Experience...", OAException.ERROR);
         }

      }
  } // end of processFormRequest 

}
