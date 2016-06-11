package com.aktheknight.solarvillageplus.util;

/**
 * Created by alex on 11/06/16.
 */
public enum PanelTier {
    tier1(50000, 32),
    tier2(100000, 64),
    tier3(200000, 128),
    tier4(500000, 256),
    tier5(1000000, 512),
    tier6(5000000, 1024);

    private long capacity;
    private int gen;

    PanelTier(long capacity, int gen) {
        this.capacity = capacity;
        this.gen = gen;
    }

    public long getCapacity() {
        return capacity;
    }

    public int getGen() {
        return gen;
    }
}
