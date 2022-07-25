public class javaGenerator extends generator {
    //consttuctor

    @Override
    public void writeExtendClass(storeData.Class data) {
        String accessStr="";
        //    if(data.isPrivate)
        //   accessStr="private";
        //    else if(data.isPublic)
        // accessStr="public";
        //    else
        //      System.out.println("class is not private or public!!");
        if(data.extendedClass.contains(","))
            System.out.print("cant have multi inheritances in java!");
        else
            engineCode=engineCode+("extends " +  accessStr +data.extendedClass)+"\n";
    }
    @Override
    public void writeEndBlock() {
        engineCode=engineCode+("}")+"\n";
    }

    @Override
    public void writeEndOfCalss() {

    }
    @Override
    public void writeStartBlock() {
        engineCode=engineCode+("{")+"\n";
    }
    @Override
    public void staticVirtualInit() {
        writeEnd();
    }
    @Override
    public void writeEnd() {
        engineCode=engineCode+(";")+'\n';
        //System.out.println();
    }
    @Override
    public void writeEndOfFunction() {
    }
    @Override
    public void writeTypeOfInput(String type) {
        engineCode=engineCode+(type);
    }
    @Override
    public void writeEqual() {
        engineCode=engineCode+("=");
    }
    @Override
    public void writefuncPrefix(storeData.Function data) {
        writeAccess(data);
        engineCode=engineCode+(data.returnStr+" ");
    }
    @Override
    public void writeReturn(Object value,String str) {
        if(str!="void"){
            engineCode=engineCode+("return "+value);
            writeEnd();
        }
    }
    @Override
    public void writeStaticAttrInitialization(storeData.Attribute staticAttr, storeData.Class data) {
    }
    @Override
    public void writeStaticAttr(storeData.Attribute attr) {
        attributeG(attr);
    }
    @Override
    public void writeAccess(storeData.generalType data) {
        if(data.isPublic)
            engineCode=engineCode+(" public ");
        else if(data.isPrivate)
            engineCode=engineCode+(" private ");
        else if(data.isProtected)
            engineCode=engineCode+("protected ");
        if(data.isStatic==true)
            engineCode=engineCode+(" static ");
        if(data.isAbstract)
            engineCode=engineCode+(" abstract ");
    }
}