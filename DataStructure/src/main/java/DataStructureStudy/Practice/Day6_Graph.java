package DataStructureStudy.Practice;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Day6_Graph {

    /*
     * 实现有向图、无向图、有权图、无权图的邻接矩阵和邻接表表示方法
     * 深度优先搜索 广度优先搜索
     * 迪杰斯特拉 a*  todo
     * 最短路径 kahn dfs
     */

    //有向有权图 邻接表表示
    class Graph {
        private LinkedList<Edge> adj[];//邻接表
        private int v;//顶点个数

        public Graph (int v) {
            this.v = v;
            this.adj = new LinkedList[v];
            for (int i = 0; i < v ; i++) {
                this.adj[i] = new LinkedList<>();
            }
        }

        private class Edge {
            public int sid;
            public int tid;
            public int w;

            public Edge (int sid, int tid, int w) {
                this.sid = sid;
                this.tid = tid;
                this.w = w;
            }
        }

        //增加dist 主要是为了迪杰斯特拉算法准备
        private class Vertex {
            private int id;
            private int dist;
            public Vertex (int id, int dist) {
                this.id = id;
                this.dist = dist;
            }
        }

        public void addEdge(int s, int t, int w) {
            this.adj[s].add(new Edge(s, t,w));
        }

        /**
         * 拓扑排序 深度优先算法求解
         */
        void topoSortByDFS() {
            LinkedList<Edge> inverseAdj[] = new LinkedList[v];
            for (int i = 0; i < v ; i++) {
                inverseAdj[i] = new LinkedList<>();
            }
            boolean visit[] = new boolean[v];
            for (int i = 0; i < v ; i++) {
                for (int j = 0; i < v ; i++) {
                    Edge w = adj[i].get(j); //i->w
                    inverseAdj[w.tid].add(new Edge(w.tid,w.sid,w.w));  //w->i
                }
            }
            for (int i = 0; i < v ; i++) {
                if (visit[i] == false) {
                    visit[i] = true;
                    dfs(i,inverseAdj,visit);
                }
            }
        }

        void dfs(int vertex,LinkedList<Edge> inverseAdj[],boolean visit[]){
            for (int j = 0; j < inverseAdj[vertex].size() ; j++) {
                int w = inverseAdj[vertex].get(j).sid;
                if (visit[w] == true) return;
                visit[w] = true;
                dfs(j, inverseAdj, visit);
            }
            System.out.println("--> " +vertex);
        }


        //广度优先
        //时间复杂度 O(E+V) 空间复杂度 O(V)
        void bfs(int s, int t) {
            if (s == t) return;
            boolean[] visited = new boolean[v];
            visited[s] = true;
            Queue<Integer> queue =  new LinkedList();
            queue.add(s);
            int prev[] = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }

            while (queue.size() != 0) {
                int w = queue.poll();
                for (int i = 0; i < adj[w].size(); i++) {
                    int q = adj[i].get(i).tid;
                    if (!visited[q]) {
                        prev[q] = w;
                        if (q == t) {

                            return;
                        }
                        visited[q] = true;
                        queue.add(q);
                    }
                }
            }
        }

        void print (int[] prev, int s, int t){
            if (prev[t] != -1 && t != s) {
                print(prev,s,prev[t]);
            }
            System.out.print(t + " " );
        }

        boolean found = false;

        //深度优先
        //时间复杂度 O(E) 空间复杂度 O(V)
        void dfs(int s, int t) {
            found = false;
            boolean[] visited = new boolean[v];
            int [] prev = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }
            runDfs(s,t,visited,prev);
            print(prev, s, t);
        }

        void runDfs(int w, int t, boolean [] visited, int[] prev) {
            if (w == t) return;
            visited[w] = true;
            if (w == t) {
                found = true;
                return;
            }
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i).tid;
                if (!visited[q]) {
                    prev[q] = w;
                    runDfs(q, t, visited ,prev);
                }
            }
        }

    }


}
