public class Main {
  public static void main(String[] args) {
    AVLTree avlTree = new AVLTree();

    System.out.println("Árvore AVL Antes da Edição:");
    avlTree.mostrarArvore();

    // Adicionar reservas de exemplo
    avlTree.insert(new Reservation("Alice", "18:00", 4));
    avlTree.insert(new Reservation("Bob", "19:30", 2));
    avlTree.insert(new Reservation("Charlie", "20:00", 6));

    // Realizar operações e testes
    avlTree.insert(new Reservation("David", "19:15", 3));
    avlTree.insert(new Reservation("Eve", "21:00", 5));

    // Testar o método de edição
    avlTree.edit("19:30", "19:45");

    // Exibir a árvore após a edição
    System.out.println("Reservas De Hoje:");
    avlTree.mostrarArvore();
  }

}

// metodos de inserção, remoção e edit.

// avlTree.insert(new Reservation("Alice", "18:00", 4));
// avlTree.delete(new Reservation("", "", 0));
// avlTree.edit("19:30", "19:45");
