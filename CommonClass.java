package xxhlp.oracle.apps.xxhlp;

import com.sun.java.util.collections.List;

import java.io.IOException;

import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.AttributeDef;

import oracle.jdbc.OracleCallableStatement;


public class CommonClass {
    public CommonClass() {
    }

    public static void main(String[] args) {
        CommonClass commonClass = new CommonClass();

    }

    public static String EMAIL_PATTERN    = "^[A-Za-z0-9-_\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String ALPHANUM_PATTERN = "^([A-Z0-9])*$";
    public static String ID_PATTERN       = "^([A-Za-z0-9-_.])*$";
    public static String PHONE_PATTERN    = "^([0-9+-., \t])*$";
    public static String ALPHA_ALL        = "^([A-Za-z \t])*$";
    public static String ALPHA_UC         = "^([A-Z])*$";
    public static String ALPHA_LC         = "^([a-z])*$";
    public static String NUMBER_ONLY      = "^([0-9])*$";

    public static final long ONE_HOUR = 60 * 60 * 1000L;
    
    /** Validate Given Text with Given Pattern
     */
    public boolean validatePattern(String Value, String ValuePattern) {
        // System.out.println(" @validatePattern() : Value " + Value);
        // System.out.println(" @validatePattern() : Pattern " + ValuePattern);

        Pattern pattern;
        Matcher matcher;
       // System.out.println(" @validatePattern() : 1");
        pattern = Pattern.compile(ValuePattern);
       // System.out.println(" @validatePattern() : 2");
        matcher = pattern.matcher(Value);
       // System.out.println(" @validatePattern() : 3");

        return matcher.matches();
    }

    /** Vadidate whether given date is greater than Current Date
     */
    public boolean isGtSysdate(Date Value) {
        Date sd = new Date();
        if (Value.after(sd))
            return true;
        else
            return false;
    }

    /** Vadidate whether given date is lesser than Current Date
     */
    public boolean isLtSysdate(Date Value) {
        Date sd = new Date();
        if (Value.before(sd))
            return true;
        else
            return false;
    }

    /** Vadidate whether given date is lesser than Current Date
     */
    public int compareDate(Date dt1, Date dt2) {
        return dt1.compareTo(dt2);
    }

    public long daysBetween(Date d1, Date d2){
    
    System.out.println("daysBetween: d2 - " + d2.getTime());
    System.out.println("daysBetween: d1 - " + d1.getTime());
    long days = (d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24) ;
    System.out.println("daysBetween: days - " + days);
    return ( days );
    }


    /** Check for Negative
     */
    public boolean isNegative(String Value) {
        int intValue = Integer.parseInt(Value) ;
        if (intValue < 0 )
            return true;
        else
            return false;
    }

    /** Check for Positive
     */
    public boolean isPositive(String Value) {
        int intValue = Integer.parseInt(Value) ;
        if (intValue > 0 )
            return true;
        else
            return false;
    }

    /** Check for Zero
     */
    public boolean isZero(String Value) {
        int intValue = Integer.parseInt(Value) ;
        if (intValue == 0 )
            return true;
        else
            return false;
    }

    public <T> T nvl(T a, T b) {
    return (a == null)?b:a;
    }

