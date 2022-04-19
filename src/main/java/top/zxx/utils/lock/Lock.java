package top.zxx.utils.lock;

public interface Lock {

    /**
     * try lock, if success return true
     *
     * @param key
     * @param owner
     * @return
     */
    boolean tryLock(String key, String owner);

    /**
     * try lock with timeout
     * @param key
     * @param owner
     * @param timeout
     * @return
     */
    boolean tryLock(String key, String owner, Integer timeout);


    /**
     * unlock
     *
     * @param key
     * @param owner
     * @return
     */
    boolean unLock(String key, String owner);
}
