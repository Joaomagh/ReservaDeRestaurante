public class AVLTree {
    private Node root;

    private static class Node {
        Reservation reservation;
        Node left, right;
        int height;

        public Node(Reservation reservation) {
            this.reservation = reservation;
            this.height = 1;
        }
    }

    public AVLTree() {
        this.root = null;
    }

    public void insert(Reservation reservation) {
        root = insertRec(root, reservation);
    }

    private int altura(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int fatorDeBalanceamento(Node node) {
        if (node == null) {
            return 0;
        }
        return altura(node.right) - altura(node.left);
    }

    private void atualizarAltura(Node node) {
        node.height = Math.max(altura(node.left), altura(node.right)) + 1;
    }

    private Node rotateRight(Node y) {
        if (y == null || y.left == null) {
            return y;
        }

        Node x = y.left;
        y.left = x.right;
        x.right = y;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        if (x == null || x.right == null) {
            return x;
        }

        Node y = x.right;
        x.right = y.left;
        y.left = x;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private Node insertRec(Node root, Reservation reservation) {
        if (root == null) {
            return new Node(reservation);
        }

        // Comparar com base no horário da reserva
        int compareResult = reservation.getHorarioReserva().compareTo(root.reservation.getHorarioReserva());

        if (compareResult < 0) {
            root.left = insertRec(root.left, reservation);
        } else if (compareResult > 0) {
            root.right = insertRec(root.right, reservation);
        } else {
            // Lidar com horários iguais (se necessário)
            return root;
        }

        // Atualizar altura e realizar rotações
        atualizarAltura(root);

        int balance = fatorDeBalanceamento(root);

        // Rotações e balanceamento
        if (balance > 1) {
            if (compareResult < 0) {
                return rotateRight(root);
            } else if (compareResult > 0) {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

        if (balance < -1) {
            if (compareResult > 0) {
                return rotateLeft(root);
            } else if (compareResult < 0) {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }

        return root;
    }

    public void delete(Reservation reservation) {
        root = deleteRec(root, reservation);
    }

    private Node deleteRec(Node root, Reservation reservation) {
        if (root == null) {
            return root;
        }
        // Comparar com base no horário da reserva
        int compareResult = reservation.getHorarioReserva().compareTo(root.reservation.getHorarioReserva());

        if (compareResult < 0) {
            root.left = deleteRec(root.left, reservation);
        } else if (compareResult > 0) {
            root.right = deleteRec(root.right, reservation);
        } else {
            // Node to be deleted found

            // Caso 1: Nó com apenas um filho ou nenhum filho
            if (root.left == null || root.right == null) {
                Node temp = (root.left != null) ? root.left : root.right;

                // Caso 1.1: Nenhum filho
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // Caso 1.2: Um filho
                    root = temp; // Copia o conteúdo do nó não nulo
                }
            } else {
                // Caso 2: Nó com dois filhos
                // Encontrar o sucessor in-order (o menor nó na subárvore direita)
                Node temp = encontrarMinimo(root.right);

                // Copiar o conteúdo do sucessor in-order para este nó
                root.reservation = temp.reservation;

                // Deletar o sucessor in-order
                root.right = deleteRec(root.right, temp.reservation);
            }
        }

        if (root == null) {
            return root;
        }

        // Atualizar altura e realizar rotações
        atualizarAltura(root);

        int balance = fatorDeBalanceamento(root);

        // Rotações e balanceamento
        if (balance > 1) {
            if (fatorDeBalanceamento(root.left) >= 0) {
                return rotateRight(root);
            } else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

        if (balance < -1) {
            if (fatorDeBalanceamento(root.right) <= 0) {
                return rotateLeft(root);
            } else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }

        return root;
    }

    public void edit(String oldReservationTime, String newReservationTime) {
        // Encontrar a reserva com o horário antigo
        Reservation reservationToEdit = encontrarReserva(root, oldReservationTime);

        if (reservationToEdit != null) {
            // Remover a reserva com o horário antigo
            delete(reservationToEdit);

            // Atualizar o horário da reserva
            reservationToEdit.setHorarioReserva(newReservationTime);

            // Adicionar a reserva atualizada com o novo horário
            insert(reservationToEdit);
        }
    }

    private Reservation encontrarReserva(Node root, String reservationTime) {
        if (root == null) {
            return null;
        }

        // Comparar com base no horário da reserva
        int compareResult = reservationTime.compareTo(root.reservation.getHorarioReserva());

        if (compareResult == 0) {
            // Node encontrado
            return root.reservation;
        } else if (compareResult < 0) {
            return encontrarReserva(root.left, reservationTime);
        } else {
            return encontrarReserva(root.right, reservationTime);
        }
    }

    private Node encontrarMinimo(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void mostrarArvore() {
        printTreeRec(root);
    }

    private void printTreeRec(Node root) {
        if (root != null) {
            printTreeRec(root.left);
            System.out.println("Cliente: " + root.reservation.getClienteNome() +
                    ", Horário: " + root.reservation.getHorarioReserva() +
                    ", Número de Pessoas: " + root.reservation.getNumeroPessoas());
            printTreeRec(root.right);
        }
    }
    // Outros métodos auxiliares, se necessário
}
