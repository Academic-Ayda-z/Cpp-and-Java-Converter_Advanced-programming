import java.util.ArrayList;
import java.util.jar.Attributes;

public class storeData{

    public static abstract class generalType{
        String name;
        boolean isStatic;
        boolean isDefault;
        boolean isPublic;
        boolean isPrivate;
        boolean isProtected;
        boolean isAbstract;
        public  generalType(String Name,boolean isS,int access,boolean isAbs){
            isStatic=isS;
            isAbstract=isAbs;
            isPrivate=false;
            isProtected=false;
            isPublic=false;
            isDefault =false;
            name=Name;
            if(access==1)
                isPrivate=true;
            else if(access==2)
                isProtected=true;
            else if(access==3)
                isPublic=true;
            else if(access==0)
                isDefault =true;
            else{
                System.out.println("generalType constructor _ wrong accessibility");
            }
        }
    }

    public static class Class extends generalType{
        ArrayList<Attribute>attrs =new ArrayList<Attribute>();
        ArrayList<Function> functions=new ArrayList<Function>();
        ArrayList<Class> innerClasses=new ArrayList<Class>();
        ArrayList<String> extendedClasses =new ArrayList<String>();
        String extendedClass;
        public Class(String Name,boolean isS,int access ,String ExtendedClass,boolean isAbs){//for java
            super(Name,isS,access,isAbs);
            extendedClass=ExtendedClass;

        }
        public Class(String Name,boolean isS,int access ,ArrayList<String>ExtendedClasses,boolean isAbs){//for cpp
            super(Name,isS,access,isAbs);
            extendedClasses=ExtendedClasses;
        }



        public void addNoneVoidFunction(String ReturnStr,Object returnVal,ArrayList<Attribute> Inputs,String Name,boolean isS,int access,boolean isAbs){
            Function func =new Function(ReturnStr,Inputs,returnVal,Name,isS,access,isAbs);
            functions.add(func);
        }

        public void addVoidFunction(String ReturnStr,ArrayList<Attribute> Inputs,String Name,boolean isS,int access,boolean isAbs){
            Function func =new Function(ReturnStr,Inputs,Name,isS,access,isAbs);
            functions.add(func);
        }
        public void addVoidFunction(String ReturnStr,String InputsStr,String Name,boolean isS,int access,boolean isAbs){
            Function func =new Function(ReturnStr,InputsStr,Name,isS,access,isAbs);
            functions.add(func);
        }
        public void addFunction(storeData.Function func){
            functions.add(func);
        }

        public void addAttribute(Attribute attr){
            attrs.add(attr);
        }
        public void addInnerClass(Class innerClass){
            innerClasses.add(innerClass);
        }
        public void removeInnerClass(Class innerClass){
            innerClasses.remove(innerClass);
        }
        public void removeFunction(Function func){
            functions.remove(func);
        }
        public void removeAttribute(Attribute attr){
            attrs.remove(attr);
        }
    }

    public static class Function extends generalType{
        String returnStr;
        Object returnValue;
        ArrayList<Attribute> attrs;
        ArrayList<Attribute> inputs;
        String inputsStr;
        public Function(String ReturnStr,String Inputs,Object returnVal,String Name,boolean isS,int access,boolean isAbs){
            super(Name,isS,access,isAbs);
            returnStr=ReturnStr;
            returnValue=returnVal;
            inputsStr=Inputs;
        }
        public Function(String ReturnStr,String Inputs,String Name,boolean isS,int access,boolean isAbs){
            super(Name,isS,access,isAbs);
            returnStr=ReturnStr;
            inputsStr=Inputs;
        }
        public Function(String ReturnStr,ArrayList<Attribute> Inputs,Object returnVal,String Name,boolean isS,int access,boolean isAbs){
            super(Name,isS,access,isAbs);
            returnStr=ReturnStr;
            returnValue=returnVal;
            inputs=Inputs;
        }
        public Function(String ReturnStr,ArrayList<Attribute> Inputs,String Name,boolean isS,int access,boolean isAbs){
            super(Name,isS,access,isAbs);
            returnStr=ReturnStr;
            inputs=Inputs;
        }
        public void addAttribute(Attribute attr){
            //        int index=attrs.length-1;
            //          if(index==-1)
//                index+=1;
            attrs.add(attr);
        }
    }

    public static class Attribute extends generalType{
        Object staticValue;
        String type;
        public Attribute(String Type,String Name,boolean isS,int access,Object staticV){
            super(Name,isS,access,false);
            type=Type;
            staticValue=staticV;
        }
        public Attribute(String Type,String Name,boolean isS,int access){
            super(Name,isS,access,false);
            type=Type;
            if(isS==true)
                System.out.println("you cant make a static attribute without initial value!");

        }
    }

}
