/*
--Insert
    void insert(Ball x) {
        Node q = new Node(x);
        if (root == null) {
            root = q;
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info.type == x.type) {
                return;
            }
            if (x.type < p.info.type) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (x.type < f.info.type) {
            f.left = q;
        } else {
            f.right = q;
        }
    }

    void insert(String xMaker, int xType, int xRadius) {
        if (xMaker.charAt(0) == 'B') {
            return;
        }
        Ball x = new Ball(xMaker, xType, xRadius);
        insert(x);
    }

//-----------------------------------

    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder2(p.left, f);
        postOrder2(p.right, f);
        //edit here
        if (p.info.radius < 5) {
            fvisit(p, f);
        }
//-------------------------------
    void inOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
       
        inOrder2(p.left, f);
        if(p.info.price < 7 )
        fvisit(p, f);
        inOrder2(p.right, f);
    }
--------------------
//xoa thang dau tien co 2 con , duyet theo in-order

    int count = 0;

    void inOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder2(p.left, f);
        if (p.left != null && p.right != null && count == 0) {
            count++;
            deleByCopy(p.info.type);
        }
        inOrder2(p.right, f);
    }

    void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.type == xPrice) { // sua theo de bai yeu cau
                break;//Found key x
            }
            if (xPrice < p.info.type) { // sua theo de bai yeu cau
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }

    }
    

//------------------
//Duyet in-order , p co 2 con , xoay trai p

   int count1 = 0;
    void inOrder3(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder3(p.left, f);
       if (p.left != null && p.right != null && count1 == 0) {
            count1++;
           rotateLeft(p);
         
            
       }
        inOrder3(p.right, f);
    }
    
     Node parent(Node ch) {
        if (ch == root || ch == null) {
            return null;
        }
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.type == ch.info.type) {
                break;
            }
            parent = p;
            if (p.info.type > ch.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return parent;
    }
     void rotateLeft(Node par) {
        if (par == null || par.right == null) {
            return;
        }
        Node ch = par.right;
        par.right = ch.left;
        ch.left = par;
        if (parent(par) == null) {
            root = ch;
            return;
        }
        if (parent(par).left == par) {
            parent(par).left = ch;
        } else {
            parent(par).right = ch;
        }
    }

//-------------------------
//xoa thang lon thu (count)

    int MaxAgeN(int n) {
        Node p = root;
        int max = -1;
        Queue q = new Queue();
        q.enqueue(root);

        if (n == 1) {
            while (!q.isEmpty()) {
                p = (Node) q.dequeue();
                if (p.info.color > max) {
                    max = p.info.color;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            }
        } else {
            p = root;
            int maxN = MaxAgeN(n - 1);
            max = 0;
            while (!q.isEmpty()) {
                p = (Node) q.dequeue();
                if (p.info.color > max && p.info.color < maxN) {
                    max = p.info.color;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            }
        }
        return max;
    }
    
    void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.color == xPrice) {
                break;//Found key x
            }
            if (xPrice < p.info.color) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
        
        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }
        
        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        
        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }
        
    }
    

//----------------------------
//xoay trai thang parent cua thang lon nhat
    void max2() {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        rotateL(parent(p));
    }

    public Node rotateL(Node par) {
        if (par == null) {
            return null;
        }
        if (par.left == null) {
            return null;
        }
        Node p = root;
        Node gr = null;
        while (p != null) {
            if (p == par) {
                break;
            }
            gr = p;
            if (p.info.color > par.info.color) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        Node ch = par.right;
        par.right = ch.left;
        ch.left = par;
        if (gr == null) {
            root = ch;
        } else if (gr.left == p) {
            gr.left = ch;
        } else if (gr.right == p) {
            gr.right = ch;
        }
        return ch;
    }

    Node parent(Node x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.color == x.info.color) {
                break;
            }
            parent = p;
            if (p.info.color > x.info.color) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return parent;
    }  

//--------------------
//xoay phai

    public void rotateRight(Node par) {
        Node p = root;
        Node gr = null;
        while (p != null) {
            if (p == par) {
                break;
            }
            gr = p;
            if (p.info.getName().compareToIgnoreCase(par.info.getName()) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (par.left == null) {
            return;
        }
        Node ch = par.left;
        par.left = ch.right;
        ch.right = par;
        if (gr == null) {
            root = ch;
        } else if (gr.left == p) {
            gr.left = ch;
        } else if (gr.right == p) {
            gr.right = ch;
        }
    }

//----------------
//xoa node thu may

    int count2 = 0;

    void preOrder4(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        count2++;
        if (count2 == 4) {
            deleByCopy(p.info.sound);
            return;
        }

        preOrder4(p.left, f);
        preOrder4(p.right, f);
    }
---------------------------
//xoay trai void
 public void rotateL(Node par) {
        
        Node p = root;
        Node gr = null;
        while (p != null) {
            if (p == par) {
                break;
            }
            gr = p;
            if (p.info.sound > par.info.sound) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        Node ch = par.right;
        par.right = ch.left;
        ch.left = par;
        if (gr == null) {
            root = ch;
        } else if (gr.left == p) {
            gr.left = ch;
        } else if (gr.right == p) {
            gr.right = ch;
        }
        
    }
//Tim thang node thu 2 theo breadth va lay no lam goc 
, xoa thang lon nhat trong cay subtree do

 void breadth2(Node p, RandomAccessFile f) throws Exception {
        int count = 0;
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null) {
                count++;
                if (count == 2) {
                    if (r.left == null && r.right == null) {
                        dele(r.info.depth);
                    } else {
                        dele(MaxN(r, 1)); //r la root cua sub-tree , n la lon thu bao nhieu
                    }
                }
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    int MaxN(Node p, int n) {
        int max = -1;
        Queue q = new Queue();
        q.enqueue(p);
        if (n == 1) {
            while (!q.isEmpty()) {
                p = (Node) q.dequeue();
                if (p.info.depth > max) {
                    max = p.info.depth;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            }
        } else {
            int maxN = MaxN(p, n - 1);
            max = 0;
            while (!q.isEmpty()) {
                p = (Node) q.dequeue();
                if (p.info.depth > max && p.info.depth < maxN) {
                    max = p.info.depth;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            }
        }
        return max;
    }

    void dele(int xDepth) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.depth == xDepth) {
                break;
            }
            parent = p;
            if (p.info.depth > xDepth) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        if (p.left == null && p.right == null) {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        if ((p.left != null && p.right == null) || (p.left == null && p.right != null)) {
            if (p == root) {
                if (p.left != null) {
                    root = p.left;
                } else {
                    root = p.right;
                }
                return;
            }
            if (parent.left == p) {
                if (p.left != null) {
                    parent.left = p.left;
                } else {
                    parent.left = p.right;
                }
            } else {
                if (p.left != null) {
                    parent.right = p.left;
                } else {
                    parent.right = p.right;
                }
            }
        }
        if (p.left != null && p.right != null) {
            Node rm = p.left;
            Node parentRM = null;
            while (rm.right != null) {
                parentRM = rm;
                rm = rm.right;
            }
            p.info = rm.info;
            if (parentRM == null) {
                p.left = rm.left;
            } else {
                parentRM.right = rm.left;
            }
        }
    }

-------------------------------
//tim thang  thu 2 con co trai duyet theo breath
    void breadth3(Node p, RandomAccessFile f) throws Exception {
        int count3 = 0;
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if(r.left != null ){
                
                count3++;
                if(count3 == 2){
                    rotateRight(r);//
                }
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

----------------------
    public int getHeight(Node p) {
        if (p == null) {
            return 0;
        }
        return Math.max(getHeight(p.left), getHeight(p.right)) + 1;
    }
 
 -------------------
//xoa thang cha cua thang node thu 4 duyet theo post-order
	int count2 = 0;
// Node parent la phuong thuc lay ra thang cha 
    Node parent(Node x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.wing == x.info.wing) {
                break;
            }
            parent = p;
            if (p.info.wing > x.info.wing) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return parent;
    }  
    
    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if(p==null) return;
        postOrder2(p.left,f);
        postOrder2(p.right,f);
        count2++;
        if(count2 == 4){
            dele(parent(p).info.wing);
        }
    }

    void dele(int xDepth) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.wing == xDepth) {
                break;
            }
            parent = p;
            if (p.info.wing > xDepth) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        if (p.left == null && p.right == null) {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        if ((p.left != null && p.right == null) || (p.left == null && p.right != null)) {
            if (p == root) {
                if (p.left != null) {
                    root = p.left;
                } else {
                    root = p.right;
                }
                return;
            }
            if (parent.left == p) {
                if (p.left != null) {
                    parent.left = p.left;
                } else {
                    parent.left = p.right;
                }
            } else {
                if (p.left != null) {
                    parent.right = p.left;
                } else {
                    parent.right = p.right;
                }
            }
        }
        if (p.left != null && p.right != null) {
            Node rm = p.left;
            Node parentRM = null;
            while (rm.right != null) {
                parentRM = rm;
                rm = rm.right;
            }
            p.info = rm.info;
            if (parentRM == null) {
                p.left = rm.left;
            } else {
                parentRM.right = rm.left;
            }
        }
    }
//---------------------------------------------------------------------------//
// f4() xoay phai 
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.
        ArrayList<Node> a = new ArrayList<>();
        breadth(root, a);
        Node p = a.get(2); // lay ra node thu may trong cay
        rotateRightModifier(p); // thuc hien xoay
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node rotateLeft(Node a) {
        if (a == null || a.right = null) {
            return a;
        }
        Node b = a.right;

        a.right = b.left;
        b.left = a;
        return b;
    }

    void rotateLeftModifier(Node a) {
        Node p = search(a);

        if (p == root) {
            root = rotateLeft(root);
        } else {

            Node parent searchParent(p);
            Node b = rotateLett(p);
            if (p != null) {
                if (parent.left == p) {
                    parent.left = b;
                } else {
                    parent.right = b;
                }
            }
        }
    }
    
    Node rotateRight(Node a) {
        if (a == null || a.left == null)
            return a;
        Node b = a.left;
        a.left = b.right;
        b.right = a;
        return b;
    }
    
    void rotateRightModifier(Node a) {
        Node p = search(a);
        if (p == root) {
            root = rotateRight(root);
        } else {
            Node parent = searchParent(p);
            Node b = rotateRight(p);
            if (p != null) {
                if (parent.left == p)
                    parent.left = b;
                else
                    parent.right = b;
            }
        }
    }
    
    Node search(Node x) {
        Node p = root;
        while (p != null && p.info != x.info) {
            if (p.info.hoof > x.info.hoof)
                p = p.left;
            else
                p = p.right;
        }
        return p;
    }

    Node searchParent(Node a) {
        if (a == null) {
            return null;
        }
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info.price > a.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }
    
    void breadth(Node p, ArrayList<Node> a) {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null) a.add(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }
//-------------------------------------------------------------
 f4() xoay trai rotate left 2 cach
------------------------------------------------------
cach 1
    int countf4 = 0;

    void inOrderf4(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrderf4(p.left, f);
        if (p.left != null && p.right != null && countf4 == 0) {
            countf4++;
            rotateLeft(p);
        }
        inOrderf4(p.right, f);
    }

    Node parent(Node ch) {
        if (ch == root || ch == null) {
            return null;
        }
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.type == ch.info.type) {
                break;
            }
            parent = p;
            if (p.info.type > ch.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return parent;
    }

    void rotateLeft(Node par) {
        if (par == null || par.right == null) {
            return;
        }
        Node ch = par.right;
        par.right = ch.left;
        ch.left = par;
        if (parent(par) == null) {
            root = ch;
            return;
        }
        if (parent(par).left == par) {
            parent(par).left = ch;
        } else {
            parent(par).right = ch;
        }
    }

//--------------------------------------------------------------
cach 2
    void inOrderf4(Node p, RandomAccessFile f, ArrayList<Node> a) throws Exception {
        if (p == null) {
            return;
        }
        inOrderf4(p.left, f, a);
        if (p.left != null && p.right != null) {
            a.add(p);
        }
        inOrderf4(p.right, f, a);
    }

    Node rotateLeft(Node a) {
        if (a == null || a.right == null) {
            return a;
        }
        Node b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
    }

    void rotateLeftModifier(Node a) {
        Node p = search(a);

        if (p == root) {
            root = rotateLeft(root);
        } else {

            Node parent = searchParent(p);
            Node b = rotateLeft(p);
            if (p != null) {
                if (parent.left == p) {
                    parent.left = b;
                } else {
                    parent.right = b;
                }
            }
        }
    }

    Node search(Node x) {
        Node p = root;
        while (p != null && p.info != x.info) {
            if (p.info.type > x.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return p;
    }

    Node searchParent(Node a) {
        if (a == null) {
            return null;
        }
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info.type > a.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }

    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.
        
        ArrayList<Node> a = new ArrayList<>();
        inOrderf4(root, f, a);
        Node p = a.get(0); // lay ra node thu may trong cay
        rotateLeftModifier(p); // thuc hien xoay
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
    
//-------------------------------------------------------------
f2()
dispaly nodes with wing > 4
    void breadth2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            // viet dieu kien truoc fvisit
            if(r.info.wing > 4){ //edit here
                fvisit(r, f);    //edit here
            }                    //edit here
            
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }
//-------------------------------------------------------
void f3{) — Suppose p is the 4-th node when performing 
the post-order traversal of the tree and f is the father 
of p. Delete the node f by copying. Output in the file 
f3.txt must be the following:
 cac buoc lam:
- de bai yeu cau node thu may
- duyet theo kieu nao
- xem de bai yeu cau xoa node cha hay node con
- deleByCopy hay deleNode

int count = 0;
    
    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder2(p.left, f); // EDIT THEO TEN SUA BEN TREN
        postOrder2(p.right, f); //EDIT THEO TEN SUA BEN TREN
        count++;                            // edit here
        if(count == 4){                     // edit here
            deleByCopy(parent(p).info.rate);// edit here
        }                                   // edit here
//        fvisit(p, f); // xoa thang fvisit di thay the bang 
                            thang deleByCopy tuy de bai
    }

    Node parent(Node x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.rate == x.info.rate) { // edit theo key de bai cho
                break;                          o day key la rate
            }
            parent = p;
            if (p.info.rate > x.info.rate) { // edit theo key de bai cho
                p = p.left;                     o day key la rate
            } else {
                p = p.right;
            }
        }
        return parent;
    }

    void deleByCopy(int xRate) {// edit theo de bai
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.rate == xRate) { //edit here
                break;//Found key x
            }
            if (xRate < p.info.rate) { // edit here
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xRate + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }

    }
//----------------------------------------------------
f4() getHeight
    int countf4 = 0;
    void postOrder3(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder3(p.left, f);
        postOrder3(p.right, f);
        countf4++; //tao bien countf4 dem vi tri
        if(countf4 == 4){ // de bai yeu cau phan tu thu may thi set count
            int k = getHeight(p);
            p.info.wing = k;
        }
        //fvisit(p, f); // xoa thang fvisit di
    }

    public int getHeight(Node p) {
        if (p == null) {
            return 0;
        }
        return Math.max(getHeight(p.left), getHeight(p.right)) + 1;
    }

//-----------------------------------------------------
Find the node p having type = 5, then calculate number of node
in the subtree with root p. Suppose this number is k, then set
p.info.price = k


    int count(Node p) {
        if (p == null) {
            return (0);
        }
        int k, h, r;
        k = count(p.left);
        h = count(p.right);
        r = k + h + 1;
        return (r);
    }

    void f4() throws Exception {
        clear();
        loadData(14);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.
        Node p = root;
        while (p != null) {
            if (p.info.type == 5) {
                break;
            }
            if (p.info.type > 5) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        int k = count(p);
        p.info.price = k;
        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }


 
 
 ||__TRAVERSAL__//breadth-first traversal
 ||     ||______//preorder
 ||     ||______//postorder
 ||     ||______//inorder
 ||     ||______//use BFS change second node have age >=5 to age = 10
 ||     ||______//preorder with condition: 3 <= price <= 5
 ||
 ||___INSERT____//insert by string
 ||       ||____//insert by integer/double
 ||       ||____//insert have age > 4 use BTF
 ||
 ||___SEARCH____//search
 ||       ||____//search by string
 ||       ||____//search by integer/double
 ||
 ||___COUNT_____//count node in tree
 ||      ||____//count node have 1 child
 ||      ||____//count node have exactly 2 child
 ||      ||____//count height of tree
 ||
 ||___DELETE____//delete by copy integer/double
 ||     ||______//delete by copy string
 ||     ||______//delete by node p
 ||
 ||___BALANCE___//balance tree
 ||     ||______//balance simple array list
 ||
 ||___ROTATE____//rotate left
 ||     ||_______//rotate right
 ||     ||_______//rotate any node
 ||     ||_______//rotate right root 
 ||
 ||___GET_______//get node father
 ||     ||____//get node by string
 ||     ||____//get node by integer/double
 ||___OTHER_____//calculate level of node
 ||     ||_____//calculate factor
 ||     ||_____//copy all node to tree by inorder traversal
 ||     ||_____//Calculate balance factor 
 ||     ||_____//Calculate level all node
 ||     ||_____//balance a binary search tree
 //-------------------------------------------------------
//breadth-first traversal
    public void bfs(Node p){ //input root
        if(p == null) return;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();// get node
            if(q.left != null){ // if lever still hava node left
                m.enqueue(q.left);
            }
            if(q.right != null){ //if lever still hava node right
                m.enqueue(q.right);
            }
            visit(q);// traversal them
        }        
    }
//-------------------------------------------------------	
//preorder
    public void preorder(Node p){
        if(p == null){
            return;
        }
        visit(p);
        preorder(p.left);
        preorder(p.right);
    }
//-------------------------------------------------------	
//postorder
	public void postorder(Node p){
        if(p == null){
            return;
        }
        postorder(p.left);
        postorder(p.right);
        visit(p);
    }
//-------------------------------------------------------	
//inorder
    public void inorder(Node p){
        if(p == null){
            return;
        }
        inorder(p.left);
        visit(p);
        inorder(p.right);
    }
//------------------------------------------------------- 
//use BFS change second node have age >=5 to age = 10
    public void changeNodeCondition(Node p){
        if(p == null) return;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();
            if(q.left != null){
                m.enqueue(q.left);
            }
            if(q.right != null){
                m.enqueue(q.right);
            }
            if(q.info.age >= 5){ //maybe or not
                c++;
                if(c == 2){
                    q.info.age = 10;
                    break; 
                }
            }
        }        
    }
//------------------------------------------------------- 
//preorder with condition: 3 <= price <= 5
   void preOrder2(Node p, RandomAccessFile f) throws Exception{
	 if(p==null) return;
     if(p.info.price>=3&&p.info.price<=5){
        fvisit(p,f);
     }
     preOrder2(p.left,f);
     preOrder2(p.right,f);
   }
//-------------------------------------------------------	 
//insert by string
    public void insert(Person x){
        Node p = new Node(x);
        if(isEmpty()){root = p; return;}
        Node f = null;
        Node q = root;
        while(q != null){
            if(q.info.name.equals(x.name)){
                System.out.println("Insertion failed, duplicated key");
                return;
            }
            else if(q.info.name.compareToIgnoreCase(x.name) > 0){f = q; q = q.left;}
            else{f = q; q = q.right;}
        }
        if(f.info.name.compareToIgnoreCase(x.name) > 0) f.left = p;
        else f.right = p;
    }
//-------------------------------------------------------    
//insert by integer/double
    public void insert(Car x){
	 Node q=new Node(x);
     if(isEmpty())
      {root=q;
        return;
       }
     Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.price == x.price)
          {System.out.println("The key " + x.price + " already exists, no insertion");
            return;
          }
         f=p;
         if(x.price < p.info.price) p=p.left; else p=p.right;
       } 
      if(x.price< f.info.price) f.left=q; else f.right=q;
    } 
//------------------------------------------------------- 
//insert have age > 4 use BTF
    public void breadthModifier(){
	if(root == null){ return; //change
            MyQueue m = new MyQueue();
            m.enqueue(root);
            while(!m.isEmpty()){
                Node q = (Node)m.dequeue();         
                if(q.left != null){
                    m.enqueue(q.left);
                }
                if(q.right != null){
                    m.enqueue(q.right);
                }
                if(q.info.age > 4){
                    h.insert(q.info);
                }
            }
		}
	}
//------------------------------------------------------- 	 
 //search
    public Node search(Person x){
        return search(root, x);
    }
//-------------------------------------------------------
//search by string
    public Node search(Node p, Person x){
        if(p == null){
            return null;
        }
        if(p.info.name.equals.x.name){
            return p;
        }
        else if(p.info.name.compareToIgnoreCase(x.name) > 0){
            return search(p.left,x);
        }
        else{
            return search(p.right,x);
        }
    }
//------------------------------------------------------- 
//search by integer/double
    public Node search(Node p, int key){
		if(p == null) return null;
		 if(p.info == key) return p;
		 else if(p.info > key) return search(p.left, key);
		 else return search(p.right, key);
		}
//------------------------------------------------------- 
//count node in tree
    public int count(Node p){
	if(p==null) return(0);
	int k,h,r;
	k = count(p.left);
	h = count(p.right);
	r = k+h+1;
	return(r);
    }
//-------------------------------------------------------	
//count node have 1 child
    int countModifer(Node p) {
        int n = 0;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()) {
            Node q = (Node)m.dequeue();
            if(q.left != null) m.enqueue(q.left);
            if(q.right != null) m.enqueue(q.right);
            // If q has only one child, increment c by 1
            if(q.left == null && q.right != null) n++;
            if(q.right == null && q.left != null) n++;
        }
        return n;
    }
//------------------------------------------------------- 
//count node have exactly 2 child
    int countNodeCo2con(Node p) {
        int n = 0;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()) {
            Node q = (Node)m.dequeue();
            if(q.left != null) m.enqueue(q.left);
            if(q.right != null) m.enqueue(q.right);
            if(q.left != null && q.right != null) n ++;
        }
        return n;
    }
//-------------------------------------------------------  
//count height of tree
    public int height(Node p) {
        if (p == null) {
            return 0;
        }
        int l = height(p.left) + 1;
        int r = height(p.right) + 1;
        return (l > r) ? l : r;
    }
//------------------------------------------------------- 
//delete by copy integer/double
    public void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.price == xPrice) {
                break;//Found key x
            }
            if (xPrice < p.info.price) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }
    }
//-------------------------------------------------------	
//delete by copy string
    void deleByCopy(String xName){
	 Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.name.equals(xName)) break;
         f=p;
         if(xName.compareTo(p.info.name)<0) p=p.left; else p=p.right;
       }
      if(p==null) return; // not found
      
      // p is leaf node
      if(p.left==null && p.right==null)
        {if(f==null) // p=root
           {root=null;
           }
           else
            {if(p==f.left) f.left=null; f.right=null;
             }
          return;
        } 

      // p has left child only
      if(p.left!=null && p.right==null)
        {if(f==null) // p=root
           {root=p.left;
           }
           else
            {if(p==f.left) f.left=p.left; f.right=p.left;
             }
          return;
        } 

      // p has right child only
      if(p.left==null && p.right!=null)
        {if(f==null) // p=root
           {root=p.right;
           }
           else
            {if(p==f.left) f.left=p.right; f.right=p.right;
             }
          return;
        } 

      // p has both 2 children
      if(p.left!=null && p.right!=null)
        {// find the right most node
          Node q=p.left;
          Node frp, rp; frp=null;rp=q;
          while(rp.right!=null) {frp=rp;rp=rp.right;}
          // rp is the right most node on the left child
          p.info=rp.info;
          if(frp==null) // rp=q
           {p.left=q.left;
           }
           else
           {
            frp.right=rp.left; 
           }
        }
    }
//------------------------------------------------------- 
//delete by node p
    public void deleteByCopy(Node p) {
        if (isEmpty()) {
            return;
        }
        if(p == null){
            System.out.println("Key does not exists, deletion failed");
            return;
        }
        // Find Node f where f is father of p
        Node f = null;
        Node q = root;
        while(q != p){
            if(q.info.name.compareTo(p.info.name) > 0){         // Changed
                f = q;
                q = q.left;
            }
            else{
                f = q;
                q = q.right;
            }
        }
        // 1. p is a leaf (no right and left child)
        if (p.left == null && p.right == null) {
            // a BST has a Node only
            if (f == null) {
                root = null;
            } 
            else if (f.left == p) {
                f.left = null;
            }
            else if(f.right == p){
                f.right = null;
            }
        }
        // 2. p has a left child only
        else if(p.left != null && p.right == null){
            if(f == null){// remove root
                root = p.left;
            }
            else if(f.right == p){
                f.right = p.left;
            }
            else if(f.left == p){
                f.left = p.left;
            }
        }
        // 3. p has a right child only
        else if(p.right != null && p.left == null){
            if(f == null){// remove root
                root = p.right;
            }
            else if(f.right == p){
                f.right = p.right;
            }
            else if(f.left == p){
                f.left = p.right;
            }
        }
        // 4. Both of right and left child 
        else if(p.left != null && p.right != null){
            f = null;
            Node rp = p.left;
            while(rp.right != null){
                f = rp;
                rp = rp.right;
            }
            p.info = rp.info;
            if(f == null){// rp has no right child 
                p.left = rp.left;
            }
            else{
                f.right = rp.left;
            }
        }
    }
//------------------------------------------------------- 
//balance tree
    public void balance(ArrayList a, int first, int last){
        if(first > last) return;
        int m = (first + last) /2;
        Person x = ((Node)a.get(m)).info;
        insert(x);
        balance(a, first, m-1);
        balance(a, m+1, last);
    }
//-------------------------------------------------------	
//balance simple array list
    public void balance(Node p){
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int first = 0;
        int last = a.size() - 1;
        BSTree b = new BSTree(); //create new tree 
        b.balance(a, first, last);
        root = b.root; //referen root to root b
    } 
//------------------------------------------------------- 
//rotate left
    public Node rotateLeft(Node p){//must be have node right
        if(p.right == null){
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }
//-------------------------------------------------------
//rotate right
    public Node rotateRight(Node p){
        if(p.left == null){
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }
//-------------------------------------------------------
//rotate any node
       public void rotateModifier(Node node){
        Node nodeRotate = rotateToRight(node);
		Node nodeFather = father(node.info.price);
		if(nodeFather==null) root = nodeRotate;
        else{
			if(nodeFather.left==node) nodeFather.left = nodeRotate; 
			else  nodeFather.right = node;
         }
	}
//-------------------------------------------------------
//rotate right root 
    Node rotateToRight(Node p){//root = rotateToRight(root)
    if(p==null || p.left==null) return(p);
        Node q=p.left;
        p.left=q.right;
        q.right=p;
        return(q);
    }
//-------------------------------------------------------	
//get node father
    Node father(int xPrice){
	 Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.price == xPrice) break;
         f=p;
         if(xPrice < p.info.price)  p=p.left; else p=p.right;
       }
     return(f);
    }
//-------------------------------------------------------		
//get node by string
    public Node getNode(String xName) {
        Node p = root;
        while (p != null) {
            if (p.info.name.compareToIgnoreCase(xName) > 0) p = p.left;
            else if (p.info.name.compareToIgnoreCase(xName) < 0) p = p.right;
            return p;
        }
        return null;
    }
//-------------------------------------------------------	
//get node by integer/double
    public Node getNode(int x) {
        Node p = root;
        while (p != null) {
            if (p.info > x) p = p.left;
            else if (p.info < x) p = p.right;
            return p;
        }
        return null;
    }
//------------------------------------------------------- 
//calculate level of node
    public void calLevel(Node p){
        if(p == null){
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        p.level = 1;//first, leve = 1
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();         
            if(q.left != null){
                q.left.level = q.level +1;
                m.enqueue(q.left);
            }
            if(q.right != null){
                q.right.level = q.level +1;
                m.enqueue(q.right);
            }
            
        }        
    }
//-------------------------------------------------------
//calculate factor
    public void calculateBalance(Node p){
        if(p == null){
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();         
            if(q.left != null){
                m.enqueue(q.left);
            }
            if(q.right != null){
                m.enqueue(q.right);
            }
            q.bal = height(q.right) - height(q.left); 
            if(isAVL && q.bal < -1 || q.bal > 1){// De cho  thuc hien nhieu
                isAVL = false;
            }
        }        
    }
//-------------------------------------------------------
//copy all node to tree by inorder traversal
    public void buildArray(ArrayList a, Node p){
        if(p == null){
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

//-------------------------------------------------------	
//Calculate balance factor 
    void calculateFactorBalance(RandomAccessFile f123) throws Exception {
        boolean isAVL = true;
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            r.bal = height(r.right) - height(r.left);//int bal in class Node; // balance factor of the node p = height(p.right) - height(p.left)
            if (r.bal >= 2 || r.bal <= -2) {
                isAVL = false;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        breadthBal(root, f123);
        if (!isAVL) {
            f123.writeBytes("\r\nThe tree is not an AVL tree\r\n");
        } else {
            f123.writeBytes("\r\nThe tree is an AVL tree\r\n");
        }
    }

    public void breadthBal(Node p, RandomAccessFile f) throws Exception {//use for balance factor
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisitBal(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void fvisitBal(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.bal + ") ");
        }
    }

    //Calculate level all node
    void calculateLevelAllNode() {
        MyQueue q = new MyQueue();
        if (isEmpty()) {
            return;
        }
        root.level = 1;
        q.enqueue(root);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null) {
                r.left.level = r.level + 1;//level in class node
            }
            if (r.right != null) {
                r.right.level = r.level + 1;
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void fvisitLevel(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.level + ") ");
        }
    }

//balance a binary search tree 
    void balance() {
        ArrayList<Person> t;
        t = new ArrayList<Person>();
        inOrder(t, root);
        int n = t.size();
        clear();
        balance(t, 0, n - 1);
    }

    void inOrder(ArrayList<Person> t, Node p) {
        if (p == null) {
            return;
        }
        inOrder(t, p.left);
        t.add(p.info);
        inOrder(t, p.right);
    }

    void balance(ArrayList<Person> t, int i, int j) {
        if (i > j) {
            return;
        }
        int k = (i + j) / 2;
        insert(t.get(k));//insert person
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }
*/