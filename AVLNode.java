/**
 * This class implements the structure of node.
 *
 * @author Zeshan Ahmed <zahme2@uwo.ca>
 */


public class AVLNode<K, V> implements Position<K, V> {

    private int height;
    private DictEntry<K, V> entry;
    private AVLNode<K, V> right;
    private AVLNode<K, V> left;

    public AVLNode(DictEntry<K, V> inputEntry, AVLNode<K, V> inputLeft, AVLNode<K, V> inputRight) {
        this.left = inputLeft;
        this.right = inputRight;
        this.entry = inputEntry;
        this.height = 0;
        if (this.left != null) {
            this.height = Math.max(this.height, 1 + this.left.getHeight());
        }
        if (this.right != null) {
            this.height = Math.max(this.height, 1 + this.right.getHeight());
        }
    }

    public AVLNode<K, V> right() {
        return this.right;
    }

    public AVLNode<K, V> left() {
        return this.left;
    }

    public DictEntry<K, V> getEntry() {
        return this.entry;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLeft(AVLNode<K, V> newLeft) {
        this.left = newLeft;
    }

    public void setRight(AVLNode<K, V> newRight) {
        this.right = newRight;
    }

    public void setEntry(DictEntry<K, V> newEntry) {
        this.entry = newEntry;
    }

    public DictEntry<K, V> element() {
        return this.entry;
    }

    public void resetHeight() throws AVLTreeException {
        if (this.left != null && this.right != null) {
            this.height = 1 + Math.max(this.left.getHeight(), this.right.getHeight());
        } else {
            throw new AVLTreeException("Attempt to update height for external node ");
        }
    }

    private int checkHeight(AVLNode r) throws AVLTreeException {
        if (r == null) {
            return -1;
        } else {
            int rightHeight = this.checkHeight(r.right());
            int leftHeight = this.checkHeight(r.left());
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

    public boolean isBST(AVLNode r) {
        if (r == null) {
            return true;
        } else {
            if (r.left() != null) {
                if (((Comparable)r.left().element().getKey()).compareTo(r.element().getKey()) >= 0) {
                    return false;
                }
            } else if (r.right() != null && ((Comparable)r.right().element().getKey()).compareTo(r.element().getKey()) <= 0) {
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
