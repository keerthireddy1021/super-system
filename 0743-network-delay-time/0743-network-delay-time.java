import java.util.*;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, k});
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int time = current[0];
            int node = current[1];
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];
                    if (dist[nextNode] > time + weight) {
                        dist[nextNode] = time + weight;
                        heap.offer(new int[]{dist[nextNode], nextNode});
                    }
                }
            }
        }
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1; // Node not reachable
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }
}
