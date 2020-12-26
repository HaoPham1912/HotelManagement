package com.h2.hotelmangement.common.bean;

public class Paging {

    private long total;
    private long offset;
    private int limit = 25;

    /**
     * Initializes attributes of the {@code Paging}
     *
     * @param offset
     * @param limit
     * @param total
     */
    public Paging(long offset, int limit, long total) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

