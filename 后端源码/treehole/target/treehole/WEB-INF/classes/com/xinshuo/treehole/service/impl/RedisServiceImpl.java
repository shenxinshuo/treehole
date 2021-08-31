package com.xinshuo.treehole.service.impl;

import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.UserLike;
import com.xinshuo.treehole.service.RedisService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveLiked2Redis(Integer likeUserID, Integer likePostID, Integer likeAnswerID) {
        String key = RedisKeyUtils.getLikedKey(likeUserID, likePostID, likeAnswerID);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, Constant.REDIS_LIKE_CODE);
    }

    @Override
    public void unlikeFromRedis(Integer likeUserID, Integer likePostID, Integer likeAnswerID) {
        String key = RedisKeyUtils.getLikedKey(likeUserID, likePostID, likeAnswerID);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, Constant.REDIS_UNLIKE_CODE);
    }

    @Override
    public void deleteLikedFromRedis(Integer likeUserID, Integer likePostID, Integer likeAnswerID) {
        String key = RedisKeyUtils.getLikedKey(likeUserID, likePostID, likeAnswerID);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public List<UserLike> getLikeDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            String key = (String) map.getKey();
            //分离出likeUserID,likePostID,likeAnswerID
            String[] split = key.split("::");
            Integer likeUserID = Integer.parseInt(split[0]);
            Integer likePostID = Integer.parseInt(split[1]);
            Integer likeAnswerID = Integer.parseInt(split[2]);
            Integer value = (Integer) map.getValue();
            //组装成userLike对象
            UserLike userLike = new UserLike(likeUserID, likePostID, likeAnswerID, value);
            list.add(userLike);
            //存到list后从redis中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }
        return list;
    }

    @Override
    public List<UserLike> getLikeDataFromRedisButNoDelete() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            String key = (String) map.getKey();
            //分离出likeUserID,likePostID,likeAnswerID
            String[] split = key.split("::");
            Integer likeUserID = Integer.parseInt(split[0]);
            Integer likePostID = Integer.parseInt(split[1]);
            Integer likeAnswerID = Integer.parseInt(split[2]);
            Integer value = (Integer) map.getValue();
            //组装成userLike对象
            UserLike userLike = new UserLike(likeUserID, likePostID, likeAnswerID, value);
            list.add(userLike);
        }
        return list;
    }

    @Override
    public int getLikeCountOfQuestionFromRedis(int qid) {
        List<UserLike> list = this.getLikeDataFromRedisButNoDelete();
        int count = 0;
        for (UserLike userLike : list) {
            if (userLike.getLikePostID() == qid && userLike.getStatus() == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getLikeCountOfAnswerFromRedis(int aid) {
        List<UserLike> list = this.getLikeDataFromRedisButNoDelete();
        int count = 0;
        for (UserLike userLike : list) {
            if (userLike.getLikeAnswerID() == aid && userLike.getStatus() == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void addLookCount(int qid) {
        //存放浏览量的key就叫lookCount
        Boolean hasKey = redisTemplate.opsForHash().hasKey(RedisKeyUtils.MAP_KEY_LOOKCOUNT, RedisKeyUtils.getMapHashKeyLookcount(qid));
        if (hasKey) {
            //存在key，加1
            redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_LOOKCOUNT, RedisKeyUtils.getMapHashKeyLookcount(qid), 1L);
        } else {
            //不存在，创建
            String hashKey = RedisKeyUtils.getMapHashKeyLookcount(qid);
            String value = String.valueOf(1L);
            redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_LOOKCOUNT, hashKey, value);
        }
    }

    @Override
    public List<LookCount> getLookCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_LOOKCOUNT, ScanOptions.NONE);
        List<LookCount> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            Integer key = Integer.parseInt((String) map.getKey());
            Long value = Long.parseLong((String) map.getValue());
            list.add(new LookCount(key, value));
            String hashKey = RedisKeyUtils.getMapHashKeyLookcount(key);

            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_LOOKCOUNT, hashKey);
        }
        return list;
    }

    @Override
    public List<LookCount> getLookCountFromRedisButNotDelete() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_LOOKCOUNT, ScanOptions.NONE);
        List<LookCount> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            Integer key = Integer.parseInt((String) map.getKey());
            Long value = Long.parseLong((String) map.getValue());
            list.add(new LookCount(key, value));
        }
        return list;
    }

    @Override
    public Long getLookCountOfQestion(int qid) {
        Object value = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_LOOKCOUNT, RedisKeyUtils.getMapHashKeyLookcount(qid));
        Long count = 0L;
        if (value != null) {
            count = Long.parseLong((String) value);
        }
        return count;
    }
}
