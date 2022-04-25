package my.algorithm.simple;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 3;

        TreeNode nine = new TreeNode();
        nine.val = 9;

        root.left = nine;

        TreeNode twenty = new TreeNode();
        twenty.val = 20;

        root.right = twenty;

        TreeNode fifty = new TreeNode();
        fifty.val = 15;
        TreeNode seven = new TreeNode();
        seven.val = 7;
        twenty.left = fifty;
        twenty.right = seven;
        BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
        List<List<Integer>> list = binaryTreeLevelOrderTraversal.levelOrder(root);

    }

    /*
     *  Input: root = [3,9,20,null,null,15,7]
     *  Output: [[3],[9,20],[15,7]]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qlen = queue.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < qlen; i++) {
                TreeNode curr = queue.poll();
                row.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            result.add(row);


            TreeNode node = queue.poll();
            System.out.println(node.val);
            Optional.ofNullable(node.left).ifPresent(queue::add);
            Optional.ofNullable(node.right).ifPresent(queue::add);
            System.out.println();
        }
        return result;
    }
}
