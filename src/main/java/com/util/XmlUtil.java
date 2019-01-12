/*
package com.util;
import javax.xml.parsers.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;


import com.pojo.User;
import org.w3c.dom.*;



public class XmlUtil{

*
     * 使用dom技术对xml进行解析
     * @param args
     * 从这里我发现： Node 是 Element, document的父类， Element类主要是
     * 增加，删除，修改，返回 等。document 创建 xml中的对象
     * 例：document.getElementById();方法。


    public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    //创建一个documentBuilderFactory实例
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
    //创建一个documentBuilder
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document = db.newDocument();

       document = db.parse("src/123.xml");
        //list(document);
         //red(document);
        User u=new User();
        u.setQq("111");
        addUser(document,u);
        red(document,"car");
        upda(document,"2387020215");


    }
    //修改
    public static void update(Document doc,String ele,User u){
        //修改元素内容
        //Element ele = (Element)doc.getElementsByTagName(element).item(0);
        // ele.setTextContent("xiaohai");
        //修改属性
        Element element = (Element)doc.getElementsByTagName(ele).item(0);
        element.setAttribute("sex", "nv");
    }

    //删除学生
    public static void del(Document doc){
        Node node = doc.getElementsByTagName("xuesheng").item(0);
        //node.getParentNode().removeChild(node);
        //删除属性
        Element ele = (Element)node;
        ele.removeAttribute("sex");
    }

    //添加用户信息到xml
    public static void addUser(Document doc, User u){
        //创建user元素
        Element user = doc.createElement("user");
        //添加属性
        user.setAttribute("qq", u.getQq());
        user.setAttribute("username", u.getUsernmae());
        user.setAttribute("jy",u.getJy()+"");
        user.setAttribute("lv",u.getLv()+"");
        //car
        Element car = doc.createElement("car");
        //添加属性
        car.setAttribute("qq", u.getQq()+"222");
        car.setAttribute("username", u.getUsernmae());
        car.setAttribute("jy",u.getJy()+"");
        car.setAttribute("lv",u.getLv()+"");
        //添加这个元素
        doc.getDocumentElement().appendChild(user);
        doc.getDocumentElement().appendChild(car);

    }
    //更新java在xml文件中操作的内容
    public static void upda(Document doc,String XmlName) throws Exception{
        //创建一个TransformerFactory实例
        TransformerFactory tff = TransformerFactory.newInstance();
    //通过TransformerFactory 得到一个转换器
        Transformer tf = tff.newTransformer();
    //通过Transformer类的方法 transform(Source xmlSource, Result outputTarget)
        tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        //将 XML Source 转换为 Result。
        tf.transform(new DOMSource(doc), new StreamResult("src/"+XmlName+".xml"));
    }

    //遍历xml文件的元素
    public static void list(Node node){
        if(node.getNodeType()==Node.ELEMENT_NODE)
            System.out.println(node.getNodeName());
        //得到该结点的子结点
        NodeList nodelist = node.getChildNodes();

        for(int i=0;i<nodelist.getLength();i++){
            Node n = (Node) nodelist.item(i);
            list(n);
        }
    }
    //获取document对象的 元素的 文本
    public static void red(Document docu,String ele){
        NodeList nodelist = docu.getElementsByTagName(ele);
        Element element = (Element)nodelist.item(0);
        System.out.println(element.getAttribute("qq"));
        System.out.println(element.getTextContent());


    }
}
*/
