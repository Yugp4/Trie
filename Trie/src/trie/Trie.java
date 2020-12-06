package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Yug Patel
 *
 */
public class Trie {
    
    // prevent instantiation
    private Trie() { }
    
    private static void recurseBuild(String s, int ind, short start, short end, String[] allWords, TrieNode root) {
        
        if(root.firstChild != null) {
            int sIndex = 0;
            int eIndex = -1;
            int start1 = start;
            String ptrString = allWords[root.substr.wordIndex].substring(root.substr.startIndex, (root.substr.endIndex)+1);
            int len = Math.min(ptrString.length(), s.length());
                for(int c = sIndex; c < len; c++) {
                    if(s.charAt(c) != ptrString.charAt(c)) {
                        break;
                    }
                    if(eIndex == -1){
                    	eIndex = start1;
                    }else {
                    eIndex++;
                    }
                }
            if(eIndex == -1) {
                if(root.sibling != null) {
                    recurseBuild(s, ind, (short)(start), (short)(end), allWords, root.sibling);
                    return;
                }else {
                    root.sibling = new TrieNode(new Indexes(ind, (short)(start), (short)(end)), null, null);
                    return;
                }
            }else {
                if(root.firstChild != null) {
                    if(eIndex - sIndex < root.substr.endIndex) {
                        TrieNode pre = new TrieNode(new Indexes(root.substr.wordIndex, (short)(sIndex), (short)(eIndex)), null, null);
                        pre.firstChild = new TrieNode(new Indexes(root.substr.wordIndex, (short)(root.substr.startIndex+(pre.substr.endIndex+1)), root.substr.endIndex), root.firstChild, null);
                        root.substr = new Indexes(pre.substr.wordIndex, pre.substr.startIndex, pre.substr.endIndex);
                        root.firstChild = new TrieNode(pre.firstChild.substr, pre.firstChild.firstChild, pre.firstChild.sibling);
                        pre.sibling = root.sibling;
                        if(root.sibling != null) {
                            pre.sibling = new TrieNode(root.sibling.substr, root.sibling.firstChild, root.sibling.sibling);
                        }
                        if(pre.sibling != null) {
                            root.sibling = new TrieNode(pre.sibling.substr, pre.sibling.firstChild, pre.sibling.sibling);
                        }else {
                        root.sibling = pre.sibling;
                        }
                        recurseBuild(s.substring(eIndex+1), ind, (short)(eIndex+1), (short)(end), allWords, root.firstChild);
                        return;
                    }else {
                    String recur = s.substring(eIndex-start1 + 1);
                    recurseBuild(recur, ind, (short)(eIndex+1), (short)(end), allWords, root.firstChild);
                    return;
                    }
                }else {
                    TrieNode common = new TrieNode(null, null, null);
                    common.substr = new Indexes(root.substr.wordIndex, (short)(sIndex), (short)(eIndex));
                    common.firstChild = new TrieNode(new Indexes(root.substr.wordIndex, (short)(eIndex+1), (short)(root.substr.endIndex)),null, null);
                    common.firstChild.sibling = new TrieNode(new Indexes(ind, (short)(eIndex+1), (short)(end)), null, null);
                    root.substr = new Indexes(common.substr.wordIndex, common.substr.startIndex, common.substr.endIndex);
                    root.firstChild = new TrieNode(common.firstChild.substr, common.firstChild.firstChild, common.firstChild.sibling);
                    common.sibling = root.sibling;
                    if(root.sibling != null) {
                        common.sibling = new TrieNode(root.sibling.substr, root.sibling.firstChild, root.sibling.sibling);
                    }
                    if(common.sibling != null) {
                        root.sibling = new TrieNode(common.sibling.substr, common.sibling.firstChild, common.sibling.sibling);
                    }else {
                    root.sibling = common.sibling;
                    }
                    return;
                }
            }
        }else {
            int sIndex = start;
            int eIndex = -1;
            int len = end;
            String rootString = allWords[root.substr.wordIndex].substring(root.substr.startIndex, root.substr.endIndex+1);
            if(rootString.length() < s.length()) {
                len = rootString.length();
            }
            for(int c = sIndex; c < len; c++) {
                if(s.charAt(c-start) != rootString.charAt(c-start)) {
                    break;
                }
                eIndex = c;
            }
            if(eIndex == -1) {
                if(root.sibling != null) {
                    recurseBuild(s, ind, start, end, allWords, root.sibling);
                    return;
                }else {
                    root.sibling = new TrieNode(new Indexes(ind, (short)(start), (short)(end)), null, null);
                    return;
                }
            }else {
                if(root.firstChild != null) {
                    if(eIndex - sIndex < root.substr.endIndex) {
                        TrieNode pre = new TrieNode(new Indexes(root.substr.wordIndex, (short)(sIndex), (short)(eIndex)), null, null);
                        pre.firstChild = new TrieNode(new Indexes(root.substr.wordIndex, (short)(root.substr.startIndex+(pre.substr.endIndex+1)), root.substr.endIndex), root.firstChild, null);
                        root.substr = new Indexes(pre.substr.wordIndex, pre.substr.startIndex, pre.substr.endIndex);
                        root.firstChild = new TrieNode(pre.firstChild.substr, pre.firstChild.firstChild, pre.firstChild.sibling);
                        pre.sibling = root.sibling;
                        if(root.sibling != null) {
                            pre.sibling = new TrieNode(root.sibling.substr, root.sibling.firstChild, root.sibling.sibling);
                        }
                        if(pre.sibling != null) {
                            root.sibling = new TrieNode(pre.sibling.substr, pre.sibling.firstChild, pre.sibling.sibling);
                        }else {
                        root.sibling = pre.sibling;
                        }
                        recurseBuild(s.substring(eIndex+1), ind, (short)(eIndex+1), (short)(end), allWords, root.firstChild);
                        return;
                    }else {
                    recurseBuild(s.substring(eIndex +1), ind, (short)(eIndex+1), (short)(end), allWords, root.firstChild);
                    return;
                    }
                }
               else {
                TrieNode common = new TrieNode(null, null, null);
                common.substr = new Indexes(root.substr.wordIndex, (short)(sIndex), (short)(eIndex));
                common.firstChild = new TrieNode(new Indexes(root.substr.wordIndex, (short)(eIndex+1), (short)(root.substr.endIndex)), null, null);
                common.sibling = root.sibling;
                common.firstChild.sibling = new TrieNode(new Indexes(ind, (short)(eIndex+1), (short)(end)), null, null);
                root.substr = new Indexes(common.substr.wordIndex, common.substr.startIndex, common.substr.endIndex);
                root.firstChild = new TrieNode(common.firstChild.substr, common.firstChild.firstChild, common.firstChild.sibling);
                common.sibling = root.sibling;
                if(root.sibling != null) {
                    common.sibling = new TrieNode(root.sibling.substr, root.sibling.firstChild, root.sibling.sibling);
                }
                if(common.sibling != null) {
                    root.sibling = new TrieNode(common.sibling.substr, common.sibling.firstChild, common.sibling.sibling);
                }else {
                root.sibling = common.sibling;
                }
                return;
            }
        }
        }
    }
    
