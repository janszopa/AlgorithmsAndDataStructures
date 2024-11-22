package pl.edu.pw.ee.aisd2023zlab4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RbtMapTest <K extends Comparable<K>, V>{

    private RbtMap tree;
    private boolean isBalanced = true;
    private boolean areBlack = true;
    
    @Before
    public void setup(){ tree = new RbtMap<>();}

    @Test
    public void shouldCreateCorrectTree_AndGetValues(){
        //given
        String[] exampleWord = {"i", "n", "w", "e", "s", "t", "o", "r", "a"};

        //when
        for(int i = 0; i < 9; i++){
            tree.setValue( exampleWord[i], exampleWord[i] );
        }
        //then
        for(int i = 0; i < 9; i++) {
            System.out.println( tree.getValue(exampleWord[i]) );
        }
        System.out.println( "\nroot: " + tree.getRoot().getValue() );
    }
    
    @Test
    public void shouldDeleteValuesCorrectly(){
        //given
        String[] exampleWord = {"i", "n", "w", "e", "s", "t", "o", "r", "a"};
        Node<K, V> root;
        
        //when
        for(int i = 0; i < 9; i++){
            tree.setValue( exampleWord[i], exampleWord[i] );
        }
        
        //when
        tree.deleteMax();
        root = tree.getRoot();
        
        //then
        int blackNodesInPath = tree.countBlackNodesInPath();
        isTreeBalanced(root, 0, blackNodesInPath);
    }
    
    /*@Test
    public void shouldThrowExeption_whenDeleteNull(){
            //given
            tree.setValue( null, null );
        
            //when
            Throwable exceptionCaught = catchThrowable(() -> {
                tree.deleteMax();
            });
            
            //than
           
    }*/

    @Test
    public void rootShouldAlwaysBeBlack(){
        //given
        int numOfElems = 10;
        RbtMap tree2 = new RbtMap<>();
        RbtMap tree3 = new RbtMap<>();
        boolean areBlack = false;

        //when
        for (int i = 0; i < numOfElems; i++){
            tree.setValue(i, "x");
            tree2.setValue(i + 1, "y");
            tree3.setValue(i * 2, "z");
        }
        if(tree.isBlack( tree.getRoot()) && tree.isBlack(tree2.getRoot()) && tree.isBlack(tree3.getRoot()) ){
            areBlack = true;
        }

        //then
        assertThat(areBlack).isEqualTo(true);
    }

   /* @Test
    public void allLeavesShouldBeBlack() { // chyba jedank nie muszą być czarne w naszym drzewie
        //given
        int numOfElems = 100;
        Node<K, V> root;

        //when
        for (int i = 0; i < numOfElems; i++){
            tree.setValue(i, "dlugasny" + i);
        }

        root = tree.getRoot();
        areLeavesBlack(root);

        //then
        assertThat(areBlack).isEqualTo(true);
    }
    public void areLeavesBlack(Node<K, V> node){
        if (node == null) {
            return;
        } else if ( node.getLeft() == null && node.getLeft() == null){
            areBlack =  tree.isBlack(node);
        } else {
            areLeavesBlack(node.getLeft());
            areLeavesBlack(node.getRight());
        }
    }*/

    @Test
    public void shouldCreateBalancedTree(){
        //given
        int numOfElems = 10;
        Node<K, V> node;
        int blackNodesInPath;

        //when
        for (int i = 0; i < numOfElems; i++){
            tree.setValue(i, "x");
        }

        node = tree.getRoot();

        blackNodesInPath = tree.countBlackNodesInPath();
        isTreeBalanced(node, 0, blackNodesInPath);

        //then
        assertThat(isBalanced).isEqualTo(true);
    }
    public void isTreeBalanced(Node<K, V> node, int counter, int numOfBlackNodes){
        if(node == null){
                isBalanced = counter == numOfBlackNodes;
        } else {
            if( tree.isBlack(node) ) { counter++; }

            isTreeBalanced(node.getLeft(), counter, numOfBlackNodes);
            isTreeBalanced(node.getRight(), counter, numOfBlackNodes);
        }
    }

}
