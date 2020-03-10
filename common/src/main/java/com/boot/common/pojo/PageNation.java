package com.boot.common.pojo;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/10 10:08
 * </pre>
 */
public class PageNation {
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
