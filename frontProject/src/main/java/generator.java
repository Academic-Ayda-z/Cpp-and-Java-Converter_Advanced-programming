import java.util.ArrayList;

public abstract class generator {
    public static int isPossible=1;
    public String engineCode=" ";
    public abstract void writeExtendClass(storeData.Class data);
    public abstract void writeEndBlock();
    public abstract void writeEndOfCalss();
    public abstract void writeStartBlock();
    public abstract void staticVirtualInit();
    public abstract void writeEnd();
    public abstract void writeEndOfFunction();
    public abstract void writeTypeOfInput(String type);
    public abstract void writeEqual();
    public abstract void writefuncPrefix(storeData.Function data);
    public abstract void writeReturn(Object value,String str);
    public abstract void writeStaticAttrInitialization(storeData.Attribute staticAttr,storeData.Class data);
    public abstract void writeStaticAttr(storeData.Attribute attr);
    public abstract void writeAccess(storeData.generalType data);
    public void functionG(storeData.Function data){
        writefuncPrefix(data);
        engineCode=engineCode+(data.name+"(");
        if(data!=null && data.inputs!=null)
            for(storeData.Attribute item : data.inputs){
                writeTypeOfInput(data.inputs.get(0).type);
                engineCode=engineCode+( " "+ item.name);
                writeEnd();
            }
        if(data.inputsStr!=null){
            engineCode=engineCode+data.inputsStr;

        }        engineCode=engineCode+(")");
        if(data.isStatic==true)
        {
            staticVirtualInit();
            if(data.attrs!=null)
                System.out.print("abstract or virtual function cant have a body!!");
        }
        else {
            writeStartBlock();
            if(data.attrs!=null)
                for(storeData.Attribute attr: data.attrs){
                    attributeG(attr);
                    writeEnd();
                }
            // f(data.returnStr!="void")i
            writeReturn(data.returnValue,data.returnStr);
            writeEndBlock();
        }
    }
    public void attributeG(storeData.Attribute data) {
        writeAccess(data);
        engineCode=engineCode+(data.type+" " + data.name);
        if (data.isStatic){
            writeEqual();
            engineCode=engineCode+( data.staticValue);

        }
        writeEnd();
    }
    public void classG(storeData.Class data){
        ArrayList<storeData.Attribute> staticAttrs = new ArrayList< storeData.Attribute>();
        int index=0;
        writeAccess(data);
        engineCode=engineCode+("class "+data.name+" ");
        if(data.extendedClass!=null && data.extendedClass!="")//null
            writeExtendClass(data);
        writeStartBlock();
        if(data.innerClasses!=null)
            for(storeData.Class innerClass: data.innerClasses){
                classG(innerClass);
               // writeStartBlock();
               // writeEndBlock();
                writeEndOfCalss();
            }
        if(data.attrs!=null)
            for(storeData.Attribute attr: data.attrs){
                if(data.isStatic) {
                    writeStaticAttr(attr);
                    staticAttrs.set(index, attr);
                }
                else
                    attributeG(attr);
                writeEnd();
            }
        if(data.functions!=null)
            for(storeData.Function func: data.functions){
                functionG(func);
                writeEndOfFunction();
            }
        while(index>0)
            writeStaticAttrInitialization(staticAttrs.get(index--),data);
        writeEndBlock();
    }
}