    /**
     * Builds a trie by inserting all words in the input array, one at a time,
     * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
     * The words in the input array are all lower case.
     * 
     * @param allWords Input array of words (lowercase) to be inserted.
     * @return Root of trie with all words inserted from the input array
     */
    
    
    public static TrieNode buildTrie(String[] allWords) {
        /** COMPLETE THIS METHOD **/
        if(allWords == null || allWords.length <= 0) {
            return new TrieNode(null, null, null);
        }
        TrieNode trie = new TrieNode(null, null, null);
        trie.firstChild = new TrieNode(new Indexes(0, (short)0, (short)(allWords[0].length()-1)), null, null);
        for(int i = 1; i < allWords.length; i++) {
            String s = allWords[i];
            short st = 0;
            short en = (short)(s.length() - 1);
            recurseBuild(s, i, st, en, allWords, trie.firstChild);
        }
        return trie;
    }
    
    /**
     * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
     * trie whose words start with this prefix. 
     * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
     * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
     * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
     * and for prefix "bell", completion would be the leaf node that holds "bell". 
     * (The last example shows that an input prefix can be an entire word.) 
     * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
     * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
     *
     * @param root Root of Trie that stores all words to search on for completion lists
     * @param allWords Array of words that have been inserted into the trie
     * @param prefix Prefix to be completed with words in trie
     * @return List of all leaf nodes in trie that hold words that start with the prefix, 
     *             order of leaf nodes does not matter.
     *         If there is no word in the tree that has this prefix, null is returned.
     */
    public static ArrayList<TrieNode> completionList(TrieNode root,
                                        String[] allWords, String prefix) {
        /** COMPLETE THIS METHOD **/
        ArrayList<TrieNode> completion = new ArrayList<TrieNode>();
        recurseCompletion(root, allWords, prefix, completion);
        if(completion.isEmpty()) {
            return null;
        }
        return completion;
    }
    
    private static void recurseCompletion(TrieNode r, String[] allWords, String pre, ArrayList<TrieNode> comp) {
        TrieNode ptr = r;
        if(ptr.substr == null) {
            ptr = ptr.firstChild;
        }
      String ptrString = allWords[ptr.substr.wordIndex].substring(ptr.substr.startIndex, ptr.substr.endIndex+1);
        if(pre.equals("")) {
            
            for(TrieNode sib = ptr; sib != null; sib = sib.sibling) {
                if(sib.firstChild == null) {
                    comp.add(sib);
                }else {
                    recurseCompletion(sib.firstChild, allWords, pre, comp);
                }
            }
            return;
        }
        if(ptrString.startsWith(pre) || pre.startsWith(ptrString)) {
            if(ptr.firstChild == null) {
                comp.add(ptr);
                return;
            }
            String toBeRecString = "";
            if(pre.length() >= ptrString.length()) {
                toBeRecString = pre.substring(ptrString.length());
            }
            recurseCompletion(ptr.firstChild, allWords, toBeRecString, comp);
            return;
        }else {
            if(ptr.sibling != null) {
            recurseCompletion(ptr.sibling, allWords, pre, comp);
            return;
            }
            else {
                return;
            }
        }
    }
    
    
    
    public static void print(TrieNode root, String[] allWords) {
        System.out.println("\nTRIE\n");
        print(root, 1, allWords);
    }
    
    private static void print(TrieNode root, int indent, String[] words) {
        if (root == null) {
            return;
        }
        for (int i=0; i < indent-1; i++) {
            System.out.print("    ");
        }
        
        if (root.substr != null) {
            String pre = words[root.substr.wordIndex]
                            .substring(0, root.substr.endIndex+1);
            System.out.println("      " + pre);
        }
        
        for (int i=0; i < indent-1; i++) {
            System.out.print("    ");
        }
        System.out.print(" ---");
        if (root.substr == null) {
            System.out.println("root");
        } else {
            System.out.println(root.substr);
        }
        
        for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
            for (int i=0; i < indent-1; i++) {
                System.out.print("    ");
            }
            System.out.println("     |");
            print(ptr, indent+1, words);
        }
    }
 }