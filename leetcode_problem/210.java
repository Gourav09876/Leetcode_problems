
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Create adjacency list
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

        Queue<Integer> q = new LinkedList<>();

        // Add nodes with indegree 0
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        // BFS
        while (!q.isEmpty()) {
            int curr = q.poll();

            result[index++] = curr;

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // Cycle detection
        if (index != numCourses) {
            return new int[0];
        }

        return result;
    }
}
