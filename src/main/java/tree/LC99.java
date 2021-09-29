package tree;

/**
 * This method still needs a O(H) space complexity due to the recursion. For inorder traversal, we need to compare two
 * neighboring variables. Thus, we need a prev pointer.
 *
 * In the dfs, we are traversing with in order. Thus, when the prev == null, we have reached the leftmost node. We can
 * mark it as prev and continue. In the else clause, if root is smaller than prev, then we have a wrong value. However,
 * it is also possible to be neighboring. Thus, we need to update x and y simultaneously.
 */
public class LC99 {
    TreeNode prev;
    TreeNode x;
    TreeNode y;

    public void recoverTree(TreeNode root) {
        dfs(root);
        if (x != null && y != null) {
            swap(x, y);
        }
    }

    private void swap(TreeNode x, TreeNode y) {
        int val = x.val;
        x.val = y.val;
        y.val = val;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev == null) {
            prev = root;
        } else {
            if (root.val < prev.val) {
                y = root;
                if (x == null) {
                    x = prev;
                }
            }
            prev = root;
        }
        dfs(root.right);
    }
}
