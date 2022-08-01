//package edu.ln.tour.job;
//
//import edu.ln.tour.constants.RedisConstant;
//import edu.ln.tour.utils.QiniuUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//@Component
//@EnableScheduling
//public class ClearImgJob {
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
//    @Scheduled(fixedDelay = 10000)
//
//    public void clearImg(){
//        System.out.println("===========进入了删除图片的定时任务==========");
//        Set<String> imgSet = redisTemplate.opsForSet().difference(RedisConstant.ROUTE_IMG_ALL, RedisConstant.ROUTE_IMG_DB);
//        if (imgSet != null){
//            for (String pickName : imgSet) {
//                //删除七牛云服务器上无效的图片
//                QiniuUtils.deleteFileFromQiniu(pickName);
//                //删除redis集合中保存的图片名称
//                redisTemplate.opsForSet().remove(RedisConstant.ROUTE_IMG_ALL,pickName);
//                System.out.println("-------删除了无效图片-------：" + pickName);
//            }
//        }
//    }
//}
