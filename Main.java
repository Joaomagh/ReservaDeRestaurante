public class Main {
  public static void main(String[] args) {
    AVLTree avlTree = new AVLTree();

    avlTree.insert(new Reservation("Alice", "17:00", 4));
    avlTree.insert(new Reservation("Davi", "17:30", 2));
    avlTree.insert(new Reservation("Charlie", "18:00", 6));
    avlTree.insert(new Reservation("Maikol", "19:15", 3));
    avlTree.insert(new Reservation("Vitor", "21:00", 5));
    avlTree.insert(new Reservation("João", "18:10", 5));

    // Exibir a árvore ANTES DA EDIÇÃO
    System.out.println("ANTES DA EDIÇÃO:");
    avlTree.mostrarArvore();

    // edições e remoções.
    avlTree.edit("17:30", "19:30");
    avlTree.delete(new Reservation("João", "18:11", 5));

    // Exibir a árvore após a edição
    System.out.println("\nReservas De Hoje:");
    avlTree.mostrarArvore();
  }

}

// metodos CRUD

// CREATE
// avlTree.insert(new Reservation("Alice", "18:00", 4));

// READ(CONSULTAR)
// avlTree.mostrarArvore();

// UPDATE (ATUALIZAR)
// avlTree.edit("19:30", "19:45");

// delete
// avlTree.delete(new Reservation("", "", 0));
