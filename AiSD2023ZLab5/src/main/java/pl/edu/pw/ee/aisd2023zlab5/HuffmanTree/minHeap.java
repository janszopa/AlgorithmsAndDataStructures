package pl.edu.pw.ee.aisd2023zlab5.HuffmanTree;

public class minHeap {
    private Node<Integer, Integer>[] heap;
    private int heapSize;
    public minHeap(){
        heap = new Node[256];
        heapSize = 0;
    }
    private void swap(Node[] heap, int firstId, int secondId) {
        if (firstId != secondId) {

            Node firstVal = heap[firstId];
            heap[firstId] = heap[secondId];
            heap[secondId] = firstVal;
        }
    }
    public void insertNode(Node<Integer, Integer> node) {
        heap[heapSize] = node;
        heapSize++;

        fixHeapUp();
    }

    private void fixHeapUp(){
        int currentIndex = heapSize - 1;

        while (currentIndex != 0 && compareNodes(heap[currentIndex],  heap[(currentIndex - 1) / 2]) == -1){
            swap(heap, currentIndex, (currentIndex - 1) / 2);
            currentIndex = (currentIndex - 1) / 2;
        }
    }

    public Node<Integer, Integer> getMin(){
        if(heapSize == 0){
            System.out.println("The heap is empty");
            return null;
        }

        Node<Integer, Integer> min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;

        fixHeapDown();

        return min;
    }

    private void fixHeapDown(){
        int id = 0;

        while (2 * id + 1 < heapSize){ //dopoki istnieje dziecko
            int minChildId = 2 * id + 1;
            if(2 * id + 2 < heapSize && compareNodes(heap[2 * id + 2], heap[2 * id + 1]) == -1) {
                minChildId = 2 * id + 2;
            }

            if(compareNodes(heap[id], heap[minChildId]) == -1){
                break;
            } else {
                swap(heap, id, minChildId);
            }
            id = minChildId;

        }
    }

    private int compareNodes(Node<Integer, Integer> firstNode, Node<Integer, Integer> secondNode){
        return firstNode.getCounter().compareTo(secondNode.getCounter());
    }

    public int getHeapSize(){ return heapSize; }
}
