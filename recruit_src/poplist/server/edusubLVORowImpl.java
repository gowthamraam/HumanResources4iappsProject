package xxhlp.oracle.apps.xxhlp.recruit.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class edusubLVORowImpl extends OAViewRowImpl {
    public static final int EDUSUB = 0;
    public static final int EDUSUBDESC = 1;

    /**This is the default constructor (do not remove)
     */
    public edusubLVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Edusub
     */
    public String getEdusub() {
        return (String) getAttributeInternal(EDUSUB);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Edusub
     */
    public void setEdusub(String value) {
        setAttributeInternal(EDUSUB, value);
    }

    /**Gets the attribute value for the calculated attribute EdusubDesc
     */
    public String getEdusubDesc() {
        return (String) getAttributeInternal(EDUSUBDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EdusubDesc
     */
    public void setEdusubDesc(String value) {
        setAttributeInternal(EDUSUBDESC, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EDUSUB:
            return getEdusub();
        case EDUSUBDESC:
            return getEdusubDesc();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EDUSUB:
            setEdusub((String)value);
            return;
        case EDUSUBDESC:
            setEdusubDesc((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
