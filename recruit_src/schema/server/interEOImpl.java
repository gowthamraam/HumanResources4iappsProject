package xxhlp.oracle.apps.xxhlp.recruit.schema.server;

import com.sun.java.util.collections.ArrayList;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
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
public class interEOImpl extends OAEntityImpl {
    public static final int APPINTID = 0;
    public static final int APPID = 1;
    public static final int INTTYPE = 2;
    public static final int INTDATE = 3;
    public static final int INTLOCATION = 4;
    public static final int NUMERICALMARKS = 5;
    public static final int ENGLISHMARKS = 6;
    public static final int ESSAYMARKS = 7;
    public static final int TOTALMARKS = 8;
    public static final int RESULT = 9;
    public static final int ATTRIBUTECATEGORY = 10;
    public static final int ATTRIBUTE1 = 11;
    public static final int ATTRIBUTE2 = 12;
    public static final int ATTRIBUTE3 = 13;
    public static final int ATTRIBUTE4 = 14;
    public static final int ATTRIBUTE5 = 15;
    public static final int ATTRIBUTE6 = 16;
    public static final int ATTRIBUTE7 = 17;
    public static final int ATTRIBUTE8 = 18;
    public static final int ATTRIBUTE9 = 19;
    public static final int ATTRIBUTE10 = 20;
    public static final int ATTRIBUTE11 = 21;
    public static final int ATTRIBUTE12 = 22;
    public static final int ATTRIBUTE13 = 23;
    public static final int ATTRIBUTE14 = 24;
    public static final int ATTRIBUTE15 = 25;
    public static final int CREATEDBY = 26;
    public static final int CREATIONDATE = 27;
    public static final int LASTUPDATEDBY = 28;
    public static final int LASTUPDATEDATE = 29;
    public static final int LASTUPDATELOGIN = 30;
    public static final int APPLEO = 31;
    public static final int PANELEO = 32;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public interEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxhlp.oracle.apps.xxhlp.recruit.schema.server.interEO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for AppIntId, using the alias name AppIntId
     */
    public Number getAppIntId() {
        return (Number)getAttributeInternal(APPINTID);
    }