    public void export2Excel(OAPageContext pageContext, String viewObj, String fileName, String maxSize, String[] attrList, String[] attrHdr) {
        
        OAViewObject eVO = (OAViewObject) pageContext.getRootApplicationModule().findViewObject(viewObj);

        System.out.println("@Common Class - export2Excel");

        if (eVO == null) {
              throw new OAException("Could not find View Object Instance " + viewObj + " in root AM...");
        }
        if (eVO.getRowCount() == 0)  {
           throw new OAException("There is no data to export...");
        }
        
        if ((fileName == null) || ("".equals(fileName))) {
           fileName = "Export";
        }

        HttpServletResponse response =  (HttpServletResponse) pageContext.getRenderingContext().getServletResponse();
        response.setContentType("application/text");
        response.setHeader("Content-Disposition","attachment; filename=" + fileName + ".csv");
        ServletOutputStream sOs = null;

        try
        {
            sOs = response.getOutputStream();
        
            int j = 0;
            int k = 0;
            boolean bb = true;
            
            System.out.println("@Common Class - export2Excel - inside try block");
            
            if ((maxSize == null) || ("".equals(maxSize)))  {
                k = Integer.parseInt(pageContext.getProfile("VO_MAX_FETCH_SIZE"));
                bb = false;
            }
            else if ("MAX".equals(maxSize)) {
               bb = true;
            }
            else {
                k = Integer.parseInt(maxSize);
                bb = false;
            }
            
            /** 
             * Making Header Row
            */
             System.out.println("@Common Class - export2Excel - Exporting Header");
             StringBuffer hdrBuf = new StringBuffer();
             if (attrHdr != null) {
                for (int i = 0; i < attrHdr.length; i++) {
                    hdrBuf.append("\"" + attrHdr[i] + "\"") ;
                    hdrBuf.append(",");
                }
             }
             String header_row = hdrBuf.toString() ;
             sOs.println(header_row);
            
            AttributeDef[] a = eVO.getAttributeDefs();
            //StringBuffer cc = new StringBuffer();
            //ArrayList exist_list = new ArrayList();
                
            /** 
             * Making Data Rows
            */
            System.out.println("@Common Class - export2Excel - Exporting Data");
            int i = 0 ;
            for (OAViewRowImpl row = (OAViewRowImpl) eVO.first(); row != null; row = (OAViewRowImpl) eVO.next())
            {
                j++;
                StringBuffer b = new StringBuffer();
                
                for (int u = 0; u < attrList.length; u++) {
                    boolean appendYes = false ;
                    
                    for (i = 0; i < eVO.getAttributeCount(); i++)  {
                        if (a[i].getName().equals(attrList[u])) {
                           appendYes = true ;
                           break ;
                        }
                    }
                        
                   // System.out.println("append a[" + i + "] : " + a[i].getName() + "   " + appendYes);
                    if (appendYes) {

                        Object o = row.getAttribute(i) ;
                        if (!(o == null)) {
                            if (o.getClass().equals(Class.forName("oracle.jbo.domain.Date"))) {
                                //formatting of date
                                oracle.jbo.domain.Date dt = (oracle.jbo.domain.Date) o;
                                java.sql.Date ts = (java.sql.Date) dt.dateValue();
                                java.text.SimpleDateFormat displayDateFormat =  new java.text.SimpleDateFormat("dd-MMM-yyyy");
                                String convertedDateString = displayDateFormat.format(ts);
                                b.append("\"" + convertedDateString + "\"");
                            }
                            else {
                                b.append("\"" + o.toString() + "\"");
                            }                            
                        } // if !(o == null)
                        else {
                            b.append("\"\"");
                        }
                        b.append(",");
                    } // if appendYes
                }
                String final_row = b.toString() ;
                sOs.println(final_row);
                if (!bb) {
                    if (j == k) {
                        break;
                    }
                }
            }  // for making of data row
        }
        catch (Exception e) {
            // TODO
            e.printStackTrace();
            throw new OAException("Unexpected Exception occured.Exception Details :" + e.toString());
        }
        finally  {
            pageContext.setDocumentRendered(false); 
            try {
                sOs.flush();
                sOs.close();
            }
            catch(IOException e) {
              e.printStackTrace();
              throw new OAException("Unexpected Exception occured.Exception Details :" + e.toString());
            }
        }
        
    } // end of export2Excel

     /**************************EXECUTE QUERY***********************************/
     public void ExecuteQuery(OAViewObject VO,String WhereClause,List param){
         VO.setWhereClause(null);
         VO.setWhereClause(WhereClause);
         VO.setWhereClauseParams(null);
         for(int i=0;i<param.size();i++){
            System.out.println("Params-->"+i+"--->"+param.get(i));
            VO.setWhereClauseParam(i,param.get(i)); 
         }
         VO.executeQuery();
         VO.first();
     }
     
     /**************************SAVE REQUEST************************************/
     public void saveRequest(OAPageContext pageContext,OAWebBean webBean,OAApplicationModule AM,String RequestId){
     String callStr = "{ call XXHLP_COMMON_PKG.SAVE_REQUEST(:1) }";
     try{
        OracleCallableStatement callable = (OracleCallableStatement)AM.getOADBTransaction().createCallableStatement(callStr,1);
        callable.setInt(1, Integer.parseInt(RequestId));
        callable.execute();
        callable.close();
     }catch(SQLException sqle){
            throw OAException.wrapperException(sqle);
      }
      catch(OAException e){
            throw OAException.wrapperException(e);
      }
     }
     
