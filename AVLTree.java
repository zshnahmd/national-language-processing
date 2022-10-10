/**
 * This class implements various AVL operations and is a main supporting file to start with. It has provided
 * implementation details that would be helpful in presenting the solution. The description of the methods are found in
 * section 4.
 *
 * @author Zeshan Ahmed <zahme2@uwo.ca>
 */

class AVLTree<K, V> {
    private AVLNode<K, V> root = null;

    public AVLTree() {
    }

    /**
     * Function to check if tree is empty
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Function makes the tree logically empty
     */
    public void makeEmpty() {
        this.root = null;
    }

    /**
     * Function to insert data
     */
    public void insert(K data) {
        this.root = this.insert(data, this.root);
    }

    /**
     * Function to get height of node
     */
    private int height(AVLNode t) {
        return t == null ? -1 : t.getHeight();
    }

    /**
     * Function to max of left/right node
     */
    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    /**
     * Function to insert data recursively
     */
    private AVLNode insert(K x, AVLNode t) {
        if (t == null) {
            t = new AVLNode(new DictEntry(x, 1), (AVLNode)null, (AVLNode)null);
        } else if (((Comparable)x).compareTo(t.element().getKey()) < 0) {
            t.setLeft(this.insert(x, t.left()));
            if (this.height(t.left()) - this.height(t.right()) == 2) {
                if (((Comparable)x).compareTo(t.left().element().getKey()) < 0) {
                    t = this.rotateWithLeftChild(t);
                } else {
                    t = this.doubleWithLeftChild(t);
                }
            }
        } else if (((Comparable)x).compareTo(t.element().getKey()) > 0) {
            t.setRight(this.insert(x, t.right()));
            if (this.height(t.right()) - this.height(t.left()) == 2) {
                if (((Comparable)x).compareTo(t.right().element().getKey()) > 0) {
                    t = this.rotateWithRightChild(t);
                } else {
                    t = this.doubleWithRightChild(t);
                }
            }
        } else {
            t.element().setValue((Integer)t.element().getValue() + 1);
        }
        try {
            t.resetHeight();
        } catch (AVLTreeException var4) {
        }
        return t;
    }

    /**
     * Rotate binary tree node with left child
     */
    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left();
        k2.setLeft(k1.right());
        k1.setRight(k2);
        try {
            k2.resetHeight();
        } catch (AVLTreeException var5) {
        }
        try {
            k1.resetHeight();
        } catch (AVLTreeException var4) {
        }
        return k1;
    }

    /**
     * Rotate binary tree node with right child
     */
    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right();
        k1.setRight(k2.left());
        k2.setLeft(k1);
        try {
            k1.resetHeight();
        } catch (AVLTreeException var5) {
        }
        try {
            k2.resetHeight();
        } catch (AVLTreeException var4) {
        }
        return k2;
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.setLeft(this.rotateWithRightChild(k3.left()));
        return this.rotateWithLeftChild(k3);
    }

    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.setRight(this.rotateWithLeftChild(k1.right()));
        return this.rotateWithRightChild(k1);
    }

    /**
     * Functions to count number of nodes
     */
    public int countNodes() {
        return this.countNodes(this.root);
    }

    private int countNodes(AVLNode r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l = l + this.countNodes(r.left());
            l += this.countNodes(r.right());
            return l;
        }
    }

    /**
     * Function to search for an element
     */
    public AVLNode<K, V> search(K val) {
        return this.search(this.root, val);
    }

    private AVLNode<K, V> search(AVLNode<K, V> r, K val) {
        if (r == null) {
            return null;
        } else {
            K rval = r.element().getKey();
            if (((Comparable)val).compareTo(rval) < 0) {
                return this.search(r.left(), val);
            } else {
                return ((Comparable)val).compareTo(rval) > 0 ? this.search(r.right(), val) : r;
            }
        }
    }

    /**
     * Function for inorder traversal
     */
    public void inorder() {
        this.inorder(this.root);
    }

    private void inorder(AVLNode r) {
        if (r != null) {
            this.inorder(r.left());
            System.out.print(r.element() + " ");
            this.inorder(r.right());
        }
    }

    /**
     * Function for preorder traversal
     */
    public void preorder() {
        this.preorder(this.root);
    }

    private void preorder(AVLNode r) {
        if (r != null) {
            System.out.print(r.element() + " ");
            this.preorder(r.left());
            this.preorder(r.right());
        }
    }

    /**
     * Function for postorder traversal
     */
    public void postorder() {
        this.postorder(this.root);
    }

    private void postorder(AVLNode r) {
        if (r != null) {
            this.postorder(r.left());
            this.postorder(r.right());
            System.out.print(r.element() + " ");
        }

    }

    private int checkHeight(AVLNode r) throws AVLTreeException {
        if (r == null) {
            return -1;
        } else {
            int leftHeight = this.checkHeight(r.left());
            int rightHeight = this.checkHeight(r.right());
            if (Math.abs(leftHeight - rightHeight) >= 2) {
                throw new AVLTreeException("");
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }
    }

    public boolean isBalanced(AVLNode r) {
        try {
            this.checkHeight(r);
            return true;
        } catch (AVLTreeException var3) {
            return false;
        }
    }

    public AVLNode getRoot() {
        return this.root;
    }

    public void setRoot(AVLNode rt) {
        this.root = rt;
    }

    public boolean isBST(AVLNode r) {
        if (r == null) {
            return true;
        } else {
            if (r.left() != null) {
                if (((Comparable)r.left().element().getKey()).compareTo(r.element()) >= 0) {
                    return false;
                }
            } else if (r.right() != null && ((Comparable)r.right().element().getKey()).compareTo(r.element()) <= 0) {
                return false;
            }
            if (!this.isBST(r.left())) {
                return false;
            } else {
                return this.isBST(r.right());
            }
        }
    }
}
