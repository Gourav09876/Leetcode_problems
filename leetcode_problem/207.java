
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // Build graph
        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];

            adj.get(b).add(a);
            indegree[a]++;
        }

        // Add courses with indegree 0
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;

        // BFS
        while (!q.isEmpty()) {
            int curr = q.poll();
            count++;

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
