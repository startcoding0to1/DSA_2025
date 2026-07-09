package patterns.a_arraysandhashing.medium;

import java.util.*;

public class Top_K_Frequent_Elements_347 {
    static class Solution {
        public int [] usingHashing(int[] nums, int k){
            Map<Integer, Integer> frequency = new HashMap<>();
            for(int i=0; i<nums.length; i++){
                frequency.put(nums[i],frequency.getOrDefault(nums[i],0)+1);
            }
            Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b)->a.getValue()-b.getValue());
//            Queue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
            for(Map.Entry<Integer, Integer> entry : frequency.entrySet()){
                pq.offer(entry);
//                pq.offer(new int[]{entry.getKey(), entry.getValue()});
                if(pq.size()>k){
                    pq.poll();
                }
            }
            int [] result = new int [k];
            for(int i=0; i<k; i++){
                result[i] = pq.poll().getKey();
//                result[i] = pq.poll()[0];
            }
            return result;
        }
        public int[] topKFrequent(int[] nums, int k) {
            return usingHashing(nums, k);
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Top K Frequent Elements: "+ Arrays.toString(s.usingHashing(new int[]{1,1,1,2,2,3}, 2)));
    }
}
