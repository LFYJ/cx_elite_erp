package com.erp.config;

import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author 791202.com
 * @date 2020/5/10
 */
@Component
public class IdGenerator {

    @Autowired
    private CachedUidGenerator cachedUidGenerator;

    /**
     * 获取uid
     * @return
     */
    public long nextId() {
        return cachedUidGenerator.getUID();
    }

    /**
     * 格式化传入的uid，方便查看其实际含义
     * @param uid
     * @return
     */
    public String parse(long uid) {
        return cachedUidGenerator.parseUID(uid);
    }
}