package lab.pawigor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String[] ve = br.readLine().split(" ");

            int vertexCount = Integer.parseInt(ve[0]);
            int edgeCount = Integer.parseInt(ve[1]);

            Graph graph = new Graph(vertexCount, edgeCount);
            for (int i = 0; i < edgeCount; i++) {
                String[] vv = br.readLine().split(" ");
                int v1 = Integer.parseInt(vv[0]);
                int v2 = Integer.parseInt(vv[1]);
                graph.addEdge(v1, v2);
            }

            br.close();

            System.out.println(graph.walk());


        } catch (IOException ignored) {

        }
    }

    public static class Graph {
        int vertexCount;
        int edgeCount;
        int[][] ave;
        Vertex[] vertexes;

        public Graph(int vertexCount, int edgeCount) {
            this.vertexCount = vertexCount;
            this.vertexes = new Vertex[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                vertexes[i] = new Vertex(i + 1, false);
            }
            this.edgeCount = edgeCount;
            this.ave = new int[vertexCount][vertexCount];
        }

        public void addEdge(int v1, int v2) {
            this.ave[v1 - 1][v2 - 1] = 1;
            this.ave[v2 - 1][v1 - 1] = 1;
        }

        public void visit(Vertex vertex) {
            if (vertex.wasVisited) return;
            vertex.wasVisited = true;
            for (int i = 0; i < vertexCount; i++) {
                if (ave[vertex.vertex - 1][i] == 1) {
                    visit(vertexes[i]);
                }
            }

        }

        public int walk() {
            int visits = 0;
            for (int i = 0; i < vertexes.length; i++) {
                Vertex vertex = vertexes[i];
                if (!vertex.wasVisited) {
                    visit(vertex);
                    visits++;
                }
            }
            return visits;
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "vertexCount=" + vertexCount +
                    ", edgeCount=" + edgeCount +
                    ", ave=" + Arrays.toString(ave) +
                    '}';
        }

        public class Vertex {
            int vertex;
            boolean wasVisited;

            public Vertex(int vertex, boolean wasVisited) {
                this.vertex = vertex;
                this.wasVisited = wasVisited;
            }

            @Override
            public String toString() {
                return "Vertex{" +
                        "vertex=" + vertex +
                        ", wasVisited=" + wasVisited +
                        '}';
            }
        }
    }
}
