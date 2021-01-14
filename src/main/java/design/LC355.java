package design;

import java.util.*;

public class LC355 {
    /**
     * The global timestamp is to uniquely mark each message with its creation time
     * following:   key:user    value:The other users that user is following
     * sent:        key:user    value:All the messages the user sent
     */
    private long timestamp;
    Map<Integer, Set<Integer>> following;
    Map<Integer, LinkedList<Message>> sent;

    /**
     * Initialize your data structure here.
     */
    public LC355() {
        following = new HashMap<>();
        sent = new HashMap<>();
        timestamp = 0;
    }

    /**
     * 1. Compose a new tweet.
     * 2. Get the list for all sent message.
     * 3. Push to the list
     */
    public void postTweet(int userId, int tweetId) {
        Message message = new Message(tweetId, timestamp++);
        LinkedList<Message> userSent = sent.get(userId);
        if (userSent == null) {
            userSent = new LinkedList<>();
            sent.put(userId, userSent);
        }
        userSent.addFirst(message);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     * 1. Use PQ to compare messages
     * 2. Process the message that user had sent
     * 3. Process the message that all the followers had sent
     * 4. Get top10 from pq
     */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Message> pq = new PriorityQueue<>((o1, o2) -> (int) o2.timestamp - (int) o1.timestamp);
        LinkedList<Message> selfFeed = sent.get(userId);
        if (selfFeed != null) {
            for (Message message : selfFeed) {
                pq.offer(message);
            }
        }
        Set<Integer> set = following.get(userId);
        if (set != null) {
            for (int i : set) {
                LinkedList<Message> userFeed = sent.get(i);
                if (userFeed != null) {
                    for (Message message : userFeed) {
                        pq.offer(message);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (pq.size() > 0 && res.size() < 10) {
            res.add(pq.poll().tweetId);
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followeeIds = following.get(followerId);
        if (followeeIds == null) {
            Set<Integer> set = new HashSet<>();
            set.add(followeeId);
            following.put(followerId, set);
        } else {
            followeeIds.add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followeeIds = following.get(followerId);
        if (followeeIds == null) {
            following.put(followerId, new HashSet<>());
        } else {
            followeeIds.remove(followeeId);
        }
    }
}

class Message {
    public int tweetId;
    public long timestamp;

    Message(int tweetId, long timestamp) {
        this.tweetId = tweetId;
        this.timestamp = timestamp;
    }
}
