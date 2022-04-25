package my.algorithm.simple;

import java.util.ArrayList;
import java.util.List;

/*
 *  Pre-order traversal is to visit the root first. Then traverse the left subtree. Finally, traverse the right subtree.
 */
public class TreePreorderTraversal {

    public static void main(String[] args) {
        /*
         *  Input: root = [1,null,2,3]
         *  Output: [1,2,3]
         */
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode two = new TreeNode();
        two.val = 2;
        TreeNode three = new TreeNode();
        three.val = 3;
        root.right = two;
        two.left = three;
        TreePreorderTraversal treePreorderTraversal = new TreePreorderTraversal();
        List<Integer> result = treePreorderTraversal.preorderTraversal(root);
        System.out.print(result.get(0));
        System.out.print(result.get(1));
        System.out.print(result.get(2));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        process(root, result);
        return result;
    }

    private void process(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) process(root.left, list);
        if (root.right != null) process(root.right, list);
    }

}