    /**Sets <code>value</code> as the attribute value for AppIntId
     */
    public void setAppIntId(Number value) {
        setAttributeInternal(APPINTID, value);
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

    /**Gets the attribute value for IntType, using the alias name IntType
     */
    public String getIntType() {
        return (String)getAttributeInternal(INTTYPE);
    }

    /**Sets <code>value</code> as the attribute value for IntType
     */
    public void setIntType(String value) {
        setAttributeInternal(INTTYPE, value);
    }

    /**Gets the attribute value for IntDate, using the alias name IntDate
     */
    public Date getIntDate() {
        return (Date)getAttributeInternal(INTDATE);
    }

    /**Sets <code>value</code> as the attribute value for IntDate
     */
    public void setIntDate(Date value) {
        setAttributeInternal(INTDATE, value);
    }

    /**Gets the attribute value for IntLocation, using the alias name IntLocation
     */
    public String getIntLocation() {
        return (String)getAttributeInternal(INTLOCATION);
    }

    /**Sets <code>value</code> as the attribute value for IntLocation
     */
    public void setIntLocation(String value) {
        setAttributeInternal(INTLOCATION, value);
    }

    /**Gets the attribute value for NumericalMarks, using the alias name NumericalMarks
     */
    public Number getNumericalMarks() {
        return (Number)getAttributeInternal(NUMERICALMARKS);
    }

    /**Sets <code>value</code> as the attribute value for NumericalMarks
     */
    public void setNumericalMarks(Number value) {
        setAttributeInternal(NUMERICALMARKS, value);
    }

    /**Gets the attribute value for EnglishMarks, using the alias name EnglishMarks
     */
    public Number getEnglishMarks() {
        return (Number)getAttributeInternal(ENGLISHMARKS);
    }

    /**Sets <code>value</code> as the attribute value for EnglishMarks
     */
    public void setEnglishMarks(Number value) {
        setAttributeInternal(ENGLISHMARKS, value);
    }

    /**Gets the attribute value for EssayMarks, using the alias name EssayMarks
     */
    public Number getEssayMarks() {
        return (Number)getAttributeInternal(ESSAYMARKS);
    }

    /**Sets <code>value</code> as the attribute value for EssayMarks
     */
    public void setEssayMarks(Number value) {
        setAttributeInternal(ESSAYMARKS, value);
    }

    /**Gets the attribute value for TotalMarks, using the alias name TotalMarks
     */
    public Number getTotalMarks() {
        return (Number)getAttributeInternal(TOTALMARKS);
    }

    /**Sets <code>value</code> as the attribute value for TotalMarks
     */
    public void setTotalMarks(Number value) {
        setAttributeInternal(TOTALMARKS, value);
    }

    /**Gets the attribute value for Result, using the alias name Result
     */
    public String getResult() {
        return (String)getAttributeInternal(RESULT);
    }

    /**Sets <code>value</code> as the attribute value for Result
     */
    public void setResult(String value) {
        setAttributeInternal(RESULT, value);
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
        case APPINTID:
            return getAppIntId();
        case APPID:
            return getAppId();
        case INTTYPE:
            return getIntType();
        case INTDATE:
            return getIntDate();
        case INTLOCATION:
            return getIntLocation();
        case NUMERICALMARKS:
            return getNumericalMarks();
        case ENGLISHMARKS:
            return getEnglishMarks();
        case ESSAYMARKS:
            return getEssayMarks();
        case TOTALMARKS:
            return getTotalMarks();
        case RESULT:
            return getResult();
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
        case PANELEO:
            return getPanelEO();
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
        case APPINTID:
            setAppIntId((Number)value);
            return;
        case APPID:
            setAppId((Number)value);
            return;
        case INTTYPE:
            setIntType((String)value);
            return;
        case INTDATE:
            setIntDate((Date)value);
            return;
        case INTLOCATION:
            setIntLocation((String)value);
            return;
        case NUMERICALMARKS:
            setNumericalMarks((Number)value);
            return;
        case ENGLISHMARKS:
            setEnglishMarks((Number)value);
            return;
        case ESSAYMARKS:
            setEssayMarks((Number)value);
            return;
        case TOTALMARKS:
            setTotalMarks((Number)value);
            return;
        case RESULT:
            setResult((String)value);
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

    /**Gets the associated entity oracle.jbo.RowIterator
     */
    public RowIterator getPanelEO() {
        return (RowIterator)getAttributeInternal(PANELEO);
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
        //Do Validations
        validateInter() ;        
        super.validateEntity();
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

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number appIntId) {
        return new Key(new Object[]{appIntId});
    }

    /**
     *  Custom Methods are below
     *  ========================
     */

    /*** All Validation related to Interview EO
     */
    public void validateInter() {

        System.out.println("at validateInter()" );
        CommonClass cc = new CommonClass() ;
        ArrayList ErrList = new ArrayList() ;

        /*** Vaidate Marks ***/
         System.out.println(" Validate Marks " );
//         if (getEnglishMarks() != null && cc.isNegative(getEnglishMarks().toString())) {
//             ErrList.add (new OAException("English Marks : Marks cannot be Negative...", OAException.ERROR));
//         }        
//
//        if (getEssayMarks() != null && cc.isNegative(getEssayMarks().toString())) {
//            ErrList.add (new OAException("Essay Marks : Marks cannot be Negative...", OAException.ERROR));
//        }  
//
//        if (getNumericalMarks() != null && cc.isNegative(getNumericalMarks().toString())) {
//            ErrList.add (new OAException("Numerical Marks : Marks cannot be Negative...", OAException.ERROR));
//        } 
//
//        if (ErrList.size()> 0) {
//           OAException.raiseBundledOAException(ErrList);
//        }


    } // end of validateInter()



}
