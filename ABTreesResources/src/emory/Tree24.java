//package emory;
//
//import java.util.*;
//
//
//public class Tree24
//{
//    /* =================================================
//       Variables
//       ================================================= */
//    public Node root;
//    public Node searchEndPos;   // Last node visited by findEntry()
//
//    /* =================================================
//       Constructor
//       ================================================= */
//    public Tree24()
//    {
//       root = null;
//    }
//
//
//    /* ================================================================
//       keySearch(k):  find entry containing key k
//
//       Return value:
//
//             e[i]   if found  (e[i].key == k)
//	     null   if not found 
//                    AND: searchEndPos = node last visited in search
//       ================================================================ */
//    public Entry keySearch(String k)
//    {
//       int i;
//       Node curr;
//
////     System.out.println("enter keySearch(" + k + ")");
//       searchEndPos = root;
//
//       curr = root;
//       while ( curr != null )
//       {
//          for (i = 0; i < 3; i++)
//          {
//            /* ============================================================
//               It is important to know that a node looks like this:
//      
//               Node:   child[0] entry[0] child[1] entry[1] ...
//
//	       0-node: null     null     null     null ...
//	       1-node: impossible !!!
//               2-node: child[0] entry[0] child[1] null ...
//	       3-node: child[0] entry[0] child[1] entry[1] child[2] null ...
//	       ============================================================= */ 
////           System.out.println("i = "+i);
//             if ( curr.e[i] != null && k.compareTo( curr.e[i].key ) < 0 )
//             {
////              System.out.println("Search subtree "+i+" of node " + curr);
//                searchEndPos = curr;
//	        curr = curr.child[i];    
//		break;                 // end to for
//             }
//
//             if ( curr.e[i] != null && k.compareTo( curr.e[i].key ) == 0 )
//             {
//	        return( curr.e[i] );    
//             }
//
//             if ( i == 2 || curr.e[i+1] == null ) // e[i] is last value key
//             {
////              System.out.println("Search subtree "+i+" of node " + curr);
//                searchEndPos = curr;
//	        curr = curr.child[i+1];    
//		break;                 // end to for
//             }
//          }
//
//       }
//
////     System.out.println("exit keySearch(" + k + ")");
//       return(null);   // k not found
//    }
//
//
//    /* ================================================
//       get(k): return value associated with key k
//       ================================================ */
//    Integer get(String k)
//    {
//       Entry e;
//
//       e = keySearch(k);
//
//       if ( e == null )
//	  return(null);
//       else
//	  return(e.value);
//    }
//
//
//
//    /* ================================================
//       put(k): insert (k,v)
//       ================================================ */
//    public Integer put(String k, Integer v)
//    {
//       Node  p;
//       Entry e;
//
//
//       /* ------------------------
//	  Special case: empty tree
//          ------------------------ */
//       if ( root == null )
//       {
//          p = new Node();
//
//	  p.e[0] = new Entry(k, v);
//
// 	  root = p;
//	  p.parent = p;          // Parent(root) == root
//
//          return(null);
//       }
//
//       /* ------------------------
//	  Other cases
//          ------------------------ */
//       e = keySearch(k);
//		// keySearch sets "searchEndPos = last node visited
//
////     System.out.println("Last node visited = "+searchEndPos);
//
//       /* ------------------------
//	  key found, update value
//          ------------------------ */
//       if ( e != null && k.compareTo(e.key) == 0 )
//       {
//          Integer oldValue;
//
//          System.out.print("*** Update value of " + k 
//				+ " in entry " + e);
//
//	  oldValue = e.value;
//	  e.value = v;
//
//	  return(oldValue);
//       }
//
//       /* ------------------------------------------
//	  key not found:
//
//              insert (k,v) in node "searchEndPos"
//          ------------------------------------------ */
//       e = new Entry(k,v);
//       insertEntryInThisNode(e, null, searchEndPos);
//
//       return(null);
//    }
//
//
//    /* =======================================================
//       insertEntryInThisNode(e, rightSubTree, p):
//
//          1. insert "e" and its "rightSubTree" in node p
//	  2. if over flow, split and insert in parent
//       ======================================================= */
//    public void insertEntryInThisNode(Entry e, Node rightSubTree, Node p)
//    {
//       int i;
//       Entry n;
//       Node q;
//
//       System.out.println("*** Insert [entry " + e + ", R subtree " 
//			+ rightSubTree + "] in node " + p);
//
//       // p is rightSubTree's parent node
//       if ( rightSubTree != null )
//          rightSubTree.parent = p;
//
//       if ( p.e[2] == null )
//       {
//          /* -----------------
//             There is space
//             ----------------- */
//          insertEntryDirectlyInNode(e, rightSubTree, p);
//       }
//       else
//       {
//          /* --------------------------------
//             There is no more space ....
//             -------------------------------- */
//          System.out.print("*** Split insert !");
//
//
//          /* ==================================
//	     Make a virtual node with 4 keys
//	     ================================== */
//          Entry[]  x_e     = new Entry[4];
//          Node[]   x_child = new Node[5];
//
//          x_child[0] = p.child[0];
//
//	  i = 0;
//	  while ( i < 3 && p.e[i].key.compareTo(e.key) < 0 )
//	  {
//	     x_e[i] = p.e[i];
//	     x_child[i+1] = p.child[i+1];
//
//	     i++;
//          }
//
//          /* ----------------------
//	     Insert e at x_e[i]
//	     ---------------------- */
//	  x_e[i] = e;
//	  x_child[i+1] = rightSubTree;
//
//	  while ( i < 3 )
//	  {
//	     x_e[i+1] = p.e[i];
//	     x_child[i+2] = p.child[i+1];
//
//	     i++;
//          }
//
//          System.out.print(" ---- Transitional node = ");
//          for (i=0; i<4; i++)
//          {
//             if ( i != 2 )
//                System.out.print( x_e[i].key + " ");
//             else
//                System.out.print( "(" + x_e[i].key + ") ");
//          }
//          System.out.println();
//
//	  /* ==================
//	     Distribute keys
//	     ================== */
//
//	  p.child[0] = x_child[0];
//	  p.e[0]     = x_e[0];
//	  p.child[1] = x_child[1];
//	  p.e[1]     = x_e[1];
//	  p.child[2] = x_child[2];
//	  p.e[2]     = null;
//	  p.child[3] = null;
//
//	  n = x_e[2];
//
//	  q = new Node();
//
//	  q.child[0] = x_child[3];
//	  q.e[0]     = x_e[3];
//	  q.child[1] = x_child[4];
//
//          if ( q.child[0] != null )		// Very sneaky bug found !!!
//	     q.child[0].parent = q;
//
//          if ( q.child[1] != null )
//	     q.child[1].parent = q;
//
//          if ( p == root )
//	  {
//             /* Split root */
//
//	     Node newRoot = new Node();
//
//             System.out.println(">>>>>>> root node SPLITS !!!....");
//
//	     newRoot.child[0] = p;
//	     newRoot.e[0]     = n;
//	     newRoot.child[1] = q;
//
//	     p.parent = newRoot;
//	     q.parent = newRoot;
//
//	     root = newRoot;
//	  }
//	  else
//	  {
//             System.out.println();
//             System.out.println("**** Node " + p + " overflowed....");
//             System.out.println("**** Recurse to insert " + n);
//             System.out.println();
//
//	     insertEntryInThisNode(n, q, p.parent);
//	  }
//       }
//    }
//
//
//    /* ==============================================================
//       insertEntryDirectlyInNode(e, rightChild, p):
//
//          Node p:                   e[2] == null for sure
//
//             child[0]  e[0]  child[1]  e[1]  child[2]  e[2]  child[3]
//
//        Let i = the proper entry
//
//        Insert e in the node p (with rightChild, being e's Right child
//        ============================================================== */
//	    
//    public void insertEntryDirectlyInNode(Entry e, Node rightChild, Node p)
//    {
//       int i;
//
//       System.out.println("+++ DIRECT Insert [entry " + e + ", R subtree " 
//			+ rightChild + "] in node " + p);
//
//       /* ---------------------------------------------------------------
//          Node p:
//
//             child[0]  e[0]  child[1]  e[1]  child[2]  e[2]  child[3]
//
//          e[2] == null for sure
//
//          **** We need to find the spot to insert e
//          --------------------------------------------------------------- */
//       
//       if ( p.e[0] == null ||
//            (p.e[0] != null && e.key.compareTo(p.e[0].key) < 0) )
//       {
//	  // e goes in e[0]
//	  for ( i = 2; i > 0; i-- )
//	  { 
//	     p.e[i] = p.e[i-1];
//	     p.child[i+1] = p.child[i];
//          }
//          p.e[0] = e;
//          p.child[1] = rightChild;
//       }
//       else if ( (p.e[1] == null) ||
//	         (p.e[1] != null && e.key.compareTo(p.e[1].key) < 0) )
//       {
//          // e goes in e[1]
//          for ( i = 2; i > 1; i-- )
//          {
//             p.e[i] = p.e[i-1];
//             p.child[i+1] = p.child[i];
//          }
//          p.e[1] = e;
//          p.child[2] = rightChild;
//       }
//       else 
//       {
//          // e goes in e[2]
//          p.e[2] = e;
//          p.child[3] = rightChild;
//       }
//
//       /* ========================================
//	  Fix parent link
//	  ======================================== */
//       if ( rightChild != null )
//       {
//          rightChild.parent = p;
//       }
//
//       System.out.println("Result: " + p);
//    }
//
//
//
//
//    /* =================================================
//       printTree()
//       ================================================= */
//
//    int MaxLevel;
//
//    void padding ( String s, int n )
//    {
//       int i;
// 
//       for ( i = 0; i < n; i++ )
//          System.out.print( s );
//    }
//
//
//    void printSub (Node p, int id, int level ) 
//    {
//       if ( level > MaxLevel )
//	  MaxLevel = level;
//
//       int i;
//
//       if ( p == null)
//          return;
//
// 
//       if ( p.child[3] != null )
//       {
//          printSub ( p.child[3], 3, level + 1 );
//       }
//
//       if ( p.child[2] != null )
//       {
//          printSub ( p.child[2], 2, level + 1 );
//       }
//
//       if ( p.child[2] != null )
//       {
//          padding( "             ", level );
//          System.out.println("" + id + ":" + p);
//
//          if ( id == 0 && level == MaxLevel )
//             System.out.println();
// 
//          if ( p.child[1] != null )
//          {
//             printSub ( p.child[1], 1, level + 1 );
//          }
//       }
//       else
//       {
//          if ( p.child[1] != null )
//          {
//             printSub ( p.child[1], 1, level + 1 );
//          }
//
//          padding( "             ", level );
//          System.out.println("" + id + ":" + p);
//
//          if ( id == 0 && level == MaxLevel )
//             System.out.println();
//       }
//
//       if ( p.child[0] != null )
//       {
//          printSub ( p.child[0], 0, level + 1 );
////        System.out.println();
//       }
//    }
//
//    public void printTree()
//    {
//       MaxLevel = 0;
//       System.out.println();
//       printSub( root, 0, 0);
//    }
// 
//
//    public boolean error;
//
//    public void checkSub (Node p) 
//    {
//       int i;
//
//       if ( p == null)
//          return;
//
//       // Check parent child relationship for node
//       for ( i = 0; i < 4; i++ )
//	  if ( p.child[i] != null )
//	  {
//	     if ( p.child[i].parent != p )
//	     {
//	        printTree();
//	        System.out.println("---------------------------");
//	        System.out.println("Error:");
//	        System.out.println("p: " + p);
//	        System.out.println("p.child[" + i + "] = " + p.child[i]);
//	        System.out.println("p.child[" + i + "].parent = " 
//			+ p.child[i].parent);
//             }
//          }
//
//       // Recurse
//       for ( i = 0; i < 4; i++ )
//	  if ( p.child[i] != null )
//	     checkSub(  p.child[i] );
//    }
//
//    public void checkTree()
//    {
//       error = false;
//       checkSub( root );
//       if ( error )
//	  System.exit(1);
//       System.out.println();
//    }
// 
//
//}
// 