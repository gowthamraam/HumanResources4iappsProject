package xxhlp.oracle.apps.xxhlp.recruit.schema.server;

import com.sun.java.util.collections.ArrayList;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;

import xxhlp.oracle.apps.xxhlp.CommonClass;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class expEOImpl extends OAEntityImpl {
    public static final int APPEMPID = 0;
    public static final int APPID = 1;
    public static final int COMPANYNAME = 2;
    public static final int DATEFROM = 3;
    public static final int DATEUPTO = 4;
    public static final int POSITIONHELD = 5;
    public static final int COUNTRY = 6;
    public static final int EXPTYPE = 7;
    public static final int ATTRIBUTECATEGORY = 8;
    public static final int ATTRIBUTE1 = 9;
    public static final int ATTRIBUTE2 = 10;
    public static final int ATTRIBUTE3 = 11;
    public static final int ATTRIBUTE4 = 12;
    public static final int ATTRIBUTE5 = 13;
    public static final int ATTRIBUTE6 = 14;
    public static final int ATTRIBUTE7 = 15;
    public static final int ATTRIBUTE8 = 16;
    public static final int ATTRIBUTE9 = 17;
    public static final int ATTRIBUTE10 = 18;
    public static final int ATTRIBUTE11 = 19;
    public static final int ATTRIBUTE12 = 20;
    public static final int ATTRIBUTE13 = 21;
    public static final int ATTRIBUTE14 = 22;
    public static final int ATTRIBUTE15 = 23;
    public static final int CREATEDBY = 24;
    public static final int CREATIONDATE = 25;
    public static final int LASTUPDATEDBY = 26;
    public static final int LASTUPDATEDATE = 27;
    public static final int LASTUPDATELOGIN = 28;
    public static final int EXPYEARS = 29;
    public static final int APPLEO = 30;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public expEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxhlp.oracle.apps.xxhlp.recruit.schema.server.expEO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for AppEmpId, using the alias name AppEmpId
     */
    public Number getAppEmpId() {
        return (Number)getAttributeInternal(APPEMPID);
    }

    /**Sets <code>value</code> as the attribute value for AppEmpId
     */
    public void setAppEmpId(Number value) {
        setAttributeInternal(APPEMPID, value);
    }

    /**Gets the attribute value for AppId, using the alias name AppId
     */
    public Number getAppId() {
        return (Number)getAttributeInternal(APPID);
    }

    /**Sets <code>value</code> as the attribute value for AppId
     */
    public void setAppId(Number value) {
        setAttributeInternal(APPID, value);
    }

    /**Gets the attribute value for CompanyName, using the alias name CompanyName
     */
    public String getCompanyName() {
        return (String)getAttributeInternal(COMPANYNAME);
    }

    /**Sets <code>value</code> as the attribute value for CompanyName
     */
    public void setCompanyName(String value) {
        setAttributeInternal(COMPANYNAME, value);
    }

    /**Gets the attribute value for DateFrom, using the alias name DateFrom
     */
    public Date getDateFrom() {
        return (Date)getAttributeInternal(DATEFROM);
    }

    /**Sets <code>value</code> as the attribute value for DateFrom
     */
    public void setDateFrom(Date value) {
        setAttributeInternal(DATEFROM, value);
    }

    /**Gets the attribute value for DateUpto, using the alias name DateUpto
     */
    public Date getDateUpto() {
        return (Date)getAttributeInternal(DATEUPTO);
    }

    /**Sets <code>value</code> as the attribute value for DateUpto
     */
    public void setDateUpto(Date value) {
        setAttributeInternal(DATEUPTO, value);
    }

    /**Gets the attribute value for PositionHeld, using the alias name PositionHeld
     */
    public String getPositionHeld() {
        return (String)getAttributeInternal(POSITIONHELD);
    }

    /**Sets <code>value</code> as the attribute value for PositionHeld
     */
    public void setPositionHeld(String value) {
        setAttributeInternal(POSITIONHELD, value);
    }

    /**Gets the attribute value for Country, using the alias name Country
     */
    public String getCountry() {
        return (String)getAttributeInternal(COUNTRY);
    }

    /**Sets <code>value</code> as the attribute value for Country
     */
    public void setCountry(String value) {
        setAttributeInternal(COUNTRY, value);
    }

    /**Gets the attribute value for ExpType, using the alias name ExpType
     */
    public String getExpType() {
        return (String)getAttributeInternal(EXPTYPE);
    }

    /**Sets <code>value</code> as the attribute value for ExpType
     */
    public void setExpType(String value) {
        setAttributeInternal(EXPTYPE, value);
    }

    /**Gets the attribute value for AttributeCategory, using the alias name AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
    }

    /**Gets the attribute value for Attribute1, using the alias name Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for Attribute2, using the alias name Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for Attribute3, using the alias name Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for Attribute4, using the alias name Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for Attribute5, using the alias name Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for Attribute6, using the alias name Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**Sets <code>value</code> as the attribute value for Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**Gets the attribute value for Attribute7, using the alias name Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**Sets <code>value</code> as the attribute value for Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**Gets the attribute value for Attribute8, using the alias name Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**Sets <code>value</code> as the attribute value for Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**Gets the attribute value for Attribute9, using the alias name Attribute9
     */
    public String getAttribute9() {
        return (String)getAttributeInternal(ATTRIBUTE9);
    }

    /**Sets <code>value</code> as the attribute value for Attribute9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**Gets the attribute value for Attribute10, using the alias name Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**Sets <code>value</code> as the attribute value for Attribute10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**Gets the attribute value for Attribute11, using the alias name Attribute11
     */
    public String getAttribute11() {
        return (String)getAttributeInternal(ATTRIBUTE11);
    }

    /**Sets <code>value</code> as the attribute value for Attribute11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**Gets the attribute value for Attribute12, using the alias name Attribute12
     */
    public String getAttribute12() {
        return (String)getAttributeInternal(ATTRIBUTE12);
    }

    /**Sets <code>value</code> as the attribute value for Attribute12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**Gets the attribute value for Attribute13, using the alias name Attribute13
     */
    public String getAttribute13() {
        return (String)getAttributeInternal(ATTRIBUTE13);
    }

    /**Sets <code>value</code> as the attribute value for Attribute13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**Gets the attribute value for Attribute14, using the alias name Attribute14
     */
    public String getAttribute14() {
        return (String)getAttributeInternal(ATTRIBUTE14);
    }

    /**Sets <code>value</code> as the attribute value for Attribute14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**Gets the attribute value for Attribute15, using the alias name Attribute15
     */
    public String getAttribute15() {
        return (String)getAttributeInternal(ATTRIBUTE15);
    }

    /**Sets <code>value</code> as the attribute value for Attribute15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPEMPID:
            return getAppEmpId();
        case APPID:
            return getAppId();
        case COMPANYNAME:
            return getCompanyName();
        case DATEFROM:
            return getDateFrom();
        case DATEUPTO:
            return getDateUpto();
        case POSITIONHELD:
            return getPositionHeld();
        case COUNTRY:
            return getCountry();
        case EXPTYPE:
            return getExpType();
        case ATTRIBUTECATEGORY:
            return getAttributeCategory();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case ATTRIBUTE9:
            return getAttribute9();
        case ATTRIBUTE10:
            return getAttribute10();
        case ATTRIBUTE11:
            return getAttribute11();
        case ATTRIBUTE12:
            return getAttribute12();
        case ATTRIBUTE13:
            return getAttribute13();
        case ATTRIBUTE14:
            return getAttribute14();
        case ATTRIBUTE15:
            return getAttribute15();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case EXPYEARS:
            return getExpYears();
        case APPLEO:
            return getApplEO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPEMPID:
            setAppEmpId((Number)value);
            return;
        case APPID:
            setAppId((Number)value);
            return;
        case COMPANYNAME:
            setCompanyName((String)value);
            return;
        case DATEFROM:
            setDateFrom((Date)value);
            return;
        case DATEUPTO:
            setDateUpto((Date)value);
            return;
        case POSITIONHELD:
            setPositionHeld((String)value);
            return;
        case COUNTRY:
            setCountry((String)value);
            return;
        case EXPTYPE:
            setExpType((String)value);
            return;
        case ATTRIBUTECATEGORY:
            setAttributeCategory((String)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case ATTRIBUTE6:
            setAttribute6((String)value);
            return;
        case ATTRIBUTE7:
            setAttribute7((String)value);
            return;
        case ATTRIBUTE8:
            setAttribute8((String)value);
            return;
        case ATTRIBUTE9:
            setAttribute9((String)value);
            return;
        case ATTRIBUTE10:
            setAttribute10((String)value);
            return;
        case ATTRIBUTE11:
            setAttribute11((String)value);
            return;
        case ATTRIBUTE12:
            setAttribute12((String)value);
            return;
        case ATTRIBUTE13:
            setAttribute13((String)value);
            return;
        case ATTRIBUTE14:
            setAttribute14((String)value);
            return;
        case ATTRIBUTE15:
            setAttribute15((String)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case EXPYEARS:
            setExpYears((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated entity applEOImpl
     */
    public applEOImpl getApplEO() {
        return (applEOImpl)getAttributeInternal(APPLEO);
    }

    /**Sets <code>value</code> as the associated entity applEOImpl
     */
    public void setApplEO(applEOImpl value) {
        setAttributeInternal(APPLEO, value);
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**Add Entity validation code in this method.
     */
    protected void validateEntity() {
        super.validateEntity();

        //Do Validations
        validateExp() ;        
    }

    /**Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**Custom DML update/insert/delete logic here.
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }


    /**
     *  Custom Methods are below
     *  ========================
     */

    /*** All Validation related to Exp EO
     */
    public void validateExp() {

        System.out.println("at validateExp()" );
        CommonClass cc = new CommonClass() ;
        ArrayList ErrList = new ArrayList() ;

        /*** Vaidate Experience Dates ***/
         System.out.println(" Validate Experience Dates " );
         if (getDateFrom() != null && cc.isGtSysdate(getDateFrom().dateValue())) {
             ErrList.add (new OAException("Experience Date From : Should be Less than or Equal to Current Date...", OAException.ERROR));
         }        

        if (getDateUpto() != null && cc.isGtSysdate(getDateUpto().dateValue())) {
            ErrList.add (new OAException("Experience Date Upto : Should be Less than or Equal to Current Date...", OAException.ERROR));
        }  

        if (getDateFrom() != null && getDateUpto() != null) {
           if (cc.compareDate(getDateFrom().dateValue(), getDateUpto().dateValue()) > 0) {
              ErrList.add (new OAException("Experience Date Range : Date From should be Less than or Equal to Date Upto...", OAException.ERROR));
           }
        }  

        if (getExpYears() != null ) {
            long yr = getExpYears().longValue() ;
            if (yr < 0 || yr > 40) {
               ErrList.add (new OAException("Experience Year : Should be between 0 and 40 ...", OAException.ERROR));
            }
        }  

        if (ErrList.size()> 0) {
           OAException.raiseBundledOAException(ErrList);
        }


    } // end of validateExp()


    /**Gets the attribute value for ExpYears, using the alias name ExpYears
     */
    public Number getExpYears() {
        return (Number)getAttributeInternal(EXPYEARS);
    }

    /**Sets <code>value</code> as the attribute value for ExpYears
     */
    public void setExpYears(Number value) {
        setAttributeInternal(EXPYEARS, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number appEmpId) {
        return new Key(new Object[]{appEmpId});
    }
}