     /**************************CANCEL REQUEST**********************************/
     public void cancelRequest(OAPageContext pageContext,OAWebBean webBean,OAApplicationModule AM,String RequestId) {
     String callStr = "{ call XXHLP_COMMON_PKG.CANCEL_REQUEST(:1) }";
     try{
        OracleCallableStatement callable = (OracleCallableStatement)AM.getOADBTransaction().createCallableStatement(callStr,1);
        callable.setInt(1, Integer.parseInt(RequestId));
        callable.execute();
        callable.close();
     }catch(SQLException sqle){
            throw OAException.wrapperException(sqle);
      }
      catch(OAException e){
            throw OAException.wrapperException(e);
      }
     }
     /**************************START WORKFLOW**********************************/
     public void StartWorkflow(OAPageContext pageContext, OAWebBean webBean,OAApplicationModule AM,String RequestId)
     {
     String callStr = "{ call XXHLP_WF_PKG.START_MAIN_PROCESS(:1,:2,:3) }";
     String retcode = null;
     String errbuf = null;
     try{
        OracleCallableStatement callable = (OracleCallableStatement)AM.getOADBTransaction().createCallableStatement(callStr, 1);
        callable.setInt(1, Integer.parseInt(RequestId));
        callable.registerOutParameter(2, Types.VARCHAR);
        callable.registerOutParameter(3, Types.VARCHAR);
        callable.execute();
        retcode = callable.getString(2);
        errbuf = callable.getString(3);
        callable.close();
     }catch(SQLException sqle){
            throw OAException.wrapperException(sqle);
      }
      catch (OAException e){
           throw OAException.wrapperException(e);
      } 
     }
     
     /***********************CONTINUE WORKFLOW**********************************/
      public void ContinueWorkflow(OAPageContext pageContext, OAWebBean webBean,OAApplicationModule AM,String RequestId,String Response,String HrProcId){
        String callStr = "{ call XXHLP_WF_PKG.OAF_CALLING_PROC(:1,:2,:3,:4,:5) }";
        String retcode = null;
        String errbuf = null;
        try{
           OracleCallableStatement callable = (OracleCallableStatement)AM.getOADBTransaction().createCallableStatement(callStr, 1);
           callable.setInt(1, Integer.parseInt(RequestId));
           callable.setString(2, Response);
           callable.setString(3, HrProcId);
           callable.registerOutParameter(4, Types.VARCHAR);
           callable.registerOutParameter(5, Types.VARCHAR);
           callable.execute();
           retcode = callable.getString(4);
           errbuf  = callable.getString(5);
           callable.close();         
        } 
        catch (SQLException sqle){
                throw OAException.wrapperException(sqle);
        } 
        catch (OAException e){
                throw OAException.wrapperException(e);
        } 
      }       
      
     /****************NAVIGATE TO EMPLOYEE REQUESTS SEARCH PAGE*****************/
     public void NavigateToEmpSearchPG(OAPageContext pageContext, OAWebBean webBean){
        pageContext.setForwardURL(
                                  "OA.jsp?page=/xxhlp/oracle/apps/xxhlp/common/srcreq/webui/hrreqPG&Role=EMP",
                                   null, 
                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                   null,
                                   null,
                                   false, 
                                   OAWebBeanConstants.ADD_BREAD_CRUMB_YES,
                                   OAWebBeanConstants.IGNORE_MESSAGES
                                  );
     }
     
     
     /****************NAVIGATE TO MANAGER REQUESTS SEARCH PAGE*****************/
     public void NavigateToMgrSearchPG(OAPageContext pageContext, OAWebBean webBean){
        pageContext.setForwardURL(
                                  "OA.jsp?page=/xxhlp/oracle/apps/xxhlp/common/srcreq/webui/hrreqPG&Role=MANAGER",
                                   null, 
                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                   null,
                                   null,
                                   false, 
                                   OAWebBeanConstants.ADD_BREAD_CRUMB_YES,
                                   OAWebBeanConstants.IGNORE_MESSAGES
                                  );
     }
     
     /****************************COMMIT TRANSACTION****************************/
     public void CommitTrx(OAApplicationModule AM){
        AM.getOADBTransaction().commit();
     }
     
     /****************************ROLLBACK TRANSACTION**************************/
     public void RollbackTrx(OAApplicationModule AM){
        AM.getOADBTransaction().rollback();
     }
     
     public void DialogPage(OAPageContext pageContext,OAException Exception,String OkButonName){
        OADialogPage dialogpage=new OADialogPage(OAException.WARNING,Exception,null,"","");
        dialogpage.setOkButtonLabel("Yes");
        dialogpage.setNoButtonLabel("No");
        dialogpage.setOkButtonItemName(OkButonName);
        dialogpage.setOkButtonToPost(true);
        dialogpage.setNoButtonToPost(true);
        dialogpage.setPostToCallingPage(true);
        pageContext.redirectToDialogPage(dialogpage);  
     }

} // end of CommonClass
