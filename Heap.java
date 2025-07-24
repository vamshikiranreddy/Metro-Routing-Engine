package MetroApp;

import java.util.*;

public class Heap {
    private List<Node> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public void insert(Node node) {
        heap.add(node);
        upHeap(heap.size() - 1);
    }

    public Node extractMin() {
        if (heap.isEmpty()) return null;
        Node min = heap.get(0);
        Node last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downHeap(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void upHeap(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (heap.get(parent).distance > heap.get(idx).distance) {
                Collections.swap(heap, parent, idx);
                idx = parent;
            } else {
                break;
            }
        }
    }

    private void downHeap(int idx) {
        int size = heap.size();
        while (true) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = idx;

            if (left < size && heap.get(left).distance < heap.get(smallest).distance)
                smallest = left;
            if (right < size && heap.get(right).distance < heap.get(smallest).distance)
                smallest = right;

            if (smallest != idx) {
                Collections.swap(heap, smallest, idx);
                idx = smallest;
            } else {
                break;
            }
        }
    }

    public static class Node {
        String station;
        int distance;

        public Node(String station, int distance) {
            this.station = station;
            this.distance = distance;
        }
    }
}
