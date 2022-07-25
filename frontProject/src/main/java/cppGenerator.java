public class cppGenerator extends generator{
    //constractor and distractor
    @Override
    public void writeExtendClass(storeData.Class data) {
        String accessStr = "";
        int index = 0;
        int len=data.extendedClasses.size();
        //System.out.println("jejew");
        while (index < len) {
            if(index==0)
                engineCode=engineCode+(":");
            accessStr = "";
            if (data.isPrivate)
                accessStr = " private ";
            else if (data.isPublic)
                accessStr = " public ";
            else
                engineCode=engineCode+("class is not private or public!!")+'\n';

            engineCode=engineCode+(accessStr + data.extendedClasses.get(index++));
            if(len!=1 && index!=len)
                engineCode=engineCode+(",");
        }

        //    for(storeData.Class ittem:data.extendedClasses ){}
    }
    @Override
    public void writeEndBlock() {
        engineCode=engineCode+("}");
    }

    @Override
    public void writeEndOfCalss() {
        writeEnd();
    }

    @Override
    public void writeStartBlock() {
        engineCode=engineCode+("{")+'\n';
    }
    @Override
    public void staticVirtualInit() {
        engineCode=engineCode+("= 0");
        writeEnd();
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
    public void writeTypeOfInput(String type) {
        engineCode=engineCode+(type);
    }
    @Override
    public void writeEnd() {
        engineCode=engineCode+(";")+'\n';
    }
    @Override
    public void writeEndOfFunction() {
        writeEnd();
    }
    @Override
    public void writeStaticAttrInitialization(storeData.Attribute attr,storeData.Class data) {
        engineCode=engineCode+(attr.type+data.name+"::"+attr.name+"="+attr.staticValue);
        writeEnd();
    }
    @Override
    public void writeReturn(Object value,String str) {
        if(str!="void" &&str!="" && str!=" " && str!="~"&&str!="~ " && value!=null){
            engineCode=engineCode+("return "+ value);
            writeEnd();
        }
    }
    @Override
    public void writeAccess(storeData.generalType data) {
        if(data.isPublic)
            engineCode=engineCode+(" public ");
        else if(data.isPrivate)
            engineCode=engineCode+(" private ");
        else if(data.isProtected)
            engineCode=engineCode+(" protected ");
        if(data.isStatic==true)
            engineCode=engineCode+(" static ");
        if(data.isAbstract)
            engineCode=engineCode+(" virtual ");
    }
    @Override
    public void writeStaticAttr(storeData.Attribute attr) {
        engineCode=engineCode+(attr.type + " "+ attr.name)+'\n';
        writeEnd();
    }
}
