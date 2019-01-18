package com.example;

import java.text.ParseException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.pojo.User;
import com.sobte.cqp.jcq.entity.*;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import com.util.ToInterface;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Timer;

/**
 * 本文件是JCQ插件的主类<br>
 * <br>
 * <p>
 * 注意修改json中的class来加载主类，如不设置则利用appid加载，最后一个单词自动大写查找<br>
 * 例：appid(com.example.demo) 则加载类 com.example.Demo<br>
 * 文档地址： https://gitee.com/Sobte/JCQ-CoolQ <br>
 * 帖子：https://cqp.cc/t/37318 <br>
 * 辅助开发变量: {@link JcqAppAbstract#CQ CQ}({@link com.sobte.cqp.jcq.entity.CoolQ 酷Q核心操作类}),
 * {@link JcqAppAbstract#CC CC}({@link com.sobte.cqp.jcq.message.CQCode 酷Q码操作类}),
 * 具体功能可以查看文档
 */
public class Demo extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    /**
     * 用main方法调试可以最大化的加快开发效率，检测和定位错误位置<br/>
     * 以下就是使用Main方法进行测试的一个简易案例
     *
     * @param args 系统参数
     */

    public static void main(String[] args) throws Exception {

        /*ToInterface ti=new ToInterface();

        String str=ti.interfaceUtil("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=18856968369","");
        System.out.println(str);
        JSONObject object = JSONObject.parseObject(str);
        System.out.println(object.get("telString"));*/
        // CQ此变量为特殊变量，在JCQ启动时实例化赋值给每个插件，而在测试中可以用CQDebug类来代替他
        CQ = new CQDebug();//new CQDebug("应用目录","应用名称") 可以用此构造器初始化应用的目录
        CQ.logInfo("[JCQ] TEST Demo", "测试启动");// 现在就可以用CQ变量来执行任何想要的操作了
        // 要测试主类就先实例化一个主类对象
        Demo demo = new Demo();
        // 下面对主类进行各方法测试,按照JCQ运行过程，模拟实际情况
        demo.startup();// 程序运行开始 调用应用初始化方法
        demo.enable();// 程序初始化完成后，启用应用，让应用正常工作
        // 开始模拟发送消息
        // 模拟私聊消息
        // 开始模拟QQ用户发送消息，以下QQ全部编造，请勿添加
//        demo.privateMsg(0, 10001, 2234567819L, "时间", 0);
//        demo.privateMsg(0, 10002, 2222222224L, "喵呜喵呜喵呜", 0);
//        demo.privateMsg(0, 10003, 2111111334L, "可以给我你的微信吗", 0);
//        demo.privateMsg(0, 10004, 3111111114L, "今天天气真好", 0);
//        demo.privateMsg(0, 10005, 3333333334L, "你好坏，都不理我QAQ", 0);
        // 模拟群聊消息
        // 开始模拟群聊消息
        demo.groupMsg(0, 10006, 3456789012L, 3333333334L, "", "一言", 0);
        demo.groupMsg(0, 10008, 3456789012L, 11111111114L, "", "", 0);
        demo.groupMsg(0, 10009, 427984429L, 2387020215L, "", "所有", 0);
        demo.groupMsg(0, 10010, 427984429L, 3333333334L, "", "所有", 0);
        demo.groupMsg(0, 10011, 427984429L, 11111111114L, "", "qwq 有没有一起开的\n[CQ:at,qq=3333333334]你玩嘛", 0);
        // ......
        // 依次类推，可以根据实际情况修改参数，和方法测试效果
        // 以下是收尾触发函数
        // demo.disable();// 实际过程中程序结束不会触发disable，只有用户关闭了此插件才会触发
        demo.exit();// 最后程序运行结束，调用exit方法
    }

    /**
     * 打包后将不会调用 请不要在此事件中写其他代码
     *
     * @return 返回应用的ApiVer、Appid
     */
    public String appInfo() {
        // 应用AppID,规则见 http://d.cqp.me/Pro/开发/基础信息#appid
        String AppID = "com.example.demo";// 记住编译后的文件和json也要使用appid做文件名
        /**
         * 本函数【禁止】处理其他任何代码，以免发生异常情况。
         * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。
         */
        return CQAPIVER + "," + AppID;
    }

    /**
     * 酷Q启动 (Type=1001)<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 请在这里执行插件初始化代码。<br>
     * 请务必尽快返回本子程序，否则会卡住其他插件以及主程序的加载。
     *
     * @return 请固定返回0
     */
    private static SqlSessionFactory sessionFactory;
    private static SqlSession sqlSession;
    private int count;
    private int i;
    public int startup() {
        // 获取应用数据目录(无需储存数据时，请将此行注释)
        String appDirectory = CQ.getAppDirectory();
        // 返回如：D:\CoolQ\app\com.sobte.cqp.jcq\app\com.example.demo\
        // 应用的所有数据、配置【必须】存放于此目录，避免给用户带来困扰。
        sqlSession=getSqlSession();
         i=1;
            CQ.sendGroupMsg(930684981, "小可爱们，我来啦！");
        return 0;

    }

    /**
     * 酷Q退出 (Type=1002)<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 无论本应用是否被启用，本函数都会在酷Q退出前执行一次，请在这里执行插件关闭代码。
     *
     * @return 请固定返回0，返回后酷Q将很快关闭，请不要再通过线程等方式执行其他代码。
     */
    public int exit() {
        CQ.sendGroupMsg(930684981, "小可爱们，我走了！");

        return 0;
    }

    /**
     * 应用已被启用 (Type=1003)<br>
     * 当应用被启用后，将收到此事件。<br>
     * 如果酷Q载入时应用已被启用，则在 {@link #startup startup}(Type=1001,酷Q启动) 被调用后，本函数也将被调用一次。<br>
     * 如非必要，不建议在这里加载窗口。
     *
     * @return 请固定返回0。
     */
    public int enable() {
        enable = true;
        return 0;
    }

    /**
     * 应用将被停用 (Type=1004)<br>
     * 当应用被停用前，将收到此事件。<br>
     * 如果酷Q载入时应用已被停用，则本函数【不会】被调用。<br>
     * 无论本应用是否被启用，酷Q关闭前本函数都【不会】被调用。
     *
     * @return 请固定返回0。
     */
    public int disable() {
        enable = false;
        return 0;
    }

    /**
     * 私聊消息 (Type=21)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType 子类型，11/来自好友 1/来自在线状态 2/来自群 3/来自讨论组
     * @param msgId   消息ID
     * @param fromQQ  来源QQ
     * @param msg     消息内容
     * @param font    字体
     * @return 返回值*不能*直接返回文本 如果要回复消息，请调用api发送<br>
     * 这里 返回  {@link IMsg#MSG_INTERCEPT MSG_INTERCEPT} - 截断本条消息，不再继续处理<br>
     * 注意：应用优先级设置为"最高"(10000)时，不得使用本返回值<br>
     * 如果不回复消息，交由之后的应用/过滤器处理，这里 返回  {@link IMsg#MSG_IGNORE MSG_IGNORE} - 忽略本条消息
     */
    public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
        // 这里处理消息
        CQ.sendPrivateMsg(fromQQ, "你发送了这样的消息：" + msg + "\n来自Java插件");
        return MSG_IGNORE;
    }

    /**
     * 群消息 (Type=2)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType       子类型，目前固定为1
     * @param msgId         消息ID
     * @param fromGroup     来源群号
     * @param fromQQ        来源QQ号
     * @param fromAnonymous 来源匿名者
     * @param msg           消息内容
     * @param font          字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */

    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg,
                        int font) {
        /*获取mybaties*/
        sqlSession=getSqlSession();
        i=1;
        User u=new User();
        /*设置qq*/
        u.setQq(fromQQ);
        /*设置时间*/
        u.setPeriod_id(getTimeString());
        /*设置星期几*/
        u.setWeek(getWeek());
        count=sqlSession.selectOne("selectByqqAndPeriod_id",u);
        // 如果消息来自匿名者
        if (fromQQ == 80000000L && !fromAnonymous.equals("")) {
            // 将匿名用户信息放到 anonymous 变量中
            Anonymous anonymous = CQ.getAnonymous(fromAnonymous);
        }

        // 解析CQ码案例 如：[CQ:at,qq=100000]
        // 解析CQ码 常用变量为 CC(CQCode) 此变量专为CQ码这种特定格式做了解析和封装
        // CC.analysis();// 此方法将CQ码解析为可直接读取的对象
        // 解析消息中的QQID
        //long qqId = CC.getAt(msg);// 此方法为简便方法，获取第一个CQ:at里的QQ号，错误时为：-1000
        //List<Long> qqIds = CC.getAts(msg); // 此方法为获取消息中所有的CQ码对象，错误时返回 已解析的数据
        // 解析消息中的图片
        //CQImage image = CC.getCQImage(msg);// 此方法为简便方法，获取第一个CQ:image里的图片数据，错误时打印异常到控制台，返回 null
        //List<CQImage> images = CC.getCQImages(msg);// 此方法为获取消息中所有的CQ图片数据，错误时打印异常到控制台，返回 已解析的数据

        // 这里处理消息
        if("专属菜单".equals(msg) ||"车队菜单".equals(msg)){
            CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                    "\n*****   菜单  *****" +
                    "\n**1.当前时间     **" +
                    "\n**2.车队赛请假   **" +
                    "\n**3.请假信息     **" +
                    "\n**4.取消请假     **" +
                    "\n**5.所有请假信息 **" +
                    "\n**（群主专用）   **" +
                    "\n人来人往、聚在这个车队不容易，珍惜网络一段缘。");
        }else if("当前时间".equals(msg)||"时间".equals(msg)){
            String str = getTimeStringAll();
            int day= Integer.parseInt(getTimeStringDay());
            if(4<=day&&day<11){
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n现在是：\n" + str +
                        "\n上午好:\n"+one(fromGroup,fromQQ));
            }else if(11<=day&&day<=13){
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n现在是：\n" + str +
                        "\n中午好：\n"+one(fromGroup,fromQQ));
            }else if(13<day&&day<18){
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n现在是：\n" + str +
                        "\n小可爱，下午啦：\n"+one(fromGroup,fromQQ));
            }else if(18<=day&&day<=22){
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n现在是：\n" + str +
                        "\n亲爱的，晚上好：\n"+one(fromGroup,fromQQ));
            }else{
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n现在是：\n" + str +
                        "\n哇，深夜了，快睡吧，身体最重要：\n"+one(fromGroup,fromQQ));
            }

        }else if("车队赛请假".equals(msg)||"请假".equals(msg)||msg.contains("请假")&& !"请假信息".equals(msg)&& !"取消请假".equals(msg)){
            if (getWeek().equals("星期六")||getWeek().equals("星期日")){
                          /*
                          * 查看今天是否已经请假
                          * */
                            if (count==0){
                                /*今天没请假*/
                                sqlSession.insert("inPeriod",u);
                                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                                        "\n你请假成功，请假时间：" + getWeek() +",本"+getWeek()+"，你可以不用参加车队赛了，" +
                                        "有时间一定要打哦"+
                                        "\n俩个星期内只能请假俩次哦");
                            }else{
                                /*今天已经请假*/
                                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                                        "\n小傻瓜、你今天已经请假了哦!!!"  +
                                        "\n俩个星期内只能请假俩次哦");
                        }
            }else{
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n车队赛只能在周六周日请假，今天是" + getWeek() +
                        "\n俩个星期内只能请假俩次哦");
            }
        }else if("请假信息".equals(msg)){
            String str="";
            List<User> list=  sqlSession.selectList("selectByqq", u);
            for(User u1:list){
                str+="qq:"+u1.getQq()+"请假时间："+u1.getPeriod_id()+"-"+u1.getWeek()+"\n";
                System.out.println();
            }
            CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                    "\n请假信息:\n" +str +
                    "\n俩个星期内只能请假俩次哦");
            /*
             * 增加取消请假功能
             * */
        }else if("取消请假".equals(msg)){
            if (count==1) {
                sqlSession.delete("deleteByqqAndPeriod_id", u);
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n你已经取消了今天的请假，晚上8点准时参加车队赛哦！！！" +
                        "\n俩个星期内只能请假俩次哦");
            }else {
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n你今天还没有请假，小糊涂。" +
                        "\n俩个星期内只能请假俩次哦");
            }
        }else if("所有".equals(msg)&&2387020215L==fromQQ){
            //过去七天

            String str="";
            List<User> list=  sqlSession.selectList("selectAll", u);
            for(User u1:list){
                str+=CC.at(u1.getQq())+"  次数："+u1.getCount()+"\n请假时间："+u1.getPeriod_id()+"-"+u1.getWeek()+"\n";
                System.out.println();
            }
            CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                    "\n俩个星期内所有请假信息:\n" +str +
                    "\n俩个星期内只能请假俩次哦");
        }else if("定时任务".equals(msg)){
            if((getWeek().equals("星期六")||getWeek().equals("星期日"))&&(getTimeStringDay().equals(19))) {
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                        "\n19.58进房打车队赛啦，不要压着8点的时间进。请假的直接在群里发请假2字就好。\n" +one(fromGroup,fromQQ)
                        );
            }else{
                one(fromGroup, fromQQ);
            }
        }else if("一言".equals(msg)){
            one(fromGroup, fromQQ);
        }else if ("github".equals(msg)){
            CQ.sendGroupMsg(fromGroup,"");
        }
      /*  else if("定时".equals(msg)){
            i++;
            System.out.println("定时"+i);
        }else if(getWeek().equals("星期六")||getWeek().equals("星期日")){
            System.out.println(getTimeStringAll());
        }*/


        /*
        * 增加统计已经连续请假俩次功能
        * */


        /*
        * 1.生成人物
        * 2.等级：
        *       1.1-10级10,20,40,80,160,320,640,1000,1500,2500
        *       2.11-20级5000,7000,9000,11000,13000,15000+2000
        *       3.+3000.。。。
        * 3.竞速:
        *       1.D车一个图180秒 初级驾照 一局100经验 10点券
        *       2.C车一个图160秒 中级驾照 一局150经验 20点券
        *       3.B车一个图140秒 高级驾照 一局300经验 50点券
        *       4.A车一个图100秒 职业驾照 一局600经验 100点券
        * 4.技巧：
        *       1.学会飘移、cw、wc减1秒  发动一次
        *       2.学会cww、wcw减2秒      发动一次
        * 5.宝石：
        *       1、2、3、4级
        * 6.休闲区
        *
        * */
        /*if("飞车".equals(msg)){
            CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                    "\n生成角色 ：创建角色-男\\女-昵称" +
                    "\n休闲区：进入休闲区、挂机、停止挂机、钓鱼、提竿");
        }*/
