package com.zoe.utilslib.ICSystem;

import org.litepal.LitePal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  程序内部通讯系统.
 *  注意事项:
 *      1.一定要及时取消注册.
 *      2.需要在LitePal中加入MappingClass.
 */
public class ICS {

    private static Map<String,ICSReceiver> receiverMap=new HashMap<>();

    public static void register(String id,ICSReceiver receiver){
        if(receiverMap.get(id) != null){
            //id已被注册
            throw new IllegalArgumentException("id:"+id+",已被注册！");
        }
        receiverMap.put(id,receiver);
        //检测该ID是否有未读消息
        List<ICI> iciList= LitePal.where("to=?",id).find(ICI.class);
        if(iciList.size()!=0){
            for(int i=0;i<iciList.size();++i){
                ICI ici=iciList.get(i);
                receiver.onReceive(ici.getMessage());
                ici.delete();
            }
        }
    }

    public static void unregister(String id){
        receiverMap.remove(id);
    }

    public static void send(String toId,String msg){
        ICSReceiver receiver=receiverMap.get(toId);
        if(receiver == null){
            return;
        }
        receiver.onReceive(msg);
    }

    public static void superSend(String toId,String msg){
        ICSReceiver receiver=receiverMap.get(toId);
        if(receiver == null){
            ICI ici=new ICI();
            ici.setTo(toId);
            ici.setMessage(msg);
            ici.save();
            return;
        }
        receiver.onReceive(msg);
    }

    public interface ICSReceiver{

        void onReceive(String msg);
    }

}
