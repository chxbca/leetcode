package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/time-based-key-value-store/submissions/
 */
class TimeMap {

    private Map<String, Queue<Entry>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
    }

    public void set(String key, String value, int timestamp) {
        Entry entry = new Entry(key, value, timestamp);
        Queue<Entry> queue = map.computeIfAbsent(key, (e) -> new PriorityQueue<>());
        queue.add(entry);
    }

    public String get(String key, int timestamp) {
        Queue<Entry> entryQueue = map.get(key);
        if (entryQueue == null) {
            return "";
        }
        for (Entry entry : entryQueue) {
            if (entry.timestamp == timestamp) {
                return entry.value;
            }
            if (entry.timestamp < timestamp) {
                return entry.value;
            }
        }
        return "";
    }

    private static class Entry implements Comparable<Entry> {
        String key;
        String value;
        int timestamp;

        public Entry(String key, String value, int timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }


        @Override
        public int compareTo(Entry o) {
            return Integer.compare(o.timestamp, timestamp);
        }
    }

}