//        try {
//            Thread.currentThread().sleep(1000 * 60 * 60 * 24);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        mt.timer4();
//        sqlSession.clearCache();
        sqlSession.commit();
//        sqlSession.close();
       /* t.cancel();*/
       /* try {
            myThread.circle(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return MSG_IGNORE;


    }

    private String one(long fromGroup, long fromQQ) {
        String urlString2 = "https://v1.hitokoto.cn/?encode=json";
        ToInterface hcu = new ToInterface();
        JSONObject my = null;
        String str="";
        try {
            my = (JSONObject) JSONObject.parse(hcu.doGet(urlString2).toString());
            str="\n" + my.get("hitokoto") ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) +
                "\n" + my.get("hitokoto") + "\n来源：" + my.get("from")+"\n当前时间："+getTimeStringAll());
        return str;
    }

    public SqlSession getSqlSession() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            //通过配置信息来构建一个SqlSessionFactory
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWeek() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    private String getTimeString() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public String getTimeStringAll() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return sdf.format(date);
    }

    public String getTimeStringDay() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(date);
    }
    public String getTimeStringMin() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return sdf.format(date);
    }
    /**
     * 讨论组消息 (Type=4)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype     子类型，目前固定为1
     * @param msgId       消息ID
     * @param fromDiscuss 来源讨论组
     * @param fromQQ      来源QQ号
     * @param msg         消息内容
     * @param font        字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群文件上传事件 (Type=11)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType   子类型，目前固定为1
     * @param sendTime  发送时间(时间戳)// 10位时间戳
     * @param fromGroup 来源群号
     * @param fromQQ    来源QQ号
     * @param file      上传文件信息
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
        GroupFile groupFile = CQ.getGroupFile(file);
        if (groupFile == null) { // 解析群文件信息，如果失败直接忽略该消息
            return MSG_IGNORE;
        }
        // 这里处理消息
        return MSG_IGNORE;
    }

    /**
     * 群事件-管理员变动 (Type=101)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/被取消管理员 2/被设置管理员
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群事件-群成员减少 (Type=102)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/群员离开 2/群员被踢
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(仅子类型为2时存在)
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群事件-群成员增加 (Type=103)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/管理员已同意 2/管理员邀请
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(即管理员QQ)
     * @param beingOperateQQ 被操作QQ(即加群的QQ)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 好友事件-好友已添加 (Type=201)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype  子类型，目前固定为1
     * @param sendTime 发送时间(时间戳)
     * @param fromQQ   来源QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int friendAdd(int subtype, int sendTime, long fromQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 请求-好友添加 (Type=301)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，目前固定为1
     * @param sendTime     发送时间(时间戳)
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
        // 这里处理消息

        /**
         * REQUEST_ADOPT 通过
         * REQUEST_REFUSE 拒绝
         */

        // CQ.setFriendAddRequest(responseFlag, REQUEST_ADOPT, null); // 同意好友添加请求
        return MSG_IGNORE;
    }

    /**
     * 请求-群添加 (Type=302)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，1/他人申请入群 2/自己(即登录号)受邀入群
     * @param sendTime     发送时间(时间戳)
     * @param fromGroup    来源群号
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg,
                               String responseFlag) {
        // 这里处理消息

        /**
         * REQUEST_ADOPT 通过
         * REQUEST_REFUSE 拒绝
         * REQUEST_GROUP_ADD 群添加
         * REQUEST_GROUP_INVITE 群邀请
         */
		/*if(subtype == 1){ // 本号为群管理，判断是否为他人申请入群
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_ADD, REQUEST_ADOPT, null);// 同意入群
		}
		if(subtype == 2){
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_INVITE, REQUEST_ADOPT, null);// 同意进受邀群
		}*/

        return MSG_IGNORE;
    }

    /**
     * 本函数会在JCQ【线程】中被调用。
     *
     * @return 固定返回0
     */
    public int menuA() {
        JOptionPane.showMessageDialog(null, "这是测试菜单A，可以在这里加载窗口");
        return 0;
    }

    /**
     * 本函数会在酷Q【线程】中被调用。
     *
     * @return 固定返回0
     */
    public int menuB() {
        JOptionPane.showMessageDialog(null, "这是测试菜单B，可以在这里加载窗口");
        return 0;
    }


}
