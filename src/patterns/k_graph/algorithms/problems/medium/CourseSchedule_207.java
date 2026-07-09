package patterns.k_graph.algorithms.problems.medium;


import java.util.*;


public class CourseSchedule_207 {

    static class UsingTopologicalSorting { //or kahn's algorithm
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> courseGraph = new ArrayList<>();
            int [] indegree = new int [numCourses];
            for(int i=0; i<numCourses; i++){
                courseGraph.add(new ArrayList<>());
            }

            for(int [] preReq : prerequisites){
                indegree[preReq[1]]++;
                courseGraph.get(preReq[0]).add(preReq[1]);
            }

            int finishCourses = 0;
            Queue<Integer> queue = new LinkedList<>();
            for(int i=0; i<indegree.length; i++){
                if(indegree[i] == 0){
                    queue.offer(i);
                }
            }
            while(!queue.isEmpty()){
                int course = queue.poll();
                finishCourses++;
                for(Integer preReq : courseGraph.get(course)){
                    indegree[preReq]--;
                    if(indegree[preReq] == 0){
                        queue.offer(preReq);
                    }
                }
            }

            return finishCourses == numCourses;

        }
    }

    static class UsingDFS {
        List<List<Integer>> courseGraph = new ArrayList<>(); //Path to complete the course before current course
        Set<Integer> visiting = new HashSet<>();

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            for (int i = 0; i < numCourses; i++) {
                courseGraph.add(new ArrayList<>());
            }

            for (int[] preReq : prerequisites) {
                courseGraph.get(preReq[0]).add(preReq[1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!dfs(i)) {
                    return false;
                }
            }
            return true;
        }

        public boolean dfs(int course) {
            if (visiting.contains(course)) { //If the cycle is detected in current course path then that couse impossible to complete
                return false;
            }
            if (courseGraph.get(course).isEmpty()) {
                return true;
            }
            visiting.add(course);
            for (Integer preReq : courseGraph.get(course)) {
                if (!dfs(preReq)) {
                    return false;
                }
            }
            visiting.remove(course);
            courseGraph.set(course, new ArrayList<>());
            return true;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // return new UsingDFS().canFinish(numCourses, prerequisites);
        return new UsingTopologicalSorting().canFinish(numCourses, prerequisites);
    }
    public static void main(String[] args) {
        CourseSchedule_207 obj = new CourseSchedule_207();
        boolean status = obj.canFinish(2, new int[][]{{1,0}});
        System.out.println("All courses can be completed: " + status);
        status = obj.canFinish(2, new int[][]{{1,0},{0,1}});
        System.out.println("All courses can be completed: " + status);
    }
}
