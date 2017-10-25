package com.zk;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * @author zhangxin
 *         Created on 17/10/25.
 */
public class DeleteGroup extends ConnetctionWatcher {
    public void delete(String groupName) {
        String path = "/" + groupName;

        try {
            List<String> children = zk.getChildren(path, false);

            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);//版本号为-1，
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
