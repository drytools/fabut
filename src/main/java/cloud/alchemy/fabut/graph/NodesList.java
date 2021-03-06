package cloud.alchemy.fabut.graph;

import cloud.alchemy.fabut.enums.NodeCheckType;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementing class for {@link IsomorphicGraph} using {@link LinkedList} as container.
 *
 * @author Dusko Vesin
 * @author Nikola Olah
 * @author Bojan Babic
 * @author Nikola Trkulja
 */
public class NodesList implements IsomorphicGraph {
    private final List<IsomorphicNodePair> isomorphicNodes;

    /**
     * Default constructor.
     */
    public NodesList() {
        isomorphicNodes = new LinkedList<>();
    }

    @Override
    public boolean containsPair(final Object expected, final Object actual) {
        return isomorphicNodes.contains(new IsomorphicNodePair(expected, actual));
    }

    @Override
    public void addPair(final Object expected, final Object actual) {
        isomorphicNodes.add(new IsomorphicNodePair(expected, actual));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getExpected(final Object actual) {
        for (final IsomorphicNodePair isomorphicNode : isomorphicNodes) {
            if (isomorphicNode.getActual() == actual) {
                return isomorphicNode.getExpected();
            }
        }
        return null;
    }

    @Override
    public boolean containsActual(final Object actual) {
        for (final IsomorphicNodePair isomorphicNode : isomorphicNodes) {
            if (isomorphicNode.getActual() == actual) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsExpected(final Object expected) {
        for (final IsomorphicNodePair isomorphicNode : isomorphicNodes) {
            if (isomorphicNode.getExpected() == expected) {
                return true;
            }
        }
        return false;
    }

    @Override
    public NodeCheckType nodeCheck(final Object expected, final Object actual) {
        if (containsPair(expected, actual)) {
            return NodeCheckType.CONTAINS_PAIR;
        } else if (containsExpected(actual) || containsActual(expected)) {
            return NodeCheckType.SINGLE_NODE;
        }
        return NodeCheckType.NEW_PAIR;
    }

}
