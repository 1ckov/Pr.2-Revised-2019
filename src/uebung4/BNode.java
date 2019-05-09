package uebung4;

import java.util.Arrays;
import java.util.Comparator;

public class BNode {

    private Comparable[] keys = null;
    private int keysSize = 0;
    private BNode[] children = null;
    private int childrenSize = 0;
    
    private Comparator<BNode> comparator = new Comparator<BNode>() {
        @Override
        public int compare(BNode node1, BNode node2) {
            return node1.getKey(0).compareTo(node2.getKey(0));
        }
    };

    public BNode parent = null;

    public BNode(BNode parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.keys =  new Comparable[maxKeySize + 1];
        this.setKeysSize(0);
        this.children = new BNode[maxChildrenSize + 1];
        this.setChildrenSize(0);
    }

    public Comparable getKey(int index) {
        return keys[index];
    }

    public int indexOf(Comparable value) {
        for (int i = 0; i < getKeysSize(); i++) {
            if (keys[i].equals(value)) return i;
        }
        return -1;
    }

    public void addKey(Comparable value) {
        keys[keysSize++] = value;
        Arrays.sort(keys, 0, getKeysSize());
    }

    public Comparable removeKey(Comparable value) {
        Comparable removed = null;
        boolean found = false;
        if (getKeysSize() == 0) return null;
        for (int i = 0; i < getKeysSize(); i++) {
            if (keys[i].equals(value)) {
                found = true;
                removed = keys[i];
            } else if (found) {
                // shift the rest of the keys down
                keys[i - 1] = keys[i];
            }
        }
        if (found) {
            setKeysSize(getKeysSize() - 1);
            keys[getKeysSize()] = null;
        }
        return removed;
    }

    public Comparable removeKey(int index) {
        if (index >= getKeysSize())
            return null;
        Comparable value = keys[index];
        for (int i = index + 1; i < getKeysSize(); i++) {
            // shift the rest of the keys down
            keys[i - 1] = keys[i];
        }
        setKeysSize(getKeysSize() - 1);
        keys[getKeysSize()] = null;
        return value;
    }

    public int numberOfKeys() {
        return getKeysSize();
    }

    public BNode getChild(int index) {
        if (index >= getChildrenSize() || index < 0)
            return null;
        return children[index];
    }

    public int indexOf(BNode child) {
        for (int i = 0; i < getChildrenSize(); i++) {
            if (children[i].equals(child))
                return i;
        }
        return -1;
    }

    public boolean addChild(BNode child) {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, getChildrenSize(), comparator);
        return true;
    }

    public boolean removeChild(BNode child) {
        boolean found = false;
        if (getChildrenSize() == 0)
            return found;
        for (int i = 0; i < getChildrenSize(); i++) {
            if (children[i].equals(child)) {
                found = true;
            } else if (found) {
                // shift the rest of the keys down
                children[i - 1] = children[i];
            }
        }
        if (found) {
            setChildrenSize(getChildrenSize() - 1);
            children[getChildrenSize()] = null;
        }
        return found;
    }

    public BNode removeChild(int index) {
        if (index >= getChildrenSize())
            return null;
        BNode value = children[index];
        children[index] = null;
        for (int i = index + 1; i < getChildrenSize(); i++) {
            // shift the rest of the keys down
            children[i - 1] = children[i];
        }
        setChildrenSize(getChildrenSize() - 1);
        children[getChildrenSize()] = null;
        return value;
    }

    public int numberOfChildren() {
        return getChildrenSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("keys=[");
        for (int i = 0; i < numberOfKeys(); i++) {
            Comparable value = getKey(i);
            builder.append(value);
            if (i < numberOfKeys() - 1)
                builder.append(", ");
        }
        builder.append("]\n");

        if (parent != null) {
            builder.append("parent=[");
            for (int i = 0; i < parent.numberOfKeys(); i++) {
                Comparable value = parent.getKey(i);
                builder.append(value);
                if (i < parent.numberOfKeys() - 1)
                    builder.append(", ");
            }
            builder.append("]\n");
        }

        if (children != null) {
            builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");
        }

        return builder.toString();
    }

	public int getChildrenSize() {
		return childrenSize;
	}

	public void setChildrenSize(int childrenSize) {
		this.childrenSize = childrenSize;
	}

	public int getKeysSize() {
		return keysSize;
	}

	public void setKeysSize(int keysSize) {
		this.keysSize = keysSize;
	}
}
