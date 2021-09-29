package tree;
import java.util.*;

public class LC257 {
    /**
     * This is a easy question. But we need to solve it by both recursive approach and iterative approach.
     */
    //Iterative
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        /**
         * We need to memorize both the path and the node by using 2 stacks. The stacks are used to mimic
         * the implicit call stack.
         * Thus, we need to first push the right child and then the left root. By doing so, during the pop,
         * we can get the left node first. This is the same as preorder traversal.
         * Besides, we can store all the numbers and adding arrows at the postprocessing. This will consume
         * less memory.
         */
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> nodeStack = new ArrayDeque<>();
        Deque<List<Integer>> pathStack = new ArrayDeque<>();

        List<Integer> path = new ArrayList<>();
        nodeStack.offerFirst(root);
        path.add(root.val);
        pathStack.offerFirst(path);
        while(!nodeStack.isEmpty()){
            TreeNode curr = nodeStack.pollFirst();
            List<Integer> temp = pathStack.pollFirst();
            if(curr.left == null && curr.right == null){
                res.add(temp);
            }
            if(curr.right != null){
                List<Integer> right = new ArrayList<>(temp);
                right.add(curr.right.val);
                nodeStack.offerFirst(curr.right);
                pathStack.offerFirst(right);
            }
            if(curr.left != null){
                List<Integer> left = new ArrayList<>(temp);
                left.add(curr.left.val);
                nodeStack.offerFirst(curr.left);
                pathStack.offerFirst(left);
            }
        }
        List<String> result = new ArrayList<>();
        for(List<Integer> a : res){
            String processed = postprocess(a);
            result.add(processed);
        }
        return result;
    }

    private String postprocess(List<Integer> path){
        StringBuilder sb = new StringBuilder();
        for(Integer num : path){
            sb.append(Integer.toString(num) + "->");
        }
        if(sb.length() > 0){
            return sb.delete(sb.length() - 2, sb.length()).toString();
        }else{
            return sb.toString();
        }
    }


    /**
     * We can also solve this problem by using recursion. One thing to notice is that we need to carefully
     * calculate the index of the string buffer.
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        dfs(sb, root, res);
        return res;
    }

    private void dfs(StringBuffer sb, TreeNode root, List<String> res){
        if(root == null){
            return;
        }
        String num = Integer.toString(root.val);
        if(root.left == null && root.right == null){
            sb.append(num);
            res.add(sb.toString());
            sb.delete(sb.length() - num.length(), sb.length());
            return;
        }
        sb.append(root.val + "->");
        dfs(sb, root.left, res);
        dfs(sb, root.right, res);
        sb.delete(sb.length() - 2 - num.length(), sb.length());
    }


}
