package tree;

public class LC297 {
    private int i = 0;

    /**
     * Serialize the binary tree with preorder traversal
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(sb, root);
        return sb.toString();
    }

    /**
     * All the null node will be denoted as a #
     * Preorder traversal
     */
    private void dfs(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val + " ");
        dfs(sb, root.left);
        dfs(sb, root.right);
    }

    /**
     * If we meet a #, adding 2 to the pointer and return null. This means a null node.
     * We may meet positive/negative number. Thus, it is important to process '-'
     * Updating pointer i will be visible to the whole class. This is a global variable.
     */
    public TreeNode deserialize(String data) {
        if (i >= data.length()) {
            return null;
        }
        if (data.charAt(i) == '#') {
            i += 2;
            return null;
        }
        StringBuffer sb = new StringBuffer();
        while (Character.isDigit(data.charAt(i)) || data.charAt(i) == '-') {
            sb.append(data.charAt(i++));
        }
        i++;
        TreeNode curr = new TreeNode(Integer.parseInt(sb.toString()));
        curr.left = deserialize(data);
        curr.right = deserialize(data);
        return curr;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
