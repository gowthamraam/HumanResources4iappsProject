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
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAUrl;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jbo.Row;

import xxhlp.oracle.apps.xxhlp.recruit.server.applAMImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.applVOImpl;
import xxhlp.oracle.apps.xxhlp.recruit.server.panelVOImpl;

/**
 * Controller for ...
 */
public class PanelCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  String appintid = null ;
  String appid    = null ;
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

    applAMImpl aAM    = (applAMImpl)pageContext.getApplicationModule(webBean);
    // OAApplicationModule aAM =  (OAApplicationModule)pageContext.getApplicationModule(webBean);
    OAViewObjectImpl tVO = (OAViewObjectImpl)aAM.findViewObject("TransientVO");
      
     /**
      * Set Enable / Disable Pages based on Role
      * ADMIN - can create or modify or view applicant details
      * OTHERS - can only view
      */
     role     = pageContext.getParameter("Role");
     readonly = pageContext.getParameter("ReadOnly");
     if (role == null | "".equals(role))
        role = "OTHERS";
     if (readonly == null | "".equals(readonly))
         readonly = "Y";
     System.out.println("PR PanelPG - role " + role);
     aAM.setEnableDisable(tVO, role, readonly) ;

      appintid = pageContext.getParameter("AppIntId"); 

      // Set the Values for Ref Number and Applicant Name
      String RefNumDisp = pageContext.getParameter("RefNumDisp") ;
      String AppNameDisp = pageContext.getParameter("AppNameDisp") ;
      OAMessageStyledTextBean rn = (OAMessageStyledTextBean) webBean.findChildRecursive("refnumdisp");
      OAMessageStyledTextBean an = (OAMessageStyledTextBean) webBean.findChildRecursive("appnamedisp");
      rn.setValue(pageContext, RefNumDisp);
      an.setValue(pageContext, AppNameDisp);

      System.out.println("PR PanelPG - AppIntId : " + appintid); 
      
      if (appintid != null && !"".equals(appintid)) {
        // Query the Selected Panel detail for Update
         System.out.println("PR PanelPG - Calling QryPanelRows  "); 

        Serializable[] params = { appintid };   
        aAM.invokeMethod("QryPanelRows", params) ;
      }
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

      applAMImpl aAM  = (applAMImpl)pageContext.getApplicationModule(webBean);
      panelVOImpl pVO = (panelVOImpl) aAM.findViewObject("panelVO") ;
      
      appid = pageContext.getParameter("QryAppId");    
      appintid = pageContext.getParameter("AppIntId"); 
      
      if(pageContext.getParameter(EVENT_PARAM)!=null){
         EventName =  pageContext.getParameter(EVENT_PARAM).toString();
      }

      if(EventName.equalsIgnoreCase("AddPanelBut"))
      {
        // aAM.invokeMethod("AddPanelRows") ;
        aAM.addrows(pVO, EventName, appid, appintid);
      }
     
      if(EventName.equalsIgnoreCase("PanelBackBut"))
      {
         aAM.invokeMethod("saveBut") ;  
         
         HashMap hmap = new HashMap();
         hmap.put("QryAppId", appid); 
         System.out.println("event PanelBackBut - role " + role); 
         pageContext.setForwardURL("OA.jsp?page=/xxhlp/oracle/apps/xxhlp/recruit/webui/RecruitPG&Role=" + role, 
                                    null, 
                                    OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                    null,
                                    hmap,
                                    true,
                                    OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                    OAWebBeanConstants.IGNORE_MESSAGES);
      }
      
       if(EventName.equalsIgnoreCase("DelPanel"))
       {
           String pkid = pageContext.getParameter("IntPanelId");    
           Serializable[] params = { "INT_PANEL", pkid };   
           System.out.println("PFR PanelPG - DelPanel pkid : " + pkid); 
           aAM.invokeMethod("deleteApplicant", params) ;
       }


    /*  if (pageContext.isLovEvent()) 
      { 
          if("lovValidate".equals.(pageContext.getParameter(OAWebBeanConstants.EVENT_PARAM)) || 
             "lovUpdate".equals.(pageContext.getParameter(OAWebBeanConstants.EVENT_PARAM))) ||
             "lovPrepare".equals.(pageContext.getParameter(OAWebBeanConstants.EVENT_PARAM)))
          {
                  null;
          } 
      }*/
          
          /*
      if(EventName.equalsIgnoreCase("lovPrepare"))
      {
          StringBuffer l_buffer  = new StringBuffer();
          StringBuffer l_buffer1 = new StringBuffer();

          l_buffer.append("javascript:mywin = openWindow(top, '");
          l_buffer1.append("/xxhlp/oracle/apps/xxhlp/recruit/webui/EmpRN&retainAM=Y");
          String url = "/OA_HTML/OA.jsp?page="+l_buffer1.toString();
          OAUrl popupUrl = new OAUrl(url, OAWebBeanConstants.ADD_BREAD_CRUMB_SAVE );
          String strUrl = popupUrl.createURL(pageContext.getRenderingContext(),true);
          l_buffer.append(strUrl.toString());
          l_buffer.append("', 'lovWindow', {width:800, height:600},false,'dialog',null);");
          pageContext.putJavaScriptFunction("Employee",l_buffer.toString());

      }
*/
   }
}
